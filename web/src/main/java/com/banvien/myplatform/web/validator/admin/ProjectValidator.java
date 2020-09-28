package com.banvien.myplatform.web.validator.admin;

import com.banvien.myplatform.core.domain.Project;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


import com.banvien.myplatform.core.bean.ProjectBean;
import com.banvien.myplatform.core.exception.ObjectNotFoundException;
import com.banvien.myplatform.core.service.ProjectService;
import com.banvien.myplatform.web.util.CommonUtil;

/**
 * <p>Validator for Project manipulation</p>
 * <p>Generated at Sat Sep 29 11:27:04 ICT 2012</p>
 *
 * @author Portal Generatior v1.1 / Generate a complete Spring/Hibernate and Spring MVC webapp
 */
@Component
public class ProjectValidator extends ApplicationObjectSupport implements Validator{

    //    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private ProjectService projectService;

    public boolean supports(Class<?> aClass) {
        return ProjectBean.class.isAssignableFrom(aClass);
    }

    public void validate(Object o, Errors errors) {
        ProjectBean cmd = (ProjectBean) o;
        trimingFields(cmd);
        validateRequiredValues(cmd, errors);
        if(validateFormat(cmd, errors)) {
            validateDuplicate(cmd, errors);
        }
    }
    private void validateRequiredValues(ProjectBean cmd, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "pojo.projectName", "errors.required", new String[] {this.getMessageSourceAccessor().getMessage("admin.project.form.name")}, "non-empty value required.");
        ValidationUtils.rejectIfEmpty(errors, "pojo.status", "errors.required", new String[] {this.getMessageSourceAccessor().getMessage("label.status")}, "non-empty value required.");
        ValidationUtils.rejectIfEmpty(errors, "pojo.startDate", "errors.required", new String[] {this.getMessageSourceAccessor().getMessage("label.startdate")}, "non-empty value required.");
        ValidationUtils.rejectIfEmpty(errors, "pojo.endDate", "errors.required", new String[] {this.getMessageSourceAccessor().getMessage("label.enddate")}, "non-empty value required.");
    }

    private void trimingFields(ProjectBean cmd) {
        if(StringUtils.isNotEmpty(cmd.getPojo().getProjectName())) {
            cmd.getPojo().setProjectName(cmd.getPojo().getProjectName().trim());
        }
    }

    private boolean validateFormat(ProjectBean cmd, Errors errors) {
        boolean isValid = true;
        if (StringUtils.isNotBlank(cmd.getPojo().getProjectName()) && !CommonUtil.isValidProjectName(cmd.getPojo().getProjectName())) {
            errors.rejectValue("pojo.projectName", "errors.invalid.format", new String[] {this.getMessageSourceAccessor().getMessage("admin.project.form.name")}, "Invalid format");
            isValid = false;
        }
        return isValid;
    }

    private void validateDuplicate(ProjectBean cmd, Errors errors){
        //ProjectName
        if(StringUtils.isNotBlank(cmd.getPojo().getProjectName())) {
            try {
                Project project = this.projectService.findByName(cmd.getPojo().getProjectName());
                if(project != null) {
                    if(cmd.getPojo().getProjectID() != null &&
                            !project.getProjectID().equals(cmd.getPojo().getProjectID()))
                        errors.rejectValue("pojo.projectName", "error.duplicated", new String[] {this.getMessageSourceAccessor().getMessage("admin.project.form.name")}, "Value has been chosen.");
                }
            } catch (ObjectNotFoundException ex) {
                // true
            }
        }
    }
}