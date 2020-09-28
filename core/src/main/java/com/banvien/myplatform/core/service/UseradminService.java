package com.banvien.myplatform.core.service;

import com.banvien.myplatform.core.bean.UseradminBean;
import com.banvien.myplatform.core.domain.Useradmin;
import com.banvien.myplatform.core.exception.DuplicateException;
import com.banvien.myplatform.core.exception.ObjectNotFoundException;
/**
 * <p>Business Service for Useradmins</p>
 * <p>Generated at Sat Sep 29 11:27:03 ICT 2012</p>
 *
 * @author Portal Generatior v1.1 / Generate a complete Spring/Hibernate and Spring MVC webapp
 */
/**
 * <p>Business Service for Useradmins</p>
 * <p>Generated at Sat Sep 29 11:27:03 ICT 2012</p>
 *
 * @author Portal Generatior v1.1 / Generate a complete Spring/Hibernate and Spring MVC webapp
 */
 public interface UseradminService extends GenericService<Useradmin, Integer>{		
	public Object[] search(UseradminBean searchBean);	
	
	Useradmin findByEmail(String email) throws ObjectNotFoundException;

    Useradmin findByUsername(String userName) throws ObjectNotFoundException;

    void updateItem(UseradminBean useradminBean) throws ObjectNotFoundException, DuplicateException;

    void addItem(UseradminBean useradminBean) throws DuplicateException;

    Integer deleteItems(String[] checkList);
    
    void updateProfile(UseradminBean useradminBean) throws ObjectNotFoundException, DuplicateException;
}