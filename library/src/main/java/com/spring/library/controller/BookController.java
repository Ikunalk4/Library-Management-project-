package com.spring.library.controller;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.library.payloads.ApiResponse;
import com.spring.library.dto.BookDto;
import com.spring.library.service.BookService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	@PostMapping("/")
	public ResponseEntity<BookDto> createBook(@Valid @RequestBody BookDto bookDto){
		BookDto createdBookDto = bookService.createBook(bookDto);
		return new ResponseEntity<>(createdBookDto,HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<BookDto> updateBook(@RequestBody BookDto bookDto, @PathVariable("id") Integer bookId){
		BookDto updateBook = this.bookService.updateBook(bookDto, bookId);
		return new ResponseEntity<BookDto>(updateBook,HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<BookDto>> getAllBook(){
		List<BookDto> allBooks = this.bookService.getAllBooks();
		return ResponseEntity.ok(allBooks);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<BookDto> getBookById(@PathVariable("id") Integer bookId){
		BookDto bookById = this.bookService.getBookById(bookId);
		return new ResponseEntity<BookDto>(bookById,HttpStatus.OK);
	}
	
	@DeleteMapping("/{bookId}")
	public ResponseEntity<ApiResponse> deleteBook(@PathVariable Integer bookId) {
		this.bookService.deleteBook(bookId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("category is deleted successfully",
				true),HttpStatus.OK);
	}
}
