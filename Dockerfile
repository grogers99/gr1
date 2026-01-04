# Stage 1: build with Maven
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /workspace

# Copy maven settings (split for build cache)
COPY pom.xml mvnw* ./
COPY .mvn .mvn
COPY src src

RUN mvn -B -DskipTests clean package

# Stage 2: runtime image (smaller)
FROM eclipse-temurin:17-jre
ARG JAR=target/gr1-0.0.1-SNAPSHOT.jar
COPY --from=build /workspace/${JAR} /app/app.jar

# make port configurable via env var PORT (default 8080)
ENV PORT=8080
EXPOSE 8080

ENTRYPOINT ["sh","-c","java -jar /app/app.jar --server.port=${PORT}"]