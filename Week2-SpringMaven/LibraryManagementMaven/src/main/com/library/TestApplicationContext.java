package com.library;

import com.library.service.BookService;

public class TestApplicationContext {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		BookService bookService = (BookService) context.getBean("bookService");
		
		bookService.someServiceMethod();
	}
}
