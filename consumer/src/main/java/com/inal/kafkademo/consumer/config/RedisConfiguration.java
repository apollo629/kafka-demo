package com.iyzipay.marketplace.api.infrastructure.configuration;

import io.lettuce.core.ClientOptions;
import io.lettuce.core.SocketOptions;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
import java.util.HashSet;

@Configuration
public class RedisConfiguration {

//    @Bean
//    @ConditionalOnProperty(value = "redis.sentinel", havingValue = "true")
//    LettuceConnectionFactory lettuceConnectionFactory(RedisProperties redisProperties) {
//        RedisSentinelConfiguration config = new RedisSentinelConfiguration(redisProperties.getSentinel().getMaster(), new HashSet<>(redisProperties.getSentinel().getNodes()));
//        config.setPassword(RedisPassword.of(redisProperties.getPassword()));
//        SocketOptions socketOptions = SocketOptions.builder()
//                .connectTimeout(Duration.ofMillis(2000))
//                .build();
//        ClientOptions clientOptions = ClientOptions.builder()
//                .socketOptions(socketOptions)
//                .cancelCommandsOnReconnectFailure(true)
//                .disconnectedBehavior(ClientOptions.DisconnectedBehavior.REJECT_COMMANDS)
//                .build();
//        LettuceClientConfiguration lettuceClientConfiguration = LettuceClientConfiguration.builder()
//                .commandTimeout(Duration.ofMillis(2000))
//                .clientOptions(clientOptions)
//                .build();
//        return new LettuceConnectionFactory(config, lettuceClientConfiguration);
//    }
//
//    @Bean
//    @ConditionalOnProperty(value = "redis.sentinel", havingValue = "false")
//    LettuceConnectionFactory lettuceConnectionFactory() {
//        return new LettuceConnectionFactory();
//    }
//
//    @Bean
//    public RedisTemplate<String, String> redisTemplate(LettuceConnectionFactory lettuceConnectionFactory) {
//        RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
//        redisTemplate.setConnectionFactory(lettuceConnectionFactory);
//        StringRedisSerializer serializer = new StringRedisSerializer();
//        redisTemplate.setKeySerializer(serializer);
//        redisTemplate.setValueSerializer(serializer);
//        return redisTemplate;
//    }
}