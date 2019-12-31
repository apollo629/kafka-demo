package com.inal.kafkademo.consumer.lock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class LockService {

    private static final Logger logger = LoggerFactory.getLogger(LockService.class);

    private LockRepository lockRepository;

    public LockService(LockRepository lockRepository) {
        this.lockRepository = lockRepository;
    }

    public void lock(String key) {
        try {
            Integer value = lockRepository.increment(RedisKeyGenerator.generateLockKey(key), 5, TimeUnit.SECONDS);
            if (!new Integer(1).equals(value)) {
                throw new LockedException();
            }
        } catch (LockedException lockedException) {
            throw lockedException;
        } catch (Exception e) {
            logger.error("Redis error occurred", e);
        }
    }

    public Boolean isLocked(String key) {
        try {
            return lockRepository.exist(RedisKeyGenerator.generateLockKey(key));
        } catch (Exception e) {
            logger.error("Redis error occurred", e);
            return false;
        }
    }

    public void unlock(Exception exception, String key) {
        if (!LockedException.class.isAssignableFrom(exception.getClass())) {
            unlock(key);
        }
    }

    public void unlock(String key) {
        try {
            lockRepository.remove(RedisKeyGenerator.generateLockKey(key));
        } catch (Exception e) {
            logger.error("Redis error occurred", e);
        }
    }
}