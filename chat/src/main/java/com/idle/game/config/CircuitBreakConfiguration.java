package com.idle.game.config;

import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 *
 * @author rafael
 */
@Configuration
@EnableCircuitBreaker
@EnableFeignClients
@Profile({"default"})
public class CircuitBreakConfiguration {

}
