package com.example.actuator.sample.health;

import org.springframework.boot.actuate.health.Health;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 메모리 내 객체로 구분되는 헬스 인디케이터
 * <p>기본은 UP 상태</p>
 *
 * @author myeongju.jung
 */
@Component
public class ManualHealthIndicator implements ChangableHealthIndicator {
    private final AtomicReference<Health> healthRef = new AtomicReference<>(Health.up().build());

    @Override
    public Health health() {
        return healthRef.get();
    }

    @Override
    public void changeHealth(Health health) {
        healthRef.set(health);
    }
}
