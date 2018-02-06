package com.idle.game;

import com.idle.game.model.mongo.Player;
import com.idle.game.model.mongo.PlayerResource;
import com.idle.game.model.mongo.Resource;
import com.idle.game.model.mongo.ResourceType;
import com.idle.game.server.repository.PlayerRepository;
import com.idle.game.server.repository.PlayerResourceRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 *
 * @author rafael
 */
@SpringBootApplication
@EnableCircuitBreaker
public class IdlePlayerApplication {

    public static void main(String[] args) {
        SpringApplication.run(IdlePlayerApplication.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                // we have to enable CORS to make requests from other domains work
                registry.addMapping("/**").allowedOrigins("*").allowedMethods("*");
            }
        };
    }

    @Bean
    public RestTemplate rest(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public javax.validation.Validator localValidatorFactoryBean() {
        return new LocalValidatorFactoryBean();
    }

    @Bean
    public CommandLineRunner init(PlayerRepository playerRepository, PlayerResourceRepository playerResourceRepository) {

        return args -> {

            Player p = new Player();
            p.setId("1");
            p.setLinkedUser("c723fc50-639d-4eec-a449-cebda70599c9");
            p.setName("rfaita");

            PlayerResource pr = new PlayerResource();

            pr.setPlayer("1");

            Resource r = new Resource();
            r.setType(ResourceType.RUNE);
            r.setValue(0L);

            pr.getResources().add(r);

            r = new Resource();
            r.setType(ResourceType.GOLD);
            r.setValue(0L);

            pr.getResources().add(r);

            r = new Resource();
            r.setType(ResourceType.GEM);
            r.setValue(0L);

            pr.getResources().add(r);

            r = new Resource();
            r.setType(ResourceType.RUNE_PS);
            r.setValue(10000L);

            pr.getResources().add(r);

            r = new Resource();
            r.setType(ResourceType.GOLD_PS);
            r.setValue(10000L);

            pr.getResources().add(r);

            r = new Resource();
            r.setType(ResourceType.GEM_PS);
            r.setValue(10000L);

            pr.getResources().add(r);

            playerRepository.save(p);
            playerResourceRepository.save(pr);

            p = new Player();
            p.setId("2");
            p.setLinkedUser("60955fdb-6af7-4e02-a088-3fee94086444");
            p.setName("joao");

            pr = new PlayerResource();

            pr.setPlayer("2");

            r = new Resource();
            r.setType(ResourceType.RUNE);
            r.setValue(0L);

            pr.getResources().add(r);

            r = new Resource();
            r.setType(ResourceType.GOLD);
            r.setValue(0L);

            pr.getResources().add(r);

            r = new Resource();
            r.setType(ResourceType.GEM);
            r.setValue(0L);

            pr.getResources().add(r);

            r = new Resource();
            r.setType(ResourceType.RUNE_PS);
            r.setValue(10000L);

            pr.getResources().add(r);

            r = new Resource();
            r.setType(ResourceType.GOLD_PS);
            r.setValue(10000L);

            pr.getResources().add(r);

            r = new Resource();
            r.setType(ResourceType.GEM_PS);
            r.setValue(10000L);

            pr.getResources().add(r);

            playerRepository.save(p);
            playerResourceRepository.save(pr);

            p = new Player();
            p.setId("3");
            p.setLinkedUser("50a5dfce-34ca-4949-8b37-d968231a2247");
            p.setName("teste");

            pr = new PlayerResource();

            pr.setPlayer("3");

            r = new Resource();
            r.setType(ResourceType.RUNE);
            r.setValue(0L);

            pr.getResources().add(r);

            r = new Resource();
            r.setType(ResourceType.GOLD);
            r.setValue(0L);

            pr.getResources().add(r);

            r = new Resource();
            r.setType(ResourceType.GEM);
            r.setValue(0L);

            pr.getResources().add(r);

            r = new Resource();
            r.setType(ResourceType.RUNE_PS);
            r.setValue(10000L);

            pr.getResources().add(r);

            r = new Resource();
            r.setType(ResourceType.GOLD_PS);
            r.setValue(10000L);

            pr.getResources().add(r);

            r = new Resource();
            r.setType(ResourceType.GEM_PS);
            r.setValue(10000L);

            pr.getResources().add(r);

            playerRepository.save(p);
            playerResourceRepository.save(pr);

        };
    }
}
