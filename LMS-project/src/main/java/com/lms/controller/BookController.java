package com.lms.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.model.Book;
import com.lms.model.BorrowedBook;
import com.lms.model.Role;
import com.lms.model.Transaction;
import com.lms.model.User;
import com.lms.model.UserRole;
import com.lms.repository.*;

@RestController
@RequestMapping("/book")
public class BookController {

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private TransactionRepository transactionRepository;

	@Autowired
	private UserRoleRepository userRoleRepository;
	
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@GetMapping("admin/{userId}")
	public List<Book> getAllBooks(@PathVariable String userId) {
		List<UserRole> userRolesList = userRoleRepository.findByUserId(userId);
		for(int i= 0 ; i < userRolesList.size(); i++)
		{
			Optional<Role> roleData = roleRepository.findByRoleId(userRolesList.get(i).getRoleId());
			if(roleData.get().getRoleName().equals("Admin")) {
				return bookRepository.findAll();
			}
		}
		return null;

	}

	@PostMapping("/admin/{userId}")
	public String addBook(@RequestBody Book book, @PathVariable String userId) {
		
		List<UserRole> userRolesList = userRoleRepository.findByUserId(userId);

		for(int i= 0 ; i < userRolesList.size(); i++)
		{
			Optional<Role> roleData = roleRepository.findByRoleId(userRolesList.get(i).getRoleId());
			if(roleData.get().getRoleName().equals("Admin")) {
				bookRepository.save(book);
				return "Book Added Successfully";
			}
		}
		return "Only Admin can Add Book, Thanks !!";
		
	}

	@GetMapping("/admin/{userId}/{bookId}")
	public Optional<Book> getBookById(@PathVariable String userId, @PathVariable String bookId) {
		List<UserRole> userRolesList = userRoleRepository.findByUserId(userId);
		for(int i =0 ; i< userRolesList.size() ; i++) {
			Optional<Role> roleData = roleRepository.findByRoleId(userRolesList.get(i).getRoleId());
			if(roleData.get().getRoleName().equals("Admin")) {
				return bookRepository.findById(bookId);
			}
		}
		return null;
	}

	
	@DeleteMapping("admin/{userId}")
	public String deleteAllBooks(@PathVariable String userId) {
		
		List<UserRole> userRolesList = userRoleRepository.findByUserId(userId);
		for(int i =0 ; i< userRolesList.size() ; i++) {
			Optional<Role> roleData = roleRepository.findByRoleId(userRolesList.get(i).getRoleId());
			if(roleData.get().getRoleName().equals("Admin")) {
				bookRepository.deleteAll();
				return "All books are succussfully deleted";
			}
		}
		return "Only User Can Delete Books, Thanks !!";
		
	}

	@DeleteMapping("/admin/{userId}/{bookId}")
	public String deleteBookById(@PathVariable String bookId, @PathVariable String userId) {
		
		List<UserRole> userRolesList = userRoleRepository.findByUserId(userId);
		for(int i =0 ; i< userRolesList.size() ; i++) {
			Optional<Role> roleData = roleRepository.findByRoleId(userRolesList.get(i).getRoleId());
			if(roleData.get().getRoleName().equals("Admin")) {
				bookRepository.deleteById(bookId);
				return "Book is succussfully deleted by " + bookId + " id.";			}
		}
		return "Only User Can Delete Books, Thanks !!";
		
	}

//	update Book Details
	
	@PutMapping("/admin/{userId}/{bookId}")
	public String updateBook(@PathVariable String bookId, @PathVariable String userId, @RequestBody Book book ) {

		List<UserRole> userRolesList = userRoleRepository.findByUserId(userId);
		for(int i =0 ; i< userRolesList.size() ; i++) {
			Optional<Role> roleData = roleRepository.findByRoleId(userRolesList.get(i).getRoleId());
			if(roleData.get().getRoleName().equals("Admin")) {
				Optional<Book> bookData = bookRepository.findById(bookId);
				if (bookData.isPresent()) {
					Book _book = bookData.get();

					_book.setTitle(book.getTitle());
					_book.setAuthor(book.getAuthor());
					_book.setCount(book.getCount());

					bookRepository.save(_book);
					return "Book Updated Succussfully !!";			
				}
				else
				{
				return "Book Not Exists !!";
				}
			}
			
		}
		return "Only User Can update Books, Thanks !!";
	}

	
	
//	get borrowed Books Details by user id.
	
	@GetMapping("borrowed/{userId}")
	public List<BorrowedBook> getBorrowedBook( @PathVariable String userId){
		
		List<UserRole> userRolesList = userRoleRepository.findByUserId(userId);
		for(int i = 0; i < userRolesList.size(); i++) {
			Optional<Role> roleData = roleRepository.findByRoleId(userRolesList.get(i).getRoleId());
			if(roleData.get().getRoleName().equals("Student")) {
				
				List<Transaction> transactionData = transactionRepository.findByStudentId(userId);
				for(int j = 0 ; j < transactionData.size() ; j++) {
					Optional<Book> bookData = bookRepository.findById(transactionData.get(i).getBookId());
					if(bookData.isPresent()) {
						List<BorrowedBook> borrowedBookDetails = new ArrayList<>();
						Optional<User> users = userRepository.findByUserId(userId);
						BorrowedBook borrowedBook = new BorrowedBook();
						borrowedBook.setTransactionId(transactionData.get(i).getTransactionId());
						borrowedBook.setUserName(users.get().getUserName());
						borrowedBook.setIssueDate(transactionData.get(i).getIssueDate());
						borrowedBook.setBookId(bookData.get().getBookId());
						borrowedBook.setBookName(bookData.get().getTitle());
						borrowedBook.setReturnDate(transactionData.get(i).getReturnDate());
						borrowedBookDetails.add(borrowedBook);
						return borrowedBookDetails;
					}
				}
				
			}
		}
		return null;	
	}
	

	
//	@GetMapping("/Admin/{userId}")
//	public List<Role> checkUserAdminOrNot(@PathVariable String userId) {
//		List<Role> roleList = new ArrayList<>();
//		List<UserRole> userRolesList = userRoleRepository.findRoleByUserId(userId);
//
//		for (int i = 0; i < userRolesList.size(); i++) {
//			Optional<Role> roleData = roleRepository.findById(userRolesList.get(i).getRoleId());
//			System.out.print("role Obj: " + roleData);
//			
//			if(roleData.isPresent())
//				roleList.add(roleData.get());
//				System.out.print("role : " + roleData.get());
//
//		}
//
//		return roleList;
//
//	}
}
