package com.banvien.myplatform.core.dao;

import java.util.Date;

import com.banvien.myplatform.core.domain.User;

/**
 * <p>Generic DAO layer for Users</p>
 * <p>Generated at date Sat Sep 29 11:27:04 ICT 2012</p>
 *
 * @author Portal Generatior v1.1 / Pojos + Hibernate mapping + Generic DAO
 * 
 */
public interface UserDAO extends GenericDAO<User,Integer> {
	public Object[] search(String email, Byte status, Date createdDateFrom,
						   Date createdDateTo, Integer firstItem, Integer maxItems, String sortExpression, String sortDirection);
}