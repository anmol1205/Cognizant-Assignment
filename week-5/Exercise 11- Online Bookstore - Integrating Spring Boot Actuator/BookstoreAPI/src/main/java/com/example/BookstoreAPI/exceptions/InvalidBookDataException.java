package com.example.BookstoreAPI.exceptions;

public class InvalidBookDataException extends RuntimeException{
	public InvalidBookDataException(String message) {
        super(message);
    }
}
