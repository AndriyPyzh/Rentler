# Rentler

## URLs

### Web Client

http://localhost:3000

### Config server

http://localhost:8888

### Registry server

http://localhost:8761

### API Gateway

http://localhost:8765

### Zipkin server

http://localhost:9411

### Postgres

http://localhost:5432

### Auth service

http://localhost:5000

### Account service

http://localhost:8100

### Apartment service

http://localhost:8200

## How to run all the things?

Keep in mind, that you are going to start 7 Spring Boot applications, 1 Postgres instance and RabbitMq. Make sure you have `4 Gb` RAM available on your machine. You can always run just vital services though: Gateway, Registry, Config, Auth Service and Account Service.

#### Before you start
- Install Docker and Docker Compose.
- Change environment variable values in `.env` file for more security or leave it as it is.
- Make sure to build the project: `mvn install -DskipTests`

Run `docker-compose up ` to start all things.




