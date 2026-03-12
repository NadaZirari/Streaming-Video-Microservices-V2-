# Streaming Video Microservices Backend

This project is a video streaming backend built with Spring Cloud microservices. It manages users, video metadata, watchlists, and watch history.

## Architecture

1.  **`config-service`**: Centralized configuration management using Spring Cloud Config.
2.  **`discovery-service`**: Service registry and discovery using Netflix Eureka.
3.  **`gateway-service`**: Unified API entry point using Spring Cloud Gateway.
4.  **`video-service`**: Manages video metadata (CRUD operations).
5.  **`user-service`**: Manages users, watchlists, and watch history. Communicates with `video-service` via OpenFeign.

## Technologies

-   **Java 17**
-   **Spring Boot 3.2.2**
-   **Spring Cloud (Eureka, Config, Gateway, OpenFeign)**
-   **Spring Data JPA**
-   **H2 Database** (per service)
-   **MapStruct** & **Lombok**
-   **Docker** & **Docker Compose**

## How to Run

### Prerequisites
- Build tools (Maven) and Docker installed.

### Build and Launch
1.  Build all services with Maven:
    ```bash
    mvn clean package -DskipTests
    ```
2.  Start the infrastructure and services using Docker Compose:
    ```bash
    docker-compose up --build
    ```

## API Documentation (via Gateway - Port 8060)

### Video Service (`/api/videos`)
- `GET /api/videos`: List all videos.
- `GET /api/videos/{id}`: Get video details.
- `POST /api/videos`: Create a new video.
- `PUT /api/videos/{id}`: Update a video.
- `DELETE /api/videos/{id}`: Delete a video.

### User Service (`/api/users`)
- `GET /api/users`: List all users.
- `POST /api/users`: Create a new user.
- `GET /api/users/{id}/watchlist`: Get user's watchlist.
- `POST /api/users/{userId}/watchlist/{videoId}`: Add video to watchlist (validates video existence via `video-service`).
- `DELETE /api/users/{userId}/watchlist/{videoId}`: Remove video from watchlist.
- `POST /api/users/{userId}/history`: Record watch history.
- `GET /api/users/{userId}/stats`: Get user watching statistics.

## Testing with Postman

A Postman collection can be configured to point to `http://localhost:8060/` for all requests, using the service paths defined above.