FROM openjdk:latest
COPY ./registry-server/target/registry-server.jar /app/
COPY ./wait-for-it.sh /wait-for-it.sh
RUN chmod +x /wait-for-it.sh
ENTRYPOINT ["./wait-for-it.sh","config-server:8888","-t","30","--","java","-jar","/app/registry-server.jar"]
EXPOSE 8761