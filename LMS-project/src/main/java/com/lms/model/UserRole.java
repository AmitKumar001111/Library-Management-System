package com.lms.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "User-Role")
public class UserRole {


	@Id
	private String userRoleId;
	private String userId;
	private String roleId;
	
	
	public String getUserRoleId() {
		return userRoleId;
	}
	public void setUserRoleId(String userRoleId) {
		this.userRoleId = userRoleId;
	}
	
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	
	
	public UserRole(String userRoleId, String userId, String roleId) {
		super();
		this.userRoleId = userRoleId;
		this.userId = userId;
		this.roleId = roleId;
	}
	
	
	
	@Override
	public String toString() {
		return "UserRole [userRoleId=" + userRoleId + ", userId=" + userId + ", roleId=" + roleId + "]";
	}	
	
	
	
}
