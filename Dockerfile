FROM eclipse-temurin:21-jre-jammy

ARG JAR_FILE=target/bazar-0.0.1-SNAPSHOT.jar

COPY ${JAR_FILE} app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app.jar"]