# Use a Gradle image to build the application
FROM gradle:7.6-jdk17 AS builder
WORKDIR /app

# Set Gradle cache directory
ENV GRADLE_USER_HOME=/app/.gradle

# Copy the entire project into the container
COPY . .

# Build the application with extra logging
RUN gradle clean build --no-daemon --info

# Use a lightweight OpenJDK image to run the application
FROM openjdk:17-jdk-slim
WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=builder /app/build/libs/*.jar app.jar

# Set the Spring profile to 'dev'
ENV SPRING_PROFILES_ACTIVE=dev

# Expose port 9000
EXPOSE 9090

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]