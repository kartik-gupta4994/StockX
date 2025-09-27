# StockT (Backend)

A backend microservices application simulating the core features of a stock trading platform. Built with **Spring Boot** and designed for scalability, security, and ease of integration.


## ✨ Features

- **User Service**
  - User registration & login
  - JWT-based authentication
  - Brute-force protection

- **Stock Service**
  - Stock listing and details
  - Real-time (mocked) price updates
  - Database seeding for stock data

- **Order Service**
  - Place buy/sell orders
  - Integrates with funds for balance checks

- **Portfolio Service**
  - Tracks user holdings and positions

- **Funds Service**
  - Manage deposits and withdrawals
  - Balance validation for orders


## 🔧 Tech Stack

- Java 17+
- Spring Boot
- Spring Security (JWT)
- Feign Clients for inter-service communication
- PostgreSQL
- Maven
- Postman for API testing


## 🗂️ Architecture

- Microservices architecture
- Each service independently deployable
- RESTful APIs
- Token-based security across services


