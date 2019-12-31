package com.inal.kafkademo.consumer.lock;

import java.util.Arrays;

public class RedisKeyGenerator {

    private static final String KEY_SEPARATOR = ":";
    private static final String LOCK_PREFIX = "lock";
    private static final String COMMON_PREFIX = "payment";

    public static String generateLockKey(String value) {
        return generateKey(LOCK_PREFIX, value);
    }

    public static String generateKey(String... value) {
        StringBuilder builder = new StringBuilder(COMMON_PREFIX);
        Arrays.asList(value).forEach(str -> builder.append(KEY_SEPARATOR).append(str));
        return builder.toString();
    }
}