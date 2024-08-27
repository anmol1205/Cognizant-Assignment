package com.example.BookstoreAPI.util;

import com.example.BookstoreAPI.model.Book;
import com.example.BookstoreAPI.model.Customer;
import com.example.BookstoreAPI.model.dto.BookDTO;
import com.example.BookstoreAPI.model.dto.CustomerDTO;

public class DTOConverter {
	
	 public static BookDTO convertToBookDTO(Book book) {
	        BookDTO bookdto = new BookDTO();
	        bookdto.setId(book.getId());
	        bookdto.setTitle(book.getAuthor());
	        bookdto.setAuthor(book.getAuthor());
	        bookdto.setPrice(book.getPrice());
	        bookdto.setIsbn(book.getIsbn());
	        return bookdto;   
	    }
	 
	 public static Book convertToBook(BookDTO bookDTO) {
		 Book book = new Book();
	        book.setId(bookDTO.getId());
	        book.setTitle(bookDTO.getTitle());
	        book.setAuthor(bookDTO.getAuthor());
	        book.setPrice(bookDTO.getPrice());
	        book.setIsbn(bookDTO.getIsbn());
	        return book;
	 }
	 
	 public static CustomerDTO convertToCustomerDTO(Customer customer) {
	        CustomerDTO customerdto = new CustomerDTO();
	        customerdto.setId(customer.getId());;
	        customerdto.setName(customer.getName());
	        customerdto.setEmail(customer.getEmail());
	        customerdto.setPassword(customer.getPassword());
	        return customerdto;
	    }
	 
	 public static Customer convertToCustomer(CustomerDTO customerDTO) {
	        Customer customer = new Customer();
	        customer.setId(customerDTO.getId());
	        customer.setName(customerDTO.getName());
	        customer.setEmail(customerDTO.getEmail());
	        customer.setPassword(customerDTO.getPassword());
	        return customer;
	    }
	 
	 

}
