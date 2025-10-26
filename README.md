# Event Ticketing System (Backend)

![Java](https://img.shields.io/badge/Java-21-blue) ![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4-green) ![PostgreSQL](https://img.shields.io/badge/PostgreSQL-blue) ![Docker](https://img.shields.io/badge/Docker-blue) ![License](https://img.shields.io/badge/License-Educational-orange)

## Overview

This project is a **backend implementation** of an event ticketing system using **Java Spring Boot**. It supports the event management lifecycle, including event creation, ticket sales, and validation.

Designed for three user types:

* **Event Organizers** – manage events and ticket types
* **Event Attendees** – purchase tickets
* **Event Staff** – validate tickets

> Note: The backend was tested with a React frontend, but the frontend is **not included** in this repository.

## Features

* Create, update, and delete events with multiple ticket types
* Manage ticket sales, availability, and limits
* Ticket validation for event entry
* Secure authentication & authorization with Spring Security, OAuth2/OpenID support

## Tech Stack

* **Java 21**
* **Spring Boot 3.4**
* **Spring Security**
* **JPA / Hibernate**
* **PostgreSQL**
* **Maven**
* **Docker**




## Getting Started

To run the backend, you need **Java 21, Maven, Docker**, and a **Keycloak server** configured for your roles.

### 1. Clone the Repository

```bash
git clone https://github.com/FatimaOmarova/Event-Ticket-Platform.git
cd Event-Ticket-Platform
```

### 2. Configure Keycloak

* Set up a Keycloak realm, clients, and roles (`Organizer`, `Attendee`, `Staff`) as required by the backend.
* Update Keycloak settings in `application.properties` (client ID, secret, realm URL, etc.).
* Ensure roles match those expected by the APIs, otherwise requests will be denied.

### 3. Configure the Database

* Update `application.properties` with your database credentials.
* If using Docker, the database container can be started via `docker-compose.yml`.

### 4. Start Docker Services

```bash
docker-compose up -d
```

* This will start the database and other required services.
* Ensure Keycloak is running and accessible for authentication.

### 5. Build and Run the Backend

```bash
mvn clean install
mvn spring-boot:run
```

* The backend API will be available at `http://localhost:8080`.

### 6. Test APIs

* APIs are **role-restricted**, so test requests using users with appropriate roles.
* Use **Postman** or another REST client to test endpoints.

## License

Educational use only, based on Devtiro course guidance.
