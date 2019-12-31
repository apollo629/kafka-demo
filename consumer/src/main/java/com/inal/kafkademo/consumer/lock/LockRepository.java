package com.inal.kafkademo.consumer.lock;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicInteger;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

@Repository
public class LockRepository {

    private final RedisTemplate<String, String> redisTemplate;

    public LockRepository(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public Integer increment(String key, Integer ttl, TimeUnit timeUnit) {
        RedisAtomicInteger redisAtomicInteger = new RedisAtomicInteger(key, redisTemplate.getConnectionFactory());
        redisAtomicInteger.expire(ttl, timeUnit);
        return redisAtomicInteger.incrementAndGet();
    }

    public Boolean exist(String key) {
        return redisTemplate.hasKey(key);
    }

    public void remove(String key) {
        redisTemplate.delete(key);
    }
}