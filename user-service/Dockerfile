FROM openjdk:latest
COPY ./user-service/target/user-service.jar /app/
COPY ./wait-for-it.sh /wait-for-it.sh
RUN chmod +x /wait-for-it.sh
ENTRYPOINT ["./wait-for-it.sh","config-server:8888","--","java","-jar","/app/user-service.jar"]
EXPOSE 8765