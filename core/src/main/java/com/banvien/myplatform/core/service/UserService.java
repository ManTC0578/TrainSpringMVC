package com.banvien.myplatform.core.service;

import com.banvien.myplatform.core.bean.UserBean;
import com.banvien.myplatform.core.domain.User;
import com.banvien.myplatform.core.exception.DuplicateException;
import com.banvien.myplatform.core.exception.ObjectNotFoundException;
/**
 * <p>Business Service for Users</p>
 * <p>Generated at Sat Sep 29 11:27:04 ICT 2012</p>
 *
 * @author Portal Generatior v1.1 / Generate a complete Spring/Hibernate and Spring MVC webapp
 */
 public interface UserService extends GenericService<User, Integer>{		
	public Object[] search(UserBean searchBean);	
	
	User findByEmail(String email) throws ObjectNotFoundException;

    void updateItem(UserBean userBean) throws ObjectNotFoundException, DuplicateException;

    void addItem(UserBean userBean) throws DuplicateException;

    Integer deleteItems(String[] checkList) throws ObjectNotFoundException;
}