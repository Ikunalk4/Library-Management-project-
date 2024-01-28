package com.spring.library.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.library.dto.BookDto;
import com.spring.library.entities.Book;
import com.spring.library.exception.ResourceNotFoundException;
import com.spring.library.repository.BookRepo;
import com.spring.library.service.BookService;


@Service
public class BookServiceImpl implements BookService{
	
	@Autowired
	private BookRepo bookRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public BookDto createBook(BookDto bookDto) {
		
		Book book = this.modelMapper.map(bookDto, Book.class);
		Book savedBook = this.bookRepo.save(book);
		return this.modelMapper.map(savedBook, BookDto.class);
	}

	@Override
	public List<BookDto> getAllBooks() {
		List<Book> books = this.bookRepo.findAll();
		List<BookDto> bookDtoList = books.stream().map( (book) -> this.modelMapper.map(book, BookDto.class))
		.collect( Collectors.toList());
		return bookDtoList;
	}

	@Override
	public BookDto getBookById(Integer bookId) {
		Book book = this.bookRepo.findById(bookId)
		.orElseThrow( () -> new ResourceNotFoundException("book", "BookId", bookId));
		return this.modelMapper.map(book, BookDto.class);
	}

	@Override
	public void deleteBook(Integer bookId) {
		Book book = this.bookRepo.findById(bookId)
				.orElseThrow( ()-> new ResourceNotFoundException("book", "BookId", bookId));
		this.bookRepo.delete(book);
	}

	@Override
	public BookDto updateBook(BookDto bookDto, Integer bookId) {
		Book book = this.bookRepo.findById(bookId)
				.orElseThrow( ()-> new ResourceNotFoundException("book", "BookId", bookId));
		book.setAuthor(bookDto.getAuthor());
		book.setEdition(bookDto.getEdition());
		book.setTitle(bookDto.getTitle());
		Book savedBook = this.bookRepo.save(book);
		BookDto dto = this.modelMapper.map(savedBook, BookDto.class);
		return dto;
	}

}
