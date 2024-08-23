package com.example.BookstoreAPI.model.dto;
import com.fasterxml.jackson.annotation.JsonProperty;


public class BookDTO {
	private Long id;
	
	@JsonProperty("book_title")
	private String title;
	
	
    private String author;
    private Double price;
    private String isbn;
    
    
	public void setId(Long id2) {
		// TODO Auto-generated method stub
		this.id = id2;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	
	public void setPrice(Double price) {
		this.price = price;
	}
	
	public Long getId() {
		return this.id;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public String getAuthor() {
		return this.author;
	}
	
	public Double getPrice() {
		return this.price;
	}
	
	public String getIsbn() {
		return this.isbn;
	}
	
	
	
}
