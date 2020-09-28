package com.banvien.myplatform.core.service;


import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.banvien.myplatform.core.bean.ProvinceBean;
import com.banvien.myplatform.core.dao.GenericDAO;
import com.banvien.myplatform.core.dao.ProvinceDAO;
import com.banvien.myplatform.core.domain.Province;
/**
 * <p>Business Service for Provinces</p>
 * <p>Generated at Sat Sep 29 11:27:04 ICT 2012</p>
 *
 * @author Portal Generatior v1.1 / Generate a complete Spring/Hibernate and Spring MVC webapp
 */
public class ProvinceServiceImpl extends GenericServiceImpl<Province, Integer> implements ProvinceService {
	protected final Log logger = LogFactory.getLog(getClass());
	private ProvinceDAO provinceDAO;
	
	public void setProvinceDAO (ProvinceDAO provinceDAO) {
		this.provinceDAO = provinceDAO;
	}
	@Override
    protected GenericDAO<Province, Integer> getGenericDAO() {
        return provinceDAO;
    }				

	public Object[] search(ProvinceBean searchBean) {
		Map<String, Object> propertyNameValues = new HashMap<String, Object>();
		          		if (searchBean.getPojo().getCode()  != null  &&  StringUtils.isNotBlank("" + searchBean.getPojo().getCode() ) ) {
			propertyNameValues.put("code",  searchBean.getPojo().getCode());
		}     	 	
		          		if (searchBean.getPojo().getName()  != null  &&  StringUtils.isNotBlank("" + searchBean.getPojo().getName() ) ) {
			propertyNameValues.put("name",  searchBean.getPojo().getName());
		}     	 	
		          		if (searchBean.getPojo().getCountryID()  != null  &&  StringUtils.isNotBlank("" + searchBean.getPojo().getCountryID() ) ) {
			propertyNameValues.put("countryID",  searchBean.getPojo().getCountryID());
		}     	 	
		          		if (searchBean.getPojo().getLanguageKey()  != null  &&  StringUtils.isNotBlank("" + searchBean.getPojo().getLanguageKey() ) ) {
			propertyNameValues.put("languageKey",  searchBean.getPojo().getLanguageKey());
		}     	 	
		          		                 
		return this.provinceDAO.searchByProperties(propertyNameValues, searchBean.getFirstItem(), searchBean.getMaxPageItems(), searchBean.getSortExpression(), searchBean.getSortDirection(), true, null);
	}	
}