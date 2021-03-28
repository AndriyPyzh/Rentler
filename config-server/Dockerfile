FROM openjdk:15-jdk-buster
COPY ./config-server/target/config-server.jar /app/
ENTRYPOINT ["java","-jar","/app/config-server.jar"]
EXPOSE 8888