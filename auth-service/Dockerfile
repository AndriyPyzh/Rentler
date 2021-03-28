FROM openjdk:15-jdk-buster
COPY ./auth-service/target/auth-service.jar /app/
ENTRYPOINT ["java","-jar","/app/auth-service.jar"]
EXPOSE 5000