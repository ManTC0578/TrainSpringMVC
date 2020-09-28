package com.banvien.myplatform.core.domain;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


/**
 * <p>Pojo mapping TABLE useradmin</p>
 * <p></p>
 *
 * <p>Generated at date</p>
 * @author Portal Generatior v1.1 / Hibernate pojos and xml mapping files.
 * 
 */
@Table(name = "UserAdmin")
@Entity
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Useradmin implements Serializable {

	/**
	 * Attribute userAdminID.
	 */
	@Column(name = "userAdminID")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer userAdminID;
	
	/**
	 * Attribute userName.
	 */
	private String userName;
	
	/**
	 * Attribute password.
	 */
	private String password;
	
	/**
	 * Attribute fullName.
	 */
	private String fullName;
	
	/**
	 * Attribute email.
	 */
	private String email;
	
	/**
	 * Attribute status.
	 */
	private Integer status;
	
	/**
	 * Attribute role.
	 */
	private String role;
	
	/**
	 * Attribute createdDate.
	 */
	private Date createdDate;
	
	/**
	 * Attribute modifiedDate.
	 */
	private Date modifiedDate;
	

	
	/**
	 * <p> 
	 * </p>
	 * @return userAdminID
	 */
	public Integer getUserAdminID() {
		return userAdminID;
	}

	/**
	 * @param userAdminID new value for userAdminID 
	 */
	public void setUserAdminID(Integer userAdminID) {
		this.userAdminID = userAdminID;
	}
	
	
	/**
	 * <p> 
	 * </p>
	 * @return userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName new value for userName 
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
	/**
	 * <p> 
	 * </p>
	 * @return password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password new value for password 
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	/**
	 * <p> 
	 * </p>
	 * @return fullName
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * @param fullName new value for fullName 
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	
	/**
	 * <p> 
	 * </p>
	 * @return email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email new value for email 
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * <p> 
	 * </p>
	 * @return role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * @param role new value for role 
	 */
	public void setRole(String role) {
		this.role = role;
	}
	
	
	/**
	 * <p> 
	 * </p>
	 * @return createdDate
	 */
	public Date getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate new value for createdDate 
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	
	/**
	 * <p> 
	 * </p>
	 * @return modifiedDate
	 */
	public Date getModifiedDate() {
		return modifiedDate;
	}

	/**
	 * @param modifiedDate new value for modifiedDate 
	 */
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	

public Useradmin(){}	
		
	public Useradmin(Integer userAdminID){this.userAdminID = userAdminID;}	
	}