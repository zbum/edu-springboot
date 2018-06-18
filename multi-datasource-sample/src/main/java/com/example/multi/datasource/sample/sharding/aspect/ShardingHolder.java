package com.example.multi.datasource.sample.sharding.aspect;

import java.util.Optional;

/**
 * @author myeongju.jung
 */
public class ShardingHolder {

    private ShardingHolder() {
        throw new UnsupportedOperationException();
    }

    private static final ThreadLocal<Integer> DISTRIBUTION_KEY = new ThreadLocal<>();

    static void clear() {
        DISTRIBUTION_KEY.remove();
    }

    public static int getKey() {
        return Optional.ofNullable(DISTRIBUTION_KEY.get())
                       .orElse(0);
    }

    static void setKey(Integer key) {
        DISTRIBUTION_KEY.set(key);
    }
}
