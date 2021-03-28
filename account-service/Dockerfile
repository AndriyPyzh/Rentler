FROM openjdk:15-jdk-buster
COPY ./account-service/target/account-service.jar /app/
ENTRYPOINT ["java","-jar","/app/account-service.jar"]
EXPOSE 8100