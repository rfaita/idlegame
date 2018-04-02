package com.idle.game;

import com.idle.game.helper.interceptor.UserFeignClientInterceptor;
import com.idle.game.server.scalar.GraphQLDate;
import feign.RequestInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author rafael
 */
@SpringBootApplication
public class IdleGraphQLApplication {

    public static void main(String[] args) {
        SpringApplication.run(IdleGraphQLApplication.class, args);
    }

    @Bean
    public RestTemplate rest(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public RequestInterceptor getUserFeignClientInterceptor() {
        return new UserFeignClientInterceptor();
    }

    @Bean
    @ConditionalOnMissingBean
    public GraphQLDate graphQLDate() {
        return new GraphQLDate();
    }

    @Bean
    public javax.validation.Validator localValidatorFactoryBean() {
        return new LocalValidatorFactoryBean();
    }

}
