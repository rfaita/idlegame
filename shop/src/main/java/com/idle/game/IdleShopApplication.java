package com.idle.game;

import com.idle.game.core.hero.type.HeroQuality;
import com.idle.game.core.hero.type.HeroTypeFaction;
import com.idle.game.core.hero.type.HeroTypeQuality;
import com.idle.game.model.Resource;
import com.idle.game.model.ResourceType;
import com.idle.game.model.shop.LootRoll;
import com.idle.game.model.shop.LootRollType;
import com.idle.game.model.shop.LootRollValue;
import com.idle.game.server.repository.LootRollRepository;
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
public class IdleShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(IdleShopApplication.class, args);
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
    public CommandLineRunner init(LootRollRepository lootRollRepository) {

        return args -> {

            LootRoll lr = new LootRoll();

            lr.setId("1");
            lr.addRoll(HeroQuality.class, new LootRollValue(HeroQuality.POOR.toString(), 50.0));
            lr.addRoll(HeroQuality.class, new LootRollValue(HeroQuality.AVERAGE.toString(), 30.0));
            lr.addRoll(HeroQuality.class, new LootRollValue(HeroQuality.BEST.toString(), 19.0));
            lr.addRoll(HeroQuality.class, new LootRollValue(HeroQuality.SHINE.toString(), 0.9));
            lr.addRoll(HeroQuality.class, new LootRollValue(HeroQuality.UNIQUE.toString(), 0.1));

            lr.addRoll(HeroTypeFaction.class, new LootRollValue(HeroTypeFaction.CHAOS.toString(), 20.0));
            lr.addRoll(HeroTypeFaction.class, new LootRollValue(HeroTypeFaction.FORTIFIED.toString(), 20.0));
            lr.addRoll(HeroTypeFaction.class, new LootRollValue(HeroTypeFaction.SAVAGE.toString(), 20.0));
            lr.addRoll(HeroTypeFaction.class, new LootRollValue(HeroTypeFaction.SHADOW.toString(), 20.0));
            lr.addRoll(HeroTypeFaction.class, new LootRollValue(HeroTypeFaction.DARK.toString(), 10.0));
            lr.addRoll(HeroTypeFaction.class, new LootRollValue(HeroTypeFaction.LIGHT.toString(), 10.0));

            lr.addRoll(HeroTypeQuality.class, new LootRollValue(HeroTypeQuality.POOR.toString(), 60.0));
            lr.addRoll(HeroTypeQuality.class, new LootRollValue(HeroTypeQuality.AVERAGE.toString(), 30.0));
            lr.addRoll(HeroTypeQuality.class, new LootRollValue(HeroTypeQuality.BEST.toString(), 10.0));

            lr.addCost(new Resource(ResourceType.GOLD, 100000L));

            lr.setName("loot.roll.default");

            lr.setType(LootRollType.HERO);

            lootRollRepository.save(lr);

            lr = new LootRoll();

            lr.setId("2");
            lr.addRoll(HeroQuality.class, new LootRollValue(HeroQuality.BEST.toString(), 89.0));
            lr.addRoll(HeroQuality.class, new LootRollValue(HeroQuality.SHINE.toString(), 10.0));
            lr.addRoll(HeroQuality.class, new LootRollValue(HeroQuality.UNIQUE.toString(), 1.0));

            lr.addRoll(HeroTypeFaction.class, new LootRollValue(HeroTypeFaction.CHAOS.toString(), 20.0));
            lr.addRoll(HeroTypeFaction.class, new LootRollValue(HeroTypeFaction.FORTIFIED.toString(), 20.0));
            lr.addRoll(HeroTypeFaction.class, new LootRollValue(HeroTypeFaction.SAVAGE.toString(), 20.0));
            lr.addRoll(HeroTypeFaction.class, new LootRollValue(HeroTypeFaction.SHADOW.toString(), 20.0));
            lr.addRoll(HeroTypeFaction.class, new LootRollValue(HeroTypeFaction.DARK.toString(), 10.0));
            lr.addRoll(HeroTypeFaction.class, new LootRollValue(HeroTypeFaction.LIGHT.toString(), 10.0));

            lr.addRoll(HeroTypeQuality.class, new LootRollValue(HeroTypeQuality.POOR.toString(), 60.0));
            lr.addRoll(HeroTypeQuality.class, new LootRollValue(HeroTypeQuality.AVERAGE.toString(), 30.0));
            lr.addRoll(HeroTypeQuality.class, new LootRollValue(HeroTypeQuality.BEST.toString(), 10.0));

            lr.addCost(new Resource(ResourceType.GEM, 150L));

            lr.setName("loot.roll.better");

            lr.setType(LootRollType.HERO);

            lootRollRepository.save(lr);

        };

    }
}
