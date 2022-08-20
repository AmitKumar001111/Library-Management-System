package com.lms.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "User")
public class User {

	@Id
	private String userId;
	private String userName;

	private int issueCount;
	
	
	
	
	public int getIssueCount() {
		return issueCount;
	}
	public void setIssueCount(int issueCount) {
		this.issueCount = issueCount;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public int increaseIssueCount() {
		return ++issueCount;
	}
	
	public int decreaseIssueCount() {
		return --issueCount;
	}
	
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
	
	
	
	public User(String userId, String userName,  int issueCount) {
		super();
		this.userId = userId;
		this.userName = userName;

		this.issueCount = issueCount;
	}
	
	
	public User() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName  + ", issueCount="+issueCount + "]";
	}
	
}
