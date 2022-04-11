package com.rb.alwaysontheroad.empty.config;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

import static io.lettuce.core.ReadFrom.REPLICA_PREFERRED;

@EnableCaching
@Configuration
@RequiredArgsConstructor
public class CacheConfig {
    private final ApplicationProps applicationProps;

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        ApplicationProps.Redis redis = applicationProps.getRedis();

        LettuceClientConfiguration clientConfig = LettuceClientConfiguration.builder()
                .readFrom(REPLICA_PREFERRED)
                .build();

        return new LettuceConnectionFactory(
                new RedisStandaloneConfiguration(redis.getHost(), redis.getPort()),
                clientConfig
        );
    }
}
