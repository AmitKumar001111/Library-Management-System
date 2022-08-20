package com.lms.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.model.Book;
import com.lms.model.Role;
import com.lms.model.Transaction;
import com.lms.model.User;
import com.lms.model.UserRole;
import com.lms.repository.BookRepository;
import com.lms.repository.RoleRepository;
import com.lms.repository.TransactionRepository;
import com.lms.repository.UserRepository;
import com.lms.repository.UserRoleRepository;

@RestController
@RequestMapping("/book-transaction")
public class TransactionController {



	@Autowired
	private TransactionRepository transactionRepository;
	
	@Autowired
	private BookRepository bookRepository; 

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserRoleRepository userRoleRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	
	private final String admin = "Admin";
	
	private final String student = "Student";
	
	@GetMapping
	public List<Transaction> getAllBorrowedBooks(){
		return transactionRepository.findAll();
	}
	


	
	@PostMapping("/issue")
	public String issueBooks(@RequestBody Transaction transaction) {

		boolean isAdmin = checkWhetherIssuerRoleISAdmin(transaction.getIssuerId());
		if (!isAdmin) {
			return "Only Admin Can Issue The Book !!";
		}

		boolean isStudent = checkWhetherUserRoleIsStudent(transaction.getStudentId());
		if (!isStudent) {
			return "Only Student can Get Books !!";
		}

		Optional<Book> bookData = bookRepository.findById(transaction.getBookId());
		if (bookData.isPresent()) {
			Book _book = bookData.get();
			int bookQnty = _book.getCount();

			Optional<User> userData = userRepository.findById(transaction.getStudentId());

			User _user = userData.get();

			int userIssueCount = _user.getIssueCount();

			if (bookQnty > 0 && userIssueCount <= 2) {

				_book.setCount(_book.decreaseCount()); // decrement the book by one.
				_user.setIssueCount(_user.increaseIssueCount()); // increment user issue Count by one.

				bookRepository.save(_book); // save the book in book repository.
				userRepository.save(_user); // save the user in user repository.

				transactionRepository.save(transaction); // save the issued book details in issue
															// repository.
				return "Book succussfully issued."; // print issued book.
			} else {
				return "All Book Copies are issued or user can Take the limited books !!";
			}

		} else {
			return "Book is Not Available !!";
		}

	}
		

	

	
	
	private boolean checkWhetherIssuerRoleISAdmin(String issuerId) {
		 boolean exists = false;
	
		List<UserRole> userRolesList = userRoleRepository.findByUserId(issuerId);
		for (int i = 0; i < userRolesList.size(); i++) {
			Optional<Role> roleData = roleRepository.findByRoleId(userRolesList.get(i).getRoleId());
			if (roleData.get().getRoleName().equals(admin)) {
				exists=true;
			}
		}
		return exists;
		
	}
	
	
	private boolean checkWhetherUserRoleIsStudent(String studentId) {
		 boolean studentExists = false;
	
		List<UserRole> userRolesList = userRoleRepository.findByUserId(studentId);
		for (int i = 0; i < userRolesList.size(); i++) {
			Optional<Role> roleData = roleRepository.findByRoleId(userRolesList.get(i).getRoleId());
			if (roleData.get().getRoleName().equals(student)) {
				studentExists=true;
			}
		}
		return studentExists;
		
	}




	@PostMapping("/return")
	public String returnBook(@RequestBody Transaction transaction) {

		boolean isAdmin = checkWhetherIssuerRoleISAdmin(transaction.getIssuerId());
		if (!isAdmin) {
			return "Only Admin Can Issue The Book !!";
		}
		Optional<Book> bookData = bookRepository.findById(transaction.getBookId());
		Optional<User> studentUserData = userRepository.findById(transaction.getStudentId());
		Optional<Transaction> transactionData = transactionRepository.findById(transaction.getTransactionId());
		if (bookData.isPresent() && studentUserData.isPresent()) {
			Book _book = bookData.get();
			_book.setCount(_book.increaseCount());

			User _user = studentUserData.get();
			_user.setIssueCount(_user.decreaseIssueCount());

			Transaction _transaction = transactionData.get();
			_transaction.setReturnStatus(true);
			_transaction.setReturnDate(_transaction.getReturnDate());

			bookRepository.save(_book);
			userRepository.save(_user);
			transactionRepository.save(_transaction);

			return "Book is succussfully Returned.";
		} else {
			return "book Data or student data is not available !!";
		}

	}
		

	
	
	
	@GetMapping("/{issueId}")
	public Optional<Transaction> getBookById(@PathVariable String issueId){
		return transactionRepository.findById(issueId);
	}
	
	@GetMapping("/view/{userId}")
	public List <Transaction> getBookByUserId(@PathVariable String userId){
		return transactionRepository.findByStudentId(userId);
	}
	
	@DeleteMapping("/{issueId}")
	public String deleteBookById(@PathVariable String issueId) {
		transactionRepository.deleteById(issueId);
		return "Book is succussfully deleted by "+issueId+" id.";
	}
	
	@DeleteMapping
	public String deleteAllBooks() {
		transactionRepository.deleteAll();
		return "All issue details Are SuccussFully Deleted !!";
	}
	
}
