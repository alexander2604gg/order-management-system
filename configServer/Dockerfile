FROM openjdk:17-jdk-slim

COPY target/configServer-0.0.1-SNAPSHOT.jar /app/configServer-0.0.1-SNAPSHOT.jar

WORKDIR /app

EXPOSE 8888

ENTRYPOINT ["java", "-jar", "configServer-0.0.1-SNAPSHOT.jar"]
