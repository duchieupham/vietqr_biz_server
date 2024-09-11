package com.vietqr.org.service.redis;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class RedisServiceImpl implements RedisService {
    private final RedisTemplate<String, Object> redisTemplate;

    public RedisServiceImpl(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public boolean insertObject(String key, Object value, int duration) {
        boolean result = false;
        try {
            result = Boolean.TRUE.equals(redisTemplate
                    .opsForValue().setIfAbsent(key, value, Duration.ofSeconds(duration)));
        } catch (Exception e) {
            throw new RuntimeException("E05");
        }

        return result;
    }

    @Override
    public Object findByKey(String key) {
        Object result = null;
        try {
            result = redisTemplate.opsForValue().get(key);
        } catch (Exception e) {
            throw new RuntimeException("E05");
        }

        return result;
    }

    @Override
    public boolean deleteByKey(String key) {
        boolean result = false;
        try {
            redisTemplate.delete(key);
            result = true;
        } catch (Exception e) {
            throw new RuntimeException("E05");
        }

        return result;
    }
}
