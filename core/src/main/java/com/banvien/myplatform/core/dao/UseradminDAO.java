package com.banvien.myplatform.core.dao;

import com.banvien.myplatform.core.domain.Useradmin;

/**
 * <p>Generic DAO layer for Useradmins</p>
 * <p>Generated at date Sat Sep 29 11:27:03 ICT 2012</p>
 *
 * @author Portal Generatior v1.1 / Pojos + Hibernate mapping + Generic DAO
 * 
 */
public interface UseradminDAO extends GenericDAO<Useradmin,Integer> {
	Useradmin findByUsername(String username);

}