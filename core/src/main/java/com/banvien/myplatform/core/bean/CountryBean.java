package com.banvien.myplatform.core.bean;
import com.banvien.myplatform.core.domain.Country;

public class CountryBean extends AbstractBean<Country> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2842044283424094092L;

	public CountryBean(){
		this.pojo = new Country();
	}	
	
}