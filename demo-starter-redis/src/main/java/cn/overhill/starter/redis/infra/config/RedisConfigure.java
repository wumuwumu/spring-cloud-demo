package cn.overhill.starter.redis.infra.config;

import cn.overhill.starter.redis.DynamicRedisTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

@Configuration
public class RedisConfigure {

    @Bean
    public DynamicRedisTemplate dynamicRedisTemplate(StringRedisTemplate stringRedisTemplate){
        DynamicRedisTemplate dynamicRedisTemplate = new DynamicRedisTemplate();
        dynamicRedisTemplate.setDefaultRedisTemplate(stringRedisTemplate);
        return dynamicRedisTemplate;
    }
}
