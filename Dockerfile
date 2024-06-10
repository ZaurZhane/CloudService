FROM openjdk:17-jdk-slim

EXPOSE 8081

COPY target/CloudApplication-0.0.1-SNAPSHOT.jar cloudapp.jar

CMD ["java", "-jar", "cloudapp.jar"]