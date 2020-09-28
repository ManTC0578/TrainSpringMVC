package com.banvien.myplatform.core.domain;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;


/**
 * <p>Pojo mapping TABLE province</p>
 * <p></p>
 *
 * <p>Generated at date</p>
 * @author Portal Generatior v1.1 / Hibernate pojos and xml mapping files.
 * 
 */
@Table(name = "Province")
@Entity
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Province implements Serializable {

	/**
	 * Attribute provinceID.
	 */
	@Column(name = "provinceID")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer provinceID;
	
	/**
	 * Attribute code.
	 */
	private String code;
	
	/**
	 * Attribute name.
	 */
	private String name;
	
	/**
	 * Attribute countryID.
	 */
	private Integer countryID;
	
	/**
	 * Attribute languageKey.
	 */
	private String languageKey;
	
	private Short displayOrder;
	
	/**
	 * <p> 
	 * </p>
	 * @return provinceID
	 */
	public Integer getProvinceID() {
		return provinceID;
	}

	/**
	 * @param provinceID new value for provinceID 
	 */
	public void setProvinceID(Integer provinceID) {
		this.provinceID = provinceID;
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
	
	

public Short getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(Short displayOrder) {
		this.displayOrder = displayOrder;
	}

public Province(){}	
		
	public Province(Integer provinceID){this.provinceID = provinceID;}	
	}