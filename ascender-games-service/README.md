# Games Service

## Overview
The Games Service is a microservice responsible for managing game-related operations in the Ascender application. It provides APIs for creating, reading, updating, and deleting game entities, as well as retrieving games that support scrims.

## Features
- CRUD operations for games
- Filtering games by criteria (e.g., scrimable)
- Integration with Eureka Server for service discovery
- OpenFeign client for inter-service communication

## API Endpoints

### Get All Games
```
GET /api/games
```
Returns a list of all games.

### Get Game by ID
```
GET /api/games/{id}
```
Returns a specific game by its ID.

### Create Game
```
POST /api/games
```
Creates a new game.

### Update Game
```
PUT /api/games/{id}
```
Updates an existing game.

### Delete Game
```
DELETE /api/games/{id}
```
Deletes a game.

### Get Scrimable Games
```
GET /api/games/scrimable
```
Returns a list of games that support scrims.

## Configuration
The service is configured to run on port 8081 and register with Eureka Server. It uses a PostgreSQL database for persistence.

## Dependencies
- Spring Boot
- Spring Data JPA
- Spring Cloud Netflix Eureka Client
- Spring Cloud OpenFeign
- PostgreSQL
- Lombok

## Building and Running
To build and run the service:

```bash
./gradlew :ascender-games-service:bootRun
```

Make sure the Eureka Server is running before starting this service.