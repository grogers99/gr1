FROM eclipse-temurin:17-jre
COPY target/gr1-0.0.1-SNAPSHOT.jar /app/app.jar
ENV PORT=8080
EXPOSE 8080
ENTRYPOINT ["sh","-c","java -jar /app/app.jar --server.port=${PORT}"]