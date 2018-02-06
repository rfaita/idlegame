package com.idle.game;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 *
 * @author rafael
 */
@SpringBootApplication
@EnableZuulProxy
public class IdleProxyApplication {

    public static void main(String[] args) {
        SpringApplication.run(IdleProxyApplication.class, args);
    }

}
