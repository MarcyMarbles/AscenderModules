# Microservices Architecture Plan for Ascender

## Overview
This document outlines the plan for splitting the Ascender application into microservices. The goal is to create a more scalable, maintainable, and resilient system.

## Microservices

### 1. ascender-common
**Purpose**: Shared code, models, and utilities used across all microservices.
**Components**:
- Common DTOs
- Utility classes
- Exception handling
- Common interfaces
- Security utilities

### 2. ascender-gateway
**Purpose**: API Gateway that routes requests to appropriate microservices.
**Components**:
- Request routing
- Load balancing
- Authentication filter
- Rate limiting
- Request/response logging
- Swagger API documentation aggregation

### 3. ascender-auth-service
**Purpose**: Handles authentication and authorization.
**Components**:
- User registration and login
- JWT token generation and validation
- Role-based access control
- User management
- Password reset functionality

### 4. ascender-games-service
**Purpose**: Manages game-related operations.
**Entities**:
- Games
**Responsibilities**:
- CRUD operations for games
- Game metadata management
- Filtering games by criteria (e.g., scrimable)

### 5. ascender-player-service
**Purpose**: Manages player profiles.
**Entities**:
- PlayerProfile
**Responsibilities**:
- CRUD operations for player profiles
- Player profile search and filtering
- Avatar and background image management
- Player statistics

### 6. ascender-team-service
**Purpose**: Manages teams and team membership.
**Entities**:
- Team
- JoinRequest (team-related)
**Responsibilities**:
- CRUD operations for teams
- Team membership management
- Team search and filtering
- Team logo and background image management
- Votekick functionality

### 7. ascender-tournament-service
**Purpose**: Manages tournaments and tournament-related operations.
**Entities**:
- Tournament
- TournamentMatch
- TournamentTeamScore
- JoinRequest (tournament-related)
**Responsibilities**:
- CRUD operations for tournaments
- Tournament registration
- Bracket generation and management
- Match scheduling and results
- Tournament team management

### 8. ascender-scrim-service
**Purpose**: Manages scrims (practice matches).
**Entities**:
- Scrim
- ScrimRequest
- MatchHistory
- TabData
**Responsibilities**:
- CRUD operations for scrims
- Scrim request management
- Match history tracking
- Scrim search and filtering

## Inter-Service Communication

The microservices will communicate with each other using:
1. **Synchronous REST calls** for immediate responses
2. **Asynchronous messaging** for event-driven communication (using a message broker like RabbitMQ or Kafka)

## Database Strategy

Each microservice will have its own database to ensure loose coupling. For entities that need to be shared across services:
1. **Database per service**: Each service has its own database with the entities it owns
2. **Data duplication**: Essential data from other services is duplicated and kept in sync via events
3. **Service calls**: For non-essential data, services call other services to get the data they need

## Deployment Strategy

The microservices will be deployed as Docker containers, orchestrated with Kubernetes or Docker Compose.

## Security

1. **API Gateway**: Authenticates requests and forwards them to internal services
2. **JWT Tokens**: Used for authentication between services
3. **HTTPS**: All external communication uses HTTPS
4. **Service-to-Service Authentication**: Services authenticate with each other

## Monitoring and Logging

1. **Centralized Logging**: Using ELK stack (Elasticsearch, Logstash, Kibana)
2. **Metrics Collection**: Using Prometheus and Grafana
3. **Distributed Tracing**: Using Jaeger or Zipkin

## Implementation Phases

1. **Phase 1**: Set up project structure and common module
2. **Phase 2**: Implement auth-service and gateway
3. **Phase 3**: Implement core domain services (games, player, team)
4. **Phase 4**: Implement complex domain services (tournament, scrim)
5. **Phase 5**: Set up monitoring, logging, and production deployment