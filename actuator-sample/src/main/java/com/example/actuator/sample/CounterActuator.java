package com.example.actuator.sample;

import org.springframework.boot.actuate.endpoint.AbstractEndpoint;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author myeongju.jung
 */
@Component
public class CounterActuator extends AbstractEndpoint<Long> {
    private final AtomicLong counter = new AtomicLong();

    public CounterActuator() {
        super("counter");
    }

    @Override
    public Long invoke() {
        return counter.incrementAndGet();
    }
}
