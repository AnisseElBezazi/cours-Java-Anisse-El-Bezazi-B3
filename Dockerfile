FROM maven:3.9.16-eclipse-temurin-17 as build

WORKDIR /app-build

COPY src/ /app-build/src
COPY pom.xml /app-build/pom.xml

RUN mvn clean package -DskipTests

RUN cp /app-build/target/*.jar /app-build/application.jar

FROM openjdk:17.0.2-jdk as  lancement

WORKDIR /app-lancement

COPY --from=build /app-build/application.jar /app-lancement/application.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar", "/app-lancement/application.jar"]