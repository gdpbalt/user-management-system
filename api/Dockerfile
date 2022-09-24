FROM maven:3-jdk-11-slim AS build
COPY src /usr/src/app/src
COPY pom.xml /usr/src/app
COPY checkstyle.xml /usr/src/app
RUN mvn -f /usr/src/app/pom.xml clean package

FROM openjdk:11-oracle
ARG JAR_FILE=target/*.jar
COPY --from=build /usr/src/app/${JAR_FILE} /app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
