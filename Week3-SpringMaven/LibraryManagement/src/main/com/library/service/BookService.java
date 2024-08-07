package com.library.service;

public class BookService {
	 private BookRepository bookRepository;

	    // Setter for dependency injection
	    public void setBookRepository(BookRepository bookRepository) {
	        this.bookRepository = bookRepository;
	    }
	    
	    public void someServiceMethod() {
	        System.out.println("BookService is working!");
	        
	    }
}
