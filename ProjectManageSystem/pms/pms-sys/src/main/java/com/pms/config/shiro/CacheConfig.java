package com.pms.config.shiro;

import lombok.extern.slf4j.Slf4j;
import net.sf.ehcache.CacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.slf4j.Logger;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;


@Slf4j
@Configuration
public class CacheConfig {

    private Logger log = org.slf4j.LoggerFactory.getLogger(getClass());

    @Bean()
    @ConditionalOnProperty(prefix = "pms.shiro", havingValue = "ehcacheManager", name = "cacheManager")
    public EhCacheManager ehCacheManager() {
        log.info("[CacheConfig#ehCacheManager] ---> init ehCacheManager");
        CacheManager cacheManager = CacheManager.getCacheManager("pms");
        EhCacheManager ehCacheManager = new EhCacheManager();
        ehCacheManager.setCacheManager(cacheManager);
        ehCacheManager.setCacheManagerConfigFile("classpath:cache/ehcache-shiro.xml");
        return ehCacheManager;
    }

    @Bean
    @ConditionalOnProperty(prefix = "pms.shiro", havingValue = "redisCacheManager", name = "cacheManager")
    public RedisCacheManager redisCacheManager(RedisTemplate<String, String> redisTemplate) {
        log.info("[CacheConfig#redisCacheManager] ---> init redisCacheManager");
        return new RedisCacheManager(redisTemplate);
    }
}
