# API Gateway

## Overview
The API Gateway is the entry point for all client requests to the Ascender microservices. It routes requests to the appropriate microservice, provides load balancing, and can handle cross-cutting concerns like authentication, logging, and monitoring.

## Features
- Request routing to appropriate microservices
- Load balancing
- Service discovery integration with Eureka
- Centralized error handling
- Monitoring and metrics via Actuator

## Routes
The API Gateway is configured with the following routes:

| Route | Service | Path |
|-------|---------|------|
| auth-service | ascender-auth-service | /api/auth/** |
| games-service | ascender-games-service | /api/games/** |
| player-service | ascender-player-service | /api/players/** |
| team-service | ascender-team-service | /api/teams/** |
| tournament-service | ascender-tournament-service | /api/tournaments/** |
| scrim-service | ascender-scrim-service | /api/scrims/** |

## Configuration
The API Gateway is configured to run on port 9090 and register with Eureka Server.

Key configuration properties:
```properties
server.port=9090
eureka.client.service-url.defaultZone=http://localhost:8761/eureka/
eureka.instance.prefer-ip-address=true
spring.cloud.gateway.discovery.locator.enabled=true
spring.cloud.gateway.discovery.locator.lower-case-service-id=true
```

## Dependencies
- Spring Boot
- Spring Cloud Gateway
- Spring Cloud Netflix Eureka Client
- Spring Boot Actuator

## Building and Running
To build and run the API Gateway:

```bash
./gradlew :ascender-gateway:bootRun
```

Make sure the Eureka Server is running before starting the API Gateway.

## Usage
Clients can access the microservices through the API Gateway using the configured routes. For example:

```
http://localhost:9090/api/games
```

This request will be routed to the games-service.