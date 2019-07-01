Axon Multi-services
---

:construction: **Attention: under construction** :construction:

Playground application with uses Axon framework to demonstrates a way of building a distributed event-driven 
system around the concepts of domain-driven design and CQRS. 

There are several modules, each is a standalone Spring Boot application:

- `common`: module containing configuration (Maven dependencies) common to other modules
- `core`: module with all commands and event shared by other modules
- `db`: H2 file database running as a TCP server
- `address`: microservice around "address" aggregate
- `person`: microservice around "person" aggregate
- `saga`: Saga implementation of a business transaction (private address assignment)


### Axon configuration

- Using Axon Server version 4.1 without event sourcing infrastructure

### API:

Access Swagger UI: at http://localhost:8080/swagger-ui.html

### Saga

This is how assigning private address to a person Saga proceeds:

![address_saga](https://github.com/gushakov/axon-multi/blob/master/address_saga.png)

### Docker

Run `docker-compose up`.

### H2 database

- Access H2 console at http://localhost:8079/h2-console
- Use Server configuration
- JDBC URL: jdbc:h2:tcp://localhost:9090/./axondb;IFEXISTS=true;DB_CLOSE_ON_EXIT=FALSE

### Links and resources used in this project

- [Error Handling for REST with Spring](https://www.baeldung.com/exception-handling-for-rest-with-spring)
- [Build a REST API with Spring](http://www.canchito-dev.com/public/blog/2017/04/22/build-a-rest-api-with-spring/)
- [Spring Boot with Docker](https://spring.io/guides/gs/spring-boot-docker/)
- [Access the Same In-Memory H2 Database in Multiple Spring Boot Applications](https://www.baeldung.com/spring-boot-access-h2-database-multiple-apps)
