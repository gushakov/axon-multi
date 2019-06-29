package com.github.axonmulti.db;

import org.h2.tools.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.SQLException;

@SpringBootApplication
public class AxonDbSpringBootApplication /*implements CommandLineRunner*/ {


    @Bean(initMethod = "start", destroyMethod = "stop")
    public Server h2DatabaseServer() throws SQLException {
        return Server.createTcpServer("-tcp", "-tcpAllowOthers",
                "-tcpPort", "9090");
    }


    public static void main(String[] args) {
        SpringApplication.run(AxonDbSpringBootApplication.class, args);
    }

    /*@Override
    public void run(String... args) throws Exception {
        Thread.currentThread().join();
    }*/
}
