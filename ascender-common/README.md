# Common Module

## Overview
The Common Module is a shared library that provides common functionality, models, and utilities used across all microservices in the Ascender application. It helps ensure consistency and reduces code duplication across services.

## Features
- Common DTOs for data transfer between services
- Shared entity models
- Exception handling
- Feign client interfaces for inter-service communication
- Common configuration for Eureka clients

## Components

### Models
- `BaseEntity`: Base class for all entities with common fields like ID, createdAt, and updatedAt

### DTOs
- `GameDto`: Data Transfer Object for Game entities

### Exceptions
- `AscenderException`: Base exception class for all application exceptions
- `ResourceNotFoundException`: Exception thrown when a requested resource is not found
- `BadRequestException`: Exception thrown when a request contains invalid data

### Clients
- `GameClient`: Feign client interface for communicating with the Games Service

### Configuration
- `EurekaClientConfig`: Common configuration for Eureka clients
- `FeignClientConfig`: Common configuration for Feign clients

## Dependencies
- Spring Boot
- Spring Data JPA
- Spring Cloud Netflix Eureka Client
- Spring Cloud OpenFeign
- Lombok
- JWT for authentication

## Usage
To use this module in other services, add it as a dependency in your build.gradle file:

```gradle
dependencies {
    implementation project(':ascender-common')
}
```

Then you can import and use the components as needed:

```java
import kz.saya.labs.ascender.common.model.BaseEntity;
import kz.saya.labs.ascender.common.dto.GameDto;
import kz.saya.labs.ascender.common.client.GameClient;
import kz.saya.labs.ascender.common.exception.ResourceNotFoundException;
```