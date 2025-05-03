# Use a Gradle image to build the application
FROM gradle:7.2.0-jdk17 AS builder
WORKDIR /app

# Copy the entire project into the container
COPY . .

# Build the application
RUN gradle build --no-daemon

# Use a lightweight OpenJDK image to run the application
FROM openjdk:17-jdk-slim
WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=builder /app/build/libs/*.jar app.jar

# Set the Spring profile to 'dev'
ENV SPRING_PROFILES_ACTIVE=dev

# Expose port 9000
EXPOSE 9000

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]