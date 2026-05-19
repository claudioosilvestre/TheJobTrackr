# TheJobTrackr

**The Job Trackr** is a RESTful API designed to simplify and organize the job application process. The platform allows users to manage companies, track job applications, monitor application statuses, and centralize all relevant job search information in a structured and scalable backend system.

## Tech Stack
- **Backend:** Java 21, Spring Boot, Spring Data JPA, Hibernate, Rest APIs
- **AI Integration:** Groq AI
- **Databse:** PostgreSQL
- **Documentation:** Swagger/OpenAPI
- **Testing:** JUnit, Mockito, JUnitParams
- **Build Tool:** Maven
## Getting Started

### Prerequisites
*   Java JDK 21 installed.
*   Maven installed.
*   PostgreSQL (installed and running)
*   Node.js(optional)
*   An IDE (IntelliJ IDEA recommended) or a terminal with Java support.
*   Groq API Key (for AI features)

### How to Run
1.  **Clone the repository:**
    ```bash
    git clone https://github.com/claudioosilvestre/TheJobTrackr.git
    ```
2.  **Configure Database:** 
    ```bash
    CREATE DATABASE thejobtrackr;
    ```
3.  **Rename:** 
    ```bash
    application.properties.example
    ```
    to:
    ```bash
    application.properties
    ```
3.  **Configure application.properties:** 
    ```bash
    Update the following fields in src/main/resources/application.properties:
    
    - DATABASE
    spring.datasource.url=jdbc:postgresql://localhost:5432/thejobtrackr
    spring.datasource.username=your_username
    spring.datasource.password=your_password

    - GROQ API CONFIGURATION
    groq.api.key=your_groq_api_key
    ```
4.  **Run the application:**
    ```bash
    mvn spring-boot:run
    ```
5.  **Open your browser and go to http://localhost:8080**

6.  **Swagger Documentation:**
    After starting the application, access Swagger UI:
    ```bash
    http://localhost:8080/swagger-ui.html
    ```
    or
    ```bash
    http://localhost:8080/swagger-ui/index.html
    ```

> **Note:** All database tables are created automatically on the first run (spring.jpa.hibernate.ddl-auto=update).


## Features
*   **User Management:** Complete CRUD operations for managing platform users with validation and profile handling.
*   **Company Management:** Create, update, retrieve, and delete companies associated with job applications.
*   **Job Application Tracking:** Track job applications through different recruitment stages with status management.
*   **RESTful API:** Well-structured REST API following clean architecture principles and best practices.
*   **Swagger Documentation:** Integrated Swagger/OpenAPI documentation for easy endpoint testing and exploration.
*   **AI Integration:** Groq AI integration ready for intelligent job application analysis and future AI-powered features.
*   **Validation & Error Handling:** Centralized validation and global exception handling for safer API interactions.
*   **Scalable Architecture:** Layered backend structure with Controllers, Services, DTOs, Repositories, and Converters.


## Technical Challenges (Key Learnings)
*   **Spring Boot Architecture:** Building a scalable REST API using Controllers, Services, DTOs, Repositories, and global exception handling.
*   **Database Modeling:** Designing relational database structures and entity associations between users, companies, and job applications.
*   **Validation & Error Handling:** Implementing request validation, custom exceptions, and consistent API error responses.
*   **REST API Design:** Structuring endpoints following RESTful conventions and proper HTTP status management.
*   **Testing:** Writing unit tests with JUnit, Mockito, and parameterized testing for service layer reliability.
*   **AI Integration:** Integrating Groq AI services and preparing the application for intelligent automation features.
*   **DTO & Converter Pattern:** Separating internal entities from external API communication using DTOs and converters.

## Future Improvements
- [ ] Add more tests
- [ ] Authentication & authorization
- [ ] JWT security implementation
- [ ] Frontend integration
- [ ] Dashboard & analytics
- [ ] Email notifications
- [ ] CV upload support
- [ ] Interview scheduling
- [ ] AI-powered job matching
- [ ] Docker deployment
- [ ] CI/CD pipeline

## Authors
*   ** Cláudio Silvestre ** — [LinkedIn](https://www.linkedin.com/in/claudioosilvestre)
