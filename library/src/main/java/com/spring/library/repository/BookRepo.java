package com.spring.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.library.entities.Book;

@Repository
public interface BookRepo extends JpaRepository<Book, Integer>{
	
}
