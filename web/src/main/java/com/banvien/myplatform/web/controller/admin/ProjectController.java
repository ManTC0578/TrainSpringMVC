package com.banvien.myplatform.web.controller.admin;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.banvien.myplatform.core.domain.UserProject;
import com.banvien.myplatform.core.domain.Project;
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
import com.banvien.myplatform.core.bean.ProjectBean;
import com.banvien.myplatform.core.exception.DuplicateException;
import com.banvien.myplatform.core.exception.ObjectNotFoundException;
import com.banvien.myplatform.core.service.UserService;
import com.banvien.myplatform.core.service.ProjectService;
import com.banvien.myplatform.web.editor.CustomDateEditor;
import com.banvien.myplatform.web.validator.admin.ProjectValidator;

@Controller
public class ProjectController extends ApplicationObjectSupport {
    private transient final Log log = LogFactory.getLog(getClass());

    @Autowired
    private UserService userService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private ProjectValidator projectValidator;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new CustomDateEditor());
    }

    @RequestMapping(value="/admin/project/edit.html", method=RequestMethod.POST)
    public ModelAndView edit(@ModelAttribute(Constants.FORM_MODEL_KEY) ProjectBean projectBean, BindingResult bindingResult) {
        ModelAndView mav = new ModelAndView("/admin/project/edit");
        Project pojo = projectBean.getPojo();
        String crudaction = projectBean.getCrudaction();
        if(StringUtils.isNotBlank(crudaction) && crudaction.equals(Constants.ACTION_INSERT_UPDATE)) {
            try {
                projectValidator.validate(projectBean, bindingResult);
                if(!bindingResult.hasErrors()) {
                    if(pojo.getProjectID() != null && pojo.getProjectID() > 0) {
                        projectService.updateItem(projectBean);
                        mav.addObject(Constants.MESSAGE_RESPONSE_MODEL_KEY, getMessageSourceAccessor().getMessage("database.update.successful"));
                    } else {
                        projectService.addItem(projectBean);
                        mav.addObject(Constants.MESSAGE_RESPONSE_MODEL_KEY, getMessageSourceAccessor().getMessage("database.add.successful"));
                    }
//                    projectBean.getPojo().setDate();
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

    @RequestMapping(value="/admin/project/edit.html", method=RequestMethod.GET)
    public ModelAndView edit(ProjectBean projectBean) {
        ModelAndView mav = new ModelAndView("/admin/project/edit");
        if(projectBean.getPojo().getProjectID() != null && projectBean.getPojo().getProjectID() > 0) {
            try {
                Project itemObj = projectService.findById(projectBean.getPojo().getProjectID());
                projectBean.setPojo(itemObj);

                if (itemObj.getUserProjects() != null) {
                    Map<Integer, Boolean> developers = new HashMap<>();
                    for (UserProject userProject : itemObj.getUserProjects()) {
                        developers.put(userProject.getUser().getUserID(), Boolean.TRUE);
                    }
                    projectBean.setUserIdInProject(developers);
                }

            }
            catch (Exception e) {
                logger.error("Could not found project admin " + projectBean.getPojo().getProjectID(), e);
            }
        }

        mav.addObject(Constants.FORM_MODEL_KEY, projectBean);
        mav.addObject("userList", userService.findAll());

        referenceData(mav);
        return mav;
    }

    @RequestMapping("/admin/project/list.html")
    public ModelAndView list(ProjectBean bean, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("/admin/project/list");
        if(StringUtils.isNotBlank(bean.getCrudaction()) && bean.getCrudaction().equals(Constants.ACTION_DELETE)) {
            Integer totalDeleted = 0;
            try {
                totalDeleted = projectService.deleteItems(bean.getCheckList());
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

//    @RequestMapping(value="/admin/project/list.html", method = RequestMethod.POST)
//    public ModelAndView list(ProjectBean bean){
//        ModelAndView mav = new ModelAndView("admin/project/list");
//        projectService.listParticipant(bean);
//        return mav;
//    }

    private void executeSearch(ProjectBean bean, HttpServletRequest request) {
        RequestUtil.initSearchBean(request, bean);

        Object[] results = projectService.search(bean);
        bean.setListResult((List<Project>)results[1]);
        bean.setTotalItems(Integer.valueOf(results[0].toString()));
    }

    private void referenceData(ModelAndView mav) {
    }
}
