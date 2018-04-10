package com.idle.game;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 *
 * @author rafael
 */
@SpringBootApplication
@EnableEurekaServer
public class IdleEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(IdleEurekaApplication.class, args);
    }

}
