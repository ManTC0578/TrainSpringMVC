package com.banvien.myplatform.web.security;


import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.util.StringUtils;

import com.banvien.myplatform.core.Constants;

/**
 *
 *
 */
public class MyAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    protected final Log logger = LogFactory.getLog(this.getClass());
    
    private String adminDefaultTargetURL;
    private String userDefaultTargetURL;
    
    /**
	 * @return the adminDefaultTargetURL
	 */
	public String getAdminDefaultTargetURL() {
		return adminDefaultTargetURL;
	}

	/**
	 * @param adminDefaultTargetURL the adminDefaultTargetURL to set
	 */
	public void setAdminDefaultTargetURL(String adminDefaultTargetURL) {
		this.adminDefaultTargetURL = adminDefaultTargetURL;
	}

	/**
	 * @return the userDefaultTargetURL
	 */
	public String getUserDefaultTargetURL() {
		return userDefaultTargetURL;
	}

	/**
	 * @param userDefaultTargetURL the userDefaultTargetURL to set
	 */
	public void setUserDefaultTargetURL(String userDefaultTargetURL) {
		this.userDefaultTargetURL = userDefaultTargetURL;
	}

	@Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws ServletException, IOException {
        String targetUrl = "";
        if (hasAuthority(authentication, Constants.ROLE_ADMIN)) {
        	targetUrl = adminDefaultTargetURL;
        }else {
        	targetUrl = userDefaultTargetURL;
        }
        if (isAlwaysUseDefaultTargetUrl() || StringUtils.hasText(request.getParameter(getTargetUrlParameter()))) {
        	clearAuthenticationAttributes(request);
            logger.debug("Redirecting to DefaultSavedRequest Url: " + targetUrl);
            getRedirectStrategy().sendRedirect(request, response, targetUrl);
            return;
        }
    }
        


	private boolean hasAuthority(Authentication authentication, String roleCode) {
    	List<GrantedAuthority> authories = (List<GrantedAuthority>)authentication.getAuthorities();
    	for (GrantedAuthority authority : authories) {
    		if (authority.getAuthority().equals(roleCode)) {
    			return true;
    		}
    	}
		return false;
	}
	
}
