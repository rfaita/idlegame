package com.idle.game;

import com.idle.game.model.ResourceType;
import com.idle.game.model.Reward;
import com.idle.game.model.RewardValue;
import com.idle.game.model.battle.BattleField;
import com.idle.game.model.battle.BattleSite;
import com.idle.game.model.campaign.CampaignPath;
import com.idle.game.server.repository.CampaignPathRepository;
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
public class IdlePlayerApplication {

    public static void main(String[] args) {
        SpringApplication.run(IdlePlayerApplication.class, args);
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
    public CommandLineRunner init(CampaignPathRepository campaignPathRepository) {

        return args -> {

            Reward r = new Reward();
            r.addReward(new RewardValue(ResourceType.ASHARD, 500L));

            CampaignPath cp = new CampaignPath();

            cp.setId("camp1");
            cp.setInitialPath(Boolean.TRUE);
            cp.setReward(r);
            cp.setBattleFieldId("camp1");
            cp.setNextCampaignPathId(null);

            campaignPathRepository.save(cp);

        };

    }

}
