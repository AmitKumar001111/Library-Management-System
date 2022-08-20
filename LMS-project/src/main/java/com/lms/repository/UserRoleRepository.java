package com.lms.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.lms.model.UserRole;


@Repository
public interface UserRoleRepository extends MongoRepository <UserRole , String>{

	List<UserRole> findByRoleId(String roleId);
	 List<UserRole>  findByUserId(String userId); //-> object(user) or empty
	 List<UserRole> findRoleByUserId(String userId);
}
