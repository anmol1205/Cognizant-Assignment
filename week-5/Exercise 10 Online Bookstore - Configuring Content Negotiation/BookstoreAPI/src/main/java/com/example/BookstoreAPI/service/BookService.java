package com.example.BookstoreAPI.service;

import com.example.BookstoreAPI.model.dto.BookDTO;
import com.example.BookstoreAPI.model.Book;
import com.example.BookstoreAPI.exceptions.BookNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class BookService {
	
	private List<Book> books = new ArrayList<>();

    public List<Book> getAllBooks(String title, String author) {
        return books.stream()
            .filter(book -> (title == null || book.getTitle().equalsIgnoreCase(title)) &&
                            (author == null || book.getAuthor().equalsIgnoreCase(author)))
            .toList();
    }
    
    public Optional<Book> getBookById(Long id) {
        return books.stream()
            .filter(book -> book.getId().equals(id))
            .findFirst();
    }

    public Book createBook(Book book) {
        book.setId((long) (books.size() + 1));
        books.add(book);
        return book;
    }

    public Optional<Book> updateBook(Long id, Book updatedBook) {
        return books.stream()
            .filter(book -> book.getId().equals(id))
            .findFirst()
            .map(book -> {
                book.setTitle(updatedBook.getTitle());
                book.setAuthor(updatedBook.getAuthor());
                book.setPrice(updatedBook.getPrice());
                book.setIsbn(updatedBook.getIsbn());
                return book;
            });
    }

    public boolean deleteBook(Long id) {
        return books.removeIf(book -> book.getId().equals(id));
    }

}
