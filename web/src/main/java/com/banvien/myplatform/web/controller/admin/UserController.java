package com.banvien.myplatform.web.controller.admin;


import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.banvien.myplatform.web.util.RequestUtil;
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
import com.banvien.myplatform.core.bean.UserBean;
import com.banvien.myplatform.core.domain.User;
import com.banvien.myplatform.core.exception.DuplicateException;
import com.banvien.myplatform.core.exception.ObjectNotFoundException;
import com.banvien.myplatform.core.service.UserService;
import com.banvien.myplatform.web.editor.CustomDateEditor;
import com.banvien.myplatform.web.validator.admin.UserValidator;

@Controller
public class UserController extends ApplicationObjectSupport {
    private transient final Log log = LogFactory.getLog(getClass());

    @Autowired
    private UserService userService;

    @Autowired
    private UserValidator userValidator;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
    	binder.registerCustomEditor(Date.class, new CustomDateEditor());
	}
    
    @RequestMapping(value="/admin/user/edit.html", method=RequestMethod.POST)
	public ModelAndView edit(@ModelAttribute(Constants.FORM_MODEL_KEY) UserBean userBean, BindingResult bindingResult) {
		ModelAndView mav = new ModelAndView("/admin/user/edit");
		User pojo = userBean.getPojo();
		String crudaction = userBean.getCrudaction();
		if(StringUtils.isNotBlank(crudaction) && crudaction.equals(Constants.ACTION_INSERT_UPDATE)) {
			try {
				userValidator.validate(userBean, bindingResult);
				if(!bindingResult.hasErrors()) {
					if(pojo.getUserID() != null && pojo.getUserID() > 0) {
						userService.updateItem(userBean);
						mav.addObject(Constants.MESSAGE_RESPONSE_MODEL_KEY, getMessageSourceAccessor().getMessage("database.update.successful"));
					} else {
						userService.addItem(userBean);
						mav.addObject(Constants.MESSAGE_RESPONSE_MODEL_KEY, getMessageSourceAccessor().getMessage("database.add.successful"));
					}
					userBean.getPojo().setPassword("");
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
    
    @RequestMapping(value="/admin/user/edit.html", method=RequestMethod.GET)
   	public ModelAndView edit(UserBean userBean) {
    	ModelAndView mav = new ModelAndView("/admin/user/edit");
    	if(userBean.getPojo().getUserID() != null && userBean.getPojo().getUserID() > 0) {
			try {
				User itemObj = userService.findById(userBean.getPojo().getUserID());
                itemObj.setPassword("");
				userBean.setPojo(itemObj);
			}
			catch (Exception e) {
				logger.error("Could not found user admin " + userBean.getPojo().getUserID(), e);
			}
		}

		mav.addObject(Constants.FORM_MODEL_KEY, userBean);
		referenceData(mav);
		return mav;
    }
    @RequestMapping({"/admin/user/list.html", "/admin/dashboard.html"})
    public ModelAndView list(UserBean bean, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("/admin/user/list");
		if(StringUtils.isNotBlank(bean.getCrudaction()) && bean.getCrudaction().equals(Constants.ACTION_DELETE)) {
			Integer totalDeleted = 0;
			try {
				totalDeleted = userService.deleteItems(bean.getCheckList());
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

    private void executeSearch(UserBean bean, HttpServletRequest request) {
        RequestUtil.initSearchBean(request, bean);

        Object[] results = userService.search(bean);
        bean.setListResult((List<User>)results[1]);
        bean.setTotalItems(Integer.valueOf(results[0].toString()));
    }
    private void referenceData(ModelAndView mav) {
    }
}
