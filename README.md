# Reservation App API

REST API for managing users, resources, and reservations with JWT-based authentication.

## Tech Stack

- Java 17
- Spring Boot 4.0.3
- Spring Web
- Spring Data JPA
- Spring Security + JWT (`jjwt`)
- MySQL
- Maven

## Features

- User registration and login with JWT access/refresh tokens
- Token refresh and logout (token revocation)
- User creation and lookup
- Resource creation and lookup
- Reservation creation and lookup
- Reservation overlap validation for the same resource and time range

## Project Structure

```text
src/main/java/com/app/reservation/reservation_app
|- controllers/   # REST controllers
|- dto/           # Request/response DTOs
|- exceptions/    # Custom exceptions
|- models/        # JPA entities and enums
|- repositories/  # Spring Data repositories
|- services/      # Business logic
|- validations/   # Validation classes
```

## Prerequisites

- JDK 17+
- Maven 3.9+
- MySQL 8+

## Local Setup

1. Create the database:

```sql
CREATE DATABASE reservation_app;
```

2. Configure properties in `src/main/resources/application.properties`.
3. Run the app:

```bash
./mvnw spring-boot:run
```

By default, the app starts on `http://localhost:8080`.

## Configuration

Current configuration is read from `src/main/resources/application.properties`:

- `spring.datasource.*` for MySQL connection
- `security.jwt.secret`
- `security.jwt.expiration`
- `security.jwt.refresh-token.expiration`
- `spring.mail.*` for email sender settings

Important notes:

- `spring.jpa.hibernate.ddl-auto=create` recreates schema on startup.
- `import.sql` inserts sample resources on startup.
- Keep secrets and credentials out of source control in real deployments.

## Seed Data

`src/main/resources/import.sql` inserts:

- `TABLE` named `Mesa 5` (capacity `2`)
- `HALL` named `Sala 10` (capacity `6`)

## Authentication and Security

- Endpoints under `/auth/**` are public.
- All `/api/**` endpoints require `Authorization: Bearer <access_token>`.
- Logout endpoint revokes the token in the database.

Authentication flow:

1. Register or login to receive `accesToken` and `refreshToken`.
2. Use `accesToken` for protected endpoints.
3. Use `refreshToken` on `/auth/refresh` to get a new access token.

## API Endpoints

### Auth

- `POST /auth/register`
- `POST /auth/login`
- `POST /auth/refresh`
- `GET /auth/confirm?token=<jwt>`
- `POST /auth/logout`

Register body:

```json
{
	"name": "Jorge",
	"email": "jorge@example.com",
	"password": "secret123"
}
```

Login body:

```json
{
	"email": "jorge@example.com",
	"password": "secret123"
}
```

Refresh request header:

```http
Authorization: Bearer <refresh_token>
```

### Users

- `POST /api/user/`
- `GET /api/user/{id}`

Create user body:

```json
{
	"name": "Ana",
	"email": "ana@example.com",
	"password": "123456"
}
```

### Resources

- `POST /api/resource/`
- `GET /api/resource/{id}`

Create resource body:

```json
{
	"type": "TABLE",
	"name": "Mesa 11",
	"capacity": 4,
	"active": true
}
```

Allowed `type` values:

- `HALL`
- `TABLE`
- `CHAIR`

### Reservations

- `POST /api/reservation/`
- `GET /api/reservation/{id}`

Create reservation body:

```json
{
	"startDate": "2026-03-20T13:00:00",
	"endDate": "2026-03-20T15:00:00",
	"status": "ACTIVE",
	"userId": 1,
	"resourceId": 1
}
```

Allowed `status` values:

- `ACTIVE`
- `CANCELLED`

## Quick cURL Example

Login:

```bash
curl -X POST http://localhost:8080/auth/login \
	-H "Content-Type: application/json" \
	-d '{"email":"jorge@example.com","password":"secret123"}'
```

Create resource (protected):

```bash
curl -X POST http://localhost:8080/api/resource/ \
	-H "Content-Type: application/json" \
	-H "Authorization: Bearer <access_token>" \
	-d '{"type":"TABLE","name":"Mesa 12","capacity":4,"active":true}'
```

## Build and Test

Build:

```bash
./mvnw clean package
```

Run tests:

```bash
./mvnw test
```

## Error Handling

Global exceptions are handled in `src/main/java/com/app/reservation/reservation_app/controllers/ErrorResponseHandler.java` and return a structured `ErrorDto` payload.

## License

No license file is currently defined in this repository.