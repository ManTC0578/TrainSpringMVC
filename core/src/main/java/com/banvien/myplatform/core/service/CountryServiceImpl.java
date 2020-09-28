package com.banvien.myplatform.core.service;


import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.banvien.myplatform.core.bean.CountryBean;
import com.banvien.myplatform.core.dao.CountryDAO;
import com.banvien.myplatform.core.dao.GenericDAO;
import com.banvien.myplatform.core.domain.Country;
/**
 * <p>Business Service for Countrys</p>
 * <p>Generated at Sat Sep 29 11:27:04 ICT 2012</p>
 *
 * @author Portal Generatior v1.1 / Generate a complete Spring/Hibernate and Spring MVC webapp
 */
public class CountryServiceImpl extends GenericServiceImpl<Country, Integer> implements CountryService {
	protected final Log logger = LogFactory.getLog(getClass());
	private CountryDAO countryDAO;
	
	public void setCountryDAO (CountryDAO countryDAO) {
		this.countryDAO = countryDAO;
	}
	@Override
    protected GenericDAO<Country, Integer> getGenericDAO() {
        return countryDAO;
    }				

	public Object[] search(CountryBean searchBean) {
		Map<String, Object> propertyNameValues = new HashMap<String, Object>();
		          		if (searchBean.getPojo().getCode()  != null  &&  StringUtils.isNotBlank("" + searchBean.getPojo().getCode() ) ) {
			propertyNameValues.put("code",  searchBean.getPojo().getCode());
		}     	 	
		          		if (searchBean.getPojo().getName()  != null  &&  StringUtils.isNotBlank("" + searchBean.getPojo().getName() ) ) {
			propertyNameValues.put("name",  searchBean.getPojo().getName());
		}     	 	
		          		if (searchBean.getPojo().getLanguageKey()  != null  &&  StringUtils.isNotBlank("" + searchBean.getPojo().getLanguageKey() ) ) {
			propertyNameValues.put("languageKey",  searchBean.getPojo().getLanguageKey());
		}     	 	
		          		                 
		return this.countryDAO.searchByProperties(propertyNameValues, searchBean.getFirstItem(), searchBean.getMaxPageItems(), searchBean.getSortExpression(), searchBean.getSortDirection(), true, null);
	}	
}