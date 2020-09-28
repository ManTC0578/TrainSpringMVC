package com.banvien.myplatform.web.security;


import com.banvien.myplatform.core.dao.UseradminDAO;
import com.banvien.myplatform.core.domain.Useradmin;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserCache;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.HashMap;
import java.util.Map;


/**
 *
 * 
 */

public class MyUserDetailsService implements UserDetailsService {
	private Logger log = Logger.getLogger(MyUserDetailsService.class);

	protected UserCache userCache = null;
	
	private UseradminDAO useradminDAO;
	

	/**
	 * @param useradminDAO the useradminDAO to set
	 */
	public void setUseradminDAO(UseradminDAO useradminDAO) {
		this.useradminDAO = useradminDAO;
	}
	/**
	 * Creates new instance of UserManagerDaoImpl
	 */
	public MyUserDetailsService() {

	}

	/**
	 * Set UserCache
	 * 
	 * @param userCache
	 *            user cache to set
	 */
	public void setUserCache(UserCache userCache) {
		this.userCache = userCache;
	}

	/**
	 * Locates the user based on the username. In the actual implementation, the
	 * search may possibly be case insensitive, or case insensitive depending on
	 * how the implementaion instance is configured. In this case, the
	 * <code>UserDetails</code> object that comes back may have a username
	 * that is of a different case than what was actually requested..
	 * 
	 * @param username
	 *            the username presented to the {@link
	 *            org.springframework.security.authentication.dao.DaoAuthenticationProvider}
	 * @return a fully populated user record (never <code>null</code>)
	 * @throws org.springframework.security.core.userdetails.UsernameNotFoundException
	 *             if the user could not be found or the user has no
	 *             GrantedAuthority
	 * @throws org.springframework.dao.DataAccessException
	 *             if user could not be found for a repository-specific reason
	 */
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {	

		Useradmin account = null;

		try {
			account = useradminDAO.findByUsername(username);
			Integer activeStatus = 1;
			if (account == null || !activeStatus.equals(account.getStatus())) {				
				throw new UsernameNotFoundException("UserProcessingFilter.usernameNotFound");
			}
			
		} catch (Exception e) {
			throw new UsernameNotFoundException(e.getMessage());
		}
		
		Map<String, SimpleGrantedAuthority> authorities = new HashMap<String, SimpleGrantedAuthority>();

		//this line of code is used to check whether the user has login or not
		authorities.put(account.getRole(), new SimpleGrantedAuthority(account.getRole()));
		authorities.put("LOGINED", new SimpleGrantedAuthority("LOGINED"));

		MyUserDetail loginUser = new MyUserDetail(username, account.getPassword(), true, true, true, true, authorities.values());
		BeanUtils.copyProperties(account, loginUser);

		return loginUser;
	}
}