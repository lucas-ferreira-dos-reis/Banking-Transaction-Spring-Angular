[![en](https://img.shields.io/badge/lang-en-red.svg)](README.md)
[![pt-br](https://img.shields.io/badge/lang-pt--br-green.svg)](README.pt-BR.md)

_Leia isto em outros idiomas: [English](README.md)_

---

# 🏦 Banking Transaction

A full-stack web application for scheduling financial transfers with **automatic dynamic fee calculation** and **real-time account statement updates** powered by WebSockets.

---

## 📌 Project Overview

The application allows users to schedule financial transfers by providing the source account, destination account, transfer amount, and transfer date. The system automatically calculates the applicable fee based on the number of days between the scheduling date and the transfer date.

### 🌟 Implemented Features

- **Real-Time Updates (Pub/Sub):** WebSocket (STOMP/SockJS) integration that notifies all connected clients whenever a new transfer is created.
- **Strategy Pattern:** Fee calculation logic is isolated using the Strategy pattern, making the system easier to maintain and extend while following the Open/Closed Principle.
- **Fail-Fast Validation:** Comprehensive frontend and backend validation ensures data consistency by preventing invalid dates, malformed requests, and transfers between identical accounts.
- **Modern Reactive Frontend:** Built with Angular 22+, leveraging Signals, Standalone Components, and the new Control Flow syntax (`@if`, `@for`).

---

## 🛠️ Technologies

### Backend

- **Java 11** & **Spring Boot 2.7.18** (The assignment required Java 11)
- **Spring Data JPA** (Database persistence)
- **H2 Database** (In-memory database required by the assignment)
- **Spring WebSocket / STOMP** (Real-time notifications)
- **JUnit 5 & MockMvc** (Unit and integration testing)
- **OpenAPI / Swagger UI** (Interactive API documentation)

### Frontend

- **Angular 22+**
- **TypeScript**
- **Angular Material** (UI components and accessibility)
- **RxJS & Signals** (Reactive state management)
- **SockJS / StompJS** (WebSocket client)

---

## 📐 Architecture

1. **Layered Architecture**
   - **Controller:** REST entry point responsible for HTTP requests, DTO validation, and response handling.
   - **Service:** Contains business rules and publishes WebSocket events.
   - **Strategy / Domain:** Encapsulates fee calculation logic according to the transfer lead time.
   - **Repository:** Database abstraction using Spring Data JPA.

2. **Event-Driven Communication**
   - Instead of requiring clients to perform an additional HTTP request after every new transfer, the backend publishes newly created transfers to the `/topic/transfers` topic. This enables real-time synchronization across multiple connected clients.

---

## 🚀 Running the Project

### Prerequisites

- **Java 11**
- **Node.js 18 or later**
- **npm**

---

### 1️⃣ Backend (Spring Boot)

1. Navigate to the backend directory:

```bash
cd backend
```

2. Run the application using the Maven Wrapper:

- Linux / macOS (Terminal):

```bash
./mvnw spring-boot:run
```

- Windows (PowerShell):

```bash
.\mvnw spring-boot:run
```

- Windows (Command Prompt):

```bash
mvnw spring-boot:run
```

3. The API will be available at:

```
http://localhost:5000
```

4. Swagger UI:

```
http://localhost:5000/api/swagger-ui/index.html
```

### 2️⃣ Frontend (Angular)

1. Open a new terminal and navigate to the frontend directory:

```bash
cd frontend
```

2. Install dependencies:

```bash
npm install
```

3. Start the development server:

```bash
ng serve
```

4. Open your browser at:

```
http://localhost:4200
```

## 🧪 Running the Tests

From the backend directory, run:

```bash
./mvnw test
```

The test suite includes:

- Fee calculation validation for all scheduling intervals (same day, 1–10 days, 11–20 days, etc.), ensuring compliance with the fee schedule. The fee schedule is present on table on the /transfers page.
- HTTP endpoint integration tests using MockMvc.
- Request payload validation (for example, preventing transfers between identical accounts).

## 📄 License

This project was developed as part of a Full-Stack technical assessment.
