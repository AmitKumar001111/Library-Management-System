package com.lms.controller;

import java.util.ArrayList;
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

import com.lms.model.Role;
import com.lms.model.User;
import com.lms.model.UserDetails;
import com.lms.model.UserRole;
import com.lms.repository.RoleRepository;
import com.lms.repository.UserRepository;
import com.lms.repository.UserRoleRepository;

@RestController
@RequestMapping("/userRole")
public class UserRoleController {



	@Autowired
	private UserRoleRepository userRoleRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
		 
	
	@PostMapping
	public String addUserRole(@RequestBody UserRole userRole) {
		userRoleRepository.save(userRole);
		
		return "UserRole SuccessFully Added.";
	}
		
	@GetMapping
	public List<UserRole> getAllUserRoles()
	{
		return userRoleRepository.findAll();
	}
		
	//to do list of user
	@GetMapping("/{roleId}")
	public List<User> getRoleById(@PathVariable String roleId, @RequestBody UserRole userRole){
		List<User> users = new ArrayList<>();
		List<UserRole> userRoles= userRoleRepository.findByRoleId(roleId);
		for(int i =0; i< userRoles.size();i++ ) {
			Optional<User> user = userRepository.findByUserId(userRoles.get(i).getUserId());
			
			if(user.isPresent()) {
			users.add(user.get());
			}
		}
		
		return users; 
	}
	
	@GetMapping("/get-user-details-by-roleId/{roleId}/{userId}")
	public List<UserDetails> getAllUserDetailsByRoleId(@PathVariable String roleId, @PathVariable String userId){
		
		List<UserRole> userRoleData = userRoleRepository.findByUserId(userId);
		for(int i =0 ; i< userRoleData.size() ; i++)
		{
			Optional<Role> roleData = roleRepository.findByRoleId(userRoleData.get(i).getRoleId());
			if(roleData.get().getRoleName().equals ("Admin")) {
				List<UserDetails> userDetailsList = new ArrayList<>(); 
				
				List<UserRole> userRoles= userRoleRepository.findByRoleId(roleId);
				
				for(int j =0; j< userRoles.size();j++ ) {
					Optional<User> user = userRepository.findByUserId(userRoles.get(i).getUserId());
					Optional<Role> userRole= roleRepository.findByRoleId(roleId);
					
					if(user.isPresent() && userRole.isPresent()) {
						UserDetails userDetails = new UserDetails();
						userDetails.setUserId(userRoles.get(i).getUserId());
						userDetails.setUserName(user.get().getUserName());
						userDetails.setRoleName(userRole.get().getRoleName());
						userDetailsList.add(userDetails);
					}
				}
				return userDetailsList;
			}
//			else {
//				return null;
//			}
		}
		return null;
	}
		
		
		
		@DeleteMapping
		public String deleteAllUserRole() {
			userRoleRepository.deleteAll();
			return "All user role data is deleted !!";
		}
		
		
		
		
		
		
		
		
		
		
		
//		
//		List<UserDetails> userDetailsList = new ArrayList<>(); 
//		
//		List<UserRole> userRoles= userRoleRepository.findByRoleId(roleId);
//		
//		for(int i =0; i< userRoles.size();i++ ) {
//			Optional<User> user = userRepository.findByUserId(userRoles.get(i).getUserId());
//			Optional<Role> userRole= roleRepository.findByRoleId(roleId);
//			
//			if(user.isPresent() && userRole.isPresent()) {
//				UserDetails userDetails = new UserDetails();
//				userDetails.setUserId(userRoles.get(i).getUserId());
//				userDetails.setUserName(user.get().getUserName());
//				userDetails.setRoleName(userRole.get().getRoleName());
//				userDetailsList.add(userDetails);
//			}
//		}
//		
		  
		 
	
	
}

	
	

