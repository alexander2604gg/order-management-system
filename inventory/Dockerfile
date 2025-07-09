FROM maven:3.8.5-openjdk-17 AS build

WORKDIR /app

COPY . .

RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim

WORKDIR /app

COPY --from=build /app/target/inventory-0.0.1-SNAPSHOT.jar /app/inventory.jar

EXPOSE 8081

CMD ["java", "-jar", "/app/inventory.jar"]
