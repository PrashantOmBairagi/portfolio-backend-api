# Portfolio Backend API

A lightweight **Spring Boot REST API** powering the contact section of my personal portfolio website.

This project started as a way to learn how real backend systems work beyond localhost — APIs, databases, cloud deployment, environment variables, validation, and frontend integration.

Someone can visit my portfolio, submit the contact form, and the message travels through a real backend pipeline into a PostgreSQL database.

---

## Live Architecture

```txt
Frontend (Vercel)
        ↓ HTTPS Request
Spring Boot REST API (Render)
        ↓
Neon PostgreSQL Database
```

---

## Features

* REST API for portfolio contact form
* Contact form data persistence using PostgreSQL
* Backend validation for form fields
* Global exception handling
* Environment variable configuration
* Cloud deployment on Render
* Neon PostgreSQL integration
* CORS configured for portfolio frontend
* Production-ready API structure

---

## Tech Stack

### Backend

* Java
* Spring Boot
* Spring Data JPA
* Hibernate

### Database

* PostgreSQL (Neon)

### Deployment

* Render
* Docker

### Testing

* Postman

---

## API Endpoints

### Submit Contact Form

```http
POST /api/contacts
```

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

---

### Get Contact By ID

```http
GET /api/contacts/{id}
```

---

### Delete Contact

```http
DELETE /api/contacts/{id}
```

---

## Validation Rules

The API validates incoming requests before saving data.

### Name

* Required
* Minimum 2 characters

### Phone

* Required
* Must contain exactly 10 digits

### Email

* Required
* Valid email format

### Message

* Required
* Minimum 5 characters

---

## Environment Variables

Sensitive configuration is stored using environment variables.

Example:

```env
MDB_URL=your_database_url
MDB_USERNAME=your_username
MDB_PASSWORD=your_password
```

---

## Running Locally

Clone repository:

```bash
git clone https://github.com/PrashantOmBairagi/portfolio-backend-api.git
```

Navigate into project:

```bash
cd portfolio-backend-api
```

Set environment variables.

Run:

```bash
mvn spring-boot:run
```

---

## Portfolio Website

Frontend portfolio:

[Live Portfolio Website](https://prashant-bairagi-portfolio.vercel.app)

Frontend repository:

[Frontend-Web-Learning (Portfolio v2)](https://github.com/PrashantOmBairagi/Frontend-Web-Learning)

---

## Why I Built This

For a long time, backend development felt abstract to me — APIs, databases, HTTP requests, deployment, cloud hosting.

This project was my way of moving beyond tutorials and building something real end-to-end.

Still learning, but this project gave me confidence that I can actually build and deploy backend systems.

---

### Built with Java, Spring Boot & optimism.
