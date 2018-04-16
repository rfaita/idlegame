package com.idle.game.config;

import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 *
 * @author rafael
 */
@Configuration
@EnableEurekaClient
@Profile({"default"})
public class DiscoveryClientConfiguration {

}
