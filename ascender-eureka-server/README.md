# Eureka Server

## Overview
The Eureka Server is a service registry that allows microservices to discover and communicate with each other without hardcoding the hostname and port. It is a key component of the Ascender microservices architecture.

## Features
- Service registration and discovery
- Health monitoring of registered services
- Load balancing support
- Fault tolerance

## Configuration
The Eureka Server is configured to run on port 8761, which is the default port for Eureka. It is configured not to register itself as a client.

Key configuration properties:
```properties
server.port=8761
eureka.client.register-with-eureka=false
eureka.client.fetch-registry=false
eureka.instance.hostname=localhost
eureka.client.service-url.defaultZone=http://${eureka.instance.hostname}:${server.port}/eureka/
eureka.server.enable-self-preservation=false
```

## Dependencies
- Spring Boot
- Spring Cloud Netflix Eureka Server

## Building and Running
To build and run the Eureka Server:

```bash
./gradlew :ascender-eureka-server:bootRun
```

Once running, you can access the Eureka dashboard at:
```
http://localhost:8761
```

## Usage
Other microservices can register with the Eureka Server by including the Eureka Client dependency and configuring their `application.properties` file:

```properties
eureka.client.service-url.defaultZone=http://localhost:8761/eureka/
eureka.instance.prefer-ip-address=true
```

Services can then discover and communicate with each other using the service name instead of hardcoded URLs.