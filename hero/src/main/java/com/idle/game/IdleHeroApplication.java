package com.idle.game;

import com.idle.game.core.type.DamageType;
import com.idle.game.core.type.DistanceType;
import com.idle.game.core.type.HeroTypeQuality;
import com.idle.game.model.mongo.HeroType;
import com.idle.game.model.mongo.Player;
import com.idle.game.server.repository.HeroTypeRepository;
import com.idle.game.server.repository.PlayerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 *
 * @author rafael
 */
@SpringBootApplication
public class IdleHeroApplication {

    public static void main(String[] args) {
        SpringApplication.run(IdleHeroApplication.class, args);
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
    public CommandLineRunner init(PlayerRepository playerRepository, HeroTypeRepository heroTypeRepository) {

        return args -> {

            Player p = new Player();
            p.setName("Rafael Faita");
            p.setUser("rfaita");

            playerRepository.save(p);

            if (heroTypeRepository.findByName("Aidan") == null) {

                HeroType htAidan = new HeroType();

                htAidan.setMinBaseHp(20000);
                htAidan.setMaxBaseHp(23000);
                htAidan.setMinBaseDmg(4000);
                htAidan.setMaxBaseDmg(5000);
                htAidan.setMinBaseSpeed(550);
                htAidan.setMaxBaseSpeed(600);
                htAidan.setMinBaseArmor(570);
                htAidan.setMaxBaseArmor(590);
                htAidan.setMinBaseMagicResist(580);
                htAidan.setMaxBaseMagicResist(590);
                htAidan.setMinBaseDodgeChance(0);
                htAidan.setMaxBaseDodgeChance(0);
                htAidan.setMinBaseLuck(0);
                htAidan.setMaxBaseLuck(0);
                htAidan.setMinBaseCritChance(0);
                htAidan.setMaxBaseCritChance(0);
                htAidan.setMinBaseCritDamage(0);
                htAidan.setMaxBaseCritDamage(0);

                htAidan.setMinMaxLevelUpIncHp(400000);
                htAidan.setMaxMaxLevelUpIncHp(500000);
                htAidan.setMinMaxLevelUpIncDmg(28000);
                htAidan.setMaxMaxLevelUpIncDmg(30000);
                htAidan.setMinMaxLevelUpIncSpeed(1100);
                htAidan.setMaxMaxLevelUpIncSpeed(1200);
                htAidan.setMinMaxLevelUpIncArmor(1350);
                htAidan.setMaxMaxLevelUpIncArmor(1550);
                htAidan.setMinMaxLevelUpIncMagicResist(1400);
                htAidan.setMaxMaxLevelUpIncMagicResist(1450);
                htAidan.setMinMaxLevelUpIncDodgeChance(0);
                htAidan.setMaxMaxLevelUpIncDodgeChance(0);
                htAidan.setMinMaxLevelUpIncLuck(0);
                htAidan.setMaxMaxLevelUpIncLuck(0);
                htAidan.setMinMaxLevelUpIncCritChance(0);
                htAidan.setMaxMaxLevelUpIncCritChance(0);
                htAidan.setMinMaxLevelUpIncCritDamage(0);
                htAidan.setMaxMaxLevelUpIncCritDamage(0);

                htAidan.setName("Aidan");
                htAidan.setDamageType(DamageType.MAGIC);
                htAidan.setDistanceType(DistanceType.RANGED);
                htAidan.setHeroTypeQuality(HeroTypeQuality.BEST);

                heroTypeRepository.save(htAidan);
            }

            if (heroTypeRepository.findByName("Dominator") == null) {

                HeroType htAidan = new HeroType();

                htAidan.setMinBaseHp(35000);
                htAidan.setMaxBaseHp(38000);
                htAidan.setMinBaseDmg(2000);
                htAidan.setMaxBaseDmg(3000);
                htAidan.setMinBaseSpeed(600);
                htAidan.setMaxBaseSpeed(610);
                htAidan.setMinBaseArmor(600);
                htAidan.setMaxBaseArmor(620);
                htAidan.setMinBaseMagicResist(600);
                htAidan.setMaxBaseMagicResist(620);
                htAidan.setMinBaseDodgeChance(0);
                htAidan.setMaxBaseDodgeChance(0);
                htAidan.setMinBaseLuck(0);
                htAidan.setMaxBaseLuck(0);
                htAidan.setMinBaseCritChance(0);
                htAidan.setMaxBaseCritChance(0);
                htAidan.setMinBaseCritDamage(0);
                htAidan.setMaxBaseCritDamage(0);

                htAidan.setMinMaxLevelUpIncHp(650000);
                htAidan.setMaxMaxLevelUpIncHp(850000);
                htAidan.setMinMaxLevelUpIncDmg(12000);
                htAidan.setMaxMaxLevelUpIncDmg(15000);
                htAidan.setMinMaxLevelUpIncSpeed(1100);
                htAidan.setMaxMaxLevelUpIncSpeed(1200);
                htAidan.setMinMaxLevelUpIncArmor(1400);
                htAidan.setMaxMaxLevelUpIncArmor(1600);
                htAidan.setMinMaxLevelUpIncMagicResist(1430);
                htAidan.setMaxMaxLevelUpIncMagicResist(1570);
                htAidan.setMinMaxLevelUpIncDodgeChance(0);
                htAidan.setMaxMaxLevelUpIncDodgeChance(0);
                htAidan.setMinMaxLevelUpIncLuck(0);
                htAidan.setMaxMaxLevelUpIncLuck(0);
                htAidan.setMinMaxLevelUpIncCritChance(0);
                htAidan.setMaxMaxLevelUpIncCritChance(0);
                htAidan.setMinMaxLevelUpIncCritDamage(0);
                htAidan.setMaxMaxLevelUpIncCritDamage(0);

                htAidan.setName("Dominator");
                htAidan.setDamageType(DamageType.PHYSICAL);
                htAidan.setDistanceType(DistanceType.MELEE);
                htAidan.setHeroTypeQuality(HeroTypeQuality.POOR);

                heroTypeRepository.save(htAidan);
            }

            if (heroTypeRepository.findByName("Field") == null) {

                HeroType htAidan = new HeroType();

                htAidan.setMinBaseHp(20000);
                htAidan.setMaxBaseHp(24000);
                htAidan.setMinBaseDmg(3400);
                htAidan.setMaxBaseDmg(3600);
                htAidan.setMinBaseSpeed(580);
                htAidan.setMaxBaseSpeed(600);
                htAidan.setMinBaseArmor(600);
                htAidan.setMaxBaseArmor(620);
                htAidan.setMinBaseMagicResist(600);
                htAidan.setMaxBaseMagicResist(620);
                htAidan.setMinBaseDodgeChance(0);
                htAidan.setMaxBaseDodgeChance(0);
                htAidan.setMinBaseLuck(0);
                htAidan.setMaxBaseLuck(0);
                htAidan.setMinBaseCritChance(0);
                htAidan.setMaxBaseCritChance(0);
                htAidan.setMinBaseCritDamage(0);
                htAidan.setMaxBaseCritDamage(0);

                htAidan.setMinMaxLevelUpIncHp(300000);
                htAidan.setMaxMaxLevelUpIncHp(400000);
                htAidan.setMinMaxLevelUpIncDmg(23000);
                htAidan.setMaxMaxLevelUpIncDmg(26000);
                htAidan.setMinMaxLevelUpIncSpeed(1100);
                htAidan.setMaxMaxLevelUpIncSpeed(1200);
                htAidan.setMinMaxLevelUpIncArmor(1400);
                htAidan.setMaxMaxLevelUpIncArmor(1600);
                htAidan.setMinMaxLevelUpIncMagicResist(1530);
                htAidan.setMaxMaxLevelUpIncMagicResist(1670);
                htAidan.setMinMaxLevelUpIncDodgeChance(0);
                htAidan.setMaxMaxLevelUpIncDodgeChance(0);
                htAidan.setMinMaxLevelUpIncLuck(0);
                htAidan.setMaxMaxLevelUpIncLuck(0);
                htAidan.setMinMaxLevelUpIncCritChance(0);
                htAidan.setMaxMaxLevelUpIncCritChance(0);
                htAidan.setMinMaxLevelUpIncCritDamage(0);
                htAidan.setMaxMaxLevelUpIncCritDamage(0);

                htAidan.setName("Field");
                htAidan.setDamageType(DamageType.PHYSICAL);
                htAidan.setDistanceType(DistanceType.RANGED);
                htAidan.setHeroTypeQuality(HeroTypeQuality.AVERAGE);

                heroTypeRepository.save(htAidan);
            }

        };

    }
}
