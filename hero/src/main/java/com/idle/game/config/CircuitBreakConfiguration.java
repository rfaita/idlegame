package com.idle.game.config;

import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 *
 * @author rafael
 */
@Configuration
@EnableCircuitBreaker
@EnableHystrix
@EnableFeignClients(basePackages = {
    "com.idle.game.helper.client.battle",
    "com.idle.game.helper.client.friend",
    "com.idle.game.helper.client.guild",
    "com.idle.game.helper.client.hero",
    "com.idle.game.helper.client.mail",
    "com.idle.game.helper.client.resource",
    "com.idle.game.helper.client.shop",
    "com.idle.game.helper.client.user"})
@Profile({"default"})
public class CircuitBreakConfiguration {

}
