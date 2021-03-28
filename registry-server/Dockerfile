FROM openjdk:15-jdk-buster
COPY ./registry-server/target/registry-server.jar /app/
ENTRYPOINT ["java","-jar","/app/registry-server.jar"]
EXPOSE 8761