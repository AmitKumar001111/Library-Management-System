package com.lms.controller;

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


import com.lms.model.User;
import com.lms.repository.UserRepository;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	@PostMapping
	public String addUser(@RequestBody User user) {
		userRepository.save(user);
		
		return "User SuccessFully Added.";
	}
	
	
	
	@GetMapping
	public List<User> getAllUser(){
		System.out.println("Please check all User Details.");
		return userRepository.findAll();
	}
	
	@DeleteMapping
	public String deleteAllUser()
	{
		userRepository.deleteAll();
		return "all users are deleted !";
	}
	
	
	@DeleteMapping("/{userId}")
	public String deleteUser(@PathVariable String userId)
	{
		userRepository.deleteById(userId);
		return "Role with "+userId+" Id is SuccussFully Deleted" ;
	}
	
	@GetMapping("/{userId}")
	public Optional<User> getUser(@PathVariable String userId){
			return userRepository.findById(userId);
	}
	
	@PutMapping("/{userId}")
	public String updateUser(@PathVariable String userId , @RequestBody User user) {
		
		 java.util.Optional<User> userData = userRepository.findById(userId);
		 if (userData.isPresent()) {
			 User _user = userData.get();
			 
			 _user.setUserName(user.getUserName());
			
			 
			 userRepository.save(_user);
			 return "User Details Succussfully Updated";
		 }
		 else {
			 return "This User Does Not Exist !!";
		 }
		 
	}
	
	
}
