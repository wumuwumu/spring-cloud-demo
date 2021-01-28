package cn.overhill.starter.redis;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.lettuce.core.TimeoutOptions;
import io.lettuce.core.resource.ClientResources;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.boot.autoconfigure.data.redis.LettuceClientConfigurationBuilderCustomizer;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.data.redis.connection.RedisConfiguration;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.time.Duration;
import java.util.function.Supplier;


public class DynamicRedisTemplate<k, v> extends AbstractRouteTemplate<k, v> {

    public DynamicRedisTemplate() {

    }

    @Autowired
    RedisProperties redisProperties;

    @Override
    protected RedisTemplate<k, v> createRedisTemplate(Object lookupKey) {
        Integer db = (Integer) lookupKey;

        return createConnectionFactory(db);

    }

    public RedisTemplate createConnectionFactory(int db) {
        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
        configuration.setHostName(String.valueOf(redisProperties.getHost()));
        configuration.setPort(Integer.parseInt(String.valueOf(redisProperties.getPort())));
        configuration.setDatabase(db);
        String password = redisProperties.getPassword();
        if (password != null && !"".equals(password)) {
            RedisPassword redisPassword = RedisPassword.of(password);
            configuration.setPassword(redisPassword);
        }

        //池配置
        GenericObjectPoolConfig genericObjectPoolConfig = new GenericObjectPoolConfig();

        RedisProperties.Pool pool = redisProperties.getLettuce().getPool();

        genericObjectPoolConfig.setMaxIdle(pool.getMaxIdle());
        genericObjectPoolConfig.setMaxTotal(pool.getMaxActive());
        genericObjectPoolConfig.setMinIdle(pool.getMinIdle());
        if (pool.getMaxWait() != null) {
            genericObjectPoolConfig.setMaxWaitMillis(pool.getMaxWait().toMillis());
        }


        Supplier<LettuceConnectionFactory> lettuceConnectionFactorySupplier = () -> {
            LettuceConnectionFactory factory;
            LettucePoolingClientConfiguration.LettucePoolingClientConfigurationBuilder builder = LettucePoolingClientConfiguration.builder();
            Duration shutdownTimeout = redisProperties.getLettuce().getShutdownTimeout();

            if (shutdownTimeout != null) {
                builder.shutdownTimeout(shutdownTimeout);
            }
            LettuceClientConfiguration clientConfiguration = builder.poolConfig(genericObjectPoolConfig).build();
            factory = new LettuceConnectionFactory(configuration, clientConfiguration);
            return factory;
        };

        LettuceConnectionFactory lettuceConnectionFactory = lettuceConnectionFactorySupplier.get();
        lettuceConnectionFactory.afterPropertiesSet();

        // 定义RedisTemplate对象
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(lettuceConnectionFactory);

        RedisSerializer stringRedisSerializer = null;
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.activateDefaultTyping(om.getPolymorphicTypeValidator(), ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        stringRedisSerializer = new StringRedisSerializer();
        // key采用String的序列化方式，value采用json序列化方式
        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setHashKeySerializer(stringRedisSerializer);
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }


    @Override
    Object determineCurrentLookUpKey() {
        return RedisThreadLocal.get();
    }
}
