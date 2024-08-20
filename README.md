# Fortes Extra Hour API

This repository contains the RESTful API developed as part of my Final Graduation Project (TCC) in partnership with Fortes Engenharia. The API is designed to manage overtime work for construction projects, allowing users to schedule overtime, assign employees, choose responsible managers, and log reasons for overtime requests. The project was implemented using Java, Spring Boot, and MongoDB.

## Table of Contents

- [Features](#features)
- [Technologies](#technologies)
- [Installation](#installation)
- [Usage](#usage)
- [API Endpoints](#api-endpoints)
- [Project Structure](#project-structure)
- [License](#license)

## Features

- **User Authentication and Security:** Implemented using JSON Web Tokens (JWT) with Spring Security.
- **Database Management:** Uses MongoDB for flexible and scalable data storage.
- **Unit Testing:** Comprehensive unit tests using JUnit to ensure the reliability of core functionalities.
- **CORS Configuration:** Configured to allow smooth communication between the frontend and backend.

## Technologies

- **Java 11**
- **Spring Boot**
- **Spring Security**
- **MongoDB**
- **JUnit**

## Installation

### Prerequisites

- Java 17
- Maven
- MongoDB

### Steps

1. **Clone the repository:**
    ```bash
    git clone https://github.com/marcelomartinsdev/fortes-extrahour-api.git
    ```
2. **Navigate to the project directory:**
    ```bash
    cd fortes-extrahour-api
    ```
3. **Build the project using Maven:**
    ```bash
    mvn clean install
    ```
4. **Run the application:**
    ```bash
    mvn spring-boot:run
    ```

The application will be accessible at `http://localhost:8080`.

## Usage

Once the API is running, you can interact with it using tools like Postman or cURL. Below are some of the key functionalities provided by the API:

- **User Registration and Login**
- **Scheduling Overtime**
- **Viewing and Managing Overtime Records**
- **Assigning and Managing Employees**
- **Secure Access to Endpoints via JWT**

## API Endpoints

### Authentication

- `POST /api/auth/login`: Authenticate a user and receive a JWT token.
- `POST /api/auth/register`: Register a new user.

### Overtime Management

- `POST /api/overtime`: Create a new overtime schedule.
- `GET /api/overtime`: Retrieve a list of all overtime schedules.
- `PUT /api/overtime/{id}`: Update an existing overtime schedule.
- `DELETE /api/overtime/{id}`: Delete an overtime schedule.

### Employee Management

- `POST /api/employees`: Add a new employee.
- `GET /api/employees`: Retrieve a list of all employees.
- `PUT /api/employees/{id}`: Update employee details.
- `DELETE /api/employees/{id}`: Remove an employee.

## Project Structure

The project follows a standard Spring Boot project structure:

```plaintext
src
├── main
│   ├── java
│   │   └── com.example.fortesextrahourapi
│   │       ├── config
│   │       ├── controller
│   │       ├── domain
│   │       ├── dto
│   │       ├── enums
│   │       ├── exceptions
│   │       ├── repositories
│   │       └── service
│   └── resources
│       ├── application.properties
│       └── static
└── test
    └── java
        └── com.example.fortesextrahourapi
```
License

This project is licensed under the MIT License - see the LICENSE file for details.

Feel free to contribute or reach out if you have any questions. You can view the complete code and documentation on my GitHub Repository.
