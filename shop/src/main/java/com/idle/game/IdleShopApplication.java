package com.idle.game;

import com.idle.game.core.hero.type.HeroQuality;
import com.idle.game.core.hero.type.HeroTypeFaction;
import com.idle.game.core.hero.type.HeroTypeQuality;
import com.idle.game.model.Resource;
import com.idle.game.model.ResourceType;
import com.idle.game.model.shop.FormationItem;
import com.idle.game.model.shop.LootRoll;
import com.idle.game.model.shop.LootRollType;
import com.idle.game.model.shop.LootRollValue;
import com.idle.game.server.repository.LootRollRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author rafael
 */
@SpringBootApplication
public class IdleShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(IdleShopApplication.class, args);
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

            lr.addRoll(HeroTypeFaction.class, new LootRollValue(HeroTypeFaction.AIR.toString(), 16.0));
            lr.addRoll(HeroTypeFaction.class, new LootRollValue(HeroTypeFaction.ARCANE.toString(), 16.0));
            lr.addRoll(HeroTypeFaction.class, new LootRollValue(HeroTypeFaction.DARK.toString(), 10.0));
            lr.addRoll(HeroTypeFaction.class, new LootRollValue(HeroTypeFaction.EARTH.toString(), 16.0));
            lr.addRoll(HeroTypeFaction.class, new LootRollValue(HeroTypeFaction.FIRE.toString(), 16.0));
            lr.addRoll(HeroTypeFaction.class, new LootRollValue(HeroTypeFaction.HOLY.toString(), 10.0));
            lr.addRoll(HeroTypeFaction.class, new LootRollValue(HeroTypeFaction.WATER.toString(), 16.0));

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

            lr.addRoll(HeroTypeFaction.class, new LootRollValue(HeroTypeFaction.AIR.toString(), 16.0));
            lr.addRoll(HeroTypeFaction.class, new LootRollValue(HeroTypeFaction.ARCANE.toString(), 16.0));
            lr.addRoll(HeroTypeFaction.class, new LootRollValue(HeroTypeFaction.DARK.toString(), 10.0));
            lr.addRoll(HeroTypeFaction.class, new LootRollValue(HeroTypeFaction.EARTH.toString(), 16.0));
            lr.addRoll(HeroTypeFaction.class, new LootRollValue(HeroTypeFaction.FIRE.toString(), 16.0));
            lr.addRoll(HeroTypeFaction.class, new LootRollValue(HeroTypeFaction.HOLY.toString(), 10.0));
            lr.addRoll(HeroTypeFaction.class, new LootRollValue(HeroTypeFaction.WATER.toString(), 16.0));

            lr.addRoll(HeroTypeQuality.class, new LootRollValue(HeroTypeQuality.POOR.toString(), 60.0));
            lr.addRoll(HeroTypeQuality.class, new LootRollValue(HeroTypeQuality.AVERAGE.toString(), 30.0));
            lr.addRoll(HeroTypeQuality.class, new LootRollValue(HeroTypeQuality.BEST.toString(), 10.0));

            lr.addCost(new Resource(ResourceType.ASHARD, 150L));

            lr.setName("loot.roll.better");

            lr.setType(LootRollType.HERO);

            lootRollRepository.save(lr);

            lr = new LootRoll();

            lr.setId("3");
            lr.addRoll(FormationItem.class, new LootRollValue("1000", 33.0));
            lr.addRoll(FormationItem.class, new LootRollValue("1001", 33.0));
            lr.addRoll(FormationItem.class, new LootRollValue("1002", 34.0));

            lr.addCost(new Resource(ResourceType.ASHARD, 150L));

            lr.setName("base.formation.help.2");

            lr.setType(LootRollType.ITEM);

            lootRollRepository.save(lr);

            lr = new LootRoll();

            lr.setId("4");
            lr.addRoll(FormationItem.class, new LootRollValue("2000", 33.0));
            lr.addRoll(FormationItem.class, new LootRollValue("2001", 33.0));
            lr.addRoll(FormationItem.class, new LootRollValue("2002", 34.0));

            lr.addCost(new Resource(ResourceType.ASHARD, 150L));

            lr.setName("base.formation.help.3");

            lr.setType(LootRollType.ITEM);

            lootRollRepository.save(lr);

            lr = new LootRoll();

            lr.setId("5");
            lr.addRoll(FormationItem.class, new LootRollValue("3000", 33.0));
            lr.addRoll(FormationItem.class, new LootRollValue("3001", 33.0));
            lr.addRoll(FormationItem.class, new LootRollValue("3002", 34.0));

            lr.addCost(new Resource(ResourceType.GOLD, 10000L));

            lr.setName("base.formation.help");

            lr.setType(LootRollType.ITEM);

            lootRollRepository.save(lr);

        };

    }
}
