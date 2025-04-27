# Ascender Player Service

## Overview
The Player Service is a microservice responsible for managing player profiles in the Ascender application. It provides APIs for creating, updating, retrieving, and deleting player profiles, as well as searching for players based on various criteria.

## Features
- CRUD operations for player profiles
- Search for players by skill level
- Find players looking for a team
- Find a player by user ID
- Update player avatar and background images

## API Endpoints

### Get all players
```
GET /api/players
```

### Get a player by ID
```
GET /api/players/{id}
```

### Create a new player
```
POST /api/players
```

### Update a player
```
PUT /api/players/{id}
```

### Delete a player
```
DELETE /api/players/{id}
```

### Find players by skill level
```
GET /api/players/skill/{skillLevel}
```

### Find players looking for a team
```
GET /api/players/looking-for-team
```

### Find a player by user ID
```
GET /api/players/user/{userId}
```

### Update a player's avatar
```
PUT /api/players/{id}/avatar/{avatarId}
```

### Update a player's profile background
```
PUT /api/players/{id}/background/{backgroundId}
```

## Configuration
The service is configured to use:
- Spring Boot 3.1.0
- Spring Cloud for service discovery (Eureka)
- Spring Cloud OpenFeign for inter-service communication
- Spring Data JPA for database access
- PostgreSQL as the database

## Dependencies
- ascender-common: Shared code, models, and utilities
- Spring Boot Starter Web
- Spring Boot Starter Data JPA
- Spring Cloud Starter Netflix Eureka Client
- Spring Cloud Starter OpenFeign
- Spring Boot Starter Validation
- SpringDoc OpenAPI UI
- Lombok
- PostgreSQL