FROM maven:3.8.1-openjdk-17 AS builder
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline -B
COPY src ./src
RUN mvn package -DskipTests

FROM openjdk:17-slim
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
ENV SPRING_PROFILES_ACTIVE=dev
EXPOSE 9010
ENTRYPOINT ["java", "-jar", "app.jar"]