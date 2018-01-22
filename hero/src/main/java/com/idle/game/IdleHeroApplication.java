package com.idle.game;

import com.idle.game.core.action.Action;
import com.idle.game.core.action.ActionEffect;
import com.idle.game.core.action.type.ActionType;
import com.idle.game.core.buff.BuffEffect;
import com.idle.game.core.buff.type.BuffEffectType;
import static com.idle.game.core.hero.type.HeroTypeFaction.CHAOS;
import static com.idle.game.core.hero.type.HeroTypeFaction.DARK;
import static com.idle.game.core.hero.type.HeroTypeFaction.FORTIFIED;
import static com.idle.game.core.hero.type.HeroTypeFaction.LIGHT;
import static com.idle.game.core.hero.type.HeroTypeFaction.SAVAGE;
import static com.idle.game.core.hero.type.HeroTypeFaction.SHADOW;
import static com.idle.game.core.hero.type.HeroTypeQuality.AVERAGE;
import static com.idle.game.core.hero.type.HeroTypeQuality.BEST;
import static com.idle.game.core.hero.type.HeroTypeQuality.POOR;
import static com.idle.game.core.hero.type.HeroTypeRole.ASSASSIN;
import static com.idle.game.core.hero.type.HeroTypeRole.MAGE;
import static com.idle.game.core.hero.type.HeroTypeRole.PRIEST;
import static com.idle.game.core.hero.type.HeroTypeRole.RANGER;
import static com.idle.game.core.hero.type.HeroTypeRole.WARRIOR;
import com.idle.game.core.type.AttributeType;
import com.idle.game.core.type.DamageType;
import static com.idle.game.core.type.DamageType.MAGIC;
import static com.idle.game.core.type.DamageType.PHYSICAL;
import static com.idle.game.core.type.DistanceType.MELEE;
import static com.idle.game.core.type.DistanceType.RANGED;
import com.idle.game.core.type.TargetType;
import com.idle.game.model.mongo.HeroType;
import com.idle.game.model.mongo.Player;
import com.idle.game.server.repository.HeroTypeRepository;
import com.idle.game.server.repository.PlayerRepository;
import com.idle.game.server.service.HeroTypeService;
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
    public javax.validation.Validator localValidatorFactoryBean() {
        return new LocalValidatorFactoryBean();
    }

    @Bean
    public CommandLineRunner init(PlayerRepository playerRepository,
            HeroTypeService heroTypeService,
            HeroTypeRepository heroTypeRepository) {

        return args -> {

            Player p = new Player();
            p.setName("Rafael Faita");
            p.setUser("rfaita");

            playerRepository.save(p);

            ActionEffect ae;
            ActionEffect sae1;
            ActionEffect sae2;
            ActionEffect sae3;
            
            Action specialAction;
            
            HeroType ht = new HeroType();
            ht.setName("Barea");
            ht.setFaction(CHAOS);
            ht.setRole(WARRIOR);
            ht.setQuality(BEST);
            ht.setDistanceType(MELEE);
            ht.setDamageType(PHYSICAL);
            ht.setMinMaxLevelHp(571480);
            ht.setMinMaxLevelDmg(19107);
            ht.setMinMaxLevelArmor(1958);
            ht.setMinMaxLevelSpeed(1042);
            ht.setMinMaxLevelMagicResist(1958);
            ht.setMinBaseHp(25663);
            ht.setMinBaseDmg(2526);
            ht.setMinBaseArmor(682);
            ht.setMinBaseSpeed(548);
            ht.setMinBaseMagicResist(682);
            ht.setMaxMaxLevelHp(698476);
            ht.setMaxMaxLevelDmg(23353);
            ht.setMaxMaxLevelArmor(2393);
            ht.setMaxMaxLevelSpeed(1274);
            ht.setMaxMaxLevelMagicResist(2393);
            ht.setMaxBaseHp(31365);
            ht.setMaxBaseDmg(3088);
            ht.setMaxBaseArmor(834);
            ht.setMaxBaseSpeed(670);
            ht.setMaxBaseMagicResist(834);

            if (heroTypeRepository.findByName(ht.getName()) == null) {
                heroTypeService.save(ht);
            }
            ht = new HeroType();
            ht.setName("Dantalian");
            ht.setFaction(CHAOS);
            ht.setRole(WARRIOR);
            ht.setQuality(POOR);
            ht.setDistanceType(MELEE);
            ht.setDamageType(PHYSICAL);
            ht.setMinMaxLevelHp(365637);
            ht.setMinMaxLevelDmg(20896);
            ht.setMinMaxLevelArmor(2642);
            ht.setMinMaxLevelSpeed(1037);
            ht.setMinMaxLevelMagicResist(2642);
            ht.setMinBaseHp(17088);
            ht.setMinBaseDmg(2966);
            ht.setMinBaseArmor(979);
            ht.setMinBaseSpeed(546);
            ht.setMinBaseMagicResist(979);
            ht.setMaxMaxLevelHp(446889);
            ht.setMaxMaxLevelDmg(25540);
            ht.setMaxMaxLevelArmor(3229);
            ht.setMaxMaxLevelSpeed(1267);
            ht.setMaxMaxLevelMagicResist(3229);
            ht.setMaxBaseHp(20886);
            ht.setMaxBaseDmg(3625);
            ht.setMaxBaseArmor(1197);
            ht.setMaxBaseSpeed(668);
            ht.setMaxBaseMagicResist(1197);

            if (heroTypeRepository.findByName(ht.getName()) == null) {
                heroTypeService.save(ht);
            }
            ht = new HeroType();
            ht.setName("Fat Mu");
            ht.setFaction(CHAOS);
            ht.setRole(RANGER);
            ht.setQuality(POOR);
            ht.setDistanceType(RANGED);
            ht.setDamageType(PHYSICAL);
            ht.setMinMaxLevelHp(279987);
            ht.setMinMaxLevelDmg(26558);
            ht.setMinMaxLevelArmor(1399);
            ht.setMinMaxLevelSpeed(1023);
            ht.setMinMaxLevelMagicResist(1399);
            ht.setMinBaseHp(15833);
            ht.setMinBaseDmg(3749);
            ht.setMinBaseArmor(569);
            ht.setMinBaseSpeed(532);
            ht.setMinBaseMagicResist(569);
            ht.setMaxMaxLevelHp(342207);
            ht.setMaxMaxLevelDmg(32460);
            ht.setMaxMaxLevelArmor(1709);
            ht.setMaxMaxLevelSpeed(1251);
            ht.setMaxMaxLevelMagicResist(1709);
            ht.setMaxBaseHp(19351);
            ht.setMaxBaseDmg(4582);
            ht.setMaxBaseArmor(695);
            ht.setMaxBaseSpeed(650);
            ht.setMaxBaseMagicResist(695);

            if (heroTypeRepository.findByName(ht.getName()) == null) {
                heroTypeService.save(ht);
            }
            ht = new HeroType();
            ht.setName("Gusta");
            ht.setFaction(CHAOS);
            ht.setRole(WARRIOR);
            ht.setQuality(POOR);
            ht.setDistanceType(MELEE);
            ht.setDamageType(PHYSICAL);
            ht.setMinMaxLevelHp(476697);
            ht.setMinMaxLevelDmg(16205);
            ht.setMinMaxLevelArmor(2055);
            ht.setMinMaxLevelSpeed(1040);
            ht.setMinMaxLevelMagicResist(2055);
            ht.setMinBaseHp(16385);
            ht.setMinBaseDmg(3296);
            ht.setMinBaseArmor(741);
            ht.setMinBaseSpeed(545);
            ht.setMinBaseMagicResist(741);
            ht.setMaxMaxLevelHp(582629);
            ht.setMaxMaxLevelDmg(19806);
            ht.setMaxMaxLevelArmor(2511);
            ht.setMaxMaxLevelSpeed(1271);
            ht.setMaxMaxLevelMagicResist(2511);
            ht.setMaxBaseHp(20027);
            ht.setMaxBaseDmg(4028);
            ht.setMaxBaseArmor(905);
            ht.setMaxBaseSpeed(667);
            ht.setMaxBaseMagicResist(905);

            if (heroTypeRepository.findByName(ht.getName()) == null) {
                heroTypeService.save(ht);
            }
            ht = new HeroType();
            ht.setName("Karim");
            ht.setFaction(CHAOS);
            ht.setRole(ASSASSIN);
            ht.setQuality(AVERAGE);
            ht.setDistanceType(MELEE);
            ht.setDamageType(PHYSICAL);
            ht.setMinMaxLevelHp(293849);
            ht.setMinMaxLevelDmg(28387);
            ht.setMinMaxLevelArmor(1399);
            ht.setMinMaxLevelSpeed(1052);
            ht.setMinMaxLevelMagicResist(1399);
            ht.setMinBaseHp(13577);
            ht.setMinBaseDmg(4190);
            ht.setMinBaseArmor(549);
            ht.setMinBaseSpeed(559);
            ht.setMinBaseMagicResist(549);
            ht.setMaxMaxLevelHp(359149);
            ht.setMaxMaxLevelDmg(34695);
            ht.setMaxMaxLevelArmor(1709);
            ht.setMaxMaxLevelSpeed(1286);
            ht.setMaxMaxLevelMagicResist(1709);
            ht.setMaxBaseHp(16594);
            ht.setMaxBaseDmg(5122);
            ht.setMaxBaseArmor(671);
            ht.setMaxBaseSpeed(683);
            ht.setMaxBaseMagicResist(671);

            if (heroTypeRepository.findByName(ht.getName()) == null) {
                heroTypeService.save(ht);
            }
            ht = new HeroType();
            ht.setName("Lord Balrog");
            ht.setFaction(CHAOS);
            ht.setRole(WARRIOR);
            ht.setQuality(AVERAGE);
            ht.setDistanceType(MELEE);
            ht.setDamageType(PHYSICAL);
            ht.setMinMaxLevelHp(557116);
            ht.setMinMaxLevelDmg(13640);
            ht.setMinMaxLevelArmor(1958);
            ht.setMinMaxLevelSpeed(1037);
            ht.setMinMaxLevelMagicResist(1958);
            ht.setMinBaseHp(26345);
            ht.setMinBaseDmg(2079);
            ht.setMinBaseArmor(751);
            ht.setMinBaseSpeed(543);
            ht.setMinBaseMagicResist(751);
            ht.setMaxMaxLevelHp(680920);
            ht.setMaxMaxLevelDmg(16672);
            ht.setMaxMaxLevelArmor(2393);
            ht.setMaxMaxLevelSpeed(1267);
            ht.setMaxMaxLevelMagicResist(2393);
            ht.setMaxBaseHp(32199);
            ht.setMaxBaseDmg(2541);
            ht.setMaxBaseArmor(917);
            ht.setMaxBaseSpeed(663);
            ht.setMaxBaseMagicResist(917);

            if (heroTypeRepository.findByName(ht.getName()) == null) {
                heroTypeService.save(ht);
            }
            ht = new HeroType();
            ht.setName("Margaret");
            ht.setFaction(CHAOS);
            ht.setRole(MAGE);
            ht.setQuality(AVERAGE);
            ht.setDistanceType(RANGED);
            ht.setDamageType(MAGIC);
            ht.setMinMaxLevelHp(262555);
            ht.setMinMaxLevelDmg(22568);
            ht.setMinMaxLevelArmor(1352);
            ht.setMinMaxLevelSpeed(1013);
            ht.setMinMaxLevelMagicResist(1352);
            ht.setMinBaseHp(13577);
            ht.setMinBaseDmg(3492);
            ht.setMinBaseArmor(549);
            ht.setMinBaseSpeed(522);
            ht.setMinBaseMagicResist(549);
            ht.setMaxMaxLevelHp(320901);
            ht.setMaxMaxLevelDmg(27583);
            ht.setMaxMaxLevelArmor(1652);
            ht.setMaxMaxLevelSpeed(1239);
            ht.setMaxMaxLevelMagicResist(1652);
            ht.setMaxBaseHp(16594);
            ht.setMaxBaseDmg(4268);
            ht.setMaxBaseArmor(671);
            ht.setMaxBaseSpeed(638);
            ht.setMaxBaseMagicResist(671);

            if (heroTypeRepository.findByName(ht.getName()) == null) {
                heroTypeService.save(ht);
            }
            ht = new HeroType();
            ht.setName("Queen");
            ht.setFaction(CHAOS);
            ht.setRole(RANGER);
            ht.setQuality(BEST);
            ht.setDistanceType(RANGED);
            ht.setDamageType(PHYSICAL);
            ht.setMinMaxLevelHp(358453);
            ht.setMinMaxLevelDmg(30017);
            ht.setMinMaxLevelArmor(1399);
            ht.setMinMaxLevelSpeed(1023);
            ht.setMinMaxLevelMagicResist(1399);
            ht.setMinBaseHp(19326);
            ht.setMinBaseDmg(3749);
            ht.setMinBaseArmor(569);
            ht.setMinBaseSpeed(529);
            ht.setMinBaseMagicResist(569);
            ht.setMaxMaxLevelHp(438109);
            ht.setMaxMaxLevelDmg(36687);
            ht.setMaxMaxLevelArmor(1709);
            ht.setMaxMaxLevelSpeed(1251);
            ht.setMaxMaxLevelMagicResist(1709);
            ht.setMaxBaseHp(23620);
            ht.setMaxBaseDmg(4582);
            ht.setMaxBaseArmor(695);
            ht.setMaxBaseSpeed(647);
            ht.setMaxBaseMagicResist(695);

            if (heroTypeRepository.findByName(ht.getName()) == null) {
                heroTypeService.save(ht);
            }
            ht = new HeroType();
            ht.setName("Skerei");
            ht.setFaction(CHAOS);
            ht.setRole(MAGE);
            ht.setQuality(AVERAGE);
            ht.setDistanceType(RANGED);
            ht.setDamageType(MAGIC);
            ht.setMinMaxLevelHp(390807);
            ht.setMinMaxLevelDmg(21846);
            ht.setMinMaxLevelArmor(1352);
            ht.setMinMaxLevelSpeed(1031);
            ht.setMinMaxLevelMagicResist(1352);
            ht.setMinBaseHp(20294);
            ht.setMinBaseDmg(3335);
            ht.setMinBaseArmor(549);
            ht.setMinBaseSpeed(533);
            ht.setMinBaseMagicResist(549);
            ht.setMaxMaxLevelHp(477653);
            ht.setMaxMaxLevelDmg(26700);
            ht.setMaxMaxLevelArmor(1652);
            ht.setMaxMaxLevelSpeed(1260);
            ht.setMaxMaxLevelMagicResist(1652);
            ht.setMaxBaseHp(24804);
            ht.setMaxBaseDmg(4077);
            ht.setMaxBaseArmor(671);
            ht.setMaxBaseSpeed(651);
            ht.setMaxBaseMagicResist(671);

            if (heroTypeRepository.findByName(ht.getName()) == null) {
                heroTypeService.save(ht);
            }
            ht = new HeroType();
            ht.setName("Amuvor");
            ht.setFaction(DARK);
            ht.setRole(ASSASSIN);
            ht.setQuality(BEST);
            ht.setDistanceType(MELEE);
            ht.setDamageType(PHYSICAL);
            ht.setMinMaxLevelHp(435599);
            ht.setMinMaxLevelDmg(24820);
            ht.setMinMaxLevelArmor(1399);
            ht.setMinMaxLevelSpeed(1109);
            ht.setMinMaxLevelMagicResist(1399);
            ht.setMinBaseHp(20506);
            ht.setMinBaseDmg(3570);
            ht.setMinBaseArmor(569);
            ht.setMinBaseSpeed(580);
            ht.setMinBaseMagicResist(569);
            ht.setMaxMaxLevelHp(532399);
            ht.setMaxMaxLevelDmg(30336);
            ht.setMaxMaxLevelArmor(1709);
            ht.setMaxMaxLevelSpeed(1355);
            ht.setMaxMaxLevelMagicResist(1709);
            ht.setMaxBaseHp(25062);
            ht.setMaxBaseDmg(4364);
            ht.setMaxBaseArmor(695);
            ht.setMaxBaseSpeed(708);
            ht.setMaxBaseMagicResist(695);

            if (heroTypeRepository.findByName(ht.getName()) == null) {
                heroTypeService.save(ht);
            }
            ht = new HeroType();
            ht.setName("Dark Arthindol");
            ht.setFaction(DARK);
            ht.setRole(MAGE);
            ht.setQuality(BEST);
            ht.setDistanceType(RANGED);
            ht.setDamageType(MAGIC);
            ht.setMinMaxLevelHp(374039);
            ht.setMinMaxLevelDmg(21641);
            ht.setMinMaxLevelArmor(1352);
            ht.setMinMaxLevelSpeed(1069);
            ht.setMinMaxLevelMagicResist(1352);
            ht.setMinBaseHp(17492);
            ht.setMinBaseDmg(3296);
            ht.setMinBaseArmor(549);
            ht.setMinBaseSpeed(560);
            ht.setMinBaseMagicResist(549);
            ht.setMaxMaxLevelHp(457159);
            ht.setMaxMaxLevelDmg(26450);
            ht.setMaxMaxLevelArmor(1652);
            ht.setMaxMaxLevelSpeed(1307);
            ht.setMaxMaxLevelMagicResist(1652);
            ht.setMaxBaseHp(21380);
            ht.setMaxBaseDmg(4028);
            ht.setMaxBaseArmor(671);
            ht.setMaxBaseSpeed(684);
            ht.setMaxBaseMagicResist(671);

            if (heroTypeRepository.findByName(ht.getName()) == null) {
                heroTypeService.save(ht);
            }
            ht = new HeroType();
            ht.setName("Das Moge");
            ht.setFaction(DARK);
            ht.setRole(RANGER);
            ht.setQuality(AVERAGE);
            ht.setDistanceType(RANGED);
            ht.setDamageType(PHYSICAL);
            ht.setMinMaxLevelHp(437340);
            ht.setMinMaxLevelDmg(22866);
            ht.setMinMaxLevelArmor(1399);
            ht.setMinMaxLevelSpeed(1031);
            ht.setMinMaxLevelMagicResist(1399);
            ht.setMinBaseHp(21001);
            ht.setMinBaseDmg(3884);
            ht.setMinBaseArmor(569);
            ht.setMinBaseSpeed(540);
            ht.setMinBaseMagicResist(569);
            ht.setMaxMaxLevelHp(534526);
            ht.setMaxMaxLevelDmg(27948);
            ht.setMaxMaxLevelArmor(1709);
            ht.setMaxMaxLevelSpeed(1260);
            ht.setMaxMaxLevelMagicResist(1709);
            ht.setMaxBaseHp(25667);
            ht.setMaxBaseDmg(4748);
            ht.setMaxBaseArmor(695);
            ht.setMaxBaseSpeed(660);
            ht.setMaxBaseMagicResist(695);

            if (heroTypeRepository.findByName(ht.getName()) == null) {
                heroTypeService.save(ht);
            }
            ht = new HeroType();
            ht.setName("Sleepless");
            ht.setFaction(DARK);
            ht.setRole(WARRIOR);
            ht.setQuality(POOR);
            ht.setDistanceType(MELEE);
            ht.setDamageType(PHYSICAL);
            ht.setMinMaxLevelHp(428710);
            ht.setMinMaxLevelDmg(17431);
            ht.setMinMaxLevelArmor(1421);
            ht.setMinMaxLevelSpeed(1044);
            ht.setMinMaxLevelMagicResist(1421);
            ht.setMinBaseHp(27076);
            ht.setMinBaseDmg(2707);
            ht.setMinBaseArmor(598);
            ht.setMinBaseSpeed(548);
            ht.setMinBaseMagicResist(598);
            ht.setMaxMaxLevelHp(523978);
            ht.setMaxMaxLevelDmg(21305);
            ht.setMaxMaxLevelArmor(1737);
            ht.setMaxMaxLevelSpeed(1276);
            ht.setMaxMaxLevelMagicResist(1737);
            ht.setMaxBaseHp(33092);
            ht.setMaxBaseDmg(3309);
            ht.setMaxBaseArmor(730);
            ht.setMaxBaseSpeed(670);
            ht.setMaxBaseMagicResist(730);

            if (heroTypeRepository.findByName(ht.getName()) == null) {
                heroTypeService.save(ht);
            }
            ht = new HeroType();
            ht.setName("Bleecker");
            ht.setFaction(FORTIFIED);
            ht.setRole(MAGE);
            ht.setQuality(POOR);
            ht.setDistanceType(RANGED);
            ht.setDamageType(MAGIC);
            ht.setMinMaxLevelHp(305987);
            ht.setMinMaxLevelDmg(27050);
            ht.setMinMaxLevelArmor(1421);
            ht.setMinMaxLevelSpeed(1011);
            ht.setMinMaxLevelMagicResist(1421);
            ht.setMinBaseHp(16104);
            ht.setMinBaseDmg(3955);
            ht.setMinBaseArmor(579);
            ht.setMinBaseSpeed(519);
            ht.setMinBaseMagicResist(579);
            ht.setMaxMaxLevelHp(373985);
            ht.setMaxMaxLevelDmg(33062);
            ht.setMaxMaxLevelArmor(1737);
            ht.setMaxMaxLevelSpeed(1235);
            ht.setMaxMaxLevelMagicResist(1737);
            ht.setMaxBaseHp(19682);
            ht.setMaxBaseDmg(4833);
            ht.setMaxBaseArmor(707);
            ht.setMaxBaseSpeed(635);
            ht.setMaxBaseMagicResist(707);

            if (heroTypeRepository.findByName(ht.getName()) == null) {
                heroTypeService.save(ht);
            }
            ht = new HeroType();
            ht.setName("Emily");
            ht.setFaction(FORTIFIED);
            ht.setRole(PRIEST);
            ht.setQuality(BEST);
            ht.setDistanceType(RANGED);
            ht.setDamageType(MAGIC);
            ht.setMinMaxLevelHp(413532);
            ht.setMinMaxLevelDmg(18454);
            ht.setMinMaxLevelArmor(1468);
            ht.setMinMaxLevelSpeed(1042);
            ht.setMinMaxLevelMagicResist(1468);
            ht.setMinBaseHp(21824);
            ht.setMinBaseDmg(2884);
            ht.setMinBaseArmor(598);
            ht.setMinBaseSpeed(523);
            ht.setMinBaseMagicResist(598);
            ht.setMaxMaxLevelHp(505428);
            ht.setMaxMaxLevelDmg(22554);
            ht.setMaxMaxLevelArmor(1794);
            ht.setMaxMaxLevelSpeed(1274);
            ht.setMaxMaxLevelMagicResist(1794);
            ht.setMaxBaseHp(26674);
            ht.setMaxBaseDmg(3524);
            ht.setMaxBaseArmor(730);
            ht.setMaxBaseSpeed(639);
            ht.setMaxBaseMagicResist(730);

            if (heroTypeRepository.findByName(ht.getName()) == null) {
                heroTypeService.save(ht);
            }
            ht = new HeroType();
            ht.setName("Flame Strike");
            ht.setFaction(FORTIFIED);
            ht.setRole(MAGE);
            ht.setQuality(AVERAGE);
            ht.setDistanceType(RANGED);
            ht.setDamageType(MAGIC);
            ht.setMinMaxLevelHp(526962);
            ht.setMinMaxLevelDmg(24611);
            ht.setMinMaxLevelArmor(1421);
            ht.setMinMaxLevelSpeed(1011);
            ht.setMinMaxLevelMagicResist(1421);
            ht.setMinBaseHp(22342);
            ht.setMinBaseDmg(3280);
            ht.setMinBaseArmor(579);
            ht.setMinBaseSpeed(517);
            ht.setMinBaseMagicResist(579);
            ht.setMaxMaxLevelHp(644064);
            ht.setMaxMaxLevelDmg(30081);
            ht.setMaxMaxLevelArmor(1737);
            ht.setMaxMaxLevelSpeed(1235);
            ht.setMaxMaxLevelMagicResist(1737);
            ht.setMaxBaseHp(27306);
            ht.setMaxBaseDmg(4008);
            ht.setMaxBaseArmor(707);
            ht.setMaxBaseSpeed(631);
            ht.setMaxBaseMagicResist(707);

            if (heroTypeRepository.findByName(ht.getName()) == null) {
                heroTypeService.save(ht);
            }
            ht = new HeroType();
            ht.setName("Honor Guard");
            ht.setFaction(FORTIFIED);
            ht.setRole(WARRIOR);
            ht.setQuality(POOR);
            ht.setDistanceType(MELEE);
            ht.setDamageType(PHYSICAL);
            ht.setMinMaxLevelHp(583668);
            ht.setMinMaxLevelDmg(12871);
            ht.setMinMaxLevelArmor(2310);
            ht.setMinMaxLevelSpeed(1037);
            ht.setMinMaxLevelMagicResist(2310);
            ht.setMinBaseHp(26816);
            ht.setMinBaseDmg(1883);
            ht.setMinBaseArmor(828);
            ht.setMinBaseSpeed(543);
            ht.setMinBaseMagicResist(828);
            ht.setMaxMaxLevelHp(713372);
            ht.setMaxMaxLevelDmg(15731);
            ht.setMaxMaxLevelArmor(2824);
            ht.setMaxMaxLevelSpeed(1267);
            ht.setMaxMaxLevelMagicResist(2824);
            ht.setMaxBaseHp(32776);
            ht.setMaxBaseDmg(2301);
            ht.setMaxBaseArmor(1012);
            ht.setMaxBaseSpeed(663);
            ht.setMaxBaseMagicResist(1012);

            if (heroTypeRepository.findByName(ht.getName()) == null) {
                heroTypeService.save(ht);
            }
            ht = new HeroType();
            ht.setName("Iceblink");
            ht.setFaction(FORTIFIED);
            ht.setRole(RANGER);
            ht.setQuality(BEST);
            ht.setDistanceType(RANGED);
            ht.setDamageType(PHYSICAL);
            ht.setMinMaxLevelHp(316914);
            ht.setMinMaxLevelDmg(20817);
            ht.setMinMaxLevelArmor(1538);
            ht.setMinMaxLevelSpeed(1026);
            ht.setMinMaxLevelMagicResist(1538);
            ht.setMinBaseHp(17088);
            ht.setMinBaseDmg(2707);
            ht.setMinBaseArmor(627);
            ht.setMinBaseSpeed(535);
            ht.setMinBaseMagicResist(627);
            ht.setMaxMaxLevelHp(387340);
            ht.setMaxMaxLevelDmg(25443);
            ht.setMaxMaxLevelArmor(1880);
            ht.setMaxMaxLevelSpeed(1254);
            ht.setMaxMaxLevelMagicResist(1880);
            ht.setMaxBaseHp(20886);
            ht.setMaxBaseDmg(3309);
            ht.setMaxBaseArmor(767);
            ht.setMaxBaseSpeed(653);
            ht.setMaxBaseMagicResist(767);

            if (heroTypeRepository.findByName(ht.getName()) == null) {
                heroTypeService.save(ht);
            }
            ht = new HeroType();
            ht.setName("Miki");
            ht.setFaction(FORTIFIED);
            ht.setRole(RANGER);
            ht.setQuality(BEST);
            ht.setDistanceType(RANGED);
            ht.setDamageType(PHYSICAL);
            ht.setMinMaxLevelHp(323174);
            ht.setMinMaxLevelDmg(21021);
            ht.setMinMaxLevelArmor(1468);
            ht.setMinMaxLevelSpeed(1080);
            ht.setMinMaxLevelMagicResist(1468);
            ht.setMinBaseHp(15774);
            ht.setMinBaseDmg(3080);
            ht.setMinBaseArmor(598);
            ht.setMinBaseSpeed(573);
            ht.setMinBaseMagicResist(598);
            ht.setMaxMaxLevelHp(394990);
            ht.setMaxMaxLevelDmg(25693);
            ht.setMaxMaxLevelArmor(1794);
            ht.setMaxMaxLevelSpeed(1320);
            ht.setMaxMaxLevelMagicResist(1794);
            ht.setMaxBaseHp(19280);
            ht.setMaxBaseDmg(3764);
            ht.setMaxBaseArmor(730);
            ht.setMaxBaseSpeed(701);
            ht.setMaxBaseMagicResist(730);

            if (heroTypeRepository.findByName(ht.getName()) == null) {
                heroTypeService.save(ht);
            }
            ht = new HeroType();
            ht.setName("Mirage");
            ht.setFaction(FORTIFIED);
            ht.setRole(ASSASSIN);
            ht.setQuality(POOR);
            ht.setDistanceType(MELEE);
            ht.setDamageType(PHYSICAL);
            ht.setMinMaxLevelHp(280497);
            ht.setMinMaxLevelDmg(26555);
            ht.setMinMaxLevelArmor(1468);
            ht.setMinMaxLevelSpeed(1049);
            ht.setMinMaxLevelMagicResist(1468);
            ht.setMinBaseHp(15774);
            ht.setMinBaseDmg(3200);
            ht.setMinBaseArmor(598);
            ht.setMinBaseSpeed(556);
            ht.setMinBaseMagicResist(598);
            ht.setMaxMaxLevelHp(342829);
            ht.setMaxMaxLevelDmg(32457);
            ht.setMaxMaxLevelArmor(1794);
            ht.setMaxMaxLevelSpeed(1283);
            ht.setMaxMaxLevelMagicResist(1794);
            ht.setMaxBaseHp(19280);
            ht.setMaxBaseDmg(3912);
            ht.setMaxBaseArmor(730);
            ht.setMaxBaseSpeed(680);
            ht.setMaxBaseMagicResist(730);

            if (heroTypeRepository.findByName(ht.getName()) == null) {
                heroTypeService.save(ht);
            }
            ht = new HeroType();
            ht.setName("OD-01");
            ht.setFaction(FORTIFIED);
            ht.setRole(MAGE);
            ht.setQuality(AVERAGE);
            ht.setDistanceType(RANGED);
            ht.setDamageType(MAGIC);
            ht.setMinMaxLevelHp(445377);
            ht.setMinMaxLevelDmg(18457);
            ht.setMinMaxLevelArmor(1468);
            ht.setMinMaxLevelSpeed(1013);
            ht.setMinMaxLevelMagicResist(1468);
            ht.setMinBaseHp(21706);
            ht.setMinBaseDmg(2589);
            ht.setMinBaseArmor(627);
            ht.setMinBaseSpeed(432);
            ht.setMinBaseMagicResist(627);
            ht.setMaxMaxLevelHp(544349);
            ht.setMaxMaxLevelDmg(22559);
            ht.setMaxMaxLevelArmor(1794);
            ht.setMaxMaxLevelSpeed(1239);
            ht.setMaxMaxLevelMagicResist(1794);
            ht.setMaxBaseHp(26530);
            ht.setMaxBaseDmg(3165);
            ht.setMaxBaseArmor(767);
            ht.setMaxBaseSpeed(528);
            ht.setMaxBaseMagicResist(767);

            if (heroTypeRepository.findByName(ht.getName()) == null) {
                heroTypeService.save(ht);
            }
            ht = new HeroType();
            ht.setName("Ormus");
            ht.setFaction(FORTIFIED);
            ht.setRole(PRIEST);
            ht.setQuality(BEST);
            ht.setDistanceType(RANGED);
            ht.setDamageType(MAGIC);
            ht.setMinMaxLevelHp(389057);
            ht.setMinMaxLevelDmg(23715);
            ht.setMinMaxLevelArmor(1468);
            ht.setMinMaxLevelSpeed(997);
            ht.setMinMaxLevelMagicResist(1468);
            ht.setMinBaseHp(17492);
            ht.setMinBaseDmg(3460);
            ht.setMinBaseArmor(598);
            ht.setMinBaseSpeed(510);
            ht.setMinBaseMagicResist(598);
            ht.setMaxMaxLevelHp(475515);
            ht.setMaxMaxLevelDmg(28985);
            ht.setMaxMaxLevelArmor(1794);
            ht.setMaxMaxLevelSpeed(1219);
            ht.setMaxMaxLevelMagicResist(1794);
            ht.setMaxBaseHp(21380);
            ht.setMaxBaseDmg(4228);
            ht.setMaxBaseArmor(730);
            ht.setMaxBaseSpeed(624);
            ht.setMaxBaseMagicResist(730);

            if (heroTypeRepository.findByName(ht.getName()) == null) {
                heroTypeService.save(ht);
            }
            ht = new HeroType();
            ht.setName("Sigmund");
            ht.setFaction(FORTIFIED);
            ht.setRole(WARRIOR);
            ht.setQuality(AVERAGE);
            ht.setDistanceType(MELEE);
            ht.setDamageType(PHYSICAL);
            ht.setMinMaxLevelHp(455989);
            ht.setMinMaxLevelDmg(19071);
            ht.setMinMaxLevelArmor(2854);
            ht.setMinMaxLevelSpeed(1042);
            ht.setMinMaxLevelMagicResist(2854);
            ht.setMinBaseHp(23691);
            ht.setMinBaseDmg(2502);
            ht.setMinBaseArmor(839);
            ht.setMinBaseSpeed(548);
            ht.setMinBaseMagicResist(839);
            ht.setMaxMaxLevelHp(557319);
            ht.setMaxMaxLevelDmg(23309);
            ht.setMaxMaxLevelArmor(3488);
            ht.setMaxMaxLevelSpeed(1274);
            ht.setMaxMaxLevelMagicResist(3488);
            ht.setMaxBaseHp(28955);
            ht.setMaxBaseDmg(3058);
            ht.setMaxBaseArmor(1025);
            ht.setMaxBaseSpeed(670);
            ht.setMaxBaseMagicResist(1025);

            if (heroTypeRepository.findByName(ht.getName()) == null) {
                heroTypeService.save(ht);
            }
            ht = new HeroType();
            ht.setName("Asmodel");
            ht.setFaction(LIGHT);
            ht.setRole(WARRIOR);
            ht.setQuality(BEST);
            ht.setDistanceType(MELEE);
            ht.setDamageType(PHYSICAL);
            ht.setMinMaxLevelHp(512027);
            ht.setMinMaxLevelDmg(16926);
            ht.setMinMaxLevelArmor(1445);
            ht.setMinMaxLevelSpeed(1045);
            ht.setMinMaxLevelMagicResist(1445);
            ht.setMinBaseHp(24013);
            ht.setMinBaseDmg(2393);
            ht.setMinBaseArmor(598);
            ht.setMinBaseSpeed(548);
            ht.setMinBaseMagicResist(598);
            ht.setMaxMaxLevelHp(625811);
            ht.setMaxMaxLevelDmg(20688);
            ht.setMaxMaxLevelArmor(1766);
            ht.setMaxMaxLevelSpeed(1277);
            ht.setMaxMaxLevelMagicResist(1766);
            ht.setMaxBaseHp(29349);
            ht.setMaxBaseDmg(2925);
            ht.setMaxBaseArmor(730);
            ht.setMaxBaseSpeed(670);
            ht.setMaxBaseMagicResist(730);

            if (heroTypeRepository.findByName(ht.getName()) == null) {
                heroTypeService.save(ht);
            }
            ht = new HeroType();
            ht.setName("Faith Blade");
            ht.setFaction(LIGHT);
            ht.setRole(ASSASSIN);
            ht.setQuality(BEST);
            ht.setDistanceType(MELEE);
            ht.setDamageType(PHYSICAL);
            ht.setMinMaxLevelHp(335076);
            ht.setMinMaxLevelDmg(25852);
            ht.setMinMaxLevelArmor(1399);
            ht.setMinMaxLevelSpeed(1109);
            ht.setMinMaxLevelMagicResist(1399);
            ht.setMinBaseHp(17088);
            ht.setMinBaseDmg(3570);
            ht.setMinBaseArmor(569);
            ht.setMinBaseSpeed(580);
            ht.setMinBaseMagicResist(569);
            ht.setMaxMaxLevelHp(409538);
            ht.setMaxMaxLevelDmg(31596);
            ht.setMaxMaxLevelArmor(1709);
            ht.setMaxMaxLevelSpeed(1355);
            ht.setMaxMaxLevelMagicResist(1709);
            ht.setMaxBaseHp(20886);
            ht.setMaxBaseDmg(4364);
            ht.setMaxBaseArmor(695);
            ht.setMaxBaseSpeed(708);
            ht.setMaxBaseMagicResist(695);

            if (heroTypeRepository.findByName(ht.getName()) == null) {
                heroTypeService.save(ht);
            }
            ht = new HeroType();
            ht.setName("Gerke");
            ht.setFaction(LIGHT);
            ht.setRole(PRIEST);
            ht.setQuality(POOR);
            ht.setDistanceType(RANGED);
            ht.setDamageType(MAGIC);
            ht.setMinMaxLevelHp(434465);
            ht.setMinMaxLevelDmg(21151);
            ht.setMinMaxLevelArmor(1399);
            ht.setMinMaxLevelSpeed(997);
            ht.setMinMaxLevelMagicResist(1399);
            ht.setMinBaseHp(24863);
            ht.setMinBaseDmg(2661);
            ht.setMinBaseArmor(598);
            ht.setMinBaseSpeed(510);
            ht.setMinBaseMagicResist(598);
            ht.setMaxMaxLevelHp(531013);
            ht.setMaxMaxLevelDmg(25851);
            ht.setMaxMaxLevelArmor(1709);
            ht.setMaxMaxLevelSpeed(1219);
            ht.setMaxMaxLevelMagicResist(1709);
            ht.setMaxBaseHp(30389);
            ht.setMaxBaseDmg(3253);
            ht.setMaxBaseArmor(730);
            ht.setMaxBaseSpeed(624);
            ht.setMaxBaseMagicResist(730);
            
            ae = new ActionEffect(ActionType.DMG, TargetType.RANDOM, 158, MAGIC);
            sae1 = new ActionEffect(ActionType.HEAL, TargetType.LESS_PERC_LIFE, 420, MAGIC, Boolean.TRUE);
            
            specialAction = new Action();
            specialAction.setMainActionEffect(ae);
            specialAction.addSecundaryActionsEffects(sae1);
            specialAction.setSpecial(Boolean.TRUE);

            ht.setSpecialAction(specialAction);
            
            if (heroTypeRepository.findByName(ht.getName()) == null) {
                heroTypeService.save(ht);
            }
            ht = new HeroType();
            ht.setName("Michelle");
            ht.setFaction(LIGHT);
            ht.setRole(RANGER);
            ht.setQuality(AVERAGE);
            ht.setDistanceType(RANGED);
            ht.setDamageType(PHYSICAL);
            ht.setMinMaxLevelHp(454645);
            ht.setMinMaxLevelDmg(17590);
            ht.setMinMaxLevelArmor(1399);
            ht.setMinMaxLevelSpeed(1069);
            ht.setMinMaxLevelMagicResist(1399);
            ht.setMinBaseHp(26369);
            ht.setMinBaseDmg(2786);
            ht.setMinBaseArmor(569);
            ht.setMinBaseSpeed(561);
            ht.setMinBaseMagicResist(569);
            ht.setMaxMaxLevelHp(555677);
            ht.setMaxMaxLevelDmg(21498);
            ht.setMaxMaxLevelArmor(1709);
            ht.setMaxMaxLevelSpeed(1307);
            ht.setMaxMaxLevelMagicResist(1709);
            ht.setMaxBaseHp(32229);
            ht.setMaxBaseDmg(3405);
            ht.setMaxBaseArmor(695);
            ht.setMaxBaseSpeed(685);
            ht.setMaxBaseMagicResist(695);

            if (heroTypeRepository.findByName(ht.getName()) == null) {
                heroTypeService.save(ht);
            }
            ht = new HeroType();
            ht.setName("Demon Hunter");
            ht.setFaction(SAVAGE);
            ht.setRole(RANGER);
            ht.setQuality(BEST);
            ht.setDistanceType(RANGED);
            ht.setDamageType(PHYSICAL);
            ht.setMinMaxLevelHp(307177);
            ht.setMinMaxLevelDmg(16915);
            ht.setMinMaxLevelArmor(1399);
            ht.setMinMaxLevelSpeed(1054);
            ht.setMinMaxLevelMagicResist(1399);
            ht.setMinBaseHp(18462);
            ht.setMinBaseDmg(2471);
            ht.setMinBaseArmor(569);
            ht.setMinBaseSpeed(562);
            ht.setMinBaseMagicResist(569);
            ht.setMaxMaxLevelHp(375439);
            ht.setMaxMaxLevelDmg(20673);
            ht.setMaxMaxLevelArmor(1709);
            ht.setMaxMaxLevelSpeed(1288);
            ht.setMaxMaxLevelMagicResist(1709);
            ht.setMaxBaseHp(22564);
            ht.setMaxBaseDmg(3021);
            ht.setMaxBaseArmor(695);
            ht.setMaxBaseSpeed(686);
            ht.setMaxBaseMagicResist(695);

            if (heroTypeRepository.findByName(ht.getName()) == null) {
                heroTypeService.save(ht);
            }
            ht = new HeroType();
            ht.setName("Dragon Slayer");
            ht.setFaction(SAVAGE);
            ht.setRole(WARRIOR);
            ht.setQuality(POOR);
            ht.setDistanceType(MELEE);
            ht.setDamageType(PHYSICAL);
            ht.setMinMaxLevelHp(620170);
            ht.setMinMaxLevelDmg(11778);
            ht.setMinMaxLevelArmor(2128);
            ht.setMinMaxLevelSpeed(1037);
            ht.setMinMaxLevelMagicResist(2128);
            ht.setMinBaseHp(34939);
            ht.setMinBaseDmg(1726);
            ht.setMinBaseArmor(598);
            ht.setMinBaseSpeed(543);
            ht.setMinBaseMagicResist(598);
            ht.setMaxMaxLevelHp(757986);
            ht.setMaxMaxLevelDmg(14396);
            ht.setMaxMaxLevelArmor(2600);
            ht.setMaxMaxLevelSpeed(1267);
            ht.setMaxMaxLevelMagicResist(2600);
            ht.setMaxBaseHp(42703);
            ht.setMaxBaseDmg(2110);
            ht.setMaxBaseArmor(730);
            ht.setMaxBaseSpeed(663);
            ht.setMaxBaseMagicResist(730);

            if (heroTypeRepository.findByName(ht.getName()) == null) {
                heroTypeService.save(ht);
            }
            ht = new HeroType();
            ht.setName("Eddga");
            ht.setFaction(SAVAGE);
            ht.setRole(WARRIOR);
            ht.setQuality(POOR);
            ht.setDistanceType(MELEE);
            ht.setDamageType(PHYSICAL);
            ht.setMinMaxLevelHp(356910);
            ht.setMinMaxLevelDmg(15890);
            ht.setMinMaxLevelArmor(1468);
            ht.setMinMaxLevelSpeed(1037);
            ht.setMinMaxLevelMagicResist(1468);
            ht.setMinBaseHp(21621);
            ht.setMinBaseDmg(2335);
            ht.setMinBaseArmor(598);
            ht.setMinBaseSpeed(541);
            ht.setMinBaseMagicResist(598);
            ht.setMaxMaxLevelHp(436224);
            ht.setMaxMaxLevelDmg(19421);
            ht.setMaxMaxLevelArmor(1794);
            ht.setMaxMaxLevelSpeed(1267);
            ht.setMaxMaxLevelMagicResist(1794);
            ht.setMaxBaseHp(26425);
            ht.setMaxBaseDmg(2853);
            ht.setMaxBaseArmor(730);
            ht.setMaxBaseSpeed(661);
            ht.setMaxBaseMagicResist(730);

            if (heroTypeRepository.findByName(ht.getName()) == null) {
                heroTypeService.save(ht);
            }
            ht = new HeroType();
            ht.setName("Faceless");
            ht.setFaction(SAVAGE);
            ht.setRole(ASSASSIN);
            ht.setQuality(POOR);
            ht.setDistanceType(MELEE);
            ht.setDamageType(PHYSICAL);
            ht.setMinMaxLevelHp(271274);
            ht.setMinMaxLevelDmg(19479);
            ht.setMinMaxLevelArmor(1399);
            ht.setMinMaxLevelSpeed(1049);
            ht.setMinMaxLevelMagicResist(1399);
            ht.setMinBaseHp(15833);
            ht.setMinBaseDmg(2884);
            ht.setMinBaseArmor(569);
            ht.setMinBaseSpeed(556);
            ht.setMinBaseMagicResist(569);
            ht.setMaxMaxLevelHp(331557);
            ht.setMaxMaxLevelDmg(23807);
            ht.setMaxMaxLevelArmor(1709);
            ht.setMaxMaxLevelSpeed(1283);
            ht.setMaxMaxLevelMagicResist(1709);
            ht.setMaxBaseHp(19351);
            ht.setMaxBaseDmg(3524);
            ht.setMaxBaseArmor(695);
            ht.setMaxBaseSpeed(680);
            ht.setMaxBaseMagicResist(695);

            if (heroTypeRepository.findByName(ht.getName()) == null) {
                heroTypeService.save(ht);
            }
            ht = new HeroType();
            ht.setName("Groo");
            ht.setFaction(SAVAGE);
            ht.setRole(WARRIOR);
            ht.setQuality(AVERAGE);
            ht.setDistanceType(MELEE);
            ht.setDamageType(PHYSICAL);
            ht.setMinMaxLevelHp(530941);
            ht.setMinMaxLevelDmg(14716);
            ht.setMinMaxLevelArmor(1468);
            ht.setMinMaxLevelSpeed(1042);
            ht.setMinMaxLevelMagicResist(1468);
            ht.setMinBaseHp(25211);
            ht.setMinBaseDmg(2060);
            ht.setMinBaseArmor(788);
            ht.setMinBaseSpeed(548);
            ht.setMinBaseMagicResist(788);
            ht.setMaxMaxLevelHp(648927);
            ht.setMaxMaxLevelDmg(17986);
            ht.setMaxMaxLevelArmor(1794);
            ht.setMaxMaxLevelSpeed(1274);
            ht.setMaxMaxLevelMagicResist(1794);
            ht.setMaxBaseHp(30813);
            ht.setMaxBaseDmg(2518);
            ht.setMaxBaseArmor(964);
            ht.setMaxBaseSpeed(670);
            ht.setMaxBaseMagicResist(964);

            if (heroTypeRepository.findByName(ht.getName()) == null) {
                heroTypeService.save(ht);
            }
            ht = new HeroType();
            ht.setName("Malassa");
            ht.setFaction(SAVAGE);
            ht.setRole(RANGER);
            ht.setQuality(AVERAGE);
            ht.setDistanceType(RANGED);
            ht.setDamageType(PHYSICAL);
            ht.setMinMaxLevelHp(302043);
            ht.setMinMaxLevelDmg(22664);
            ht.setMinMaxLevelArmor(1399);
            ht.setMinMaxLevelSpeed(1026);
            ht.setMinMaxLevelMagicResist(1399);
            ht.setMinBaseHp(18462);
            ht.setMinBaseDmg(2471);
            ht.setMinBaseArmor(569);
            ht.setMinBaseSpeed(535);
            ht.setMinBaseMagicResist(569);
            ht.setMaxMaxLevelHp(369163);
            ht.setMaxMaxLevelDmg(27700);
            ht.setMaxMaxLevelArmor(1709);
            ht.setMaxMaxLevelSpeed(1254);
            ht.setMaxMaxLevelMagicResist(1709);
            ht.setMaxBaseHp(22564);
            ht.setMaxBaseDmg(3021);
            ht.setMaxBaseArmor(695);
            ht.setMaxBaseSpeed(653);
            ht.setMaxBaseMagicResist(695);

            if (heroTypeRepository.findByName(ht.getName()) == null) {
                heroTypeService.save(ht);
            }
            ht = new HeroType();
            ht.setName("Rosa");
            ht.setFaction(SAVAGE);
            ht.setRole(PRIEST);
            ht.setQuality(AVERAGE);
            ht.setDistanceType(RANGED);
            ht.setDamageType(MAGIC);
            ht.setMinMaxLevelHp(379067);
            ht.setMinMaxLevelDmg(17424);
            ht.setMinMaxLevelArmor(1399);
            ht.setMinMaxLevelSpeed(1019);
            ht.setMinMaxLevelMagicResist(1399);
            ht.setMinBaseHp(20012);
            ht.setMinBaseDmg(3080);
            ht.setMinBaseArmor(549);
            ht.setMinBaseSpeed(543);
            ht.setMinBaseMagicResist(549);
            ht.setMaxMaxLevelHp(463304);
            ht.setMaxMaxLevelDmg(21296);
            ht.setMaxMaxLevelArmor(1709);
            ht.setMaxMaxLevelSpeed(1245);
            ht.setMaxMaxLevelMagicResist(1709);
            ht.setMaxBaseHp(24460);
            ht.setMaxBaseDmg(3764);
            ht.setMaxBaseArmor(671);
            ht.setMaxBaseSpeed(663);
            ht.setMaxBaseMagicResist(671);

            if (heroTypeRepository.findByName(ht.getName()) == null) {
                heroTypeService.save(ht);
            }
            ht = new HeroType();
            ht.setName("Starlight");
            ht.setFaction(SAVAGE);
            ht.setRole(MAGE);
            ht.setQuality(AVERAGE);
            ht.setDistanceType(RANGED);
            ht.setDamageType(MAGIC);
            ht.setMinMaxLevelHp(259483);
            ht.setMinMaxLevelDmg(28427);
            ht.setMinMaxLevelArmor(1352);
            ht.setMinMaxLevelSpeed(1011);
            ht.setMinMaxLevelMagicResist(1352);
            ht.setMinBaseHp(15656);
            ht.setMinBaseDmg(4003);
            ht.setMinBaseArmor(549);
            ht.setMinBaseSpeed(519);
            ht.setMinBaseMagicResist(549);
            ht.setMaxMaxLevelHp(317145);
            ht.setMaxMaxLevelDmg(34744);
            ht.setMaxMaxLevelArmor(1652);
            ht.setMaxMaxLevelSpeed(1235);
            ht.setMaxMaxLevelMagicResist(1652);
            ht.setMaxBaseHp(19136);
            ht.setMaxBaseDmg(4893);
            ht.setMaxBaseArmor(671);
            ht.setMaxBaseSpeed(635);
            ht.setMaxBaseMagicResist(671);

            if (heroTypeRepository.findByName(ht.getName()) == null) {
                heroTypeService.save(ht);
            }
            ht = new HeroType();
            ht.setName("Vesa");
            ht.setFaction(SAVAGE);
            ht.setRole(PRIEST);
            ht.setQuality(BEST);
            ht.setDistanceType(RANGED);
            ht.setDamageType(MAGIC);
            ht.setMinMaxLevelHp(386665);
            ht.setMinMaxLevelDmg(23599);
            ht.setMinMaxLevelArmor(1399);
            ht.setMinMaxLevelSpeed(1004);
            ht.setMinMaxLevelMagicResist(1399);
            ht.setMinBaseHp(19652);
            ht.setMinBaseDmg(3067);
            ht.setMinBaseArmor(569);
            ht.setMinBaseSpeed(505);
            ht.setMinBaseMagicResist(569);
            ht.setMaxMaxLevelHp(472591);
            ht.setMaxMaxLevelDmg(28843);
            ht.setMaxMaxLevelArmor(1709);
            ht.setMaxMaxLevelSpeed(1228);
            ht.setMaxMaxLevelMagicResist(1709);
            ht.setMaxBaseHp(24019);
            ht.setMaxBaseDmg(3749);
            ht.setMaxBaseArmor(695);
            ht.setMaxBaseSpeed(617);
            ht.setMaxBaseMagicResist(695);

            if (heroTypeRepository.findByName(ht.getName()) == null) {
                heroTypeService.save(ht);
            }
            ht = new HeroType();
            ht.setName("Aidan");
            ht.setFaction(SHADOW);
            ht.setRole(MAGE);
            ht.setQuality(AVERAGE);
            ht.setDistanceType(RANGED);
            ht.setDamageType(MAGIC);
            ht.setMinMaxLevelHp(369313);
            ht.setMinMaxLevelDmg(26199);
            ht.setMinMaxLevelArmor(1282);
            ht.setMinMaxLevelSpeed(1013);
            ht.setMinMaxLevelMagicResist(1282);
            ht.setMinBaseHp(19646);
            ht.setMinBaseDmg(4119);
            ht.setMinBaseArmor(519);
            ht.setMinBaseSpeed(522);
            ht.setMinBaseMagicResist(519);
            ht.setMaxMaxLevelHp(451383);
            ht.setMaxMaxLevelDmg(32021);
            ht.setMaxMaxLevelArmor(1566);
            ht.setMaxMaxLevelSpeed(1239);
            ht.setMaxMaxLevelMagicResist(1566);
            ht.setMaxBaseHp(24012);
            ht.setMaxBaseDmg(5035);
            ht.setMaxBaseArmor(635);
            ht.setMaxBaseSpeed(638);
            ht.setMaxBaseMagicResist(635);

            if (heroTypeRepository.findByName(ht.getName()) == null) {
                heroTypeService.save(ht);
            }
            ht = new HeroType();
            ht.setName("Baade");
            ht.setFaction(SHADOW);
            ht.setRole(WARRIOR);
            ht.setQuality(POOR);
            ht.setDistanceType(MELEE);
            ht.setDamageType(PHYSICAL);
            ht.setMinMaxLevelHp(519777);
            ht.setMinMaxLevelDmg(14872);
            ht.setMinMaxLevelArmor(1399);
            ht.setMinMaxLevelSpeed(1040);
            ht.setMinMaxLevelMagicResist(1399);
            ht.setMinBaseHp(24015);
            ht.setMinBaseDmg(2256);
            ht.setMinBaseArmor(569);
            ht.setMinBaseSpeed(545);
            ht.setMinBaseMagicResist(569);
            ht.setMaxMaxLevelHp(635283);
            ht.setMaxMaxLevelDmg(18176);
            ht.setMaxMaxLevelArmor(1709);
            ht.setMaxMaxLevelSpeed(1271);
            ht.setMaxMaxLevelMagicResist(1709);
            ht.setMaxBaseHp(29351);
            ht.setMaxBaseDmg(2758);
            ht.setMaxBaseArmor(695);
            ht.setMaxBaseSpeed(667);
            ht.setMaxBaseMagicResist(695);
            
            //Attacks the weakest enemy for 207% of Baade’s ATK, and reduces the target’s ATK by 27% for two rounds. Can’t be dodged.
            BuffEffect b1 = new BuffEffect(BuffEffectType.DECREASE_ATTRIBUTE,27, 0, 2, AttributeType.DMG);
                    
            ae = new ActionEffect(ActionType.DMG, TargetType.LESS_LIFE, 207, DamageType.PHYSICAL, Boolean.FALSE);
            ae.addBuffEffect(b1);
            
            specialAction = new Action();
            specialAction.setMainActionEffect(ae);
            specialAction.setSpecial(Boolean.TRUE);

            ht.setSpecialAction(specialAction);

            if (heroTypeRepository.findByName(ht.getName()) == null) {
                heroTypeService.save(ht);
            }
            ht = new HeroType();
            ht.setName("Blood Blade");
            ht.setFaction(SHADOW);
            ht.setRole(ASSASSIN);
            ht.setQuality(BEST);
            ht.setDistanceType(MELEE);
            ht.setDamageType(PHYSICAL);
            ht.setMinMaxLevelHp(275895);
            ht.setMinMaxLevelDmg(26004);
            ht.setMinMaxLevelArmor(1328);
            ht.setMinMaxLevelSpeed(1055);
            ht.setMinMaxLevelMagicResist(1328);
            ht.setMinBaseHp(16716);
            ht.setMinBaseDmg(3695);
            ht.setMinBaseArmor(539);
            ht.setMinBaseSpeed(562);
            ht.setMinBaseMagicResist(539);
            ht.setMaxMaxLevelHp(337205);
            ht.setMaxMaxLevelDmg(31782);
            ht.setMaxMaxLevelArmor(1624);
            ht.setMaxMaxLevelSpeed(1289);
            ht.setMaxMaxLevelMagicResist(1624);
            ht.setMaxBaseHp(20430);
            ht.setMaxBaseDmg(4517);
            ht.setMaxBaseArmor(659);
            ht.setMaxBaseSpeed(686);
            ht.setMaxBaseMagicResist(659);

            if (heroTypeRepository.findByName(ht.getName()) == null) {
                heroTypeService.save(ht);
            }
            ht = new HeroType();
            ht.setName("Corpsedemon");
            ht.setFaction(SHADOW);
            ht.setRole(WARRIOR);
            ht.setQuality(BEST);
            ht.setDistanceType(MELEE);
            ht.setDamageType(PHYSICAL);
            ht.setMinMaxLevelHp(555466);
            ht.setMinMaxLevelDmg(15686);
            ht.setMinMaxLevelArmor(1757);
            ht.setMinMaxLevelSpeed(1042);
            ht.setMinMaxLevelMagicResist(1757);
            ht.setMinBaseHp(32962);
            ht.setMinBaseDmg(2071);
            ht.setMinBaseArmor(631);
            ht.setMinBaseSpeed(541);
            ht.setMinBaseMagicResist(631);
            ht.setMaxMaxLevelHp(678902);
            ht.setMaxMaxLevelDmg(19172);
            ht.setMaxMaxLevelArmor(2147);
            ht.setMaxMaxLevelSpeed(1274);
            ht.setMaxMaxLevelMagicResist(2147);
            ht.setMaxBaseHp(40286);
            ht.setMaxBaseDmg(2531);
            ht.setMaxBaseArmor(771);
            ht.setMaxBaseSpeed(661);
            ht.setMaxBaseMagicResist(771);

            if (heroTypeRepository.findByName(ht.getName()) == null) {
                heroTypeService.save(ht);
            }
            ht = new HeroType();
            ht.setName("Dominator");
            ht.setFaction(SHADOW);
            ht.setRole(WARRIOR);
            ht.setQuality(POOR);
            ht.setDistanceType(MELEE);
            ht.setDamageType(PHYSICAL);
            ht.setMinMaxLevelHp(605224);
            ht.setMinMaxLevelDmg(12461);
            ht.setMinMaxLevelArmor(1352);
            ht.setMinMaxLevelSpeed(1037);
            ht.setMinMaxLevelMagicResist(1352);
            ht.setMinBaseHp(32962);
            ht.setMinBaseDmg(2256);
            ht.setMinBaseArmor(549);
            ht.setMinBaseSpeed(543);
            ht.setMinBaseMagicResist(549);
            ht.setMaxMaxLevelHp(739718);
            ht.setMaxMaxLevelDmg(15231);
            ht.setMaxMaxLevelArmor(1652);
            ht.setMaxMaxLevelSpeed(1267);
            ht.setMaxMaxLevelMagicResist(1652);
            ht.setMaxBaseHp(40286);
            ht.setMaxBaseDmg(2758);
            ht.setMaxBaseArmor(671);
            ht.setMaxBaseSpeed(663);
            ht.setMaxBaseMagicResist(671);

            if (heroTypeRepository.findByName(ht.getName()) == null) {
                heroTypeService.save(ht);
            }
            ht = new HeroType();
            ht.setName("Field");
            ht.setFaction(SHADOW);
            ht.setRole(RANGER);
            ht.setQuality(AVERAGE);
            ht.setDistanceType(RANGED);
            ht.setDamageType(PHYSICAL);
            ht.setMinMaxLevelHp(298966);
            ht.setMinMaxLevelDmg(22834);
            ht.setMinMaxLevelArmor(1328);
            ht.setMinMaxLevelSpeed(1026);
            ht.setMinMaxLevelMagicResist(1328);
            ht.setMinBaseHp(20601);
            ht.setMinBaseDmg(3200);
            ht.setMinBaseArmor(549);
            ht.setMinBaseSpeed(535);
            ht.setMinBaseMagicResist(549);
            ht.setMaxMaxLevelHp(365402);
            ht.setMaxMaxLevelDmg(27908);
            ht.setMaxMaxLevelArmor(1624);
            ht.setMaxMaxLevelSpeed(1254);
            ht.setMaxMaxLevelMagicResist(1624);
            ht.setMaxBaseHp(25179);
            ht.setMaxBaseDmg(3912);
            ht.setMaxBaseArmor(671);
            ht.setMaxBaseSpeed(653);
            ht.setMaxBaseMagicResist(671);

            if (heroTypeRepository.findByName(ht.getName()) == null) {
                heroTypeService.save(ht);
            }
            ht = new HeroType();
            ht.setName("Kamath");
            ht.setFaction(SHADOW);
            ht.setRole(RANGER);
            ht.setQuality(BEST);
            ht.setDistanceType(RANGED);
            ht.setDamageType(PHYSICAL);
            ht.setMinMaxLevelHp(389358);
            ht.setMinMaxLevelDmg(23333);
            ht.setMinMaxLevelArmor(1622);
            ht.setMinMaxLevelSpeed(1028);
            ht.setMinMaxLevelMagicResist(1622);
            ht.setMinBaseHp(22083);
            ht.setMinBaseDmg(3519);
            ht.setMinBaseArmor(592);
            ht.setMinBaseSpeed(529);
            ht.setMinBaseMagicResist(592);
            ht.setMaxMaxLevelHp(475882);
            ht.setMaxMaxLevelDmg(28518);
            ht.setMaxMaxLevelArmor(1982);
            ht.setMaxMaxLevelSpeed(1256);
            ht.setMaxMaxLevelMagicResist(1982);
            ht.setMaxBaseHp(26991);
            ht.setMaxBaseDmg(4301);
            ht.setMaxBaseArmor(724);
            ht.setMaxBaseSpeed(647);
            ht.setMaxBaseMagicResist(724);

            if (heroTypeRepository.findByName(ht.getName()) == null) {
                heroTypeService.save(ht);
            }
            ht = new HeroType();
            ht.setName("Kharma");
            ht.setFaction(SHADOW);
            ht.setRole(PRIEST);
            ht.setQuality(AVERAGE);
            ht.setDistanceType(RANGED);
            ht.setDamageType(MAGIC);
            ht.setMinMaxLevelHp(289234);
            ht.setMinMaxLevelDmg(31225);
            ht.setMinMaxLevelArmor(1282);
            ht.setMinMaxLevelSpeed(997);
            ht.setMinMaxLevelMagicResist(1282);
            ht.setMinBaseHp(15715);
            ht.setMinBaseDmg(3955);
            ht.setMinBaseArmor(519);
            ht.setMinBaseSpeed(510);
            ht.setMinBaseMagicResist(519);
            ht.setMaxMaxLevelHp(353508);
            ht.setMaxMaxLevelDmg(38163);
            ht.setMaxMaxLevelArmor(1566);
            ht.setMaxMaxLevelSpeed(1219);
            ht.setMaxMaxLevelMagicResist(1566);
            ht.setMaxBaseHp(19207);
            ht.setMaxBaseDmg(4833);
            ht.setMaxBaseArmor(635);
            ht.setMaxBaseSpeed(624);
            ht.setMaxBaseMagicResist(635);

            if (heroTypeRepository.findByName(ht.getName()) == null) {
                heroTypeService.save(ht);
            }
            ht = new HeroType();
            ht.setName("Lutz");
            ht.setFaction(SHADOW);
            ht.setRole(ASSASSIN);
            ht.setQuality(POOR);
            ht.setDistanceType(MELEE);
            ht.setDamageType(PHYSICAL);
            ht.setMinMaxLevelHp(343806);
            ht.setMinMaxLevelDmg(24994);
            ht.setMinMaxLevelArmor(1328);
            ht.setMinMaxLevelSpeed(1049);
            ht.setMinMaxLevelMagicResist(1328);
            ht.setMinBaseHp(18069);
            ht.setMinBaseDmg(3695);
            ht.setMinBaseArmor(539);
            ht.setMinBaseSpeed(556);
            ht.setMinBaseMagicResist(539);
            ht.setMaxMaxLevelHp(420208);
            ht.setMaxMaxLevelDmg(30548);
            ht.setMaxMaxLevelArmor(1624);
            ht.setMaxMaxLevelSpeed(1283);
            ht.setMaxMaxLevelMagicResist(1624);
            ht.setMaxBaseHp(22085);
            ht.setMaxBaseDmg(4517);
            ht.setMaxBaseArmor(659);
            ht.setMaxBaseSpeed(680);
            ht.setMaxBaseMagicResist(659);

            if (heroTypeRepository.findByName(ht.getName()) == null) {
                heroTypeService.save(ht);
            }
            ht = new HeroType();
            ht.setName("Walter");
            ht.setFaction(SHADOW);
            ht.setRole(ASSASSIN);
            ht.setQuality(BEST);
            ht.setDistanceType(MELEE);
            ht.setDamageType(PHYSICAL);
            ht.setMinMaxLevelHp(262545);
            ht.setMinMaxLevelDmg(27681);
            ht.setMinMaxLevelArmor(1328);
            ht.setMinMaxLevelSpeed(1052);
            ht.setMinMaxLevelMagicResist(1328);
            ht.setMinBaseHp(15892);
            ht.setMinBaseDmg(3813);
            ht.setMinBaseArmor(539);
            ht.setMinBaseSpeed(559);
            ht.setMinBaseMagicResist(539);
            ht.setMaxMaxLevelHp(320889);
            ht.setMaxMaxLevelDmg(33833);
            ht.setMaxMaxLevelArmor(1624);
            ht.setMaxMaxLevelSpeed(1286);
            ht.setMaxMaxLevelMagicResist(1624);
            ht.setMaxBaseHp(19424);
            ht.setMaxBaseDmg(4661);
            ht.setMaxBaseArmor(659);
            ht.setMaxBaseSpeed(683);
            ht.setMaxBaseMagicResist(659);

            if (heroTypeRepository.findByName(ht.getName()) == null) {
                heroTypeService.save(ht);
            }
        };

    }
}
