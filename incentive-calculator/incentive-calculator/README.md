# Incentive Calculator System

## 📌 Overview
This project is a backend-driven **Incentive Calculator System** designed for a large car dealership group.  
The system automates sales incentive calculation by ingesting sales data and incentive rules, applying business logic, and computing incentives per salesperson.

The focus of this assignment is on **backend logic, data handling, and system design**, rather than UI perfection.

---

## 🛠️ Tech Stack
- Java 17
- Spring Boot 3.2.1
- Spring Data JPA
- H2 In-Memory Database
- Maven
- REST APIs
- CSV-based data ingestion

---

## 🧩 System Design (High Level)
1. Sales data is uploaded via CSV and stored in the database.
2. Structured incentive rules (CSV-based) are uploaded and stored separately.
3. Incentives are calculated by applying rules on aggregated sales data.
4. Results are exposed through REST APIs.
5. The system is designed to be extensible for unstructured/ad-hoc rules and frontend dashboards.

---

## 🚀 How to Run the Application

### Prerequisites
- Java 17+
- Maven

### Steps
```bash
mvn clean install
mvn spring-boot:run
