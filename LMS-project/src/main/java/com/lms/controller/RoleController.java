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

import com.lms.model.Role;
import com.lms.repository.RoleRepository;

@RestController
@RequestMapping("/role")
public class RoleController {

	@Autowired
	private RoleRepository roleRepository;
	
	
	@PostMapping
	public String addRole(@RequestBody Role role) {
		
	
			roleRepository.save(role);
			return "Role SuccessFully Added.";
		
	}
	
	
	
	@GetMapping
	public List<Role> getRole(){
		System.out.println("Please check all Roles.");
		return roleRepository.findAll();
	}
	
	@DeleteMapping
	public String deleteAllRole()
	{
		roleRepository.deleteAll();
		return "all roles are deleted !";
	}
	
	
	@DeleteMapping("/{roleId}")
	public String deleteRole(@PathVariable String roleId)
	{
		roleRepository.deleteById(roleId);
		return "Role with "+roleId+" Id is SuccussFully Deleted" ;
	}
	
	@GetMapping("/{roleId}")
	public Optional<Role> getRole(@PathVariable String roleId){
			return roleRepository.findById(roleId);
	}
	
	
	@PutMapping("/{roleId}")
	public String updateRole(@PathVariable String roleId , @RequestBody Role role) {
		
		 Optional<Role> roleData = roleRepository.findById(roleId);
		 if (roleData.isPresent()) {
			 Role _role = roleData.get();
			 
			 _role.setRoleName(role.getRoleName());
			
			 roleRepository.save(_role);
			 return "Role has succussfully Updated";
		 }
		 else {
			 return "this Role_Id not exist, please try for valid role !!";
		 }
		 
	}
}
