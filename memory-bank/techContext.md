# Technical Context: ToDo Application

## Technologies Used

### Backend
- **Java 17**: Core programming language
- **Spring Boot 3.5.0**: Application framework
- **Spring Data JPA**: Data access layer abstraction
- **Hibernate ORM**: Object-relational mapping
- **Jakarta Persistence API**: Java persistence standard
- **Lombok**: Reduces boilerplate code through annotations

### Frontend
- **HTML5**: Structure of the web application
- **CSS3**: Styling with custom properties (CSS variables)
- **JavaScript (ES6+)**: Client-side functionality
- **Font Awesome 6**: Icon library

### Database
- **PostgreSQL 12.22**: Relational database
- **HikariCP**: High-performance JDBC connection pool

### Build & Development
- **Maven**: Build automation and dependency management
- **Spring DevTools**: Development-time features (hot reload, etc.)

## Development Setup

### Prerequisites
- JDK 17 or higher
- Maven 3.6 or higher
- PostgreSQL 12 or higher

### Database Configuration
The application is configured to connect to a PostgreSQL database with the following parameters:
- **URL**: jdbc:postgresql://localhost:5432/mydb
- **Username**: gareth
- **Password**: password
- **Driver**: org.postgresql.Driver

These settings are defined in `application.properties`:

```properties
spring.application.name=gr1
spring.datasource.url=jdbc:postgresql://localhost:5432/mydb
spring.datasource.username=gareth
spring.datasource.password=password
spring.datasource.driver-class-name=org.postgresql.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
```

### Running the Application
The application can be started using Maven:
```bash
./mvnw spring-boot:run
```

Once running, the application is accessible at:
- **URL**: http://localhost:8080
- **API Base Path**: http://localhost:8080/tasks

## Technical Constraints

### Database Schema
- Hibernate automatically creates/updates the database schema based on entity definitions
- The `spring.jpa.hibernate.ddl-auto=update` setting ensures schema updates without data loss

### API Design
- REST API follows standard HTTP methods for CRUD operations
- JSON is used for data exchange
- No authentication or authorization is implemented

### Frontend Limitations
- No frontend build system or package manager (e.g., npm, webpack)
- No frontend frameworks (e.g., React, Vue, Angular)
- CSS is included directly in the HTML file
- External dependencies loaded via CDN (Font Awesome)

## Dependencies

### Core Dependencies
- `spring-boot-starter-web`: Web application support
- `spring-boot-starter-data-jpa`: JPA support
- `postgresql`: PostgreSQL JDBC driver
- `lombok`: Annotation processor for boilerplate reduction

### Development Dependencies
- `spring-boot-devtools`: Development-time features
- `spring-boot-starter-test`: Testing support

## Tool Usage Patterns

### Database Management
- Hibernate handles schema creation and updates
- Spring Data JPA repositories provide data access methods
- Entity classes define the data model

### API Development
- Controller classes define REST endpoints
- Request mapping annotations specify HTTP methods and paths
- Repository injection for data access

### Frontend Development
- Single HTML file with embedded CSS and JavaScript
- Asynchronous JavaScript for API communication
- DOM manipulation for UI updates

### Testing
- JUnit for unit testing
- Spring Boot Test for integration testing
