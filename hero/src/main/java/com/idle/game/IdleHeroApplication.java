package com.idle.game;

import com.idle.game.core.action.Action;
import com.idle.game.core.action.ActionEffect;
import com.idle.game.core.action.type.ActionType;
import com.idle.game.core.buff.BuffEffect;
import com.idle.game.core.buff.type.BuffEffectType;
import static com.idle.game.core.formation.type.FormationPositionType.ALL_LINES;
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
import com.idle.game.core.hero.type.HeroTypeSize;
import com.idle.game.core.type.AttributeType;
import com.idle.game.core.type.DamageType;
import static com.idle.game.core.type.DamageType.FIRE;
import com.idle.game.core.type.DefenseType;
import static com.idle.game.core.type.DistanceType.MELEE;
import static com.idle.game.core.type.DistanceType.RANGED;
import com.idle.game.core.type.TargetType;
import com.idle.game.model.HeroType;
import com.idle.game.server.repository.HeroTypeRepository;
import com.idle.game.server.service.HeroTypeService;
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
public class IdleHeroApplication {

    public static void main(String[] args) {
        SpringApplication.run(IdleHeroApplication.class, args);
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
    public CommandLineRunner init(HeroTypeService heroTypeService,
            HeroTypeRepository heroTypeRepository) {

        return args -> {

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

            ht.setMinMaxLevelHp(571480);
            ht.setMinMaxLevelDmg(19107);
            ht.setMinMaxLevelAp(19107);
            ht.setMinMaxLevelDefense(DefenseType.BLUNT, 1958);
            ht.setMinMaxLevelSpeed(1042);
            ht.setMinMaxLevelDefense(DefenseType.FIRE, 1958);
            ht.setMinBaseHp(25663);
            ht.setMinBaseDmg(2526);
            ht.setMinBaseAp(2526);
            ht.setMinBaseDefense(DefenseType.BLUNT, 682);
            ht.setMinBaseSpeed(548);
            ht.setMinBaseDefense(DefenseType.FIRE, 682);
            ht.setMaxMaxLevelHp(698476);
            ht.setMaxMaxLevelDmg(23353);
            ht.setMaxMaxLevelAp(23353);
            ht.setMaxMaxLevelDefense(DefenseType.BLUNT, 2393);
            ht.setMaxMaxLevelSpeed(1274);
            ht.setMaxMaxLevelDefense(DefenseType.FIRE, 2393);
            ht.setMaxBaseHp(31365);
            ht.setMaxBaseDmg(3088);
            ht.setMaxBaseAp(3088);
            ht.setMaxBaseDefense(DefenseType.BLUNT, 834);
            ht.setMaxBaseSpeed(670);
            ht.setMaxBaseDefense(DefenseType.FIRE, 834);

            ht.setSize(HeroTypeSize.SMALL);
            if (heroTypeRepository.findByName(ht.getName()) == null) {
                heroTypeService.save(ht);
            }
            ht = new HeroType();
            ht.setName("Dantalian");
            ht.setFaction(CHAOS);
            ht.setRole(WARRIOR);
            ht.setQuality(POOR);
            ht.setDistanceType(MELEE);

            ht.setMinMaxLevelHp(365637);
            ht.setMinMaxLevelDmg(20896);
            ht.setMinMaxLevelAp(20896);
            ht.setMinMaxLevelDefense(DefenseType.BLUNT, 2642);
            ht.setMinMaxLevelSpeed(1037);
            ht.setMinMaxLevelDefense(DefenseType.FIRE, 2642);
            ht.setMinBaseHp(17088);
            ht.setMinBaseDmg(2966);
            ht.setMinBaseAp(2966);
            ht.setMinBaseDefense(DefenseType.BLUNT, 979);
            ht.setMinBaseSpeed(546);
            ht.setMinBaseDefense(DefenseType.FIRE, 979);
            ht.setMaxMaxLevelHp(446889);
            ht.setMaxMaxLevelDmg(25540);
            ht.setMaxMaxLevelAp(25540);
            ht.setMaxMaxLevelDefense(DefenseType.BLUNT, 3229);
            ht.setMaxMaxLevelSpeed(1267);
            ht.setMaxMaxLevelDefense(DefenseType.FIRE, 3229);
            ht.setMaxBaseHp(20886);
            ht.setMaxBaseDmg(3625);
            ht.setMaxBaseAp(3625);
            ht.setMaxBaseDefense(DefenseType.BLUNT, 1197);
            ht.setMaxBaseSpeed(668);
            ht.setMaxBaseDefense(DefenseType.FIRE, 1197);

            ht.setSize(HeroTypeSize.SMALL);
            if (heroTypeRepository.findByName(ht.getName()) == null) {
                heroTypeService.save(ht);
            }
            ht = new HeroType();
            ht.setName("Fat Mu");
            ht.setFaction(CHAOS);
            ht.setRole(RANGER);
            ht.setQuality(POOR);
            ht.setDistanceType(RANGED);

            ht.setMinMaxLevelHp(279987);
            ht.setMinMaxLevelDmg(26558);
            ht.setMinMaxLevelAp(26558);
            ht.setMinMaxLevelDefense(DefenseType.BLUNT, 1399);
            ht.setMinMaxLevelSpeed(1023);
            ht.setMinMaxLevelDefense(DefenseType.FIRE, 1399);
            ht.setMinBaseHp(15833);
            ht.setMinBaseDmg(3749);
            ht.setMinBaseAp(3749);
            ht.setMinBaseDefense(DefenseType.BLUNT, 569);
            ht.setMinBaseSpeed(532);
            ht.setMinBaseDefense(DefenseType.FIRE, 569);
            ht.setMaxMaxLevelHp(342207);
            ht.setMaxMaxLevelDmg(32460);
            ht.setMaxMaxLevelAp(32460);
            ht.setMaxMaxLevelDefense(DefenseType.BLUNT, 1709);
            ht.setMaxMaxLevelSpeed(1251);
            ht.setMaxMaxLevelDefense(DefenseType.FIRE, 1709);
            ht.setMaxBaseHp(19351);
            ht.setMaxBaseDmg(4582);
            ht.setMaxBaseAp(4582);
            ht.setMaxBaseDefense(DefenseType.BLUNT, 695);
            ht.setMaxBaseSpeed(650);
            ht.setMaxBaseDefense(DefenseType.FIRE, 695);

            ht.setSize(HeroTypeSize.SMALL);
            if (heroTypeRepository.findByName(ht.getName()) == null) {
                heroTypeService.save(ht);
            }
            ht = new HeroType();
            ht.setName("Gusta");
            ht.setFaction(CHAOS);
            ht.setRole(WARRIOR);
            ht.setQuality(POOR);
            ht.setDistanceType(MELEE);

            ht.setMinMaxLevelHp(476697);
            ht.setMinMaxLevelDmg(16205);
            ht.setMinMaxLevelAp(16205);
            ht.setMinMaxLevelDefense(DefenseType.BLUNT, 2055);
            ht.setMinMaxLevelSpeed(1040);
            ht.setMinMaxLevelDefense(DefenseType.FIRE, 2055);
            ht.setMinBaseHp(16385);
            ht.setMinBaseDmg(3296);
            ht.setMinBaseAp(3296);
            ht.setMinBaseDefense(DefenseType.BLUNT, 741);
            ht.setMinBaseSpeed(545);
            ht.setMinBaseDefense(DefenseType.FIRE, 741);
            ht.setMaxMaxLevelHp(582629);
            ht.setMaxMaxLevelDmg(19806);
            ht.setMaxMaxLevelAp(19806);
            ht.setMaxMaxLevelDefense(DefenseType.BLUNT, 2511);
            ht.setMaxMaxLevelSpeed(1271);
            ht.setMaxMaxLevelDefense(DefenseType.FIRE, 2511);
            ht.setMaxBaseHp(20027);
            ht.setMaxBaseDmg(4028);
            ht.setMaxBaseAp(4028);
            ht.setMaxBaseDefense(DefenseType.BLUNT, 905);
            ht.setMaxBaseSpeed(667);
            ht.setMaxBaseDefense(DefenseType.FIRE, 905);

            ht.setSize(HeroTypeSize.SMALL);
            if (heroTypeRepository.findByName(ht.getName()) == null) {
                heroTypeService.save(ht);
            }
            ht = new HeroType();
            ht.setName("Karim");
            ht.setFaction(CHAOS);
            ht.setRole(ASSASSIN);
            ht.setQuality(AVERAGE);
            ht.setDistanceType(MELEE);

            ht.setMinMaxLevelHp(293849);
            ht.setMinMaxLevelDmg(28387);
            ht.setMinMaxLevelAp(28387);
            ht.setMinMaxLevelDefense(DefenseType.BLUNT, 1399);
            ht.setMinMaxLevelSpeed(1052);
            ht.setMinMaxLevelDefense(DefenseType.FIRE, 1399);
            ht.setMinBaseHp(13577);
            ht.setMinBaseDmg(4190);
            ht.setMinBaseAp(4190);
            ht.setMinBaseDefense(DefenseType.BLUNT, 549);
            ht.setMinBaseSpeed(559);
            ht.setMinBaseDefense(DefenseType.FIRE, 549);
            ht.setMaxMaxLevelHp(359149);
            ht.setMaxMaxLevelDmg(34695);
            ht.setMaxMaxLevelAp(34695);
            ht.setMaxMaxLevelDefense(DefenseType.BLUNT, 1709);
            ht.setMaxMaxLevelSpeed(1286);
            ht.setMaxMaxLevelDefense(DefenseType.FIRE, 1709);
            ht.setMaxBaseHp(16594);
            ht.setMaxBaseDmg(5122);
            ht.setMaxBaseAp(5122);
            ht.setMaxBaseDefense(DefenseType.BLUNT, 671);
            ht.setMaxBaseSpeed(683);
            ht.setMaxBaseDefense(DefenseType.FIRE, 671);

            ht.setSize(HeroTypeSize.SMALL);
            if (heroTypeRepository.findByName(ht.getName()) == null) {
                heroTypeService.save(ht);
            }
            ht = new HeroType();
            ht.setName("Lord Balrog");
            ht.setFaction(CHAOS);
            ht.setRole(WARRIOR);
            ht.setQuality(AVERAGE);
            ht.setDistanceType(MELEE);

            ht.setMinMaxLevelHp(557116);
            ht.setMinMaxLevelDmg(13640);
            ht.setMinMaxLevelAp(13640);
            ht.setMinMaxLevelDefense(DefenseType.BLUNT, 1958);
            ht.setMinMaxLevelSpeed(1037);
            ht.setMinMaxLevelDefense(DefenseType.FIRE, 1958);
            ht.setMinBaseHp(26345);
            ht.setMinBaseDmg(2079);
            ht.setMinBaseAp(2079);
            ht.setMinBaseDefense(DefenseType.BLUNT, 751);
            ht.setMinBaseSpeed(543);
            ht.setMinBaseDefense(DefenseType.FIRE, 751);
            ht.setMaxMaxLevelHp(680920);
            ht.setMaxMaxLevelDmg(16672);
            ht.setMaxMaxLevelAp(16672);
            ht.setMaxMaxLevelDefense(DefenseType.BLUNT, 2393);
            ht.setMaxMaxLevelSpeed(1267);
            ht.setMaxMaxLevelDefense(DefenseType.FIRE, 2393);
            ht.setMaxBaseHp(32199);
            ht.setMaxBaseDmg(2541);
            ht.setMaxBaseAp(2541);
            ht.setMaxBaseDefense(DefenseType.BLUNT, 917);
            ht.setMaxBaseSpeed(663);
            ht.setMaxBaseDefense(DefenseType.FIRE, 917);

            ht.setSize(HeroTypeSize.SMALL);
            if (heroTypeRepository.findByName(ht.getName()) == null) {
                heroTypeService.save(ht);
            }
            ht = new HeroType();
            ht.setName("Margaret");
            ht.setFaction(CHAOS);
            ht.setRole(MAGE);
            ht.setQuality(AVERAGE);
            ht.setDistanceType(RANGED);

            ht.setMinMaxLevelHp(262555);
            ht.setMinMaxLevelDmg(22568);
            ht.setMinMaxLevelAp(22568);
            ht.setMinMaxLevelDefense(DefenseType.BLUNT, 1352);
            ht.setMinMaxLevelSpeed(1013);
            ht.setMinMaxLevelDefense(DefenseType.FIRE, 1352);
            ht.setMinBaseHp(13577);
            ht.setMinBaseDmg(3492);
            ht.setMinBaseAp(3492);
            ht.setMinBaseDefense(DefenseType.BLUNT, 549);
            ht.setMinBaseSpeed(522);
            ht.setMinBaseDefense(DefenseType.FIRE, 549);
            ht.setMaxMaxLevelHp(320901);
            ht.setMaxMaxLevelDmg(27583);
            ht.setMaxMaxLevelAp(27583);
            ht.setMaxMaxLevelDefense(DefenseType.BLUNT, 1652);
            ht.setMaxMaxLevelSpeed(1239);
            ht.setMaxMaxLevelDefense(DefenseType.FIRE, 1652);
            ht.setMaxBaseHp(16594);
            ht.setMaxBaseDmg(4268);
            ht.setMaxBaseAp(4268);
            ht.setMaxBaseDefense(DefenseType.BLUNT, 671);
            ht.setMaxBaseSpeed(638);
            ht.setMaxBaseDefense(DefenseType.FIRE, 671);

            ht.setSize(HeroTypeSize.SMALL);
            if (heroTypeRepository.findByName(ht.getName()) == null) {
                heroTypeService.save(ht);
            }
            ht = new HeroType();
            ht.setName("Queen");
            ht.setFaction(CHAOS);
            ht.setRole(RANGER);
            ht.setQuality(BEST);
            ht.setDistanceType(RANGED);

            ht.setMinMaxLevelHp(358453);
            ht.setMinMaxLevelDmg(30017);
            ht.setMinMaxLevelAp(30017);
            ht.setMinMaxLevelDefense(DefenseType.BLUNT, 1399);
            ht.setMinMaxLevelSpeed(1023);
            ht.setMinMaxLevelDefense(DefenseType.FIRE, 1399);
            ht.setMinBaseHp(19326);
            ht.setMinBaseDmg(3749);
            ht.setMinBaseAp(3749);
            ht.setMinBaseDefense(DefenseType.BLUNT, 569);
            ht.setMinBaseSpeed(529);
            ht.setMinBaseDefense(DefenseType.FIRE, 569);
            ht.setMaxMaxLevelHp(438109);
            ht.setMaxMaxLevelDmg(36687);
            ht.setMaxMaxLevelAp(36687);
            ht.setMaxMaxLevelDefense(DefenseType.BLUNT, 1709);
            ht.setMaxMaxLevelSpeed(1251);
            ht.setMaxMaxLevelDefense(DefenseType.FIRE, 1709);
            ht.setMaxBaseHp(23620);
            ht.setMaxBaseDmg(4582);
            ht.setMaxBaseAp(4582);
            ht.setMaxBaseDefense(DefenseType.BLUNT, 695);
            ht.setMaxBaseSpeed(647);
            ht.setMaxBaseDefense(DefenseType.FIRE, 695);

            ht.setSize(HeroTypeSize.SMALL);
            if (heroTypeRepository.findByName(ht.getName()) == null) {
                heroTypeService.save(ht);
            }
            ht = new HeroType();
            ht.setName("Skerei");
            ht.setFaction(CHAOS);
            ht.setRole(MAGE);
            ht.setQuality(AVERAGE);
            ht.setDistanceType(RANGED);

            ht.setMinMaxLevelHp(390807);
            ht.setMinMaxLevelDmg(21846);
            ht.setMinMaxLevelAp(21846);
            ht.setMinMaxLevelDefense(DefenseType.BLUNT, 1352);
            ht.setMinMaxLevelSpeed(1031);
            ht.setMinMaxLevelDefense(DefenseType.FIRE, 1352);
            ht.setMinBaseHp(20294);
            ht.setMinBaseDmg(3335);
            ht.setMinBaseAp(3335);
            ht.setMinBaseDefense(DefenseType.BLUNT, 549);
            ht.setMinBaseSpeed(533);
            ht.setMinBaseDefense(DefenseType.FIRE, 549);
            ht.setMaxMaxLevelHp(477653);
            ht.setMaxMaxLevelDmg(26700);
            ht.setMaxMaxLevelAp(26700);
            ht.setMaxMaxLevelDefense(DefenseType.BLUNT, 1652);
            ht.setMaxMaxLevelSpeed(1260);
            ht.setMaxMaxLevelDefense(DefenseType.FIRE, 1652);
            ht.setMaxBaseHp(24804);
            ht.setMaxBaseDmg(4077);
            ht.setMaxBaseAp(4077);
            ht.setMaxBaseDefense(DefenseType.BLUNT, 671);
            ht.setMaxBaseSpeed(651);
            ht.setMaxBaseDefense(DefenseType.FIRE, 671);

            ht.setSize(HeroTypeSize.SMALL);
            if (heroTypeRepository.findByName(ht.getName()) == null) {
                heroTypeService.save(ht);
            }
            ht = new HeroType();
            ht.setName("Amuvor");
            ht.setFaction(DARK);
            ht.setRole(ASSASSIN);
            ht.setQuality(BEST);
            ht.setDistanceType(MELEE);

            ht.setMinMaxLevelHp(435599);
            ht.setMinMaxLevelDmg(24820);
            ht.setMinMaxLevelAp(24820);
            ht.setMinMaxLevelDefense(DefenseType.BLUNT, 1399);
            ht.setMinMaxLevelSpeed(1109);
            ht.setMinMaxLevelDefense(DefenseType.FIRE, 1399);
            ht.setMinBaseHp(20506);
            ht.setMinBaseDmg(3570);
            ht.setMinBaseAp(3570);
            ht.setMinBaseDefense(DefenseType.BLUNT, 569);
            ht.setMinBaseSpeed(580);
            ht.setMinBaseDefense(DefenseType.FIRE, 569);
            ht.setMaxMaxLevelHp(532399);
            ht.setMaxMaxLevelDmg(30336);
            ht.setMaxMaxLevelAp(30336);
            ht.setMaxMaxLevelDefense(DefenseType.BLUNT, 1709);
            ht.setMaxMaxLevelSpeed(1355);
            ht.setMaxMaxLevelDefense(DefenseType.FIRE, 1709);
            ht.setMaxBaseHp(25062);
            ht.setMaxBaseDmg(4364);
            ht.setMaxBaseAp(4364);
            ht.setMaxBaseDefense(DefenseType.BLUNT, 695);
            ht.setMaxBaseSpeed(708);
            ht.setMaxBaseDefense(DefenseType.FIRE, 695);

            ht.setSize(HeroTypeSize.SMALL);
            if (heroTypeRepository.findByName(ht.getName()) == null) {
                heroTypeService.save(ht);
            }
            ht = new HeroType();
            ht.setName("Dark Arthindol");
            ht.setFaction(DARK);
            ht.setRole(MAGE);
            ht.setQuality(BEST);
            ht.setDistanceType(RANGED);

            ht.setMinMaxLevelHp(374039);
            ht.setMinMaxLevelDmg(21641);
            ht.setMinMaxLevelAp(21641);
            ht.setMinMaxLevelDefense(DefenseType.BLUNT, 1352);
            ht.setMinMaxLevelSpeed(1069);
            ht.setMinMaxLevelDefense(DefenseType.FIRE, 1352);
            ht.setMinBaseHp(17492);
            ht.setMinBaseDmg(3296);
            ht.setMinBaseAp(3296);
            ht.setMinBaseDefense(DefenseType.BLUNT, 549);
            ht.setMinBaseSpeed(560);
            ht.setMinBaseDefense(DefenseType.FIRE, 549);
            ht.setMaxMaxLevelHp(457159);
            ht.setMaxMaxLevelDmg(26450);
            ht.setMaxMaxLevelAp(26450);
            ht.setMaxMaxLevelDefense(DefenseType.BLUNT, 1652);
            ht.setMaxMaxLevelSpeed(1307);
            ht.setMaxMaxLevelDefense(DefenseType.FIRE, 1652);
            ht.setMaxBaseHp(21380);
            ht.setMaxBaseDmg(4028);
            ht.setMaxBaseAp(4028);
            ht.setMaxBaseDefense(DefenseType.BLUNT, 671);
            ht.setMaxBaseSpeed(684);
            ht.setMaxBaseDefense(DefenseType.FIRE, 671);

            ht.setSize(HeroTypeSize.SMALL);
            if (heroTypeRepository.findByName(ht.getName()) == null) {
                heroTypeService.save(ht);
            }
            ht = new HeroType();
            ht.setName("Das Moge");
            ht.setFaction(DARK);
            ht.setRole(RANGER);
            ht.setQuality(AVERAGE);
            ht.setDistanceType(RANGED);

            ht.setMinMaxLevelHp(437340);
            ht.setMinMaxLevelDmg(22866);
            ht.setMinMaxLevelAp(22866);
            ht.setMinMaxLevelDefense(DefenseType.BLUNT, 1399);
            ht.setMinMaxLevelSpeed(1031);
            ht.setMinMaxLevelDefense(DefenseType.FIRE, 1399);
            ht.setMinBaseHp(21001);
            ht.setMinBaseDmg(3884);
            ht.setMinBaseAp(3884);
            ht.setMinBaseDefense(DefenseType.BLUNT, 569);
            ht.setMinBaseSpeed(540);
            ht.setMinBaseDefense(DefenseType.FIRE, 569);
            ht.setMaxMaxLevelHp(534526);
            ht.setMaxMaxLevelDmg(27948);
            ht.setMaxMaxLevelAp(27948);
            ht.setMaxMaxLevelDefense(DefenseType.BLUNT, 1709);
            ht.setMaxMaxLevelSpeed(1260);
            ht.setMaxMaxLevelDefense(DefenseType.FIRE, 1709);
            ht.setMaxBaseHp(25667);
            ht.setMaxBaseDmg(4748);
            ht.setMaxBaseAp(4748);
            ht.setMaxBaseDefense(DefenseType.BLUNT, 695);
            ht.setMaxBaseSpeed(660);
            ht.setMaxBaseDefense(DefenseType.FIRE, 695);

            ht.setSize(HeroTypeSize.SMALL);
            if (heroTypeRepository.findByName(ht.getName()) == null) {
                heroTypeService.save(ht);
            }
            ht = new HeroType();
            ht.setName("Sleepless");
            ht.setFaction(DARK);
            ht.setRole(WARRIOR);
            ht.setQuality(POOR);
            ht.setDistanceType(MELEE);

            ht.setMinMaxLevelHp(428710);
            ht.setMinMaxLevelDmg(17431);
            ht.setMinMaxLevelAp(17431);
            ht.setMinMaxLevelDefense(DefenseType.BLUNT, 1421);
            ht.setMinMaxLevelSpeed(1044);
            ht.setMinMaxLevelDefense(DefenseType.FIRE, 1421);
            ht.setMinBaseHp(27076);
            ht.setMinBaseDmg(2707);
            ht.setMinBaseAp(2707);
            ht.setMinBaseDefense(DefenseType.BLUNT, 598);
            ht.setMinBaseSpeed(548);
            ht.setMinBaseDefense(DefenseType.FIRE, 598);
            ht.setMaxMaxLevelHp(523978);
            ht.setMaxMaxLevelDmg(21305);
            ht.setMaxMaxLevelAp(21305);
            ht.setMaxMaxLevelDefense(DefenseType.BLUNT, 1737);
            ht.setMaxMaxLevelSpeed(1276);
            ht.setMaxMaxLevelDefense(DefenseType.FIRE, 1737);
            ht.setMaxBaseHp(33092);
            ht.setMaxBaseDmg(3309);
            ht.setMaxBaseAp(3309);
            ht.setMaxBaseDefense(DefenseType.BLUNT, 730);
            ht.setMaxBaseSpeed(670);
            ht.setMaxBaseDefense(DefenseType.FIRE, 730);

            ht.setSize(HeroTypeSize.SMALL);
            if (heroTypeRepository.findByName(ht.getName()) == null) {
                heroTypeService.save(ht);
            }
            ht = new HeroType();
            ht.setName("Bleecker");
            ht.setFaction(FORTIFIED);
            ht.setRole(MAGE);
            ht.setQuality(POOR);
            ht.setDistanceType(RANGED);

            ht.setMinMaxLevelHp(305987);
            ht.setMinMaxLevelDmg(27050);
            ht.setMinMaxLevelAp(27050);
            ht.setMinMaxLevelDefense(DefenseType.BLUNT, 1421);
            ht.setMinMaxLevelSpeed(1011);
            ht.setMinMaxLevelDefense(DefenseType.FIRE, 1421);
            ht.setMinBaseHp(16104);
            ht.setMinBaseDmg(3955);
            ht.setMinBaseAp(3955);
            ht.setMinBaseDefense(DefenseType.BLUNT, 579);
            ht.setMinBaseSpeed(519);
            ht.setMinBaseDefense(DefenseType.FIRE, 579);
            ht.setMaxMaxLevelHp(373985);
            ht.setMaxMaxLevelDmg(33062);
            ht.setMaxMaxLevelAp(33062);
            ht.setMaxMaxLevelDefense(DefenseType.BLUNT, 1737);
            ht.setMaxMaxLevelSpeed(1235);
            ht.setMaxMaxLevelDefense(DefenseType.FIRE, 1737);
            ht.setMaxBaseHp(19682);
            ht.setMaxBaseDmg(4833);
            ht.setMaxBaseAp(4833);
            ht.setMaxBaseDefense(DefenseType.BLUNT, 707);
            ht.setMaxBaseSpeed(635);
            ht.setMaxBaseDefense(DefenseType.FIRE, 707);

            ht.setSize(HeroTypeSize.SMALL);
            if (heroTypeRepository.findByName(ht.getName()) == null) {
                heroTypeService.save(ht);
            }
            ht = new HeroType();
            ht.setName("Emily");
            ht.setFaction(FORTIFIED);
            ht.setRole(PRIEST);
            ht.setQuality(BEST);
            ht.setDistanceType(RANGED);

            ht.setMinMaxLevelHp(413532);
            ht.setMinMaxLevelDmg(18454);
            ht.setMinMaxLevelAp(18454);
            ht.setMinMaxLevelDefense(DefenseType.BLUNT, 1468);
            ht.setMinMaxLevelSpeed(1042);
            ht.setMinMaxLevelDefense(DefenseType.FIRE, 1468);
            ht.setMinBaseHp(21824);
            ht.setMinBaseDmg(2884);
            ht.setMinBaseAp(2884);
            ht.setMinBaseDefense(DefenseType.BLUNT, 598);
            ht.setMinBaseSpeed(523);
            ht.setMinBaseDefense(DefenseType.FIRE, 598);
            ht.setMaxMaxLevelHp(505428);
            ht.setMaxMaxLevelDmg(22554);
            ht.setMaxMaxLevelAp(22554);
            ht.setMaxMaxLevelDefense(DefenseType.BLUNT, 1794);
            ht.setMaxMaxLevelSpeed(1274);
            ht.setMaxMaxLevelDefense(DefenseType.FIRE, 1794);
            ht.setMaxBaseHp(26674);
            ht.setMaxBaseDmg(3524);
            ht.setMaxBaseAp(3524);
            ht.setMaxBaseDefense(DefenseType.BLUNT, 730);
            ht.setMaxBaseSpeed(639);
            ht.setMaxBaseDefense(DefenseType.FIRE, 730);

            ht.setSize(HeroTypeSize.SMALL);
            if (heroTypeRepository.findByName(ht.getName()) == null) {
                heroTypeService.save(ht);
            }
            ht = new HeroType();
            ht.setName("Flame Strike");
            ht.setFaction(FORTIFIED);
            ht.setRole(MAGE);
            ht.setQuality(AVERAGE);
            ht.setDistanceType(RANGED);

            ht.setMinMaxLevelHp(526962);
            ht.setMinMaxLevelDmg(24611);
            ht.setMinMaxLevelAp(24611);
            ht.setMinMaxLevelDefense(DefenseType.BLUNT, 1421);
            ht.setMinMaxLevelSpeed(1011);
            ht.setMinMaxLevelDefense(DefenseType.FIRE, 1421);
            ht.setMinBaseHp(22342);
            ht.setMinBaseDmg(3280);
            ht.setMinBaseAp(3280);
            ht.setMinBaseDefense(DefenseType.BLUNT, 579);
            ht.setMinBaseSpeed(517);
            ht.setMinBaseDefense(DefenseType.FIRE, 579);
            ht.setMaxMaxLevelHp(644064);
            ht.setMaxMaxLevelDmg(30081);
            ht.setMaxMaxLevelAp(30081);
            ht.setMaxMaxLevelDefense(DefenseType.BLUNT, 1737);
            ht.setMaxMaxLevelSpeed(1235);
            ht.setMaxMaxLevelDefense(DefenseType.FIRE, 1737);
            ht.setMaxBaseHp(27306);
            ht.setMaxBaseDmg(4008);
            ht.setMaxBaseAp(4008);
            ht.setMaxBaseDefense(DefenseType.BLUNT, 707);
            ht.setMaxBaseSpeed(631);
            ht.setMaxBaseDefense(DefenseType.FIRE, 707);

            ht.setSize(HeroTypeSize.SMALL);
            if (heroTypeRepository.findByName(ht.getName()) == null) {
                heroTypeService.save(ht);
            }
            ht = new HeroType();
            ht.setName("Honor Guard");
            ht.setFaction(FORTIFIED);
            ht.setRole(WARRIOR);
            ht.setQuality(POOR);
            ht.setDistanceType(MELEE);

            ht.setMinMaxLevelHp(583668);
            ht.setMinMaxLevelDmg(12871);
            ht.setMinMaxLevelAp(12871);
            ht.setMinMaxLevelDefense(DefenseType.BLUNT, 2310);
            ht.setMinMaxLevelSpeed(1037);
            ht.setMinMaxLevelDefense(DefenseType.FIRE, 2310);
            ht.setMinBaseHp(26816);
            ht.setMinBaseDmg(1883);
            ht.setMinBaseAp(1883);
            ht.setMinBaseDefense(DefenseType.BLUNT, 828);
            ht.setMinBaseSpeed(543);
            ht.setMinBaseDefense(DefenseType.FIRE, 828);
            ht.setMaxMaxLevelHp(713372);
            ht.setMaxMaxLevelDmg(15731);
            ht.setMaxMaxLevelAp(15731);
            ht.setMaxMaxLevelDefense(DefenseType.BLUNT, 2824);
            ht.setMaxMaxLevelSpeed(1267);
            ht.setMaxMaxLevelDefense(DefenseType.FIRE, 2824);
            ht.setMaxBaseHp(32776);
            ht.setMaxBaseDmg(2301);
            ht.setMaxBaseAp(2301);
            ht.setMaxBaseDefense(DefenseType.BLUNT, 1012);
            ht.setMaxBaseSpeed(663);
            ht.setMaxBaseDefense(DefenseType.FIRE, 1012);

            ht.setSize(HeroTypeSize.SMALL);
            if (heroTypeRepository.findByName(ht.getName()) == null) {
                heroTypeService.save(ht);
            }
            ht = new HeroType();
            ht.setName("Iceblink");
            ht.setFaction(FORTIFIED);
            ht.setRole(RANGER);
            ht.setQuality(BEST);
            ht.setDistanceType(RANGED);

            ht.setMinMaxLevelHp(316914);
            ht.setMinMaxLevelDmg(20817);
            ht.setMinMaxLevelAp(20817);
            ht.setMinMaxLevelDefense(DefenseType.BLUNT, 1538);
            ht.setMinMaxLevelSpeed(1026);
            ht.setMinMaxLevelDefense(DefenseType.FIRE, 1538);
            ht.setMinBaseHp(17088);
            ht.setMinBaseDmg(2707);
            ht.setMinBaseAp(2707);
            ht.setMinBaseDefense(DefenseType.BLUNT, 627);
            ht.setMinBaseSpeed(535);
            ht.setMinBaseDefense(DefenseType.FIRE, 627);
            ht.setMaxMaxLevelHp(387340);
            ht.setMaxMaxLevelDmg(25443);
            ht.setMaxMaxLevelAp(25443);
            ht.setMaxMaxLevelDefense(DefenseType.BLUNT, 1880);
            ht.setMaxMaxLevelSpeed(1254);
            ht.setMaxMaxLevelDefense(DefenseType.FIRE, 1880);
            ht.setMaxBaseHp(20886);
            ht.setMaxBaseDmg(3309);
            ht.setMaxBaseAp(3309);
            ht.setMaxBaseDefense(DefenseType.BLUNT, 767);
            ht.setMaxBaseSpeed(653);
            ht.setMaxBaseDefense(DefenseType.FIRE, 767);

            ht.setSize(HeroTypeSize.SMALL);
            if (heroTypeRepository.findByName(ht.getName()) == null) {
                heroTypeService.save(ht);
            }
            ht = new HeroType();
            ht.setName("Miki");
            ht.setFaction(FORTIFIED);
            ht.setRole(RANGER);
            ht.setQuality(BEST);
            ht.setDistanceType(RANGED);

            ht.setMinMaxLevelHp(323174);
            ht.setMinMaxLevelDmg(21021);
            ht.setMinMaxLevelAp(21021);
            ht.setMinMaxLevelDefense(DefenseType.BLUNT, 1468);
            ht.setMinMaxLevelSpeed(1080);
            ht.setMinMaxLevelDefense(DefenseType.FIRE, 1468);
            ht.setMinBaseHp(15774);
            ht.setMinBaseDmg(3080);
            ht.setMinBaseAp(3080);
            ht.setMinBaseDefense(DefenseType.BLUNT, 598);
            ht.setMinBaseSpeed(573);
            ht.setMinBaseDefense(DefenseType.FIRE, 598);
            ht.setMaxMaxLevelHp(394990);
            ht.setMaxMaxLevelDmg(25693);
            ht.setMaxMaxLevelAp(25693);
            ht.setMaxMaxLevelDefense(DefenseType.BLUNT, 1794);
            ht.setMaxMaxLevelSpeed(1320);
            ht.setMaxMaxLevelDefense(DefenseType.FIRE, 1794);
            ht.setMaxBaseHp(19280);
            ht.setMaxBaseDmg(3764);
            ht.setMaxBaseAp(3764);
            ht.setMaxBaseDefense(DefenseType.BLUNT, 730);
            ht.setMaxBaseSpeed(701);
            ht.setMaxBaseDefense(DefenseType.FIRE, 730);

            ht.setSize(HeroTypeSize.SMALL);
            if (heroTypeRepository.findByName(ht.getName()) == null) {
                heroTypeService.save(ht);
            }
            ht = new HeroType();
            ht.setName("Mirage");
            ht.setFaction(FORTIFIED);
            ht.setRole(ASSASSIN);
            ht.setQuality(POOR);
            ht.setDistanceType(MELEE);

            ht.setMinMaxLevelHp(280497);
            ht.setMinMaxLevelDmg(26555);
            ht.setMinMaxLevelAp(26555);
            ht.setMinMaxLevelDefense(DefenseType.BLUNT, 1468);
            ht.setMinMaxLevelSpeed(1049);
            ht.setMinMaxLevelDefense(DefenseType.FIRE, 1468);
            ht.setMinBaseHp(15774);
            ht.setMinBaseDmg(3200);
            ht.setMinBaseAp(3200);
            ht.setMinBaseDefense(DefenseType.BLUNT, 598);
            ht.setMinBaseSpeed(556);
            ht.setMinBaseDefense(DefenseType.FIRE, 598);
            ht.setMaxMaxLevelHp(342829);
            ht.setMaxMaxLevelDmg(32457);
            ht.setMaxMaxLevelAp(32457);
            ht.setMaxMaxLevelDefense(DefenseType.BLUNT, 1794);
            ht.setMaxMaxLevelSpeed(1283);
            ht.setMaxMaxLevelDefense(DefenseType.FIRE, 1794);
            ht.setMaxBaseHp(19280);
            ht.setMaxBaseDmg(3912);
            ht.setMaxBaseAp(3912);
            ht.setMaxBaseDefense(DefenseType.BLUNT, 730);
            ht.setMaxBaseSpeed(680);
            ht.setMaxBaseDefense(DefenseType.FIRE, 730);

            ht.setSize(HeroTypeSize.SMALL);
            if (heroTypeRepository.findByName(ht.getName()) == null) {
                heroTypeService.save(ht);
            }
            ht = new HeroType();
            ht.setName("OD-01");
            ht.setFaction(FORTIFIED);
            ht.setRole(MAGE);
            ht.setQuality(AVERAGE);
            ht.setDistanceType(RANGED);

            ht.setMinMaxLevelHp(445377);
            ht.setMinMaxLevelDmg(18457);
            ht.setMinMaxLevelAp(18457);
            ht.setMinMaxLevelDefense(DefenseType.BLUNT, 1468);
            ht.setMinMaxLevelSpeed(1013);
            ht.setMinMaxLevelDefense(DefenseType.FIRE, 1468);
            ht.setMinBaseHp(21706);
            ht.setMinBaseDmg(2589);
            ht.setMinBaseAp(2589);
            ht.setMinBaseDefense(DefenseType.BLUNT, 627);
            ht.setMinBaseSpeed(432);
            ht.setMinBaseDefense(DefenseType.FIRE, 627);
            ht.setMaxMaxLevelHp(544349);
            ht.setMaxMaxLevelDmg(22559);
            ht.setMaxMaxLevelAp(22559);
            ht.setMaxMaxLevelDefense(DefenseType.BLUNT, 1794);
            ht.setMaxMaxLevelSpeed(1239);
            ht.setMaxMaxLevelDefense(DefenseType.FIRE, 1794);
            ht.setMaxBaseHp(26530);
            ht.setMaxBaseDmg(3165);
            ht.setMaxBaseAp(3165);
            ht.setMaxBaseDefense(DefenseType.BLUNT, 767);
            ht.setMaxBaseSpeed(528);
            ht.setMaxBaseDefense(DefenseType.FIRE, 767);

            ht.setSize(HeroTypeSize.SMALL);
            if (heroTypeRepository.findByName(ht.getName()) == null) {
                heroTypeService.save(ht);
            }
            ht = new HeroType();
            ht.setName("Ormus");
            ht.setFaction(FORTIFIED);
            ht.setRole(PRIEST);
            ht.setQuality(BEST);
            ht.setDistanceType(RANGED);

            ht.setMinMaxLevelHp(389057);
            ht.setMinMaxLevelDmg(23715);
            ht.setMinMaxLevelAp(23715);
            ht.setMinMaxLevelDefense(DefenseType.BLUNT, 1468);
            ht.setMinMaxLevelSpeed(997);
            ht.setMinMaxLevelDefense(DefenseType.FIRE, 1468);
            ht.setMinBaseHp(17492);
            ht.setMinBaseDmg(3460);
            ht.setMinBaseAp(3460);
            ht.setMinBaseDefense(DefenseType.BLUNT, 598);
            ht.setMinBaseSpeed(510);
            ht.setMinBaseDefense(DefenseType.FIRE, 598);
            ht.setMaxMaxLevelHp(475515);
            ht.setMaxMaxLevelDmg(28985);
            ht.setMaxMaxLevelAp(28985);
            ht.setMaxMaxLevelDefense(DefenseType.BLUNT, 1794);
            ht.setMaxMaxLevelSpeed(1219);
            ht.setMaxMaxLevelDefense(DefenseType.FIRE, 1794);
            ht.setMaxBaseHp(21380);
            ht.setMaxBaseDmg(4228);
            ht.setMaxBaseAp(4228);
            ht.setMaxBaseDefense(DefenseType.BLUNT, 730);
            ht.setMaxBaseSpeed(624);
            ht.setMaxBaseDefense(DefenseType.FIRE, 730);

            ht.setSize(HeroTypeSize.SMALL);
            if (heroTypeRepository.findByName(ht.getName()) == null) {
                heroTypeService.save(ht);
            }
            ht = new HeroType();
            ht.setName("Sigmund");
            ht.setFaction(FORTIFIED);
            ht.setRole(WARRIOR);
            ht.setQuality(AVERAGE);
            ht.setDistanceType(MELEE);

            ht.setMinMaxLevelHp(455989);
            ht.setMinMaxLevelDmg(19071);
            ht.setMinMaxLevelAp(19071);
            ht.setMinMaxLevelDefense(DefenseType.BLUNT, 2854);
            ht.setMinMaxLevelSpeed(1042);
            ht.setMinMaxLevelDefense(DefenseType.FIRE, 2854);
            ht.setMinBaseHp(23691);
            ht.setMinBaseDmg(2502);
            ht.setMinBaseAp(2502);
            ht.setMinBaseDefense(DefenseType.BLUNT, 839);
            ht.setMinBaseSpeed(548);
            ht.setMinBaseDefense(DefenseType.FIRE, 839);
            ht.setMaxMaxLevelHp(557319);
            ht.setMaxMaxLevelDmg(23309);
            ht.setMaxMaxLevelAp(23309);
            ht.setMaxMaxLevelDefense(DefenseType.BLUNT, 3488);
            ht.setMaxMaxLevelSpeed(1274);
            ht.setMaxMaxLevelDefense(DefenseType.FIRE, 3488);
            ht.setMaxBaseHp(28955);
            ht.setMaxBaseDmg(3058);
            ht.setMaxBaseAp(3058);
            ht.setMaxBaseDefense(DefenseType.BLUNT, 1025);
            ht.setMaxBaseSpeed(670);
            ht.setMaxBaseDefense(DefenseType.FIRE, 1025);

            ht.setSize(HeroTypeSize.SMALL);
            if (heroTypeRepository.findByName(ht.getName()) == null) {
                heroTypeService.save(ht);
            }
            ht = new HeroType();
            ht.setName("Asmodel");
            ht.setFaction(LIGHT);
            ht.setRole(WARRIOR);
            ht.setQuality(BEST);
            ht.setDistanceType(MELEE);

            ht.setMinMaxLevelHp(512027);
            ht.setMinMaxLevelDmg(16926);
            ht.setMinMaxLevelAp(16926);
            ht.setMinMaxLevelDefense(DefenseType.BLUNT, 1445);
            ht.setMinMaxLevelSpeed(1045);
            ht.setMinMaxLevelDefense(DefenseType.FIRE, 1445);
            ht.setMinBaseHp(24013);
            ht.setMinBaseDmg(2393);
            ht.setMinBaseAp(2393);
            ht.setMinBaseDefense(DefenseType.BLUNT, 598);
            ht.setMinBaseSpeed(548);
            ht.setMinBaseDefense(DefenseType.FIRE, 598);
            ht.setMaxMaxLevelHp(625811);
            ht.setMaxMaxLevelDmg(20688);
            ht.setMaxMaxLevelAp(20688);
            ht.setMaxMaxLevelDefense(DefenseType.BLUNT, 1766);
            ht.setMaxMaxLevelSpeed(1277);
            ht.setMaxMaxLevelDefense(DefenseType.FIRE, 1766);
            ht.setMaxBaseHp(29349);
            ht.setMaxBaseDmg(2925);
            ht.setMaxBaseAp(2925);
            ht.setMaxBaseDefense(DefenseType.BLUNT, 730);
            ht.setMaxBaseSpeed(670);
            ht.setMaxBaseDefense(DefenseType.FIRE, 730);

            ht.setSize(HeroTypeSize.SMALL);
            if (heroTypeRepository.findByName(ht.getName()) == null) {
                heroTypeService.save(ht);
            }
            ht = new HeroType();
            ht.setName("Faith Blade");
            ht.setFaction(LIGHT);
            ht.setRole(ASSASSIN);
            ht.setQuality(BEST);
            ht.setDistanceType(MELEE);

            ht.setMinMaxLevelHp(335076);
            ht.setMinMaxLevelDmg(25852);
            ht.setMinMaxLevelAp(25852);
            ht.setMinMaxLevelDefense(DefenseType.BLUNT, 1399);
            ht.setMinMaxLevelSpeed(1109);
            ht.setMinMaxLevelDefense(DefenseType.FIRE, 1399);
            ht.setMinBaseHp(17088);
            ht.setMinBaseDmg(3570);
            ht.setMinBaseAp(3570);
            ht.setMinBaseDefense(DefenseType.BLUNT, 569);
            ht.setMinBaseSpeed(580);
            ht.setMinBaseDefense(DefenseType.FIRE, 569);
            ht.setMaxMaxLevelHp(409538);
            ht.setMaxMaxLevelDmg(31596);
            ht.setMaxMaxLevelAp(31596);
            ht.setMaxMaxLevelDefense(DefenseType.BLUNT, 1709);
            ht.setMaxMaxLevelSpeed(1355);
            ht.setMaxMaxLevelDefense(DefenseType.FIRE, 1709);
            ht.setMaxBaseHp(20886);
            ht.setMaxBaseDmg(4364);
            ht.setMaxBaseAp(4364);
            ht.setMaxBaseDefense(DefenseType.BLUNT, 695);
            ht.setMaxBaseSpeed(708);
            ht.setMaxBaseDefense(DefenseType.FIRE, 695);

            ht.setSize(HeroTypeSize.SMALL);
            if (heroTypeRepository.findByName(ht.getName()) == null) {
                heroTypeService.save(ht);
            }
            ht = new HeroType();
            ht.setName("Gerke");
            ht.setFaction(LIGHT);
            ht.setRole(PRIEST);
            ht.setQuality(POOR);
            ht.setDistanceType(RANGED);

            ht.setMinMaxLevelHp(434465);
            ht.setMinMaxLevelDmg(21151);
            ht.setMinMaxLevelAp(21151);
            ht.setMinMaxLevelDefense(DefenseType.BLUNT, 1399);
            ht.setMinMaxLevelSpeed(997);
            ht.setMinMaxLevelDefense(DefenseType.FIRE, 1399);
            ht.setMinBaseHp(24863);
            ht.setMinBaseDmg(2661);
            ht.setMinBaseAp(2661);
            ht.setMinBaseDefense(DefenseType.BLUNT, 598);
            ht.setMinBaseSpeed(510);
            ht.setMinBaseDefense(DefenseType.FIRE, 598);
            ht.setMaxMaxLevelHp(531013);
            ht.setMaxMaxLevelDmg(25851);
            ht.setMaxMaxLevelAp(25851);
            ht.setMaxMaxLevelDefense(DefenseType.BLUNT, 1709);
            ht.setMaxMaxLevelSpeed(1219);
            ht.setMaxMaxLevelDefense(DefenseType.FIRE, 1709);
            ht.setMaxBaseHp(30389);
            ht.setMaxBaseDmg(3253);
            ht.setMaxBaseAp(3253);
            ht.setMaxBaseDefense(DefenseType.BLUNT, 730);
            ht.setMaxBaseSpeed(624);
            ht.setMaxBaseDefense(DefenseType.FIRE, 730);

            ae = new ActionEffect(ActionType.DMG, TargetType.RANDOM, 158, FIRE);
            sae1 = new ActionEffect(ActionType.HEAL, TargetType.LOWER_LIFE, 420, FIRE, Boolean.TRUE);

            specialAction = new Action();
            specialAction.setMainActionEffect(ae);
            specialAction.addSecundaryActionsEffects(sae1);
            specialAction.setSpecial(Boolean.TRUE);
            specialAction.setFormationPositionType(ALL_LINES);

            ht.getSpecialActions().add(specialAction);

            ht.setSize(HeroTypeSize.SMALL);
            if (heroTypeRepository.findByName(ht.getName()) == null) {
                heroTypeService.save(ht);
            }
            ht = new HeroType();
            ht.setName("Michelle");
            ht.setFaction(LIGHT);
            ht.setRole(RANGER);
            ht.setQuality(AVERAGE);
            ht.setDistanceType(RANGED);

            ht.setMinMaxLevelHp(454645);
            ht.setMinMaxLevelDmg(17590);
            ht.setMinMaxLevelAp(17590);
            ht.setMinMaxLevelDefense(DefenseType.BLUNT, 1399);
            ht.setMinMaxLevelSpeed(1069);
            ht.setMinMaxLevelDefense(DefenseType.FIRE, 1399);
            ht.setMinBaseHp(26369);
            ht.setMinBaseDmg(2786);
            ht.setMinBaseAp(2786);
            ht.setMinBaseDefense(DefenseType.BLUNT, 569);
            ht.setMinBaseSpeed(561);
            ht.setMinBaseDefense(DefenseType.FIRE, 569);
            ht.setMaxMaxLevelHp(555677);
            ht.setMaxMaxLevelDmg(21498);
            ht.setMaxMaxLevelAp(21498);
            ht.setMaxMaxLevelDefense(DefenseType.BLUNT, 1709);
            ht.setMaxMaxLevelSpeed(1307);
            ht.setMaxMaxLevelDefense(DefenseType.FIRE, 1709);
            ht.setMaxBaseHp(32229);
            ht.setMaxBaseDmg(3405);
            ht.setMaxBaseAp(3405);
            ht.setMaxBaseDefense(DefenseType.BLUNT, 695);
            ht.setMaxBaseSpeed(685);
            ht.setMaxBaseDefense(DefenseType.FIRE, 695);

            ht.setSize(HeroTypeSize.SMALL);
            if (heroTypeRepository.findByName(ht.getName()) == null) {
                heroTypeService.save(ht);
            }
            ht = new HeroType();
            ht.setName("Demon Hunter");
            ht.setFaction(SAVAGE);
            ht.setRole(RANGER);
            ht.setQuality(BEST);
            ht.setDistanceType(RANGED);

            ht.setMinMaxLevelHp(307177);
            ht.setMinMaxLevelDmg(16915);
            ht.setMinMaxLevelAp(16915);
            ht.setMinMaxLevelDefense(DefenseType.BLUNT, 1399);
            ht.setMinMaxLevelSpeed(1054);
            ht.setMinMaxLevelDefense(DefenseType.FIRE, 1399);
            ht.setMinBaseHp(18462);
            ht.setMinBaseDmg(2471);
            ht.setMinBaseAp(2471);
            ht.setMinBaseDefense(DefenseType.BLUNT, 569);
            ht.setMinBaseSpeed(562);
            ht.setMinBaseDefense(DefenseType.FIRE, 569);
            ht.setMaxMaxLevelHp(375439);
            ht.setMaxMaxLevelDmg(20673);
            ht.setMaxMaxLevelAp(20673);
            ht.setMaxMaxLevelDefense(DefenseType.BLUNT, 1709);
            ht.setMaxMaxLevelSpeed(1288);
            ht.setMaxMaxLevelDefense(DefenseType.FIRE, 1709);
            ht.setMaxBaseHp(22564);
            ht.setMaxBaseDmg(3021);
            ht.setMaxBaseAp(3021);
            ht.setMaxBaseDefense(DefenseType.BLUNT, 695);
            ht.setMaxBaseSpeed(686);
            ht.setMaxBaseDefense(DefenseType.FIRE, 695);

            ht.setSize(HeroTypeSize.SMALL);
            if (heroTypeRepository.findByName(ht.getName()) == null) {
                heroTypeService.save(ht);
            }
            ht = new HeroType();
            ht.setName("Dragon Slayer");
            ht.setFaction(SAVAGE);
            ht.setRole(WARRIOR);
            ht.setQuality(POOR);
            ht.setDistanceType(MELEE);

            ht.setMinMaxLevelHp(620170);
            ht.setMinMaxLevelDmg(11778);
            ht.setMinMaxLevelAp(11778);
            ht.setMinMaxLevelDefense(DefenseType.BLUNT, 2128);
            ht.setMinMaxLevelSpeed(1037);
            ht.setMinMaxLevelDefense(DefenseType.FIRE, 2128);
            ht.setMinBaseHp(34939);
            ht.setMinBaseDmg(1726);
            ht.setMinBaseAp(1726);
            ht.setMinBaseDefense(DefenseType.BLUNT, 598);
            ht.setMinBaseSpeed(543);
            ht.setMinBaseDefense(DefenseType.FIRE, 598);
            ht.setMaxMaxLevelHp(757986);
            ht.setMaxMaxLevelDmg(14396);
            ht.setMaxMaxLevelAp(14396);
            ht.setMaxMaxLevelDefense(DefenseType.BLUNT, 2600);
            ht.setMaxMaxLevelSpeed(1267);
            ht.setMaxMaxLevelDefense(DefenseType.FIRE, 2600);
            ht.setMaxBaseHp(42703);
            ht.setMaxBaseDmg(2110);
            ht.setMaxBaseAp(2110);
            ht.setMaxBaseDefense(DefenseType.BLUNT, 730);
            ht.setMaxBaseSpeed(663);
            ht.setMaxBaseDefense(DefenseType.FIRE, 730);

            ht.setSize(HeroTypeSize.SMALL);
            if (heroTypeRepository.findByName(ht.getName()) == null) {
                heroTypeService.save(ht);
            }
            ht = new HeroType();
            ht.setName("Eddga");
            ht.setFaction(SAVAGE);
            ht.setRole(WARRIOR);
            ht.setQuality(POOR);
            ht.setDistanceType(MELEE);

            ht.setMinMaxLevelHp(356910);
            ht.setMinMaxLevelDmg(15890);
            ht.setMinMaxLevelAp(15890);
            ht.setMinMaxLevelDefense(DefenseType.BLUNT, 1468);
            ht.setMinMaxLevelSpeed(1037);
            ht.setMinMaxLevelDefense(DefenseType.FIRE, 1468);
            ht.setMinBaseHp(21621);
            ht.setMinBaseDmg(2335);
            ht.setMinBaseAp(2335);
            ht.setMinBaseDefense(DefenseType.BLUNT, 598);
            ht.setMinBaseSpeed(541);
            ht.setMinBaseDefense(DefenseType.FIRE, 598);
            ht.setMaxMaxLevelHp(436224);
            ht.setMaxMaxLevelDmg(19421);
            ht.setMaxMaxLevelAp(19421);
            ht.setMaxMaxLevelDefense(DefenseType.BLUNT, 1794);
            ht.setMaxMaxLevelSpeed(1267);
            ht.setMaxMaxLevelDefense(DefenseType.FIRE, 1794);
            ht.setMaxBaseHp(26425);
            ht.setMaxBaseDmg(2853);
            ht.setMaxBaseAp(2853);
            ht.setMaxBaseDefense(DefenseType.BLUNT, 730);
            ht.setMaxBaseSpeed(661);
            ht.setMaxBaseDefense(DefenseType.FIRE, 730);

            ht.setSize(HeroTypeSize.SMALL);
            if (heroTypeRepository.findByName(ht.getName()) == null) {
                heroTypeService.save(ht);
            }
            ht = new HeroType();
            ht.setName("Faceless");
            ht.setFaction(SAVAGE);
            ht.setRole(ASSASSIN);
            ht.setQuality(POOR);
            ht.setDistanceType(MELEE);

            ht.setMinMaxLevelHp(271274);
            ht.setMinMaxLevelDmg(19479);
            ht.setMinMaxLevelAp(19479);
            ht.setMinMaxLevelDefense(DefenseType.BLUNT, 1399);
            ht.setMinMaxLevelSpeed(1049);
            ht.setMinMaxLevelDefense(DefenseType.FIRE, 1399);
            ht.setMinBaseHp(15833);
            ht.setMinBaseDmg(2884);
            ht.setMinBaseAp(2884);
            ht.setMinBaseDefense(DefenseType.BLUNT, 569);
            ht.setMinBaseSpeed(556);
            ht.setMinBaseDefense(DefenseType.FIRE, 569);
            ht.setMaxMaxLevelHp(331557);
            ht.setMaxMaxLevelDmg(23807);
            ht.setMaxMaxLevelAp(23807);
            ht.setMaxMaxLevelDefense(DefenseType.BLUNT, 1709);
            ht.setMaxMaxLevelSpeed(1283);
            ht.setMaxMaxLevelDefense(DefenseType.FIRE, 1709);
            ht.setMaxBaseHp(19351);
            ht.setMaxBaseDmg(3524);
            ht.setMaxBaseAp(3524);
            ht.setMaxBaseDefense(DefenseType.BLUNT, 695);
            ht.setMaxBaseSpeed(680);
            ht.setMaxBaseDefense(DefenseType.FIRE, 695);

            ht.setSize(HeroTypeSize.SMALL);
            if (heroTypeRepository.findByName(ht.getName()) == null) {
                heroTypeService.save(ht);
            }
            ht = new HeroType();
            ht.setName("Groo");
            ht.setFaction(SAVAGE);
            ht.setRole(WARRIOR);
            ht.setQuality(AVERAGE);
            ht.setDistanceType(MELEE);

            ht.setMinMaxLevelHp(530941);
            ht.setMinMaxLevelDmg(14716);
            ht.setMinMaxLevelAp(14716);
            ht.setMinMaxLevelDefense(DefenseType.BLUNT, 1468);
            ht.setMinMaxLevelSpeed(1042);
            ht.setMinMaxLevelDefense(DefenseType.FIRE, 1468);
            ht.setMinBaseHp(25211);
            ht.setMinBaseDmg(2060);
            ht.setMinBaseAp(2060);
            ht.setMinBaseDefense(DefenseType.BLUNT, 788);
            ht.setMinBaseSpeed(548);
            ht.setMinBaseDefense(DefenseType.FIRE, 788);
            ht.setMaxMaxLevelHp(648927);
            ht.setMaxMaxLevelDmg(17986);
            ht.setMaxMaxLevelAp(17986);
            ht.setMaxMaxLevelDefense(DefenseType.BLUNT, 1794);
            ht.setMaxMaxLevelSpeed(1274);
            ht.setMaxMaxLevelDefense(DefenseType.FIRE, 1794);
            ht.setMaxBaseHp(30813);
            ht.setMaxBaseDmg(2518);
            ht.setMaxBaseAp(2518);
            ht.setMaxBaseDefense(DefenseType.BLUNT, 964);
            ht.setMaxBaseSpeed(670);
            ht.setMaxBaseDefense(DefenseType.FIRE, 964);

            ht.setSize(HeroTypeSize.SMALL);
            if (heroTypeRepository.findByName(ht.getName()) == null) {
                heroTypeService.save(ht);
            }
            ht = new HeroType();
            ht.setName("Malassa");
            ht.setFaction(SAVAGE);
            ht.setRole(RANGER);
            ht.setQuality(AVERAGE);
            ht.setDistanceType(RANGED);

            ht.setMinMaxLevelHp(302043);
            ht.setMinMaxLevelDmg(22664);
            ht.setMinMaxLevelAp(22664);
            ht.setMinMaxLevelDefense(DefenseType.BLUNT, 1399);
            ht.setMinMaxLevelSpeed(1026);
            ht.setMinMaxLevelDefense(DefenseType.FIRE, 1399);
            ht.setMinBaseHp(18462);
            ht.setMinBaseDmg(2471);
            ht.setMinBaseAp(2471);
            ht.setMinBaseDefense(DefenseType.BLUNT, 569);
            ht.setMinBaseSpeed(535);
            ht.setMinBaseDefense(DefenseType.FIRE, 569);
            ht.setMaxMaxLevelHp(369163);
            ht.setMaxMaxLevelDmg(27700);
            ht.setMaxMaxLevelAp(27700);
            ht.setMaxMaxLevelDefense(DefenseType.BLUNT, 1709);
            ht.setMaxMaxLevelSpeed(1254);
            ht.setMaxMaxLevelDefense(DefenseType.FIRE, 1709);
            ht.setMaxBaseHp(22564);
            ht.setMaxBaseDmg(3021);
            ht.setMaxBaseAp(3021);
            ht.setMaxBaseDefense(DefenseType.BLUNT, 695);
            ht.setMaxBaseSpeed(653);
            ht.setMaxBaseDefense(DefenseType.FIRE, 695);

            ht.setSize(HeroTypeSize.SMALL);
            if (heroTypeRepository.findByName(ht.getName()) == null) {
                heroTypeService.save(ht);
            }
            ht = new HeroType();
            ht.setName("Rosa");
            ht.setFaction(SAVAGE);
            ht.setRole(PRIEST);
            ht.setQuality(AVERAGE);
            ht.setDistanceType(RANGED);

            ht.setMinMaxLevelHp(379067);
            ht.setMinMaxLevelDmg(17424);
            ht.setMinMaxLevelAp(17424);
            ht.setMinMaxLevelDefense(DefenseType.BLUNT, 1399);
            ht.setMinMaxLevelSpeed(1019);
            ht.setMinMaxLevelDefense(DefenseType.FIRE, 1399);
            ht.setMinBaseHp(20012);
            ht.setMinBaseDmg(3080);
            ht.setMinBaseAp(3080);
            ht.setMinBaseDefense(DefenseType.BLUNT, 549);
            ht.setMinBaseSpeed(543);
            ht.setMinBaseDefense(DefenseType.FIRE, 549);
            ht.setMaxMaxLevelHp(463304);
            ht.setMaxMaxLevelDmg(21296);
            ht.setMaxMaxLevelAp(21296);
            ht.setMaxMaxLevelDefense(DefenseType.BLUNT, 1709);
            ht.setMaxMaxLevelSpeed(1245);
            ht.setMaxMaxLevelDefense(DefenseType.FIRE, 1709);
            ht.setMaxBaseHp(24460);
            ht.setMaxBaseDmg(3764);
            ht.setMaxBaseAp(3764);
            ht.setMaxBaseDefense(DefenseType.BLUNT, 671);
            ht.setMaxBaseSpeed(663);
            ht.setMaxBaseDefense(DefenseType.FIRE, 671);

            ht.setSize(HeroTypeSize.SMALL);
            if (heroTypeRepository.findByName(ht.getName()) == null) {
                heroTypeService.save(ht);
            }
            ht = new HeroType();
            ht.setName("Starlight");
            ht.setFaction(SAVAGE);
            ht.setRole(MAGE);
            ht.setQuality(AVERAGE);
            ht.setDistanceType(RANGED);

            ht.setMinMaxLevelHp(259483);
            ht.setMinMaxLevelDmg(28427);
            ht.setMinMaxLevelAp(28427);
            ht.setMinMaxLevelDefense(DefenseType.BLUNT, 1352);
            ht.setMinMaxLevelSpeed(1011);
            ht.setMinMaxLevelDefense(DefenseType.FIRE, 1352);
            ht.setMinBaseHp(15656);
            ht.setMinBaseDmg(4003);
            ht.setMinBaseAp(4003);
            ht.setMinBaseDefense(DefenseType.BLUNT, 549);
            ht.setMinBaseSpeed(519);
            ht.setMinBaseDefense(DefenseType.FIRE, 549);
            ht.setMaxMaxLevelHp(317145);
            ht.setMaxMaxLevelDmg(34744);
            ht.setMaxMaxLevelAp(34744);
            ht.setMaxMaxLevelDefense(DefenseType.BLUNT, 1652);
            ht.setMaxMaxLevelSpeed(1235);
            ht.setMaxMaxLevelDefense(DefenseType.FIRE, 1652);
            ht.setMaxBaseHp(19136);
            ht.setMaxBaseDmg(4893);
            ht.setMaxBaseAp(4893);
            ht.setMaxBaseDefense(DefenseType.BLUNT, 671);
            ht.setMaxBaseSpeed(635);
            ht.setMaxBaseDefense(DefenseType.FIRE, 671);

            ht.setSize(HeroTypeSize.SMALL);
            if (heroTypeRepository.findByName(ht.getName()) == null) {
                heroTypeService.save(ht);
            }
            ht = new HeroType();
            ht.setName("Vesa");
            ht.setFaction(SAVAGE);
            ht.setRole(PRIEST);
            ht.setQuality(BEST);
            ht.setDistanceType(RANGED);

            ht.setMinMaxLevelHp(386665);
            ht.setMinMaxLevelDmg(23599);
            ht.setMinMaxLevelAp(23599);
            ht.setMinMaxLevelDefense(DefenseType.BLUNT, 1399);
            ht.setMinMaxLevelSpeed(1004);
            ht.setMinMaxLevelDefense(DefenseType.FIRE, 1399);
            ht.setMinBaseHp(19652);
            ht.setMinBaseDmg(3067);
            ht.setMinBaseAp(3067);
            ht.setMinBaseDefense(DefenseType.BLUNT, 569);
            ht.setMinBaseSpeed(505);
            ht.setMinBaseDefense(DefenseType.FIRE, 569);
            ht.setMaxMaxLevelHp(472591);
            ht.setMaxMaxLevelDmg(28843);
            ht.setMaxMaxLevelAp(28843);
            ht.setMaxMaxLevelDefense(DefenseType.BLUNT, 1709);
            ht.setMaxMaxLevelSpeed(1228);
            ht.setMaxMaxLevelDefense(DefenseType.FIRE, 1709);
            ht.setMaxBaseHp(24019);
            ht.setMaxBaseDmg(3749);
            ht.setMaxBaseAp(3749);
            ht.setMaxBaseDefense(DefenseType.BLUNT, 695);
            ht.setMaxBaseSpeed(617);
            ht.setMaxBaseDefense(DefenseType.FIRE, 695);

            ht.setSize(HeroTypeSize.SMALL);
            if (heroTypeRepository.findByName(ht.getName()) == null) {
                heroTypeService.save(ht);
            }
            ht = new HeroType();
            ht.setName("Aidan");
            ht.setFaction(SHADOW);
            ht.setRole(MAGE);
            ht.setQuality(AVERAGE);
            ht.setDistanceType(RANGED);

            ht.setMinMaxLevelHp(369313);
            ht.setMinMaxLevelDmg(26199);
            ht.setMinMaxLevelAp(26199);
            ht.setMinMaxLevelDefense(DefenseType.BLUNT, 1282);
            ht.setMinMaxLevelSpeed(1013);
            ht.setMinMaxLevelDefense(DefenseType.FIRE, 1282);
            ht.setMinBaseHp(19646);
            ht.setMinBaseDmg(4119);
            ht.setMinBaseAp(4119);
            ht.setMinBaseDefense(DefenseType.BLUNT, 519);
            ht.setMinBaseSpeed(522);
            ht.setMinBaseDefense(DefenseType.FIRE, 519);
            ht.setMaxMaxLevelHp(451383);
            ht.setMaxMaxLevelDmg(32021);
            ht.setMaxMaxLevelAp(32021);
            ht.setMaxMaxLevelDefense(DefenseType.BLUNT, 1566);
            ht.setMaxMaxLevelSpeed(1239);
            ht.setMaxMaxLevelDefense(DefenseType.FIRE, 1566);
            ht.setMaxBaseHp(24012);
            ht.setMaxBaseDmg(5035);
            ht.setMaxBaseAp(5035);
            ht.setMaxBaseDefense(DefenseType.BLUNT, 635);
            ht.setMaxBaseSpeed(638);
            ht.setMaxBaseDefense(DefenseType.FIRE, 635);

            ht.setSize(HeroTypeSize.SMALL);
            if (heroTypeRepository.findByName(ht.getName()) == null) {
                heroTypeService.save(ht);
            }
            ht = new HeroType();
            ht.setName("Baade");
            ht.setFaction(SHADOW);
            ht.setRole(WARRIOR);
            ht.setQuality(POOR);
            ht.setDistanceType(MELEE);

            ht.setMinMaxLevelHp(519777);
            ht.setMinMaxLevelDmg(14872);
            ht.setMinMaxLevelAp(14872);
            ht.setMinMaxLevelDefense(DefenseType.BLUNT, 1399);
            ht.setMinMaxLevelSpeed(1040);
            ht.setMinMaxLevelDefense(DefenseType.FIRE, 1399);
            ht.setMinBaseHp(24015);
            ht.setMinBaseDmg(2256);
            ht.setMinBaseAp(2256);
            ht.setMinBaseDefense(DefenseType.BLUNT, 569);
            ht.setMinBaseSpeed(545);
            ht.setMinBaseDefense(DefenseType.FIRE, 569);
            ht.setMaxMaxLevelHp(635283);
            ht.setMaxMaxLevelDmg(18176);
            ht.setMaxMaxLevelAp(18176);
            ht.setMaxMaxLevelDefense(DefenseType.BLUNT, 1709);
            ht.setMaxMaxLevelSpeed(1271);
            ht.setMaxMaxLevelDefense(DefenseType.FIRE, 1709);
            ht.setMaxBaseHp(29351);
            ht.setMaxBaseDmg(2758);
            ht.setMaxBaseAp(2758);
            ht.setMaxBaseDefense(DefenseType.BLUNT, 695);
            ht.setMaxBaseSpeed(667);
            ht.setMaxBaseDefense(DefenseType.FIRE, 695);

            //Attacks the weakest enemy for 207% of Baade’s ATK, and reduces the target’s ATK by 27% for two rounds. Can’t be dodged.
            BuffEffect b1 = new BuffEffect(BuffEffectType.DECREASE_ATTRIBUTE, 27, 0, 2, AttributeType.DMG);

            ae = new ActionEffect(ActionType.DMG, TargetType.LOWER_LIFE, 207, DamageType.BLUNT, Boolean.FALSE);
            ae.addBuffEffect(b1);

            specialAction = new Action();
            specialAction.setMainActionEffect(ae);
            specialAction.setSpecial(Boolean.TRUE);
            specialAction.setFormationPositionType(ALL_LINES);

            ht.getSpecialActions().add(specialAction);

            ht.setSize(HeroTypeSize.SMALL);
            if (heroTypeRepository.findByName(ht.getName()) == null) {
                heroTypeService.save(ht);
            }
            ht = new HeroType();
            ht.setName("Blood Blade");
            ht.setFaction(SHADOW);
            ht.setRole(ASSASSIN);
            ht.setQuality(BEST);
            ht.setDistanceType(MELEE);

            ht.setMinMaxLevelHp(275895);
            ht.setMinMaxLevelDmg(26004);
            ht.setMinMaxLevelAp(26004);
            ht.setMinMaxLevelDefense(DefenseType.BLUNT, 1328);
            ht.setMinMaxLevelSpeed(1055);
            ht.setMinMaxLevelDefense(DefenseType.FIRE, 1328);
            ht.setMinBaseHp(16716);
            ht.setMinBaseDmg(3695);
            ht.setMinBaseAp(3695);
            ht.setMinBaseDefense(DefenseType.BLUNT, 539);
            ht.setMinBaseSpeed(562);
            ht.setMinBaseDefense(DefenseType.FIRE, 539);
            ht.setMaxMaxLevelHp(337205);
            ht.setMaxMaxLevelDmg(31782);
            ht.setMaxMaxLevelAp(31782);
            ht.setMaxMaxLevelDefense(DefenseType.BLUNT, 1624);
            ht.setMaxMaxLevelSpeed(1289);
            ht.setMaxMaxLevelDefense(DefenseType.FIRE, 1624);
            ht.setMaxBaseHp(20430);
            ht.setMaxBaseDmg(4517);
            ht.setMaxBaseAp(4517);
            ht.setMaxBaseDefense(DefenseType.BLUNT, 659);
            ht.setMaxBaseSpeed(686);
            ht.setMaxBaseDefense(DefenseType.FIRE, 659);

            ht.setSize(HeroTypeSize.SMALL);
            if (heroTypeRepository.findByName(ht.getName()) == null) {
                heroTypeService.save(ht);
            }
            ht = new HeroType();
            ht.setName("Corpsedemon");
            ht.setFaction(SHADOW);
            ht.setRole(WARRIOR);
            ht.setQuality(BEST);
            ht.setDistanceType(MELEE);

            ht.setMinMaxLevelHp(555466);
            ht.setMinMaxLevelDmg(15686);
            ht.setMinMaxLevelAp(15686);
            ht.setMinMaxLevelDefense(DefenseType.BLUNT, 1757);
            ht.setMinMaxLevelSpeed(1042);
            ht.setMinMaxLevelDefense(DefenseType.FIRE, 1757);
            ht.setMinBaseHp(32962);
            ht.setMinBaseDmg(2071);
            ht.setMinBaseAp(2071);
            ht.setMinBaseDefense(DefenseType.BLUNT, 631);
            ht.setMinBaseSpeed(541);
            ht.setMinBaseDefense(DefenseType.FIRE, 631);
            ht.setMaxMaxLevelHp(678902);
            ht.setMaxMaxLevelDmg(19172);
            ht.setMaxMaxLevelAp(19172);
            ht.setMaxMaxLevelDefense(DefenseType.BLUNT, 2147);
            ht.setMaxMaxLevelSpeed(1274);
            ht.setMaxMaxLevelDefense(DefenseType.FIRE, 2147);
            ht.setMaxBaseHp(40286);
            ht.setMaxBaseDmg(2531);
            ht.setMaxBaseAp(2531);
            ht.setMaxBaseDefense(DefenseType.BLUNT, 771);
            ht.setMaxBaseSpeed(661);
            ht.setMaxBaseDefense(DefenseType.FIRE, 771);

            ht.setSize(HeroTypeSize.SMALL);
            if (heroTypeRepository.findByName(ht.getName()) == null) {
                heroTypeService.save(ht);
            }
            ht = new HeroType();
            ht.setName("Dominator");
            ht.setFaction(SHADOW);
            ht.setRole(WARRIOR);
            ht.setQuality(POOR);
            ht.setDistanceType(MELEE);

            ht.setMinMaxLevelHp(605224);
            ht.setMinMaxLevelDmg(12461);
            ht.setMinMaxLevelAp(12461);
            ht.setMinMaxLevelDefense(DefenseType.BLUNT, 1352);
            ht.setMinMaxLevelSpeed(1037);
            ht.setMinMaxLevelDefense(DefenseType.FIRE, 1352);
            ht.setMinBaseHp(32962);
            ht.setMinBaseDmg(2256);
            ht.setMinBaseAp(2256);
            ht.setMinBaseDefense(DefenseType.BLUNT, 549);
            ht.setMinBaseSpeed(543);
            ht.setMinBaseDefense(DefenseType.FIRE, 549);
            ht.setMaxMaxLevelHp(739718);
            ht.setMaxMaxLevelDmg(15231);
            ht.setMaxMaxLevelAp(15231);
            ht.setMaxMaxLevelDefense(DefenseType.BLUNT, 1652);
            ht.setMaxMaxLevelSpeed(1267);
            ht.setMaxMaxLevelDefense(DefenseType.FIRE, 1652);
            ht.setMaxBaseHp(40286);
            ht.setMaxBaseDmg(2758);
            ht.setMaxBaseAp(2758);
            ht.setMaxBaseDefense(DefenseType.BLUNT, 671);
            ht.setMaxBaseSpeed(663);
            ht.setMaxBaseDefense(DefenseType.FIRE, 671);

            ht.setSize(HeroTypeSize.SMALL);
            if (heroTypeRepository.findByName(ht.getName()) == null) {
                heroTypeService.save(ht);
            }
            ht = new HeroType();
            ht.setName("Field");
            ht.setFaction(SHADOW);
            ht.setRole(RANGER);
            ht.setQuality(AVERAGE);
            ht.setDistanceType(RANGED);

            ht.setMinMaxLevelHp(298966);
            ht.setMinMaxLevelDmg(22834);
            ht.setMinMaxLevelAp(22834);
            ht.setMinMaxLevelDefense(DefenseType.BLUNT, 1328);
            ht.setMinMaxLevelSpeed(1026);
            ht.setMinMaxLevelDefense(DefenseType.FIRE, 1328);
            ht.setMinBaseHp(20601);
            ht.setMinBaseDmg(3200);
            ht.setMinBaseAp(3200);
            ht.setMinBaseDefense(DefenseType.BLUNT, 549);
            ht.setMinBaseSpeed(535);
            ht.setMinBaseDefense(DefenseType.FIRE, 549);
            ht.setMaxMaxLevelHp(365402);
            ht.setMaxMaxLevelDmg(27908);
            ht.setMaxMaxLevelAp(27908);
            ht.setMaxMaxLevelDefense(DefenseType.BLUNT, 1624);
            ht.setMaxMaxLevelSpeed(1254);
            ht.setMaxMaxLevelDefense(DefenseType.FIRE, 1624);
            ht.setMaxBaseHp(25179);
            ht.setMaxBaseDmg(3912);
            ht.setMaxBaseAp(3912);
            ht.setMaxBaseDefense(DefenseType.BLUNT, 671);
            ht.setMaxBaseSpeed(653);
            ht.setMaxBaseDefense(DefenseType.FIRE, 671);

            ht.setSize(HeroTypeSize.SMALL);
            if (heroTypeRepository.findByName(ht.getName()) == null) {
                heroTypeService.save(ht);
            }
            ht = new HeroType();
            ht.setName("Kamath");
            ht.setFaction(SHADOW);
            ht.setRole(RANGER);
            ht.setQuality(BEST);
            ht.setDistanceType(RANGED);

            ht.setMinMaxLevelHp(389358);
            ht.setMinMaxLevelDmg(23333);
            ht.setMinMaxLevelAp(23333);
            ht.setMinMaxLevelDefense(DefenseType.BLUNT, 1622);
            ht.setMinMaxLevelSpeed(1028);
            ht.setMinMaxLevelDefense(DefenseType.FIRE, 1622);
            ht.setMinBaseHp(22083);
            ht.setMinBaseDmg(3519);
            ht.setMinBaseAp(3519);
            ht.setMinBaseDefense(DefenseType.BLUNT, 592);
            ht.setMinBaseSpeed(529);
            ht.setMinBaseDefense(DefenseType.FIRE, 592);
            ht.setMaxMaxLevelHp(475882);
            ht.setMaxMaxLevelDmg(28518);
            ht.setMaxMaxLevelAp(28518);
            ht.setMaxMaxLevelDefense(DefenseType.BLUNT, 1982);
            ht.setMaxMaxLevelSpeed(1256);
            ht.setMaxMaxLevelDefense(DefenseType.FIRE, 1982);
            ht.setMaxBaseHp(26991);
            ht.setMaxBaseDmg(4301);
            ht.setMaxBaseAp(4301);
            ht.setMaxBaseDefense(DefenseType.BLUNT, 724);
            ht.setMaxBaseSpeed(647);
            ht.setMaxBaseDefense(DefenseType.FIRE, 724);

            ht.setSize(HeroTypeSize.SMALL);
            if (heroTypeRepository.findByName(ht.getName()) == null) {
                heroTypeService.save(ht);
            }
            ht = new HeroType();
            ht.setName("Kharma");
            ht.setFaction(SHADOW);
            ht.setRole(PRIEST);
            ht.setQuality(AVERAGE);
            ht.setDistanceType(RANGED);

            ht.setMinMaxLevelHp(289234);
            ht.setMinMaxLevelDmg(31225);
            ht.setMinMaxLevelAp(31225);
            ht.setMinMaxLevelDefense(DefenseType.BLUNT, 1282);
            ht.setMinMaxLevelSpeed(997);
            ht.setMinMaxLevelDefense(DefenseType.FIRE, 1282);
            ht.setMinBaseHp(15715);
            ht.setMinBaseDmg(3955);
            ht.setMinBaseAp(3955);
            ht.setMinBaseDefense(DefenseType.BLUNT, 519);
            ht.setMinBaseSpeed(510);
            ht.setMinBaseDefense(DefenseType.FIRE, 519);
            ht.setMaxMaxLevelHp(353508);
            ht.setMaxMaxLevelDmg(38163);
            ht.setMaxMaxLevelAp(38163);
            ht.setMaxMaxLevelDefense(DefenseType.BLUNT, 1566);
            ht.setMaxMaxLevelSpeed(1219);
            ht.setMaxMaxLevelDefense(DefenseType.FIRE, 1566);
            ht.setMaxBaseHp(19207);
            ht.setMaxBaseDmg(4833);
            ht.setMaxBaseAp(4833);
            ht.setMaxBaseDefense(DefenseType.BLUNT, 635);
            ht.setMaxBaseSpeed(624);
            ht.setMaxBaseDefense(DefenseType.FIRE, 635);

            ht.setSize(HeroTypeSize.SMALL);
            if (heroTypeRepository.findByName(ht.getName()) == null) {
                heroTypeService.save(ht);
            }
            ht = new HeroType();
            ht.setName("Lutz");
            ht.setFaction(SHADOW);
            ht.setRole(ASSASSIN);
            ht.setQuality(POOR);
            ht.setDistanceType(MELEE);

            ht.setMinMaxLevelHp(343806);
            ht.setMinMaxLevelDmg(24994);
            ht.setMinMaxLevelAp(24994);
            ht.setMinMaxLevelDefense(DefenseType.BLUNT, 1328);
            ht.setMinMaxLevelSpeed(1049);
            ht.setMinMaxLevelDefense(DefenseType.FIRE, 1328);
            ht.setMinBaseHp(18069);
            ht.setMinBaseDmg(3695);
            ht.setMinBaseAp(3695);
            ht.setMinBaseDefense(DefenseType.BLUNT, 539);
            ht.setMinBaseSpeed(556);
            ht.setMinBaseDefense(DefenseType.FIRE, 539);
            ht.setMaxMaxLevelHp(420208);
            ht.setMaxMaxLevelDmg(30548);
            ht.setMaxMaxLevelAp(30548);
            ht.setMaxMaxLevelDefense(DefenseType.BLUNT, 1624);
            ht.setMaxMaxLevelSpeed(1283);
            ht.setMaxMaxLevelDefense(DefenseType.FIRE, 1624);
            ht.setMaxBaseHp(22085);
            ht.setMaxBaseDmg(4517);
            ht.setMaxBaseAp(4517);
            ht.setMaxBaseDefense(DefenseType.BLUNT, 659);
            ht.setMaxBaseSpeed(680);
            ht.setMaxBaseDefense(DefenseType.FIRE, 659);

            ht.setSize(HeroTypeSize.SMALL);
            if (heroTypeRepository.findByName(ht.getName()) == null) {
                heroTypeService.save(ht);
            }
            ht = new HeroType();
            ht.setName("Walter");
            ht.setFaction(SHADOW);
            ht.setRole(ASSASSIN);
            ht.setQuality(BEST);
            ht.setDistanceType(MELEE);

            ht.setMinMaxLevelHp(262545);
            ht.setMinMaxLevelDmg(27681);
            ht.setMinMaxLevelAp(27681);
            ht.setMinMaxLevelDefense(DefenseType.BLUNT, 1328);
            ht.setMinMaxLevelSpeed(1052);
            ht.setMinMaxLevelDefense(DefenseType.FIRE, 1328);
            ht.setMinBaseHp(15892);
            ht.setMinBaseDmg(3813);
            ht.setMinBaseAp(3813);
            ht.setMinBaseDefense(DefenseType.BLUNT, 539);
            ht.setMinBaseSpeed(559);
            ht.setMinBaseDefense(DefenseType.FIRE, 539);
            ht.setMaxMaxLevelHp(320889);
            ht.setMaxMaxLevelDmg(33833);
            ht.setMaxMaxLevelAp(33833);
            ht.setMaxMaxLevelDefense(DefenseType.BLUNT, 1624);
            ht.setMaxMaxLevelSpeed(1286);
            ht.setMaxMaxLevelDefense(DefenseType.FIRE, 1624);
            ht.setMaxBaseHp(19424);
            ht.setMaxBaseDmg(4661);
            ht.setMaxBaseAp(4661);
            ht.setMaxBaseDefense(DefenseType.BLUNT, 659);
            ht.setMaxBaseSpeed(683);
            ht.setMaxBaseDefense(DefenseType.FIRE, 659);

            ht.setSize(HeroTypeSize.SMALL);
            if (heroTypeRepository.findByName(ht.getName()) == null) {
                heroTypeService.save(ht);
            }
        };

    }
}
