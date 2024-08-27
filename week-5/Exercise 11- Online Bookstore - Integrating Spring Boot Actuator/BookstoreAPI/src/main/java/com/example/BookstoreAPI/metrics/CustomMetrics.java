package com.example.BookstoreAPI.metrics;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Counter;
import org.springframework.stereotype.Component;

@Component
public class CustomMetrics {
	private final Counter customCounter;

    public CustomMetrics(MeterRegistry meterRegistry) {
        // Register a custom counter metric
        this.customCounter = meterRegistry.counter("custom_counter_metric", "type", "example");
    }

    public void incrementCounter() {
        this.customCounter.increment();
    }
}
