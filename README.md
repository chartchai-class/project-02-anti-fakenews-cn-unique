# Social Anti-Fake News System (Spring Boot Backend)

## Team Info

- **Team Name:** uniqu
- **Members:**
  - **Student ID:** 20232081, **Name:** liuwenhao, **Contribution rate:** 40%
  - **Student ID:** 20232054, **Name:** wangyuzhe, **Contribution rate:** 30%
  - **Student ID:** 20232097, **Name:** wangpengyue, **Contribution rate:** 30%

## Project Description

This is the backend service for the Social Anti-Fake News System. It provides a RESTful API to manage news articles, user votes, and comments. It is built with Spring Boot and uses a MySQL database for data persistence.

## API Features

- **News Management:**
  - `GET /news`: Fetches a paginated list of all news articles.
  - `GET /news/{id}`: Retrieves a single news article by its ID.
  - `POST /news`: Adds a new news article to the database.
- **Voting:**
  - `POST /news/{id}/vote`: Submits a vote for a specific news article. The vote details are included in the request body.

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
- A running MySQL instance

### Setup

1.  **Configure Database:**
    - The database connection is configured in `src/main/resources/application-prod.properties`.
    - You may need to create a local configuration file (e.g., `application-dev.properties`) and update the `spring.datasource.url`, `spring.datasource.username`, and `spring.datasource.password` properties to match your local MySQL setup.
    - Create a database named `fakenews`. Spring Boot can create the schema automatically on startup if configured.

2.  **Run the application:**
    - You can run the application using your IDE by running the `main` method in `Application.java`.
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
