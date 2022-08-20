package com.lms.model;

import org.springframework.data.annotation.Id;


public class Transaction{
	@Id
	private String transactionId;
	
	private String studentId;
	
	private String bookId;
	
	private String issueDate;
	
	private boolean returnStatus;
	
	private String returnDate;

	private String issuerId;
	
	
	public String getIssuerId() {
		return issuerId;
	}

	public void setIssuerId(String issuerId) {
		this.issuerId = issuerId;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public String getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
	}

	public boolean isReturnStatus() {
		return returnStatus;
	}

	public void setReturnStatus(boolean returnStatus) {
		this.returnStatus = returnStatus;
	}

	public String getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}

	
	public Transaction(String transactionId, String studentId, String bookId, String issueDate, boolean returnStatus,
			String returnDate, String issuerId) {
		super();
		this.transactionId = transactionId;
		this.studentId = studentId;
		this.bookId = bookId;
		this.issueDate = issueDate;
		this.returnStatus = returnStatus;
		this.returnDate = returnDate;
		this.issuerId = issuerId;
	}

	
	@Override
	public String toString() {
		return "Transaction [transactionId=" + transactionId + ", studentId=" + studentId + ", bookId=" + bookId
				+ ", issueDate=" + issueDate + ", returnStatus=" + returnStatus + ", returnDate=" + returnDate + ", issuerId=" +issuerId + "]";
	}
	
	
	
}	
	
