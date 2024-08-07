package com.library.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.After;
import org.springframework.stereotype.Component;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
@Component
public class LoggingAspect {
	private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);
	
	 @Pointcut("execution(* com.library.service.*.*(..))")
	    public void serviceMethods() {}
	
	@Before("execution(* com.library.service.BookService.*(..))")
	public void logBefore() {
		logger.info("Method execution started");
	}
	
	@After("execution(* com.library.service.BookService.*(..))")
    public void logAfter() {
		logger.info("Method execution finished");
    }
	
}
