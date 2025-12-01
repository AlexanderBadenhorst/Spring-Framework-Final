# 📚 Library Management System (Spring Boot REST API)

A fully functional **Spring Boot backend application** that manages Authors, Books, Borrowers, and Loans.  
The system exposes a complete **REST API**, fully **documented and testable via Swagger**, and follows clean layered architecture.

---

## 🚀 Features

### ✔ Authors
- Create, read, update, delete authors
- Auto-generated IDs
- Validation + exception handling

### ✔ Books
- CRUD operations
- Track total copies and available copies
- Linked to authors
- Validation to ensure required fields and valid values

### ✔ Borrowers
- CRUD operations
- Valid email & phone number format checking

### ✔ Loans
- Borrow book (decrements available copies)
- Return book (increments available copies)
- Loan deletion
- Full relationship validation:
    - Cannot borrow non-existing book
    - Cannot borrow if no copies available
    - Cannot return a non-existing loan

### ✔ Swagger API Documentation
Access the API documentation and test all endpoints through Swagger UI:

http://localhost:8080/swagger-ui/index.html


---

## 🧱 Architecture

The project follows clean layered Spring architecture:

controller/
service/
repository/
model/
dto/
exception/


- **Controller Layer** – Handles API requests
- **Service Layer** – Business logic
- **Repository Layer** – Database access (Spring Data JPA)
- **Model Layer** – JPA entities
- **DTO Layer** – Data transfer and validation
- **Exception Layer** – Centralized error handling

---

## 🛠 Technologies Used

- **Java 17**
- **Spring Boot**
- **Spring Web**
- **Spring Data JPA**
- **Hibernate**
- **H2 Database**
- **Lombok**
- **Swagger / Springdoc OpenAPI**

---

## ▶ Running the Application

### 1. Clone the repository
Repository available at:

https://github.com/AlexanderBadenhorst/Spring-Framework-Final/tree/3a7985894ca1a03e15a6191e15bb42bf22689c6d/Project/library

### 2. Open the project in IntelliJ / Eclipse / VS Code
### 3. Run the Spring Boot application

**Run the main class:**

*LibraryApplication.java*

## 4. Open Swagger UI
http://localhost:8080/swagger-ui/index.html


You can now test every endpoint from the browser.

---

## 📂 API Overview
### Authors
- GET    /api/authors
- GET    /api/authors/{id}
- POST   /api/authors
- PUT    /api/authors/{id}
- DELETE /api/authors/{id}

### Books
- GET    /api/books
- GET    /api/books/{id}
- POST   /api/books
- PUT    /api/books/{id}
- DELETE /api/books/{id}

### Borrowers
- GET    /api/borrowers
- GET    /api/borrowers/{id}
- POST   /api/borrowers
- PUT    /api/borrowers/{id}
- DELETE /api/borrowers/{id}

### Loans
- POST   /api/loans/borrow/{bookId}/{borrowerId}
- POST   /api/loans/return/{loanId}
- GET    /api/loans
- GET    /api/loans/{id}
- DELETE /api/loans/{id}

---

## 🧪 Testing

### The application is fully testable in Swagger:

- Input validation

- CRUD operations

- Business rules (loan availability, returns)

- Clean JSON responses

---

## 🧩 Error Handling

### A global exception handler returns clean JSON error responses for:

- Resource not found

- Validation errors

- SQL constraint violations

### Example error:

- *{
"error": "Borrower not found",
"timestamp": "2025-12-01T12:34:56.789Z"
}*
---
## 📝 Notes

- Designed for academic demonstration and future expansion.

- Easily extendable with authentication, roles, or real database integration (MySQL/PostgreSQL).
---
## 👨‍💻 Author

- **Alexander Badenhorst**
- View more of my work: https://github.com/AlexanderBadenhorst


---