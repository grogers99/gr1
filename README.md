# GR1 — Spring Boot ToDo App ✅

A small Spring Boot ToDo application with a REST API and a static frontend. Tasks are persisted using JPA and PostgreSQL.

---

## 🔧 Purpose
- Demonstration / simple personal ToDo app.
- Backend: Spring Boot REST API that manages `Task` entities (description, status, dueDate).
- Frontend: Single-page UI at `src/main/resources/static/index.html` that calls the API.

## ⚙️ Requirements
- Java 17
- Maven 3.x
- PostgreSQL (or change to an in-memory DB for quick dev)

## ⚡ Quickstart — build & run
1. Ensure PostgreSQL is running and the DB exists (or update DB config):

   Edit `src/main/resources/application.properties` if needed. Example:

   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/mydb
   spring.datasource.username=gareth
   spring.datasource.password=password
   spring.datasource.driver-class-name=org.postgresql.Driver

   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true
   ```

2. Build the jar:

   ```bash
   mvn clean package
   ```

3. Run the application:

   ```bash
   java -jar target/gr1-0.0.1-SNAPSHOT.jar
   ```

4. Open the UI in your browser:

   ```
   http://localhost:8080/index.html
   ```

> Development (hot reload with devtools):
> ```bash
> mvn spring-boot:run
> ```

## 🔗 REST endpoints (examples)
- List tasks (GET):
  ```bash
  curl http://localhost:8080/tasks/show
  ```
- Add task (PUT):
  ```bash
  curl -X PUT "http://localhost:8080/tasks/add?description=Buy%20milk"
  ```
  With due date: `?description=Buy%20milk&dueDate=2026-01-10` (YYYY-MM-DD)
- Complete task (POST):
  ```bash
  curl -X POST http://localhost:8080/tasks/complete/{id}
  ```
- Delete task (DELETE):
  ```bash
  curl -X DELETE http://localhost:8080/tasks/delete/{id}
  ```
- Update due date (PATCH):
  ```bash
  curl -X PATCH "http://localhost:8080/tasks/update-due-date/1?dueDate=2026-01-15"
  ```

## 🧪 Tests
Run unit tests with:

```bash
mvn test
```

## Notes & Tips
- Lombok (`@Data`) is used; ensure your IDE has Lombok support for best editing experience.
- `spring.jpa.hibernate.ddl-auto=update` will create/update tables automatically on startup (good for development).

## Using H2 (in-memory) 🧪
- An `application-h2.properties` profile is included for using an in-memory H2 DB (dependency `com.h2database:h2` was added to `pom.xml`).
- H2 is useful for quick development or demos because it requires no external DB setup.

Quick commands:
- With Maven (dev run):

```bash
SPRING_PROFILES_ACTIVE=h2 mvn spring-boot:run
```

- With the packaged jar:

```bash
java -jar -Dspring.profiles.active=h2 target/gr1-0.0.1-SNAPSHOT.jar
```

H2 console (for debugging): `http://localhost:8080/h2-console` 
- JDBC URL: `jdbc:h2:mem:gr1db`  
- User: `sa`  
- Password: (leave blank)

**Note:** the in-memory DB is ephemeral and uses `create-drop` by default (data is lost when the app stops). If you want persistence between runs, change the JDBC URL to a file-based H2 URL like `jdbc:h2:file:./data/gr1` and adjust `spring.jpa.hibernate.ddl-auto` as needed.

---

If you want, I can also:
- Add a short `Makefile` or Maven profile for local dev 🔧
- Add a small health endpoint (`/actuator/health`) (actuator is included) ✅

Let me know which you'd like next.
