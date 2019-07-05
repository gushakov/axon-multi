package com.github.axonmulti.db;

import org.h2.tools.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.SQLException;

@SpringBootApplication
public class DbSpringBootApplication {


    @Bean(initMethod = "start", destroyMethod = "stop")
    public Server h2DatabaseServer() throws SQLException {
        return Server.createTcpServer("-tcp", "-tcpAllowOthers",
                "-tcpPort", "9090");
    }


    public static void main(String[] args) {
        SpringApplication.run(DbSpringBootApplication.class, args);
    }
}
