package com.example.BookstoreAPI.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CustomerDTO {
	private Long id;
    private String name;
    private String email;
    private String Password;
    
    public void setId(Long id) {
    	this.id = id;
    }
    
    public void setName(String name) {
    	this.name = name;
    }
    
    public void setEmail(String email) {
    	this.email = email;
    }
    
    public void setPassword(String password) {
    	this.Password = password;
    }
    
    public Long getId() {
    	return this.id;
    }
    
    public String getName() {
    	return this.name;
    }
    
    public String getEmail() {
    	return this.email;
    }
    
    public String getPassword() {
    	return this.Password;
    }
    
}
