package kz.saya.labs.ascender.common.config;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Configuration;

/**
 * Common configuration for Eureka clients.
 * This configuration can be imported by all microservices.
 */
@Configuration
@EnableDiscoveryClient
public class EurekaClientConfig {
    // The @EnableDiscoveryClient annotation is sufficient to register with Eureka
    // Additional configuration can be added here if needed
}