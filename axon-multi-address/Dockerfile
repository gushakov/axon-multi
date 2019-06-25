FROM openjdk:8-jdk-alpine
RUN apk --no-cache add curl
RUN curl -sfL $(curl -s https://api.github.com/repos/powerman/dockerize/releases/latest | grep -i /dockerize-$(uname -s)-$(uname -m)\" | cut -d\" -f4) | install /dev/stdin /usr/local/bin/dockerize
VOLUME /tmp
ARG JAR_FILE=target/axon-multi-address-1.0-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
