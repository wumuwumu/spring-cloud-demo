package cn.overhill.starter.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.cache.CacheProperties;

@SpringBootApplication
public class RedisApplication {
    public static void main(String[] args){
        SpringApplication.run(CacheProperties.Redis.class);
    }
}
