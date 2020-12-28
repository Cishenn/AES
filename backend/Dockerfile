FROM maven:3-adoptopenjdk-8
WORKDIR /app
COPY pom.xml pom.xml
COPY src src
RUN mvn package

FROM openjdk:8-jdk-alpine
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
ARG JAR_FILE=target/autoExam-0.0.1-SNAPSHOT.jar
COPY --from=0 /app/${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]