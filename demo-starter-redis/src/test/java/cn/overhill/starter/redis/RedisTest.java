package cn.overhill.starter.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RedisTest {


    @Autowired
    DynamicRedisTemplate<String,String> redisTemplate;


    @Test
    public void insert(){
        redisTemplate.opsForValue().set("selectdb","1");
        System.out.println(redisTemplate.opsForValue().get("selectdb"));

        RedisThreadLocal.set(2);
        redisTemplate.opsForValue().set("selectdb","2");
        System.out.println(redisTemplate.opsForValue().get("selectdb"));

    }


}
