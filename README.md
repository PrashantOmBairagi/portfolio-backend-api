
# Portfolio Backend API

A lightweight **Spring Boot REST API** powering the contact section of my personal portfolio website.

This project started as a way to learn how real backend systems work beyond localhost — APIs, databases, cloud deployment, Docker, networking, HTTPS, environment variables, validation, and frontend integration.

Someone can visit my portfolio, submit the contact form, and the request travels through a real production deployment pipeline — validated, processed, and delivered directly to my inbox.

---

## 🚀 Live Architecture

```text
                         Internet
                            │
                            │ HTTPS
                            ▼
                    AWS EC2 Instance
                            │
                     Nginx :80 / :443
                            │
                    HTTPS termination
                            │
                            ▼
                 Portfolio Contact API
                            │
                    Docker :8090
                            │
                    Spring Boot :8080
                            │
                            ▼
                     Spring Mail
                            │
                     Gmail SMTP :587
                            │
                            ▼
                  Portfolio Owner Inbox
````

The API is proudly hosted on my own **AWS EC2 instance** using Docker and Nginx instead of a managed deployment platform.

### EC2 Service Architecture

```text
AWS EC2
│
├── Nginx
│   ├── Port 80  → HTTP → HTTPS redirect
│   └── Port 443 → HTTPS
│
├── Smart Finance Tracker
│   └── Docker :8080
│
└── Portfolio Contact API
    └── Docker :8090 → Spring Boot :8080
```

Nginx acts as the reverse proxy and keeps the application containers behind the public HTTPS entry point.

---

## ✨ Features

* REST API for portfolio contact form
* Email forwarding directly to my inbox
* Spring Mail with Gmail SMTP
* Backend validation for all form fields
* Global exception handling
* Environment variable configuration
* Dockerized Spring Boot application
* AWS EC2 deployment
* Nginx reverse proxy
* HTTPS with Let's Encrypt
* Automated TLS certificate renewal
* AWS Security Group network configuration
* CORS configured for the portfolio frontend
* Production-oriented deployment structure

---

## 🛠️ Tech Stack

### Backend

* Java 21
* Spring Boot
* Spring Mail
* JavaMailSender
* Spring Data JPA *(DB layer preserved, currently inactive)*
* Hibernate *(preserved for future re-activation)*
* Lombok

### Deployment & Infrastructure

* AWS EC2
* Docker
* Docker Hub
* Nginx
* Let's Encrypt
* Certbot
* systemd
* AWS Security Groups

### Email

* Gmail SMTP
* TLS
* SMTP port `587`

### Frontend

* Vercel

### Testing

* Postman
* cURL

---

## 📡 API Endpoints

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

A successful submission triggers an email containing the submitted contact details.

---

### Health Check

```http
GET /health-check
```

Returns:

```text
OK
```

Used to verify that the deployed service is running correctly.

---

## 🌐 Production Routing

The Contact API is accessed through Nginx over HTTPS.

```text
https://43.204.7.243/contact-api/...
                │
                ▼
             Nginx :443
                │
                ▼
          EC2 :8090
                │
                ▼
       Docker Container
                │
                ▼
      Spring Boot :8080
```

The Docker container continues to use port `8080`.

The EC2 host maps:

```text
8090 → 8080
```

using Docker port mapping:

```bash
-p 8090:8080
```

This allows multiple backend services to coexist on the same EC2 instance without changing their internal application ports.

---

## 🔒 HTTPS & TLS

The API is served over HTTPS using a **Let's Encrypt TLS certificate**.

```text
Client
  │
  │ HTTPS :443
  ▼
Nginx
  │
  │ Reverse Proxy
  ▼
Docker Container :8090
  │
  ▼
Spring Boot :8080
```

Certificate management is automated using **Certbot + systemd**.

The renewal flow uses the Nginx webroot:

```text
systemd timer
      │
      ▼
certbot renew
      │
      ▼
Let's Encrypt HTTP-01 validation
      │
      ▼
/var/www/certbot/.well-known/acme-challenge/
      │
      ▼
New certificate
      │
      ▼
Nginx reload
```

The renewal configuration has been tested using:

```bash
sudo certbot renew --dry-run
```

This verifies that the renewal process can successfully complete without manually stopping Nginx.

---

## 🔐 Network Configuration

The EC2 Security Group allows only the required public services:

| Port | Protocol | Purpose                           |
| ---- | -------- | --------------------------------- |
| 22   | TCP      | SSH administration                |
| 80   | TCP      | HTTP and Let's Encrypt validation |
| 443  | TCP      | HTTPS                             |

The Contact API's application port:

```text
8090
```

is not intended to be directly exposed to the Internet.

Instead:

```text
Internet
   │
   ▼
443 HTTPS
   │
   ▼
Nginx
   │
   ▼
127.0.0.1:8090
   │
   ▼
Docker
   │
   ▼
Spring Boot:8080
```

This keeps the application layer behind the reverse proxy.

---

## 🐳 Docker Deployment

The application is packaged as a Docker image and published to Docker Hub.

```text
Local Development
       │
       ▼
docker build
       │
       ▼
Docker Image
       │
       ▼
Docker Hub
       │
       ▼
AWS EC2
       │
       ▼
docker pull
       │
       ▼
Docker Container
```

The application runs internally on port `8080`.

On EC2:

```text
EC2 :8090 → Container :8080
```

Example:

```bash
docker run -d \
  --name portfolio-contact-api \
  --env-file /home/ec2-user/contact-api.env \
  -p 8090:8080 \
  panditjikochhoro/portfolio-contact-api:latest
```

---

## ☁️ AWS EC2 Deployment

The backend is hosted directly on an **AWS EC2 instance**.

The deployment includes:

* EC2 compute instance
* Docker containerization
* Docker Hub image distribution
* Nginx reverse proxy
* HTTPS termination
* Let's Encrypt certificates
* Automated certificate renewal
* AWS Security Group configuration
* Internal Docker port mapping
* Environment-based secret management

The EC2 instance currently hosts multiple backend services:

```text
AWS EC2
│
├── Smart Finance Tracker
│   └── :8080
│
└── Portfolio Contact API
    └── :8090
```

Nginx provides the public HTTPS entry point and routes traffic to the appropriate service.

---

## 📧 Email Configuration

The API uses Gmail SMTP to forward contact form submissions.

Spring Mail configuration:

```properties
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=${MAIL_USERNAME}
spring.mail.password=${MAIL_PASSWORD}

spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
spring.mail.properties.mail.smtp.starttls.required=true
```

Sensitive values are supplied through environment variables:

```env
MAIL_USERNAME=your_sender_gmail
MAIL_PASSWORD=your_gmail_app_password
```

The application uses a **Gmail App Password** rather than storing a normal Gmail account password.

Secrets are not stored in the source code or Docker image.

---

## 🧪 Validation Rules

The API validates incoming requests before processing them.

### Name

* Required
* Between 2 and 50 characters

### Phone

* Optional
* If provided, must contain exactly 10 digits

### Email

* Required
* Must be a valid email address

### Message

* Required
* Between 5 and 500 characters

---

## 🖥️ Running Locally

Clone the repository:

```bash
git clone https://github.com/PrashantOmBairagi/portfolio-backend-api.git
```

Navigate into the project:

```bash
cd portfolio-backend-api
```

Set the required environment variables:

```env
MAIL_USERNAME=your_sender_gmail
MAIL_PASSWORD=your_gmail_app_password
```

Run:

```bash
mvn spring-boot:run
```

The application will start on:

```text
http://localhost:8080
```

---

## 🐳 Build Docker Image

Build the image:

```bash
docker build -t portfolio-contact-api .
```

Tag the image:

```bash
docker tag portfolio-contact-api:latest \
YOUR_DOCKERHUB_USERNAME/portfolio-contact-api:latest
```

Push it to Docker Hub:

```bash
docker push YOUR_DOCKERHUB_USERNAME/portfolio-contact-api:latest
```

On EC2:

```bash
docker pull YOUR_DOCKERHUB_USERNAME/portfolio-contact-api:latest
```

Run:

```bash
docker run -d \
  --name portfolio-contact-api \
  --env-file /home/ec2-user/contact-api.env \
  -p 8090:8080 \
  YOUR_DOCKERHUB_USERNAME/portfolio-contact-api:latest
```

---

## 🗄️ Database

The project originally included a PostgreSQL persistence layer using **Neon PostgreSQL**.

The database layer is currently inactive because the Contact API was simplified to operate as a stateless email-forwarding service.

The JPA/Hibernate code is preserved in the codebase and can be re-enabled in the future by provisioning a PostgreSQL instance and restoring the required environment variables.

---

## 🧠 Could This Have Been a Lambda?

Yes.

A simple AWS Lambda or Google Cloud Function could have handled email forwarding with fewer moving parts and potentially lower infrastructure overhead.

But that's not the point.

This project was built as a **learning exercise in real backend engineering**.

The goal was to understand:

* REST APIs
* Spring Boot
* validation
* dependency injection
* email services
* Docker
* cloud deployment
* Linux servers
* reverse proxies
* HTTPS
* TLS certificates
* networking
* environment-driven configuration
* production deployment workflows

A managed function would have short-circuited much of that learning.

The goal was never simply the cheapest solution.

**The goal was understanding how the pieces actually fit together.**

---

## 🌐 Portfolio Website

### Live Portfolio

[https://prashant-bairagi-portfolio.vercel.app](https://prashant-bairagi-portfolio.vercel.app)

### Frontend Repository

[https://github.com/PrashantOmBairagi/Frontend-Web-Learning](https://github.com/PrashantOmBairagi/Frontend-Web-Learning)

### Backend Repository

[https://github.com/PrashantOmBairagi/portfolio-backend-api](https://github.com/PrashantOmBairagi/portfolio-backend-api)

---

## 📈 Why I Built This

For a long time, backend development felt abstract to me — APIs, databases, HTTP requests, deployment, cloud hosting and networking.

This project was my way of moving beyond tutorials and building something real end-to-end.

It started as a simple contact API.

It eventually became a practical introduction to deploying and operating backend services on cloud infrastructure.

The project helped me understand not just how to write a Spring Boot API, but also how to package it, deploy it, expose it securely, configure networking, manage secrets, terminate HTTPS, and operate it on a real cloud server.

Still learning, but this project gave me confidence that I can actually build, deploy, and operate backend systems.

---

### Built with Java, Spring Boot, Docker, AWS & optimism.

```

**One correction from our current deployment:** the public URL shown in the README's routing section is the EC2 IP, while your frontend is currently still calling `/api/contacts`. Since we just added `/contact-api/` as the Nginx route, we'll need to update the frontend API URL separately if it isn't already using that path.
```
