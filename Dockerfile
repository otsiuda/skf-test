FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} web-app.jar
ENTRYPOINT ["java","-jar","/web-app.jar"]