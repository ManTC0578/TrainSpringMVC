package com.banvien.myplatform.core.service;



import java.util.List;

import com.banvien.myplatform.core.domain.Country;
import com.banvien.myplatform.core.domain.Province;
import com.banvien.myplatform.core.exception.ObjectNotFoundException;

public interface GlobalMetaDataService {
	public void initializeGlobalMetaData();
	
	public Country lookupCountry(Integer countryID) throws ObjectNotFoundException;
	
	public Province lookupProvince(Integer provinceID) throws ObjectNotFoundException;
	
	public List<Country> getCountries();
	
    public List<Province> getProvinces();
    
    public List<String> getRoles();
    
}
