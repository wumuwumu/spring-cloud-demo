package cn.overhill.starter.redis;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.RedisSystemException;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class AbstractRouteTemplate<k,v> extends RedisTemplate<k,v> implements InitializingBean {

    Map<Object,RedisTemplate<k,v>> redisTemplateMap = new ConcurrentHashMap<>();


    public RedisTemplate<k, v> getDefaultRedisTemplate() {
        return defaultRedisTemplate;
    }

    public void setDefaultRedisTemplate(RedisTemplate<k, v> defaultRedisTemplate) {
        this.defaultRedisTemplate = defaultRedisTemplate;
    }

    RedisTemplate<k,v> defaultRedisTemplate;

    public RedisTemplate<k,v> determineTargetRedisTemplate(){
        Object lookupKey = determineCurrentLookUpKey();
        if(lookupKey == null){
            return defaultRedisTemplate;
        }
        RedisTemplate<k,v> redisTemplate = redisTemplateMap.get(lookupKey);
        if(redisTemplate == null){
            redisTemplate = createRedisTemplate(lookupKey);
            redisTemplateMap.put(lookupKey,redisTemplate);
        }
        return redisTemplate;
    }

    protected abstract RedisTemplate<k,v> createRedisTemplate(Object lookupKey);

    abstract Object determineCurrentLookUpKey();

    @Override
    public SetOperations<k, v> opsForSet() {
        return determineTargetRedisTemplate().opsForSet();
    }

    @Override
    public ValueOperations<k, v> opsForValue() {
        return determineTargetRedisTemplate().opsForValue();
    }

    @Override
    public RedisConnectionFactory getConnectionFactory() {
        return determineTargetRedisTemplate().getConnectionFactory();
    }

    @Override
    public RedisConnectionFactory getRequiredConnectionFactory() {
        return determineTargetRedisTemplate().getRequiredConnectionFactory();
    }

    @Override
    public void setConnectionFactory(RedisConnectionFactory connectionFactory) {
        determineTargetRedisTemplate().setConnectionFactory(connectionFactory);
    }
}
