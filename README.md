# Portfolio Contact API

Cloud-deployed backend system built using **Java**, **Spring Boot**, and **PostgreSQL** to handle contact form submissions from a live portfolio website.

The API enables contact form handling, persistent database storage, and seamless frontend-backend communication through a cloud-hosted architecture.

🌐 **Live Portfolio:**  
https://prashant-bairagi-portfolio.vercel.app/

---

## Tech Stack

### Backend
- Java
- Spring Boot
- REST APIs

### Database
- Neon PostgreSQL

### Cloud & Deployment
- Render (Backend Hosting)
- Neon PostgreSQL (Cloud Database)
- Vercel (Frontend Hosting)

### Tools
- Maven
- Postman
- Git & GitHub
- IntelliJ IDEA

---

## Features

- Contact form submission handling
- Persistent database storage
- REST API endpoints
- Frontend-backend integration
- Cloud deployment
- End-to-end API testing using Postman

---

## System Architecture

```text
Portfolio Website (Vercel)
            ↓
Spring Boot REST API (Render)
            ↓
Neon PostgreSQL Database
```

This architecture demonstrates frontend-backend communication with cloud-hosted database persistence.

---

## REST API Design

The backend follows RESTful API principles for handling portfolio contact form submissions and contact data management.

### Submit Contact Form

```http
POST /api/contacts
```

Handles contact form submissions from the live portfolio website and stores data in PostgreSQL.

#### Request Body

```json
{
  "name": "Prashant",
  "phone": "9876543210",
  "email": "example@gmail.com",
  "message": "Hello!"
}
```

---

### Get All Contacts

```http
GET /api/contacts
```

Retrieves all submitted contact entries.

> Note: This endpoint exists for backend testing and development purposes and is not publicly exposed in the deployed application.

---

### Get Contact By ID

```http
GET /api/contacts/{id}
```

Retrieves a specific contact entry using its unique ID.

> Note: Currently restricted from public usage in production deployment.

---

### Delete Contact

```http
DELETE /api/contacts/{id}
```

Deletes a specific contact entry by ID.

> Note: Used for backend management/testing and not publicly accessible in the deployed version.

---

The project demonstrates practical REST API development involving request handling, database persistence, and endpoint design using Spring Boot.

---

## API Workflow

1. User submits contact form on portfolio website
2. Request sent to Spring Boot REST API
3. Backend validates and processes request
4. Data stored in PostgreSQL database
5. Response returned to frontend

---

## Environment Configuration

Sensitive credentials are managed using **environment variables** instead of hardcoding secrets.

Example:

```env
MDB_URL=
MDB_USERNAME=
MDB_PASSWORD=
```

This improves deployment flexibility and keeps credentials secure across environments.

---

## API Testing

Endpoints were tested and validated using **Postman** to ensure proper request handling and backend reliability.

---

## Project Status

✅ **Completed & Deployed**

Implemented:
- REST API development
- PostgreSQL database integration
- Frontend-backend communication
- Cloud deployment using Render + Neon + Vercel
- Contact form handling
- Environment variable configuration

---

## Future Improvements

Planned enhancements:

- Email notification system
- Rate limiting & spam protection
- Better request validation
- Admin dashboard for messages
- Authentication for protected endpoints

---

## Author

**Prashant Bairagi**

🌐 Portfolio: https://prashant-bairagi-portfolio.vercel.app/  
💻 GitHub: https://github.com/PrashantOmBairagi  
💼 LinkedIn: https://linkedin.com/in/prashant-bairagi-kmlpr
