package com.idle.game;

import com.idle.game.model.battle.BattleField;
import com.idle.game.model.battle.BattleSite;
import com.idle.game.server.repository.BattleFieldRepository;
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
public class IdleBattleApplication {

    public static void main(String[] args) {
        SpringApplication.run(IdleBattleApplication.class, args);
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
    public CommandLineRunner init(BattleFieldRepository battleFieldRepository) {

        return args -> {
            BattleField bf = new BattleField();
            
            bf.setId("camp1");
            bf.addInitialLayers();
            
            bf.getLayer(0).addSite(new BattleSite("camp1-0-0", null));
            
            bf.getLayer(1).addSite(new BattleSite("camp1-1-0", "camp1-0-0"));
            bf.getLayer(1).addSite(new BattleSite("camp1-1-1", "camp1-0-0"));
            bf.getLayer(1).addSite(new BattleSite("camp1-1-2", "camp1-0-0"));
            
            bf.getLayer(2).addSite(new BattleSite("camp1-2-0", "camp1-1-0"));
            bf.getLayer(2).addSite(new BattleSite("camp1-2-1", "camp1-1-0"));
            bf.getLayer(2).addSite(new BattleSite("camp1-2-2", "camp1-1-0"));
            
            bf.getLayer(2).addSite(new BattleSite("camp1-2-3", "camp1-1-1"));
            bf.getLayer(2).addSite(new BattleSite("camp1-2-4", "camp1-1-1"));
            bf.getLayer(2).addSite(new BattleSite("camp1-2-5", "camp1-1-1"));
            
            bf.getLayer(2).addSite(new BattleSite("camp1-2-6", "camp1-1-2"));
            bf.getLayer(2).addSite(new BattleSite("camp1-2-7", "camp1-1-2"));
            bf.getLayer(2).addSite(new BattleSite("camp1-2-8", "camp1-1-2"));
            
            battleFieldRepository.save(bf);

        };
                
    }

}
