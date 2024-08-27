package com.example.BookstoreAPI.controller;

import com.example.BookstoreAPI.model.Book;
import com.example.BookstoreAPI.util.*;

import jakarta.validation.Valid;

import org.springframework.http.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.example.BookstoreAPI.assembler.BookModelAssembler;
import com.example.BookstoreAPI.exceptions.*;
import com.example.BookstoreAPI.model.dto.*;
import org.springframework.boot.*;
import com.example.BookstoreAPI.service.*;

//import com.example.BookstoreAPI.service.BookService;


import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;



import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/books")
public class BookController {

    private List<Book> books = new ArrayList<>();
    
    @Autowired
    private BookService bookService;
    
    @Autowired
    private BookModelAssembler bookModelAssembler;

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<BookDTO>>> getAllBooks(@RequestParam(required = false) String title, @RequestParam(required = false) String author) {
        List<BookDTO> filteredBooks = books.stream()
            .filter(book -> (title == null || book.getTitle().equalsIgnoreCase(title)) &&
                            (author == null || book.getAuthor().equalsIgnoreCase(author)))
            .map(this::convertToDTO)
            .toList();
        
        // Convert each BookDTO to EntityModel<BookDTO>
        List<EntityModel<BookDTO>> bookModels = filteredBooks.stream()
            .map(bookModelAssembler::toModel)
            .collect(Collectors.toList());
        
        // Wrap the list of EntityModel<BookDTO> in a CollectionModel
        CollectionModel<EntityModel<BookDTO>> collectionModel = CollectionModel.of(bookModels);

        // Add a self-link to the collection model (e.g., /books)
        collectionModel.add(linkTo(methodOn(BookController.class).getAllBooks(title, author)).withSelfRel());

        // Return the collection model wrapped in a ResponseEntity
        return ResponseEntity.ok(collectionModel);
        
        

      
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<BookDTO>> getBookById(@PathVariable Long id) {
    	 Book book = bookService.getBookById(id)
                 .orElseThrow(() -> new BookNotFoundException("Book not found"));
         BookDTO bookDTO = DTOConverter.convertToBookDTO(book);
         return ResponseEntity.ok(bookModelAssembler.toModel(bookDTO));
    }
    
    
    @PostMapping
    public ResponseEntity<EntityModel<BookDTO>> createBook(@Valid @RequestBody BookDTO bookDTO) {
    	Book book = DTOConverter.convertToBook(bookDTO);
        Book createdBook = bookService.createBook(book);
        BookDTO responseDTO = DTOConverter.convertToBookDTO(createdBook);
        return new ResponseEntity<>(bookModelAssembler.toModel(responseDTO), HttpStatus.CREATED);
    
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<BookDTO>> updateBook(@PathVariable Long id, @RequestBody BookDTO updatedBookDTO) {
    	Book updatedBook = DTOConverter.convertToBook(updatedBookDTO);
        Book book = bookService.updateBook(id, updatedBook)
                .orElseThrow(() -> new BookNotFoundException("Book not found"));
        BookDTO responseDTO = DTOConverter.convertToBookDTO(book);
        return ResponseEntity.ok(bookModelAssembler.toModel(responseDTO));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
    	 if (bookService.deleteBook(id)) {
             return new ResponseEntity<>(HttpStatus.NO_CONTENT);
         }
         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    private BookDTO convertToDTO(Book book) {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId((Long) book.getId());
        bookDTO.setTitle(book.getTitle());
        bookDTO.setAuthor(book.getAuthor());
        bookDTO.setPrice(book.getPrice());
        bookDTO.setIsbn(book.getIsbn());
        return bookDTO;
    }

    private Book convertToEntity(BookDTO bookDTO) {
        Book book = new Book();
        book.setId(bookDTO.getId());
        book.setTitle(bookDTO.getTitle());
        book.setAuthor(bookDTO.getAuthor());
        book.setPrice(bookDTO.getPrice());
        book.setIsbn(bookDTO.getIsbn());
        return book;
    }

}


//@RestController
//@RequestMapping("/books")
//public class BookController {
//	private List<Book> books = new ArrayList<>();
//	
//	// GET all books
//    @GetMapping
//    public ResponseEntity<List<Book>> getAllBooks(
//    	
//    	@RequestParam(required = false) String title,
//        @RequestParam(required = false) String author) {
//
//            List<Book> filteredBooks = books;
//
//            if (title != null && !title.isEmpty()) {
//                filteredBooks = filteredBooks.stream()
//                    .filter(book -> book.getTitle().equalsIgnoreCase(title))
//                    .toList();
//            }
//
//            if (author != null && !author.isEmpty()) {
//                filteredBooks = filteredBooks.stream()
//                    .filter(book -> book.getAuthor().equalsIgnoreCase(author))
//                    .toList();
//            }
//
//            return new ResponseEntity<>(filteredBooks, HttpStatus.OK);
//    }
//    
//    
////    //GET a book by ID
////    @GetMapping("/{id}")
////    @ResponseStatus(HttpStatus.OK)// response status for the exercise 5
////    public Book getBookById(@PathVariable Long id){
////    	return books.stream()
////    			.filter(book -> book.getId().equals(id))
////    			.findFirst()
////    			.orElseThrow(() -> new BookNotFoundException("Book not found"));
////    	HttpHeaders headers = new HttpHeaders();
////        headers.add("Custom-Header", "This is a custom header value");
////
////        return new ResponseEntity<>(book, headers, HttpStatus.OK);
////    }
//    
//    @GetMapping("/{id}")
//    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
//        Book book = books.stream()
//            .filter(b -> b.getId().equals(id))
//            .findFirst()
//            .orElseThrow(() -> new BookNotFoundException("Book not found"));
//        if (book == null) {
//            throw new BookNotFoundException("Book with ID " + id + " not found");
//        }
//        return new ResponseEntity<>(book, HttpStatus.OK);
//
//        // Creating a response entity with custom headers
////        HttpHeaders headers = new HttpHeaders();
////        headers.add("Custom-Header", "This is a custom header value");
////
////        return new ResponseEntity<>(book, headers, HttpStatus.OK);
//    }
//    
// // Example of setting a custom status and adding headers for a POST request
//    @PostMapping
//    public ResponseEntity<Book> createBook(@RequestBody Book book) {
//        book.setId((long) (books.size() + 1));
//        books.add(book);
//        
//        if (book.getTitle() == null || book.getAuthor() == null) {
//            throw new InvalidBookDataException("Book title or author cannot be null");
//        }
//        // Save book and return response
//        return new ResponseEntity<>(book, HttpStatus.CREATED);
//    
//
////        HttpHeaders headers = new HttpHeaders();
////        headers.add("Location", "/books/" + book.getId()); // Example of a Location header
////
////        return new ResponseEntity<>(book, headers, HttpStatus.CREATED);
//    }
//    
//    
//    
//    
//    // POST a book
////    @PostMapping
////    @ResponseStatus(HttpStatus.CREATED)
////    public Book createBook(@RequestBody Book book){
////    	 book.setId((long) (books.size() + 1));
////    	 books.add(book);
////         return book;
////    }
//    
//    @PutMapping
//    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book updatedBook){
//    	return books.stream()
//    			.filter(book -> book.getId().equals(id))
//    			.findFirst()
//    			.map(book -> {
//    				book.setTitle(updatedBook.getTitle());
//                    book.setAuthor(updatedBook.getAuthor());
//                    book.setPrice(updatedBook.getPrice());
//                    book.setIsbn(updatedBook.getIsbn());
//                    return new ResponseEntity<>(book, HttpStatus.OK);
//    			})
//    			.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
//    			
//    	
//    }
//    
//    @PutMapping("/{id}")
//    public ResponseEntity<Book> updateBookbyId(@PathVariable Long id, @RequestBody Book updatedBook){
//    	return books.stream()
//    			.filter(book -> book.getId().equals(id))
//    			.findFirst()
//    			.map(book -> {
//    				 	book.setTitle(updatedBook.getTitle());
//    	                book.setAuthor(updatedBook.getAuthor());
//    	                book.setPrice(updatedBook.getPrice());
//    	                book.setIsbn(updatedBook.getIsbn());
//    	                return new ResponseEntity<>(book, HttpStatus.OK);
//    			})
//    			.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
//    }
//    
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Book> deleteBook(@PathVariable Long id){
//    	boolean removed = books.removeIf(book -> book.getId().equals(id));
//    	 if (removed) {
//             return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//         }
//         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }
//    
//    
//    
//}