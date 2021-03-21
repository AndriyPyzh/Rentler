FROM openjdk:latest
COPY ./auth-service/target/auth-service.jar /app/
COPY ./wait-for-it.sh /wait-for-it.sh
RUN chmod +x /wait-for-it.sh
ENTRYPOINT ["./wait-for-it.sh","config-server:8888","-t","30","--","java","-jar","/app/auth-service.jar"]
EXPOSE 8765