package com.example.BookstoreAPI.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
	private Long id;
    private String name;
    private String email;
    private String password;
    
    public Long getID() {
    	return this.id;
    }
    
    public String getName() {
    	return this.name;
    }
    
    public String getEmail() {
    	return this.email;
    }
    
    public String getPassword() {
    	return this.password;
    }
    
	public void setId(long l) {
		// TODO Auto-generated method stub
		this.id = l;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email2) {
		// TODO Auto-generated method stub
		this.email = email2;
	}

	public void setPassword(String password2) {
		// TODO Auto-generated method stub
		this.password = password2;
	}

	
	
}
