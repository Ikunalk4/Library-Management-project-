package com.spring.library.service;

import java.util.List;

import com.spring.library.dto.BookDto;

public interface BookService {
	
	BookDto createBook(BookDto bookDto);
	
	List<BookDto> getAllBooks();
	
	BookDto getBookById(Integer bookId );
	
	void deleteBook(Integer bookId);
	
	BookDto updateBook(BookDto bookDto, Integer bookId);
}
