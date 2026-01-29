# Use Java 17
FROM eclipse-temurin:17-jdk-alpine

# Set workdir
WORKDIR /app

# Copy Maven wrapper and pom.xml
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Make mvnw executable
RUN chmod +x mvnw

# Pre-download dependencies (cache layer)
RUN ./mvnw dependency:go-offline

# Copy source code
COPY src src

# Build the application
RUN ./mvnw clean package -DskipTests

# Expose port 8080
EXPOSE 8080

# Run Spring Boot JAR
CMD ["java", "-jar", "target/job-tracker-0.0.1-SNAPSHOT.jar"]
