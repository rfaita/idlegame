package com.idle.game.tests;

import com.idle.game.helper.PlayerHelper;
import com.idle.game.model.mongo.Hero;
import com.idle.game.server.repository.HeroRepository;
import com.idle.game.server.service.HeroService;
import static com.idle.game.tests.helper.TestHelper.createPlayer;
import static com.idle.game.tests.helper.TestHelper.createHero;
import static com.idle.game.tests.helper.TestHelper.createHeroMaxLevel;
import javax.validation.ValidationException;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author rafael
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class HeroServiceTest {

    @MockBean
    private PlayerHelper playerHelper;

    @MockBean
    private HeroRepository heroRepository;

    @Autowired
    private HeroService heroService;

    @Rule
    public ExpectedException expcetionExpect = ExpectedException.none();

    @Test
    public void testHeroLevelUpPlayerNotFound() {

        when(playerHelper.getPlayerByLinkedUser("1")).thenReturn(null);

        expcetionExpect.expect(ValidationException.class);
        expcetionExpect.expectMessage("player.not.found");

        heroService.levelUp("1", "1");

    }

    @Test
    public void testHeroLevelUpHeroNotFound() {

        when(playerHelper.getPlayerByLinkedUser("1")).thenReturn(createPlayer("1"));

        when(heroRepository.findById("1")).thenReturn(null);

        expcetionExpect.expect(ValidationException.class);
        expcetionExpect.expectMessage("hero.not.found");

        heroService.levelUp("1", "1");

    }

    @Test
    public void testHeroLevelUpPlayerNotOwnerOfHero() {

        when(playerHelper.getPlayerByLinkedUser("1")).thenReturn(createPlayer("1"));

        when(heroRepository.findById("1")).thenReturn(createHero("1", "2"));

        expcetionExpect.expect(ValidationException.class);
        expcetionExpect.expectMessage("player.is.not.owner.of.this.hero");

        heroService.levelUp("1", "1");

    }

    @Test
    public void testHeroLevelUpMaxLevelReached() {

        when(playerHelper.getPlayerByLinkedUser("1")).thenReturn(createPlayer("1"));

        when(heroRepository.findById("1")).thenReturn(createHeroMaxLevel("1", "1"));

        expcetionExpect.expect(ValidationException.class);
        expcetionExpect.expectMessage("hero.max.level.reached");

        heroService.levelUp("1", "1");

    }

    @Test
    public void testHeroLevelUp() {

        when(playerHelper.getPlayerByLinkedUser("1")).thenReturn(createPlayer("1"));

        when(heroRepository.findById("1")).thenReturn(createHero("1", "1"));

        Hero heroRet = heroService.levelUp("1", "1");

        Assert.assertEquals("level must be 2", new Integer(2), heroRet.getLevel());

    }
}