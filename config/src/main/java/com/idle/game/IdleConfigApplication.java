package com.idle.game;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 *
 * @author rafael
 */
@SpringBootApplication
@EnableConfigServer
public class IdleConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(IdleConfigApplication.class, args);
    }

}
