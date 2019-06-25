Axon Multi-services
---

:construction: **Attention: under construction** :construction:

Separate Axon microservices communicating via a distributed command bus (Eureka) and a distributed event bus (RabbitMQ).
No Axon server, no event store, event sourcing is not used.

The idea is to have separate Axon components:

- A microservice on top of `Person` aggregate
- A microservice on top of `Address` aggregate
- A microservice on top of a Saga component which will manage Person/Address assignments

Each component stores its own information only in its own local database (H2).


### Links and resources used in this project

- [Axon Reference Guide, Distributing the command bus](https://docs.axoniq.io/reference-guide/configuring-infrastructure-components/command-processing/command-dispatching#distributing-the-command-bus)
- [Axon Reference Guide, Spring Cloud](https://docs.axoniq.io/reference-guide/extensions/spring-cloud)
- [Introduction to Spring Cloud Netflix – Eureka](https://www.baeldung.com/spring-cloud-netflix-eureka)
- [Error Handling for REST with Spring](https://www.baeldung.com/exception-handling-for-rest-with-spring)
- [Build a REST API with Spring](http://www.canchito-dev.com/public/blog/2017/04/22/build-a-rest-api-with-spring/)
- [Spring Boot with Docker](https://spring.io/guides/gs/spring-boot-docker/)
- [saikatkar/sample-axon-kafka](https://github.com/saikatkar/sample-axon-kafka)
- [sfav/axon-springboot-cloud](https://github.com/sfav/axon-springboot-cloud)
