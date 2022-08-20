package com.lms.repository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.lms.model.Book;

@Repository
public interface BookRepository extends MongoRepository <Book , String> {

}
