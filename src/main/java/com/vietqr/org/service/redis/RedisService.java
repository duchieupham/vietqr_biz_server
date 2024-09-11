package com.vietqr.org.service.redis;

import org.springframework.stereotype.Service;

@Service
public interface RedisService {
    public boolean insertObject(String key, Object value, int duration);

    public Object findByKey(String key);

    public boolean deleteByKey(String key);
}
