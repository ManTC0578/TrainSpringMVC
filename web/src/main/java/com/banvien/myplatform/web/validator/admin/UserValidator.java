package com.banvien.myplatform.web.validator.admin;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.banvien.myplatform.core.bean.UserBean;
import com.banvien.myplatform.core.domain.User;
import com.banvien.myplatform.core.exception.ObjectNotFoundException;
import com.banvien.myplatform.core.service.UserService;
import com.banvien.myplatform.web.util.CommonUtil;

/**
 * <p>Validator for User manipulation</p>
 * <p>Generated at Sat Sep 29 11:27:04 ICT 2012</p>
 *	
 * @author Portal Generatior v1.1 / Generate a complete Spring/Hibernate and Spring MVC webapp
 */
@Component
public class UserValidator extends ApplicationObjectSupport implements Validator{	    
	@Autowired
	private UserService userService;
	
	 public boolean supports(Class<?> aClass) {
        return UserBean.class.isAssignableFrom(aClass);
    }
	public void validate(Object o, Errors errors) {
        UserBean cmd = (UserBean)o;        
        trimingFields(cmd);
        validateRequiredValues(cmd, errors);
        if(validateFormat(cmd, errors)) {
        	validateDuplicate(cmd, errors);
        }
    }
	private void validateRequiredValues(UserBean cmd, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pojo.email", "errors.required", new String[] {this.getMessageSourceAccessor().getMessage("admin.useradmin.form.email")}, "non-empty value required.");
        ValidationUtils.rejectIfEmpty(errors, "pojo.status", "errors.required", new String[] {this.getMessageSourceAccessor().getMessage("label.status")}, "non-empty value required.");
        ValidationUtils.rejectIfEmpty(errors, "pojo.fullName", "errors.required", new String[] {this.getMessageSourceAccessor().getMessage("label.fullName")}, "non-empty value required.");
        if (cmd.getPojo().getUserID() == null) {
            ValidationUtils.rejectIfEmpty(errors, "pojo.password", "errors.required", new String[] {this.getMessageSourceAccessor().getMessage("admin.useradmin.form.password")}, "non-empty value required.");
        }
    }

    private void trimingFields(UserBean cmd) {
    	if(StringUtils.isNotEmpty(cmd.getPojo().getEmail())) {
    		cmd.getPojo().setEmail(cmd.getPojo().getEmail().trim());
    	}
    }

    private boolean validateFormat(UserBean cmd, Errors errors) {
    	boolean isValid = true;
        if (StringUtils.isNotBlank(cmd.getPojo().getEmail()) && !CommonUtil.isValidEmail(cmd.getPojo().getEmail())) {
            errors.rejectValue("pojo.email", "errors.invalid.format", new String[] {this.getMessageSourceAccessor().getMessage("admin.useradmin.form.email")}, "Invalid format");
            isValid = false;
        }
        return isValid;
    }

    private void validateDuplicate(UserBean cmd, Errors errors) {
    	//Username
    	if(StringUtils.isNotBlank(cmd.getPojo().getEmail())) {
    		try {
    			User user = this.userService.findByEmail(cmd.getPojo().getEmail());
                if(user != null) {
                    if(cmd.getPojo().getUserID() != null &&
                            !user.getUserID().equals(cmd.getPojo().getUserID()))
                    errors.rejectValue("pojo.email", "error.duplicated", new String[] {this.getMessageSourceAccessor().getMessage("admin.useradmin.form.email")}, "Value has been chosen.");
                }
    		} catch (ObjectNotFoundException ex) {
    			// true
    		}
    	}
    }
}