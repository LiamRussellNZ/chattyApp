# ChattyApp

## 📘 Project Overview

ChattyApp is a standalone Spring Boot backend API for a simple message board application. It allows users to post text messages and retrieve all posted messages via HTTP endpoints.

This project demonstrates a minimal viable product (MVP) for a message board, focusing on basic create-and-read functionality – users can:

- **Post** new messages
- **Retrieve** the list of all posted messages

---

## 🚀 Getting Started

### ✅ Prerequisites

- **Java 17+**
- **Maven**

### 📦 Setup and Run

1. **Clone the repository:**

   ```bash
   git clone https://github.com/LiamRussellNZ/chattyApp.git
   cd chattyApp
   ```

2. **Run the application using Maven:**

   ```bash
   mvn spring-boot:run
   ```

   This starts the Spring Boot app on [http://localhost:8080](http://localhost:8080)

---

## 🔗 API Endpoints

### `GET /messages`

- Retrieves all messages.
- **Response:** HTTP 200 OK
- **Returns:** JSON array of messages.

### `POST /messages`

- Creates a new message.
- **Request Body:**

   ```json
   {
     "author": "Alice",
     "content": "Hello world"
   }
   ```

- **Response:** HTTP 201 Created
- **Returns:** JSON of the saved message, including:

   ```json
   {
     "id": 1,
     "author": "Alice",
     "content": "Hello world",
     "postedAt": "2025-06-08T19:35:48.123"
   }
   ```

---

## 🧪 Sample `curl` Commands

### ➕ Post a message

```bash
curl -X POST http://localhost:8080/messages \
  -H "Content-Type: application/json" \
  -d '{"author": "Alice", "content": "Hello, world!"}'
```

### 📥 Get all messages

```bash
curl http://localhost:8080/messages
```

---

## 🗂️ Data Model

Each `Message` object contains:

- `id` (`Long`) – Auto-generated message ID
- `author` (`String`) – Name of the poster (required)
- `content` (`String`) – Message body (required)
- `postedAt` (`LocalDateTime`) – Timestamp of creation

---

## ⚠️ Validation

- Fields `author` and `content` are **required**
- If missing or blank:
    - The API returns **HTTP 400 Bad Request**
    - A meaningful error message is included in the response

Validation is enforced using:
- `@Valid` in the controller
- `@NotBlank` annotations in the model

---

## 📝 Notes

- 💾 Uses **H2 in-memory database** by default (no external setup required)
- 🧾 **Logs requests** via SLF4J for traceability