package com.example.BookstoreAPI.controller;

import com.example.BookstoreAPI.model.Book;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
	private List<Book> books = new ArrayList<>();
	
	// GET all books
    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks(
    	
    	@RequestParam(required = false) String title,
        @RequestParam(required = false) String author ) {

            List<Book> filteredBooks = books;

            if (title != null && !title.isEmpty()) {
                filteredBooks = filteredBooks.stream()
                    .filter(book -> book.getTitle().equalsIgnoreCase(title))
                    .toList();
            }

            if (author != null && !author.isEmpty()) {
                filteredBooks = filteredBooks.stream()
                    .filter(book -> book.getAuthor().equalsIgnoreCase(author))
                    .toList();
            }

            return new ResponseEntity<>(filteredBooks, HttpStatus.OK);
    }
    
    
    //GET a book by ID
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id){
    	return books.stream()
    			.filter(book -> book.getId().equals(id))
    			.findFirst()
    			.map(book -> new ResponseEntity<>(book, HttpStatus.OK))
    			.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    
    
    
    // POST a book
    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book){
    	 book.setId((long) (books.size() + 1));
    	 books.add(book);
         return new ResponseEntity<>(book, HttpStatus.CREATED);
    }
    
    @PutMapping
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book updatedBook){
    	return books.stream()
    			.filter(book -> book.getId().equals(id))
    			.findFirst()
    			.map(book -> {
    				book.setTitle(updatedBook.getTitle());
                    book.setAuthor(updatedBook.getAuthor());
                    book.setPrice(updatedBook.getPrice());
                    book.setIsbn(updatedBook.getIsbn());
                    return new ResponseEntity<>(book, HttpStatus.OK);
    			})
    			.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    			
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBookbyId(@PathVariable Long id, @RequestBody Book updatedBook){
    	return books.stream()
    			.filter(book -> book.getId().equals(id))
    			.findFirst()
    			.map(book -> {
    				 	book.setTitle(updatedBook.getTitle());
    	                book.setAuthor(updatedBook.getAuthor());
    	                book.setPrice(updatedBook.getPrice());
    	                book.setIsbn(updatedBook.getIsbn());
    	                return new ResponseEntity<>(book, HttpStatus.OK);
    			})
    			.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Book> deleteBook(@PathVariable Long id){
    	boolean removed = books.removeIf(book -> book.getId().equals(id));
    	 if (removed) {
             return new ResponseEntity<>(HttpStatus.NO_CONTENT);
         }
         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    
    
}
