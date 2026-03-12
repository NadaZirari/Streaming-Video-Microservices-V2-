FROM eclipse-temurin:17-jdk-jammy
RUN apt-get update && apt-get install -y curl
VOLUME /tmp
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
