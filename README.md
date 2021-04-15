Axon Multi-services
---

:construction: **Attention: under construction** :construction:

:warning: Note, in `AssignPrivateAddressSaga.java` there is a use of `QueryGateway` for validation
of the address assigment. This goes against CQRS principles stating that a Saga must not access
the read-model (or projections). See [this question and answer](https://stackoverflow.com/questions/34284697/why-cant-sagas-query-the-read-side)
for more details.


### Introduction

Playground application with uses Axon framework to demonstrates a way of building a distributed event-driven 
system around the concepts of domain-driven design and CQRS. 

### Application structure

There are several modules, they are standalone Spring Boot applications or libraries:

- `common`: module containing configuration (Maven dependencies) common to other modules
- `core`: module with all commands and events shared by other modules
- `db`: H2 file database running as a TCP server
- `address`: microservice around "address" aggregate
- `person`: microservice around "person" aggregate
- `saga`: Saga implementation of a business transaction (private address assignment)

### Axon configuration

- Using Axon Server version 4.1 _without event sourcing infrastructure_
- Aggregates, projections, and Sagas persist their states in the separate schemas of the central H2 database

### CQRS and messaging infrastructure

This is the overview of the system:

![architecture](https://github.com/gushakov/axon-multi/blob/master/cqrs.png)

### API

- http://localhost:8080/swagger-ui.html (Person)
- http://localhost:8081/swagger-ui.html (Address)

### Saga

This is how assigning private address to a person Saga proceeds:

![address_saga](https://github.com/gushakov/axon-multi/blob/master/address_saga.png)

### Build and run with Docker

- Run `mvn package` on the project
- Run `docker-compose up`

### H2 database

- Access H2 console at http://localhost:8079/h2-console
- Use Server configuration
- JDBC URL: jdbc:h2:tcp://localhost:9090/./axondb;IFEXISTS=true;DB_CLOSE_ON_EXIT=FALSE

### Links and resources used in this project

- [Configuring Axon Server for Docker](https://hub.docker.com/r/axoniq/axonserver/#configuring-axon-server)
- [Error Handling for REST with Spring](https://www.baeldung.com/exception-handling-for-rest-with-spring)
- [Build a REST API with Spring](http://www.canchito-dev.com/public/blog/2017/04/22/build-a-rest-api-with-spring/)
- [Spring Boot with Docker](https://spring.io/guides/gs/spring-boot-docker/)
- [Access the Same In-Memory H2 Database in Multiple Spring Boot Applications](https://www.baeldung.com/spring-boot-access-h2-database-multiple-apps)
- [How to Make Legacy Code Reactivee](https://itnext.io/how-to-make-legacy-code-reactive-2debcb3d0a40)
- [#HOWTO: Best Practices for Flyway and Hibernate with Spring Boot](https://rieckpil.de/howto-best-practices-for-flyway-and-hibernate-with-spring-boot/)