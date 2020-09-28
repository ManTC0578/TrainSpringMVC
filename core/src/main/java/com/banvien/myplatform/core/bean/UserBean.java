package com.banvien.myplatform.core.bean;


import java.util.Date;

import com.banvien.myplatform.core.domain.User;

public class UserBean extends AbstractBean<User> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5172852127241583233L;

	public UserBean(){
		this.pojo = new User();
	}	
	
	private Date createdDateFrom;
	
	private Date createdDateTo;

	public Date getCreatedDateFrom() {
		return createdDateFrom;
	}
	public void setCreatedDateFrom(Date createdDateFrom) {
		this.createdDateFrom = createdDateFrom;
	}
	public Date getCreatedDateTo() {
		return createdDateTo;
	}
	public void setCreatedDateTo(Date createdDateTo) {
		this.createdDateTo = createdDateTo;
	}
	
	
}