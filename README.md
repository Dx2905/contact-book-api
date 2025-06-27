

# 📒 Contact Book API

A secure and production-ready Contact Book REST API built with Spring Boot, PostgreSQL, and JWT Authentication. Supports full CRUD operations on user contacts, packaged via Docker and ready for deployment on GCP Cloud Run.

---

## ✅ Features

- 🔐 JWT-based user authentication (signup/login)
- 👥 CRUD operations on contacts
- 🧾 PostgreSQL for persistent storage
- 📦 Dockerized for easy deployment
- ☁️ Ready for GCP Cloud Run deployment
- ⚙️ Configured with `application.yml`

---

## 🛠️ Tech Stack

| Component        | Technology            |
|------------------|------------------------|
| API Framework     | Spring Boot (Java)     |
| Authentication    | JWT (HS256)            |
| Database          | PostgreSQL             |
| Caching (optional)| Redis (Spring Cache)   |
| Packaging         | Docker                 |
| Deployment        | GCP Cloud Run          |
| Config Management | `application.yml`      |

---

## 🚀 Running the App

### 1. Clone and Build

```bash
git clone https://github.com/your-username/contact-book-api.git
cd contact-book-api
./mvnw clean package
```

### 2. Docker Compose (PostgreSQL + App)

```bash
docker-compose up --build
```

Ensure port `8080` is free and PostgreSQL isn’t already running locally on `5432`.

---

## 🔐 Authentication Flow

### 🔸 Sign Up

`POST /auth/signup`

```json
{
  "username": "elon",
  "email": "elon@tesla.com",
  "password": "123456"
}
```

### 🔸 Login

`POST /auth/login`

```json
{
  "email": "elon@tesla.com",
  "password": "123456"
}
```

📬 **Response**: JWT Token  
Use this token in `Authorization` header:

```
Authorization: Bearer <your_token_here>
```

---

## 📒 Contact Endpoints

All endpoints require a valid Bearer token.

| Method | Endpoint         | Description        |
|--------|------------------|--------------------|
| GET    | `/contacts`      | List all contacts  |
| POST   | `/contacts`      | Create new contact |
| PUT    | `/contacts/{id}` | Update contact     |
| DELETE | `/contacts/{id}` | Delete contact     |

### 🔹 Sample Contact JSON

```json
{
  "name": "Elon Musk",
  "email": "elon@tesla.com",
  "phone": "1234567890",
  "notes": "CEO of Tesla"
}
```

---

## 🗄️ Environment Configuration

Edit `src/main/resources/application.yml`:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://host.docker.internal:5432/contactdb
    username: gaurav
    password: your_password
jwt:
  secret: thisisaverysecretkeythatshouldbe32bytesmin
  expiration-ms: 3600000
```

> For Docker to access host DB, use `host.docker.internal`.

---

## 🧪 Testing (via Postman)

1. Register user → `/auth/signup`
2. Login → `/auth/login` → copy token
3. Use Bearer token in header
4. Test `/contacts` endpoints

---

## 📦 Packaging & Deployment

```bash
docker build -t contact-book-api .
docker run -p 8080:8080 contact-book-api
```

### ⛅ GCP Cloud Run (Optional)
- Build image
- Push to Artifact Registry
- Deploy via Cloud Run console or `gcloud` CLI

---

## 📁 Project Structure

```
src
├── main
│   ├── java/com/example/contactbook
│   │   ├── controller
│   │   ├── service
│   │   ├── dto
│   │   ├── entity
│   │   ├── security
│   │   └── ContactBookApplication.java
│   └── resources
│       └── application.yml
```

---

## ✍️ Author

- **Fnu Gaurav**  
  [LinkedIn](https://www.linkedin.com/in/fnu-gaurav-653355252/) • [GitHub](https://github.com/Dx2905)

---

## 🧾 License

This project is licensed under the MIT License.
```

---

