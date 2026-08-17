# Portfolio Backend API

A lightweight **Spring Boot REST API** powering the contact section of my personal portfolio website.

This project started as a way to learn how real backend systems work beyond localhost — APIs, databases, cloud deployment, environment variables, validation, and frontend integration.

Someone can visit my portfolio, submit the contact form, and the message travels through a real backend pipeline — validated, processed, and delivered directly to my inbox.

---

## Live Architecture

```txt
Frontend (Vercel)
        ↓ HTTPS POST /api/contacts
Spring Boot REST API (Render)
        ↓ Bean Validation
  EmailService (Spring Mail)
        ↓ Gmail SMTP (TLS)
  Inbox → hello.prashantbairagi@gmail.com
```

> **Note:** The service was originally wired to a Neon PostgreSQL database. The DB layer is preserved in the codebase (commented out) and can be re-enabled by provisioning a new instance and restoring the environment variables.

---

## Features

* REST API for portfolio contact form
* **Email forwarding** — contact submissions are delivered directly to my inbox via Spring Mail (Gmail SMTP)
* **Stateless operation** — no database required; eliminates cold-start DB connection overhead entirely
* Backend validation for all form fields (name, email, phone, message)
* Global exception handling with structured error responses
* Environment variable configuration (no secrets in source code)
* Cloud deployment on Render with Docker
* CORS configured for the portfolio frontend
* Production-ready API structure with clean separation of concerns

---

## Tech Stack

### Backend

* Java 21
* Spring Boot
* Spring Mail (JavaMailSender)
* Spring Data JPA *(DB layer preserved, currently inactive)*
* Hibernate *(preserved for future re-activation)*
* Lombok

### Deployment

* Render (Docker, auto-deploy on push)

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

Triggers an email to the portfolio owner with all submitted details.

---

### Health Check

```http
GET /health-check
```

Returns `OK` — used by Render to verify the service is live.

---

## Validation Rules

The API validates incoming requests before processing.

### Name

* Required
* Between 2 and 50 characters

### Phone

* Optional
* If provided, must be exactly 10 digits

### Email

* Required
* Valid email format

### Message

* Required
* Between 5 and 500 characters

---

## Environment Variables

Sensitive configuration is stored as environment variables — set on Render's dashboard, never in source code.

```env
# Spring Mail (active)
MAIL_USERNAME=your_sender_gmail
MAIL_PASSWORD=your_gmail_app_password   # Generate at myaccount.google.com/apppasswords

# PostgreSQL (inactive — restore when DB is provisioned)
# MDB_URL=your_database_url
# MDB_USERNAME=your_db_username
# MDB_PASSWORD=your_db_password
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

Set environment variables (`MAIL_USERNAME`, `MAIL_PASSWORD`), then run:

```bash
mvn spring-boot:run
```

---

## Could This Have Been a Lambda?

Yes — a simple AWS Lambda or Google Cloud Function could have handled email forwarding with fewer moving parts and zero server cost.

But that's not the point.

This project was built as a **learning exercise** in real Spring Boot backend development: setting up a multi-layer service, handling validation, wiring dependencies, configuring cloud deployment with Docker, and managing environment-driven configuration. A managed function would have short-circuited all of that learning.

The goal was never the cheapest solution — it was understanding how these pieces fit together end-to-end.

---

## Portfolio Website

Frontend:
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
