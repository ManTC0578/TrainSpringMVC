package com.banvien.myplatform.core.service;

import com.banvien.myplatform.core.bean.CountryBean;
import com.banvien.myplatform.core.domain.Country;
/**
 * <p>Business Service for Countrys</p>
 * <p>Generated at Sat Sep 29 11:27:04 ICT 2012</p>
 *
 * @author Portal Generatior v1.1 / Generate a complete Spring/Hibernate and Spring MVC webapp
 */
 public interface CountryService extends GenericService<Country, Integer>{		
	public Object[] search(CountryBean searchBean);		
}