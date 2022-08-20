package com.lms.model;



public class UserDetails {
	private String userId;
	private String userName; 
	private String roleName;
//	private List<String> address;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public UserDetails(String userId, String userName, String roleName) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.roleName = roleName;
	}
	public UserDetails() {
		// TODO Auto-generated constructor stub
	}
	
	
}
