FROM maven:3.8.5-openjdk-17 AS build

WORKDIR /app

COPY . .

RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim

WORKDIR /app

COPY --from=build /app/target/products-0.0.1-SNAPSHOT.jar /app/products.jar

EXPOSE 8083

CMD ["java", "-jar", "/app/products.jar"]
