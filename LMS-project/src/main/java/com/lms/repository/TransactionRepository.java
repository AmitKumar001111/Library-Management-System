package com.lms.repository;
import com.lms.model.Transaction;


import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;


public interface TransactionRepository extends MongoRepository <Transaction, String>  {

	List<Transaction> findByStudentId(String userId);
	Optional<Transaction> findById(String userId);
}
