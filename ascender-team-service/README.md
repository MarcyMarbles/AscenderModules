# Ascender Team Service

## Overview
The Team Service is a microservice responsible for managing teams and team membership in the Ascender application. It provides APIs for creating, updating, retrieving, and deleting teams, as well as managing team membership and join requests.

## Features
- CRUD operations for teams
- Team membership management
- Join request processing
- Search for teams by game, creator, or player
- Votekick functionality

## API Endpoints

### Team Endpoints

#### Get all teams
```
GET /api/teams
```

#### Get a team by ID
```
GET /api/teams/{id}
```

#### Create a new team
```
POST /api/teams
```

#### Update a team
```
PUT /api/teams/{id}
```

#### Delete a team
```
DELETE /api/teams/{id}
```

#### Add a player to a team
```
POST /api/teams/{id}/players
```

#### Remove a player from a team
```
DELETE /api/teams/{teamId}/players/{playerId}
```

#### Find teams by game
```
GET /api/teams/game/{gameId}
```

#### Find teams by creator
```
GET /api/teams/creator/{creatorId}
```

#### Find teams by player
```
GET /api/teams/player/{playerId}
```

#### Initiate a votekick
```
POST /api/teams/{teamId}/votekick/{targetId}
```

### Join Request Endpoints

#### Get all join requests
```
GET /api/join-requests
```

#### Get a join request by ID
```
GET /api/join-requests/{id}
```

#### Create a new join request
```
POST /api/join-requests
```

#### Update a join request
```
PUT /api/join-requests/{id}
```

#### Delete a join request
```
DELETE /api/join-requests/{id}
```

#### Find join requests by team
```
GET /api/join-requests/team/{teamId}
```

#### Find pending join requests by team
```
GET /api/join-requests/team/{teamId}/pending
```

#### Find join requests by player
```
GET /api/join-requests/player/{playerId}
```

#### Find pending join requests by player
```
GET /api/join-requests/player/{playerId}/pending
```

#### Accept a join request
```
PUT /api/join-requests/{id}/accept
```

#### Reject a join request
```
PUT /api/join-requests/{id}/reject
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