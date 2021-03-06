package com.idle.game.config;

import static com.idle.game.constant.CacheConstants.PVPRATING_FIND_PVP_RATINGS;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 *
 * @author rafael
 */
@Configuration
@EnableCaching
@Profile({"default"})
public class RedisConfiguration {

    @Value("${idle.config.redis.hostname}")
    private String redisHostName;

    @Value("${idle.config.redis.port}")
    private int redisPort;

    @Value("${idle.config.pvpRating.expire}")
    private Long pvpRatingCache;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        JedisConnectionFactory factory = new JedisConnectionFactory();
        factory.setHostName(redisHostName);
        factory.setPort(redisPort);
        factory.setUsePool(true);
        return factory;
    }

    @Bean
    RedisTemplate<Object, Object> redisTemplate() {
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(jedisConnectionFactory());
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        return redisTemplate;
    }

//    @Bean
//    RedisCacheManager cacheManager() {
//        RedisCacheManager redisCacheManager = RedisCacheManager.create(jedisConnectionFactory());
//
//        Map<String, Long> cacheExpiresPolice = new HashMap<>();
//
//        cacheExpiresPolice.put(PVPRATING_FIND_PVP_RATINGS, pvpRatingCache);
//
//        redisCacheManager.setExpires(cacheExpiresPolice);
//        return redisCacheManager;
//    }
}
