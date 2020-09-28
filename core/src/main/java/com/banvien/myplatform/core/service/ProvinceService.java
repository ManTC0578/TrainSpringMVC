package com.banvien.myplatform.core.service;

import com.banvien.myplatform.core.bean.ProvinceBean;
import com.banvien.myplatform.core.domain.Province;
/**
 * <p>Business Service for Provinces</p>
 * <p>Generated at Sat Sep 29 11:27:04 ICT 2012</p>
 *
 * @author Portal Generatior v1.1 / Generate a complete Spring/Hibernate and Spring MVC webapp
 */
 public interface ProvinceService extends GenericService<Province, Integer>{		
	public Object[] search(ProvinceBean searchBean);		
}