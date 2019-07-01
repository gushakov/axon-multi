Axon Multi-services
---

:warning: **Attention: incomplete version** :warning:

Please see `master` branch.

Playground application featuring separate Axon microservices communicating via a distributed command bus (Eureka) and a distributed event bus (RabbitMQ).
No Axon server, no event store, event sourcing is not used. All microservices persist their data in a separate schema of
a common H2 database.

The basic setup is a group of autonomous components working together:

- Eureka server
- RabbitMQ server (from the base image)
- H2 database in TCP server mode
- A microservice on top of `Person` aggregate
- A microservice on top of `Address` aggregate
- A microservice on top of a projection handling component (TODO:)
- A Saga component for long business transactions (assigning a private address to a person)

The idea here is to see how Axon helps up to tie up together the concepts of DDD, EDA, CQRS, 
(what they call) "locational transparency", Saga in a working example of a ecosystem which
is capable of handling ongoing changes in business requirements.

### Docker

All components can be run in Docker. Database, RabbitMQ, and Eureka can be started independently. They
should probably be started as services via `docker-compose`.

The aggregates, projections, Sagas are dependent on these services and on each other. They can be run
individually from the IDE or via `docker-compose`.

### H2 database

There is a H2 database console deployed with `db` module:

- http://localhost:8079/h2-console
- Use `jdbc:h2:tcp://localhost:9090/./axondb;IFEXISTS=true;DB_CLOSE_ON_EXIT=FALSE`
- Usual user and password


### Links and resources used in this project

- [Axon Reference Guide, Distributing the command bus](https://docs.axoniq.io/reference-guide/configuring-infrastructure-components/command-processing/command-dispatching#distributing-the-command-bus)
- [Axon Reference Guide, Spring Cloud](https://docs.axoniq.io/reference-guide/extensions/spring-cloud)
- [Introduction to Spring Cloud Netflix – Eureka](https://www.baeldung.com/spring-cloud-netflix-eureka)
- [Error Handling for REST with Spring](https://www.baeldung.com/exception-handling-for-rest-with-spring)
- [Build a REST API with Spring](http://www.canchito-dev.com/public/blog/2017/04/22/build-a-rest-api-with-spring/)
- [Spring Boot with Docker](https://spring.io/guides/gs/spring-boot-docker/)
- [Access the Same In-Memory H2 Database in Multiple Spring Boot Applications](https://www.baeldung.com/spring-boot-access-h2-database-multiple-apps)
- [saikatkar/sample-axon-kafka](https://github.com/saikatkar/sample-axon-kafka)
- [sfav/axon-springboot-cloud](https://github.com/sfav/axon-springboot-cloud)
- [powerman/dockerize](https://github.com/powerman/dockerize)