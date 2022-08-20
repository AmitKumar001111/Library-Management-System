package com.lms.model;

public class BorrowedBook {

	private String transactionId;
	private String userName;
	private String issueDate;
	private String returnStatus;
	private String returnDate;
	private String bookName;
	private String bookId;
	
	
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getBookId() {
		return bookId;
	}
	public void setBookId(String bookId) {
		this.bookId = bookId;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	

	
	public String getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
	}
	
	public String getReturnStatus() {
		return returnStatus;
	}
	public void setReturnStatus(String returnStatus) {
		this.returnStatus = returnStatus;
	}
	
	public String getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}
	
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public BorrowedBook(String transactionId, String userName , String issueDate, String returnStatus,
			String returnDate, String bookName, String bookId) {
		super();
		this.transactionId = transactionId;
		this.userName= userName;
		this.issueDate = issueDate;
		this.returnStatus = returnStatus;
		this.returnDate = returnDate;
		this.bookName = bookName;
		this.bookId = bookId;
	}
	public BorrowedBook() {
		// TODO Auto-generated constructor stub
	}
	
	
}
