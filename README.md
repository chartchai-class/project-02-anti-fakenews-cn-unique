# Social Anti-Fake News System (Spring Boot Backend)

## Team Info

- **Team Name:** uniqu
- **Members:**
  - **Student ID:** 20232081, **Name:** liuwenhao, **Contribution rate:** 40%
  - **Student ID:** 20232054, **Name:** wangyuzhe, **Contribution rate:** 30%
  - **Student ID:** 20232097, **Name:** wangpengyue, **Contribution rate:** 30%

## Project Description

This is the backend service for the Social Anti-Fake News System. It provides a RESTful API to manage news articles, user votes, and comments. It is built with Spring Boot and uses a MySQL database for data persistence.
Please note: This version of the project focuses on core functionalities. User authentication, registration, and authorization features (e.g., JWT) have been simplified/removed for this specific version.

## Live Deployment

- **Backend API:** http://59.45.10.246:3292
- **MySQL Database:** mysql://59.45.10.246:3293 (for direct access, e.g., using a client)
- **phpMyAdmin:** http://59.45.10.246:3294

## API Features

- **News Management:**
  - `GET /api/news`: Fetches a paginated list of all news articles.
  - `GET /api/news/{id}`: Retrieves a single news article by its ID.
  - `POST /api/news`: Adds a new news article to the database.
    ```json
    {
        "topic": "Example News Topic",
        "shortDescription": "A brief summary of the news.",
        "fullContent": "The detailed content of the news article.",
        "reporter": "John Doe",
        "imageUrl": "http://example.com/image.jpg"
    }
    ```
- **Voting:**
  - `POST /api/news/{newsId}/votes`: Submits a vote for a specific news article.
    ```json
    {
        "isFake": true,
        "comment": "This information seems fabricated.",
        "imageUrl": "http://example.com/proof.jpg",
        "voter": "Anonymous"
    }
    ```

## Tech Stack

- **Java 17**
- **Spring Boot 3.2.5**
- **Spring Web:** For building RESTful APIs.
- **Spring Data JPA:** For data persistence and repository management.
- **MySQL:** The relational database for storing data.
- **Lombok:** To reduce boilerplate code.
- **Maven:** For project and dependency management.

## Local Development

### Prerequisites

- Java 17 or later
- Maven 3.x
- Docker and Docker Compose (for running a local MySQL instance)

### Setup

1.  **Configure Database:**
    - The application expects database credentials via environment variables (e.g., `SPRING_DATASOURCE_USERNAME`, `SPRING_DATASOURCE_PASSWORD`).
    - For local development, it's recommended to run MySQL via Docker Compose.
    - The `docker-compose.yml` in the project root defines a `db` service with a `MYSQL_ROOT_PASSWORD` set.
    - Ensure your application's environment variables (or local `application.properties`/`application.yml`) match these.
    - The database name is `antifakenews_db` (as per `application-prod.properties`). Spring Boot can create the schema automatically on startup.

2.  **Run the application:**
    - You can run the application using your IDE by running the `main` method in `Application.java`. Ensure appropriate environment variables are set for your IDE run configuration.
    - Alternatively, you can use Maven:
      ```bash
      mvn spring-boot:run
      ```
    - The API server will start on `http://localhost:8080`.

## Directory Structure

- `src/main/java/se331/lab/Application.java`: The main Spring Boot application entry point.
- `src/main/java/se331/lab/news/controller/NewsController.java`: Defines the REST API endpoints.
- `src/main/java/se331/lab/news/service/NewsService.java`: Contains the core business logic.
- `src/main/java/se331/lab/news/repository/`: Spring Data JPA repositories for database interaction.
- `src/main/java/se331/lab/news/entity/`: JPA entity classes (`News.java`, `Vote.java`).
- `src/main/java/se331/lab/util/DataLoader.java`: Pre-loads the database with initial data on startup.
- `pom.xml`: Maven project object model, containing dependencies and build configuration.
