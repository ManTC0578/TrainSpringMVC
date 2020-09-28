package com.banvien.myplatform.core.domain;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;


/**
 * <p>Pojo mapping TABLE city</p>
 * <p></p>
 *
 * <p>Generated at date</p>
 * @author Portal Generatior v1.1 / Hibernate pojos and xml mapping files.
 * 
 */
@Table(name = "City")
@Entity
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class City implements Serializable {

	/**
	 * Attribute cityID.
	 */
	@Column(name = "cityID")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer cityID;
	
	/**
	 * Attribute countryID.
	 */
	private Integer countryID;
	
	/**
	 * Attribute code.
	 */
	private String code;
	
	/**
	 * Attribute name.
	 */
	private String name;
	
	/**
	 * Attribute languageKey.
	 */
	private String languageKey;
	

	
	/**
	 * <p> 
	 * </p>
	 * @return cityID
	 */
	public Integer getCityID() {
		return cityID;
	}

	/**
	 * @param cityID new value for cityID 
	 */
	public void setCityID(Integer cityID) {
		this.cityID = cityID;
	}
	
	
	/**
	 * <p> 
	 * </p>
	 * @return countryID
	 */
	public Integer getCountryID() {
		return countryID;
	}

	/**
	 * @param countryID new value for countryID 
	 */
	public void setCountryID(Integer countryID) {
		this.countryID = countryID;
	}
	
	
	/**
	 * <p> 
	 * </p>
	 * @return code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code new value for code 
	 */
	public void setCode(String code) {
		this.code = code;
	}
	
	/**
	 * <p> 
	 * </p>
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name new value for name 
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * <p> 
	 * </p>
	 * @return languageKey
	 */
	public String getLanguageKey() {
		return languageKey;
	}

	/**
	 * @param languageKey new value for languageKey 
	 */
	public void setLanguageKey(String languageKey) {
		this.languageKey = languageKey;
	}
	

public City(){}	
		
	public City(Integer cityID){this.cityID = cityID;}	
	}