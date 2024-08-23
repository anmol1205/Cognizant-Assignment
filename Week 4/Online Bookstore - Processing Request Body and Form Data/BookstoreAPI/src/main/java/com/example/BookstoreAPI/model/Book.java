package com.example.BookstoreAPI.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
	private Long id;
    private String title;
    private String author;
    private Double price;
    private String isbn;
    
	public Object getId() {
		// TODO Auto-generated method stub
		return this.id;
	}

	public void setId(long l) {
		// TODO Auto-generated method stub
		this.id = l;
	}

	public String getTitle() {
		// TODO Auto-generated method stub
		return this.title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	
	public void setAuthor(String author) {
		this.author = author;
	}

	public String getAuthor() {
		// TODO Auto-generated method stub
		return this.author;
	}

	public Double getPrice() {
		// TODO Auto-generated method stub
		return this.price;
	}
	
	public void setPrice(Double price) {
		this.price = price;
	}
	
	public String getIsbn() {
		return this.isbn;
	}
	
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	

}
