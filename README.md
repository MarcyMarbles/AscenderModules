# Ascender - Microservices Architecture

## Overview
Ascender is a platform for esports teams and players, now refactored into a microservices architecture. This architecture provides better scalability, maintainability, and resilience compared to the previous monolithic approach.

## Architecture
The application is split into the following components:

### Core Infrastructure
- **Eureka Server**: Service registry for service discovery
- **API Gateway**: Entry point for all client requests, handles routing and load balancing
- **Common Module**: Shared code, models, and utilities used across all microservices

### Domain Services
- **Games Service**: Manages game-related operations
- **Player Service**: Manages player profiles
- **Team Service**: Manages teams and team membership
- **Tournament Service**: Manages tournaments and tournament-related operations
- **Scrim Service**: Manages scrims (practice matches)
- **Auth Service**: Handles authentication and authorization

## Technology Stack
- **Java 24**: Programming language
- **Spring Boot 3.4.5**: Application framework
- **Spring Cloud**: Microservices ecosystem
- **Spring Data JPA**: Data access layer
- **PostgreSQL**: Database
- **Gradle**: Build tool
- **Netflix Eureka**: Service discovery
- **Spring Cloud Gateway**: API Gateway
- **OpenFeign**: Declarative REST client
- **Lombok**: Reduces boilerplate code

## Project Structure
```
ascender/
├── ascender-common/            # Shared code and utilities
├── ascender-eureka-server/     # Service registry
├── ascender-gateway/           # API Gateway
├── ascender-auth-service/      # Authentication service
├── ascender-games-service/     # Games management service
├── ascender-player-service/    # Player profile service
├── ascender-team-service/      # Team management service
├── ascender-tournament-service/# Tournament management service
├── ascender-scrim-service/     # Scrim management service
└── build.gradle                # Root build file
```

## Getting Started

### Prerequisites
- Java 24 or higher
- PostgreSQL
- Gradle

### Building and Running
1. Clone the repository
2. Start the Eureka Server:
   ```
   ./gradlew :ascender-eureka-server:bootRun
   ```
3. Start the API Gateway:
   ```
   ./gradlew :ascender-gateway:bootRun
   ```
4. Start the required microservices:
   ```
   ./gradlew :ascender-games-service:bootRun
   ./gradlew :ascender-player-service:bootRun
   # etc.
   ```

### Accessing Services
- Eureka Dashboard: http://localhost:8761
- API Gateway: http://localhost:9090
- Games Service (via Gateway): http://localhost:9090/api/games

## Inter-Service Communication
Services communicate with each other using:
1. **OpenFeign**: For synchronous REST calls
2. **Service Discovery**: Services locate each other via Eureka

## Database Strategy
Each microservice has its own database to ensure loose coupling:
- Games Service: ascender_games
- Player Service: ascender_players
- Team Service: ascender_teams
- Tournament Service: ascender_tournaments
- Scrim Service: ascender_scrims
- Auth Service: ascender_auth

## Future Enhancements
- Implement message queues for asynchronous communication
- Add circuit breakers for fault tolerance
- Implement distributed tracing
- Set up centralized logging
- Containerize services with Docker
- Implement Kubernetes for orchestration