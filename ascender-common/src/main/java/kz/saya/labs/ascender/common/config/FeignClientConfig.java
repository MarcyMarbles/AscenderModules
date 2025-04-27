package kz.saya.labs.ascender.common.config;

import feign.Logger;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Common configuration for Feign clients.
 * This configuration can be imported by all microservices.
 */
@Configuration
@EnableFeignClients(basePackages = "kz.saya.labs.ascender")
public class FeignClientConfig {

    /**
     * Sets the logging level for Feign clients.
     * FULL level logs the headers, body, and metadata for both requests and responses.
     */
    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}