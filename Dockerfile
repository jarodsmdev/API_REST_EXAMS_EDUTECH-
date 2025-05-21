# Etapa 1: Compilación del proyecto
FROM maven:3.8.8-eclipse-temurin-17 AS build
WORKDIR /exams_app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa 2: Ejecución de la aplicación
FROM eclipse-temurin:17-jre
WORKDIR /exams_app
COPY --from=build /exams_app/target/*.jar exams_app.jar
EXPOSE 8084
ENTRYPOINT ["java", "-jar", "exams_app.jar"]