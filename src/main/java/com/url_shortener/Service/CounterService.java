package com.url_shortener.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class CounterService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    public Long incrementCounter(String key) {
        // Equivalent to Redis INCR command
        return redisTemplate.opsForValue().increment(key);
    }
}
