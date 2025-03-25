FROM eclipse-temurin:17-jdk-jammy as builder

# 1. Copia primero SOLO los archivos esenciales
WORKDIR /app
COPY .mvn/wrapper/maven-wrapper.jar .mvn/wrapper/
COPY .mvn/wrapper/maven-wrapper.properties .mvn/wrapper/
COPY mvnw .
COPY pom.xml .

# 2. Descarga dependencias (cache eficiente)
RUN ./mvnw dependency:go-offline -B

# 3. Copia el resto
COPY src src
RUN ./mvnw clean package -DskipTests

# Fase final
FROM eclipse-temurin:17-jdk-jammy
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]