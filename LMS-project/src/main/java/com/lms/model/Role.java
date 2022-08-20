package com.lms.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Role")
public class Role {
	
	@Id
	private String roleId;
	private String roleName;
	
	
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	
	
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	
	@Override
	public String toString() {
		return "Role [roleId=" + roleId + ", roleName=" + roleName + "]";
	}
	
	public Role(String roleId, String roleName) {
		super();
		this.roleId = roleId;
		this.roleName = roleName;
	}
	public Role() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	
}
