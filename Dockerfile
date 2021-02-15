FROM openjdk:8-jdk-alpine
RUN addgroup -S appuser && adduser -S appuser -G appuser
USER appuser:appuser
ARG JAR_FILE=target/ebi-assignment-task-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
EXPOSE 2020
ENTRYPOINT ["java","-jar","/app.jar"]