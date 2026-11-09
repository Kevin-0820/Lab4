FROM eclipse-temurin:21-jdk
ENV TZ="America/Bogota"
WORKDIR /app
COPY target/consulta-service-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "app.jar"]
