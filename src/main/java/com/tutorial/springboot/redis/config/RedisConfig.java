package com.tutorial.springboot.redis.config;

import java.time.Duration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.cache.interceptor.SimpleCacheErrorHandler;
import org.springframework.cache.jcache.config.JCacheConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.CacheKeyPrefix;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
@EnableCaching
@Slf4j
public class RedisConfig extends JCacheConfigurerSupport {

  /**
   * Jedisの設定
   *
   * @return Jedisの設定
   */
  @Bean
  public RedisConnectionFactory connectionFactory() {
    JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
    JedisClientConfiguration clientConfig = JedisClientConfiguration.builder()
        .connectTimeout(Duration.ofMillis(100))
        .readTimeout(Duration.ofMillis(100))
        .usePooling()
        .poolConfig(jedisPoolConfig)
        .build();
    RedisStandaloneConfiguration standaloneConfiguration = new RedisStandaloneConfiguration();
    return new JedisConnectionFactory(standaloneConfiguration, clientConfig);
  }

  /**
   * Springboot cacheManagerの設定
   *
   * @param connectionFactory Jedisの設定
   * @return RedisCacheManager
   */
  @Bean
  public RedisCacheManager cacheManager(RedisConnectionFactory connectionFactory) {
    CacheKeyPrefix cacheKeyPrefix = cacheName -> "tutorial::" + cacheName + "::";
    RedisCacheConfiguration redisCacheConfig = RedisCacheConfiguration
        .defaultCacheConfig()
        .disableCachingNullValues()
        .computePrefixWith(cacheKeyPrefix)
        .serializeValuesWith(
            RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()))
        .entryTtl(Duration.ofMinutes(20));
    return RedisCacheManager.RedisCacheManagerBuilder
        .fromConnectionFactory(connectionFactory)
        .cacheDefaults(redisCacheConfig).build();
  }

  @Override
  public CacheErrorHandler errorHandler() {
    return new SimpleCacheErrorHandler() {
      @Override
      public void handleCacheGetError(RuntimeException exception, Cache cache, Object key) {
        log.warn(exception.getMessage(), exception);
      }
      @Override
      public void handleCachePutError(RuntimeException exception, Cache cache, Object key,
          Object value) {
        log.warn(exception.getMessage(), exception);
      }
      @Override
      public void handleCacheEvictError(RuntimeException exception, Cache cache, Object key) {
        log.warn(exception.getMessage(), exception);
      }
      @Override
      public void handleCacheClearError(RuntimeException exception, Cache cache) {
        log.warn(exception.getMessage(), exception);
      }
    };
  }
}
