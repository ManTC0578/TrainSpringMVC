package com.banvien.myplatform.core.domain;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * <p>Pojo mapping TABLE user</p>
 * <p></p>
 *
 * <p>Generated at date</p>
 * @author Portal Generatior v1.1 / Hibernate pojos and xml mapping files.
 * 
 */
@Table(name = "User")
@Entity
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class User implements Serializable {

	/**
	 * Attribute userID.
	 */
	@Column(name = "userID")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer userID;

	/**
	 * Attribute fullName.
	 */
	private String fullName;
	
	/**
	 * Attribute email.
	 */
	private String email;
	
	/**
	 * Attribute password.
	 */
	private String password;
	
	/**
	 * Attribute isActive.
	 */
	private Byte status;
	
	/**
	 * Attribute createdDate.
	 */
	private Date createdDate;
	
	/**
	 * Attribute modifiedDate.
	 */
	private Date modifiedDate;
	
	private String modifiedBy;
	
	/**
	 * <p> 
	 * </p>
	 * @return userID
	 */
	public Integer getUserID() {
		return userID;
	}

	/**
	 * @param userID new value for userID 
	 */
	public void setUserID(Integer userID) {
		this.userID = userID;
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
	

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public User(){}
		
	public User(Integer userID){this.userID = userID;}

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private List<ProjectMember> members;

	public void setMembers(List<ProjectMember> members) {
		this.members = members;
	}

	public List<ProjectMember> getMembers() {
		return members;
	}
}