package com.lms.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



@Document(collection = "Book")
public class Book {
	
	@Id
	private String bookId;
	private String title;
	private String author;
	private int count;

	
	
//	int i =8;
//	int j = i--;
//	System.out.println(i--);
//	System.out.println(j);
//	
//	
	
	
	public int getCount() {
		return count;
	}
	
	

	public int decreaseCount() {
		return --count;
	}
	
	
	public int increaseCount() {
		return ++count;
	}
	
	
	public void setCount(int count) {
		this.count = count;
	}
	
	
	public String getBookId() {
		return bookId;
	}
	public void setBookId(String bookId) {
		this.bookId = bookId;
	}
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	
	
	public Book(String bookId, String title, String author, int count) {
		super();
		this.bookId = bookId;
		this.title = title;
		this.author = author;
		this.count = count;
		
	
	}
	
	public Book() {
		// TODO Auto-generated constructor stub
	}
	
//	public int bookCount() {
//		return count--;
//	}
	
	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", title=" + title + ", author=" + author + ", count=" + count + "]";
	}
	
	
	
}
