package com.example.BookstoreAPI.controller;

import com.example.BookstoreAPI.model.Customer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {
	private List<Customer> customers = new ArrayList<>();
	
	
	
	// POST to create a new customer (JSON Request Body)
    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        customer.setId((long) (customers.size() + 1)); // Simple ID generation
        customers.add(customer);
        return new ResponseEntity<>(customer, HttpStatus.CREATED);
    }
    
    // POST to create a new customer (Form Data : x-www-form-urlencoded)
    @PostMapping("/register")
    public ResponseEntity<Customer> registerCustomer(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String password) {
    	Customer customer = new Customer();
        customer.setId((long) (customers.size() + 1)); // Simple ID generation
        customer.setName(name);
        customer.setEmail(email);
        customer.setPassword(password);

        customers.add(customer);
        return new ResponseEntity<>(customer, HttpStatus.CREATED);
    }
    
    // GET to retrieve all customers
    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }
	
}
