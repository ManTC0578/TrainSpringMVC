package com.banvien.myplatform.core.service;


import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.banvien.myplatform.core.bean.CityBean;
import com.banvien.myplatform.core.dao.CityDAO;
import com.banvien.myplatform.core.dao.GenericDAO;
import com.banvien.myplatform.core.domain.City;
/**
 * <p>Business Service for Citys</p>
 * <p>Generated at Sat Sep 29 11:27:04 ICT 2012</p>
 *
 * @author Portal Generatior v1.1 / Generate a complete Spring/Hibernate and Spring MVC webapp
 */
public class CityServiceImpl extends GenericServiceImpl<City, Integer> implements CityService {
	protected final Log logger = LogFactory.getLog(getClass());
	private CityDAO cityDAO;
	
	public void setCityDAO (CityDAO cityDAO) {
		this.cityDAO = cityDAO;
	}
	@Override
    protected GenericDAO<City, Integer> getGenericDAO() {
        return cityDAO;
    }				

	public Object[] search(CityBean searchBean) {
		Map<String, Object> propertyNameValues = new HashMap<String, Object>();
		          		if (searchBean.getPojo().getCountryID()  != null  &&  StringUtils.isNotBlank("" + searchBean.getPojo().getCountryID() ) ) {
			propertyNameValues.put("countryID",  searchBean.getPojo().getCountryID());
		}     	 	
		          		if (searchBean.getPojo().getCode()  != null  &&  StringUtils.isNotBlank("" + searchBean.getPojo().getCode() ) ) {
			propertyNameValues.put("code",  searchBean.getPojo().getCode());
		}     	 	
		          		if (searchBean.getPojo().getName()  != null  &&  StringUtils.isNotBlank("" + searchBean.getPojo().getName() ) ) {
			propertyNameValues.put("name",  searchBean.getPojo().getName());
		}     	 	
		          		if (searchBean.getPojo().getLanguageKey()  != null  &&  StringUtils.isNotBlank("" + searchBean.getPojo().getLanguageKey() ) ) {
			propertyNameValues.put("languageKey",  searchBean.getPojo().getLanguageKey());
		}     	 	
		          		                 
		return this.cityDAO.searchByProperties(propertyNameValues, searchBean.getFirstItem(), searchBean.getMaxPageItems(), searchBean.getSortExpression(), searchBean.getSortDirection(), true, null);
	}	
}