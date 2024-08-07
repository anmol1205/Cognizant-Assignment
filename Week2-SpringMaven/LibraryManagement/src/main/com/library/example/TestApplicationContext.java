package com.library.example;

public class TestApplicationContext {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlContext("applicationContext.xml");
		
		BookService bookService = (BookService) context.getBean("bookService");
		
		bookService.someServiceMethod();
		
	}
}
