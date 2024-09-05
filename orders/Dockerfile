FROM openjdk:17-jdk-slim

COPY target/orders-0.0.1-SNAPSHOT.jar /app/orders-0.0.1-SNAPSHOT.jar

WORKDIR /app

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "orders-0.0.1-SNAPSHOT.jar"]