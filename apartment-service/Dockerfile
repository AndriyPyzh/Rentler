FROM openjdk:latest
COPY ./apartment-service/target/apartment-service.jar /app/
COPY ./wait-for-it.sh /wait-for-it.sh
RUN chmod +x /wait-for-it.sh
ENTRYPOINT ["./wait-for-it.sh","config-server:8888","-t","30","--","java","-jar","/app/apartment-service.jar"]
EXPOSE 8765