package com.example.multi.datasource.sample.sharding.aspect;

import lombok.NonNull;
import lombok.experimental.UtilityClass;

import java.util.Optional;

/**
 * @author myeongju.jung
 */
@UtilityClass
public class ShardingHolder {
    private static ThreadLocal<Integer> distributionKey = new ThreadLocal<>();

    void clear() {
        distributionKey.remove();
    }

    public int getKey() {
        return Optional.ofNullable(distributionKey.get())
                       .orElse(0);
    }

    void setKey(@NonNull Integer key) {
        distributionKey.set(key);
    }
}
