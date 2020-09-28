package com.banvien.myplatform.web.validator.admin;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.banvien.myplatform.core.bean.UseradminBean;
import com.banvien.myplatform.core.domain.Useradmin;
import com.banvien.myplatform.core.exception.ObjectNotFoundException;
import com.banvien.myplatform.core.service.UseradminService;
import com.banvien.myplatform.web.util.CommonUtil;

/**
 * <p>Validator for Useradmin manipulation</p>
 * <p>Generated at Sat Sep 29 11:27:03 ICT 2012</p>
 *	
 * @author Portal Generatior v1.1 / Generate a complete Spring/Hibernate and Spring MVC webapp
 */
@Component
public class UseradminValidator extends ApplicationObjectSupport implements Validator{	    
	@Autowired
	private UseradminService useradminService;
	
	 public boolean supports(Class<?> aClass) {
        return UseradminBean.class.isAssignableFrom(aClass);
    }
	public void validate(Object o, Errors errors) {
        UseradminBean cmd = (UseradminBean)o; 
        trimingFields(cmd);
        validateRequiredValues(cmd, errors);
        if(validateFormat(cmd, errors)) {
        	validateDuplicate(cmd, errors);
        }
    }
	private void validateRequiredValues(UseradminBean cmd, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pojo.fullName", "errors.required", new String[] {this.getMessageSourceAccessor().getMessage("admin.useradmin.form.fullname")}, "non-empty value required.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pojo.userName", "errors.required", new String[] {this.getMessageSourceAccessor().getMessage("admin.useradmin.form.username")}, "non-empty value required.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pojo.email", "errors.required", new String[] {this.getMessageSourceAccessor().getMessage("admin.useradmin.form.email")}, "non-empty value required.");
        if (cmd.getPojo().getUserAdminID() == null) {
            ValidationUtils.rejectIfEmpty(errors, "pojo.password", "errors.required", new String[] {this.getMessageSourceAccessor().getMessage("admin.useradmin.form.password")}, "non-empty value required.");
        }
    }

    private void trimingFields(UseradminBean cmd) {
    	if(StringUtils.isNotEmpty(cmd.getPojo().getFullName())) {
    		cmd.getPojo().setFullName(cmd.getPojo().getFullName().trim());
    	}
    	if(StringUtils.isNotEmpty(cmd.getPojo().getUserName())) {
    		cmd.getPojo().setUserName(cmd.getPojo().getUserName().trim());
    	}
    	if(StringUtils.isNotEmpty(cmd.getPojo().getEmail())) {
    		cmd.getPojo().setEmail(cmd.getPojo().getEmail().trim());
    	}
    }

    private boolean validateFormat(UseradminBean cmd, Errors errors) {
    	boolean isValid = true;
        if (StringUtils.isNotBlank(cmd.getPojo().getEmail()) && !CommonUtil.isValidEmail(cmd.getPojo().getEmail())) {
            errors.rejectValue("pojo.email", "errors.invalid.format", new String[] {this.getMessageSourceAccessor().getMessage("admin.useradmin.form.email")}, "Invalid format");
            isValid = false;
        }
        if (StringUtils.isNotBlank(cmd.getPojo().getUserName()) && !CommonUtil.isValidUsername(cmd.getPojo().getUserName())) {
            errors.rejectValue("pojo.userName", "errors.invalid.format", new String[] {this.getMessageSourceAccessor().getMessage("admin.useradmin.form.username")}, "Invalid format");
            isValid = false;
        }
        return isValid;
    }

    private void validateDuplicate(UseradminBean cmd, Errors errors) {
    	//Username
		try {
			Useradmin user = this.useradminService.findByUsername(cmd.getPojo().getUserName());
            if(user != null) {
                if(cmd.getPojo().getUserAdminID() != null &&
                        !user.getUserAdminID().equals(cmd.getPojo().getUserAdminID()))
                errors.rejectValue("pojo.userName", "error.duplicated", new String[] {this.getMessageSourceAccessor().getMessage("admin.useradmin.form.username")}, "Value has been chosen.");
            }
		} catch (ObjectNotFoundException ex) {
			// true
		}	
    	//Email
    	if(StringUtils.isNotBlank(cmd.getPojo().getEmail())) {
    		try {
    			Useradmin user = this.useradminService.findByEmail(cmd.getPojo().getEmail());
                if(user != null) {
                    if(cmd.getPojo().getUserAdminID() != null &&
                            !user.getUserAdminID().equals(cmd.getPojo().getUserAdminID()))
                    errors.rejectValue("pojo.email", "error.duplicated", new String[] {this.getMessageSourceAccessor().getMessage("admin.useradmin.form.email")}, "Value has been chosen.");
                }
    		} catch (ObjectNotFoundException ex) {
    			// true
    		}
    	}
    }

}