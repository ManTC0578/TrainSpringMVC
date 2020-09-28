package com.banvien.myplatform.web.controller.admin;


import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.banvien.myplatform.core.service.GlobalMetaDataService;
import com.banvien.myplatform.core.service.UseradminService;
import com.banvien.myplatform.web.editor.CustomDateEditor;
import com.banvien.myplatform.web.util.RequestUtil;
import com.banvien.myplatform.web.validator.admin.UseradminValidator;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.banvien.myplatform.core.Constants;
import com.banvien.myplatform.core.bean.UseradminBean;
import com.banvien.myplatform.core.domain.Useradmin;
import com.banvien.myplatform.core.exception.DuplicateException;
import com.banvien.myplatform.core.exception.ObjectNotFoundException;

@Controller
public class UseradminController extends ApplicationObjectSupport {
    private transient final Log log = LogFactory.getLog(getClass());

    @Autowired
    private UseradminService useradminService;

    @Autowired
    private GlobalMetaDataService globalMetaDataService;
    
    @Autowired
    private UseradminValidator useradminValidator;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
    	binder.registerCustomEditor(Date.class, new CustomDateEditor());
	}
    
    @RequestMapping(value="/admin/useradmin/edit.html", method=RequestMethod.POST)
	public ModelAndView edit(@ModelAttribute(Constants.FORM_MODEL_KEY) UseradminBean useradminBean, BindingResult bindingResult) {
		ModelAndView mav = new ModelAndView("/admin/useradmin/edit");
		Useradmin pojo = useradminBean.getPojo();
		String crudaction = useradminBean.getCrudaction();
		if(StringUtils.isNotBlank(crudaction) && crudaction.equals(Constants.ACTION_INSERT_UPDATE)) {
			try {
				useradminValidator.validate(useradminBean, bindingResult);
				if(!bindingResult.hasErrors()) {
					if(pojo.getUserAdminID() != null && pojo.getUserAdminID() > 0) {
						useradminService.updateItem(useradminBean);
						mav.addObject(Constants.MESSAGE_RESPONSE_MODEL_KEY, getMessageSourceAccessor().getMessage("database.update.successful"));
					} else {
						useradminService.addItem(useradminBean);
						mav.addObject(Constants.MESSAGE_RESPONSE_MODEL_KEY, getMessageSourceAccessor().getMessage("database.add.successful"));
					}
					mav.addObject("success", true);

				}
			}catch (ObjectNotFoundException oe) {
				logger.error(oe.getMessage(), oe);
				mav.addObject(Constants.MESSAGE_RESPONSE_MODEL_KEY, getMessageSourceAccessor().getMessage("database.exception.keynotfound"));
			}catch (DuplicateException de) {
				logger.error(de.getMessage(), de);
				mav.addObject(Constants.MESSAGE_RESPONSE_MODEL_KEY, getMessageSourceAccessor().getMessage("database.exception.duplicate"));
			}catch(Exception e) {
				logger.error(e.getMessage(), e);
				mav.addObject(Constants.MESSAGE_RESPONSE_MODEL_KEY, getMessageSourceAccessor().getMessage("general.exception.msg"));
			}
		}
		referenceData(mav);
		return mav;
	}
    
    @RequestMapping(value="/admin/useradmin/edit.html", method=RequestMethod.GET)
   	public ModelAndView edit(UseradminBean useradminBean) {
    	ModelAndView mav = new ModelAndView("/admin/useradmin/edit");
    	if(useradminBean.getPojo().getUserAdminID() != null && useradminBean.getPojo().getUserAdminID() > 0) {
			try {
				Useradmin itemObj = useradminService.findById(useradminBean.getPojo().getUserAdminID());
                itemObj.setPassword("");
				useradminBean.setPojo(itemObj);
			}
			catch (Exception e) {
				logger.error("Could not found user admin " + useradminBean.getPojo().getUserAdminID(), e);
			}
		}

		mav.addObject(Constants.FORM_MODEL_KEY, useradminBean);
		referenceData(mav);
		return mav;
    }
    @RequestMapping({"/admin/useradmin/list.html"})
    public ModelAndView list(UseradminBean bean, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("/admin/useradmin/list");
		if(StringUtils.isNotBlank(bean.getCrudaction()) && bean.getCrudaction().equals(Constants.ACTION_DELETE)) {
			Integer totalDeleted = 0;
			try {
				totalDeleted = useradminService.deleteItems(bean.getCheckList());
				mav.addObject("totalDeleted", totalDeleted);
			}catch (Exception e) {
				log.error(e.getMessage());
				mav.addObject(Constants.MESSAGE_RESPONSE_MODEL_KEY, getMessageSourceAccessor().getMessage("database.multipledelete.exception"));
			}
		}
        executeSearch(bean, request);
        mav.addObject(Constants.LIST_MODEL_KEY, bean);
        return mav;
    }

    private void executeSearch(UseradminBean bean, HttpServletRequest request) {
        RequestUtil.initSearchBean(request, bean);

        Object[] results = useradminService.search(bean);
        bean.setListResult((List<Useradmin>)results[1]);
        bean.setTotalItems(Integer.valueOf(results[0].toString()));
    }
    private void referenceData(ModelAndView mav) {
    	mav.addObject("roles", globalMetaDataService.getRoles());
    }
}
