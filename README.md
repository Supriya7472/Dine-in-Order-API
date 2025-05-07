# 🍽️ Dine-in Order API

A RESTful backend service built using **Spring Boot** for managing dine-in restaurant operations such as menu, table booking, and order management — all secured with JWT authentication.



## 🚀 Features

- ✅ User registration & login with secure JWT
- ✅ Role-based access (Admin/User)
- ✅ CRUD operations for Menu items
- ✅ Place, view, and delete orders
- ✅ Table management and reservations
- ✅ Swagger UI for testing endpoints



## 🛠️ Tech Stack

| Category     | Tools Used                                 |
|--------------|--------------------------------------------|
| **Backend**  | Java 17, Spring Boot, Spring Security, JPA |
| **Database** | MySQL                                      |
| **Build Tool** | Maven                                    |
| **API Test** | Swagger UI, Postman                        |
| **Auth**     | JWT (Access + Refresh Tokens)              |



## ⚙️ Getting Started

### ✅ Prerequisites

- Java 17+
- Maven
- MySQL installed and running

### 🔧 Setup Instructions

1. **Clone the repository**  
   ```bash
   git clone https://github.com/Supriya7472/Dine-in-Order-API.git
   cd Dine-in-Order-API

2. **Run the application**
    ```bash
    mvn clean install
    mvn spring-boot:run

3. **Access Swagger UI**
    ```bash
    http://localhost:8080/swagger-ui.html

### 📬 Sample API Endpoints

| Method | Endpoint             | Description           |
| ------ | -------------------- | --------------------- |
| POST   | `/api/auth/register` | Register new user     |
| POST   | `/api/auth/login`    | Login & get JWT token |
| GET    | `/api/menu`          | List all menu items   |
| POST   | `/api/orders`        | Place a new order     |
| GET    | `/api/orders/user`   | View your orders      |
| POST   | `/api/table/book`    | Reserve a table       |


⚠️ Secured endpoints require a valid JWT token in the Authorization header:
## 👩‍💻 Developed By

**Supriya** — Java Full Stack Developer  
GitHub: [Supriya7472](https://github.com/Supriya7472)



## 📄 License

This project is open-source and available under the MIT License.


