package com.test.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.test.lms.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long>{
	
}
