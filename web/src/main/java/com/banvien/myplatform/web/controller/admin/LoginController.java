package com.banvien.myplatform.web.controller.admin;

/**
 * The LoginController to handle the login process from specified external URL for specified Organization  
 */

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 *
 */
@Controller
public class LoginController extends ApplicationObjectSupport {
	@RequestMapping(value="/login.html")	
    public ModelAndView login(@RequestParam(value="type", required=false)String type, HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("adminLogin");
		if(StringUtils.isNotBlank(type) && type.equals("admin")) {
			mav = new ModelAndView("adminLogin");
		}
        return mav;
    }
}
