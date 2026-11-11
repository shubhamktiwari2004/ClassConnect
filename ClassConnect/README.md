
# ClassConnect

A role-based educational management platform built using **Java and Spring Boot**, designed to simplify academic management and communication between Admins, Teachers, and Students.

## Features

- **Admin Dashboard:** Manage students, teachers, and academic activities.
- **Teacher Dashboard:** Access teaching-related features and manage student information.
- **Student Dashboard:** Access student-specific information and academic resources.
- **Authentication & Authorization:** Secure login with role-based access control.
- **Remember Me:** Persistent login functionality.
- **Secure Password Storage:** BCrypt password hashing.
- **REST APIs:** JWT-based authentication for API access.

## Tech Stack

- **Backend:** Java 21, Spring Boot 3.3.5, Spring Security
- **Database:** MySQL
- **ORM:** Spring Data JPA, Hibernate
- **Frontend:** Thymeleaf, HTML, CSS, Bootstrap
- **Security:** Spring Security, JWT, BCrypt
- **Build Tool:** Maven

## Project Structure

```text
ClassConnect/
├── src/
│   ├── main/
│   │   ├── java/
│   │   └── resources/
│   └── test/
├── pom.xml
└── README.md
```

## Getting Started

### Prerequisites

- Java 21 or later
- MySQL
- Maven

### Installation

1. Clone the repository:

   ```bash
   git clone https://github.com/YOUR_USERNAME/class-connect.git
   ```

2. Navigate to the project directory:

   ```bash
   cd class-connect
   ```

3. Create a MySQL database for the application.

4. Configure your database credentials in `application.properties` or through environment variables.

5. Run the application:

   ```bash
   mvn spring-boot:run
   ```

6. Open your browser and visit:

   ```text
   http://localhost:8080
   ```

## Future Improvements

- Additional academic management features.
- Enhanced reporting and analytics.
- Improved dashboard customization.
- Additional REST API capabilities.

## Author

**Shubham Tiwari**

- GitHub: [shubhamktiwari2004](https://github.com/shubhamktiwari2004)
- LinkedIn: [Shubham Tiwari](https://linkedin.com/in/shubham-tiwari-aa948b284)

---

If you find this project useful, feel free to explore the repository!
