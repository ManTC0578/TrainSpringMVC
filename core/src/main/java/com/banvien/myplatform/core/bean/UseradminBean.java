package com.banvien.myplatform.core.bean;
import com.banvien.myplatform.core.domain.Useradmin;

public class UseradminBean extends AbstractBean<Useradmin> {
	
	private static final long serialVersionUID = -3877202823616112261L;

	public UseradminBean(){
		this.pojo = new Useradmin();
	}	
	private String newPassword;

	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	
	
	
}