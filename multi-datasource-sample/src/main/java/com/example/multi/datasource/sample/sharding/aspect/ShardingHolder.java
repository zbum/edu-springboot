package com.example.multi.datasource.sample.sharding.aspect;

import lombok.NonNull;
import lombok.experimental.UtilityClass;

import java.util.Optional;

/**
 * @author myeongju.jung
 */
@UtilityClass
public class ShardingHolder {
    private static final ThreadLocal<Integer> DISTRIBUTION_KEY = new ThreadLocal<>();

    void clear() {
        DISTRIBUTION_KEY.remove();
    }

    public int getKey() {
        return Optional.ofNullable(DISTRIBUTION_KEY.get())
                       .orElse(0);
    }

    void setKey(@NonNull Integer key) {
        DISTRIBUTION_KEY.set(key);
    }
}
