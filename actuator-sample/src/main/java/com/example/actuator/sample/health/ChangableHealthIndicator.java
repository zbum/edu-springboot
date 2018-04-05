package com.example.actuator.sample.health;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;

/**
 * @author myeongju.jung
 */
public interface ChangableHealthIndicator extends HealthIndicator {
    void changeHealth(Health health);
}
