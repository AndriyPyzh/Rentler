FROM openjdk:latest
COPY ./flat-service/target/flat-service.jar /app/
COPY ./wait-for-it.sh /wait-for-it.sh
RUN chmod +x /wait-for-it.sh
ENTRYPOINT ["./wait-for-it.sh","config-server:8888","--","java","-jar","/app/flat-service.jar"]
EXPOSE 8765