package com.example.BookstoreAPI.metrics;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Service;

@Service
public class BookMetricsService {
	 private final MeterRegistry meterRegistry;

	    public BookMetricsService(MeterRegistry meterRegistry) {
	        this.meterRegistry = meterRegistry;
	        meterRegistry.counter("books.created").increment();  // Example: Increment book creation counter
	    }

	    public void incrementBookCreationCounter() {
	        meterRegistry.counter("books.created").increment();
	    }
}
