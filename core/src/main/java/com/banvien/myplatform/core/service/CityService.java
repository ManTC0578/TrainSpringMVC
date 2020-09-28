package com.banvien.myplatform.core.service;

import com.banvien.myplatform.core.bean.CityBean;
import com.banvien.myplatform.core.domain.City;
/**
 * <p>Business Service for Citys</p>
 * <p>Generated at Sat Sep 29 11:27:04 ICT 2012</p>
 *
 * @author Portal Generatior v1.1 / Generate a complete Spring/Hibernate and Spring MVC webapp
 */
 public interface CityService extends GenericService<City, Integer>{		
	public Object[] search(CityBean searchBean);		
}