package com.idle.game.tests;

import com.idle.game.model.Hero;
import com.idle.game.server.repository.HeroRepository;
import com.idle.game.server.service.HeroService;
import static com.idle.game.tests.helper.TestHelper.createHero;
import static com.idle.game.tests.helper.TestHelper.createHeroMaxLevel;
import java.util.Optional;
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
    private HeroRepository heroRepository;
    
    @Autowired
    private HeroService heroService;
    
    @Rule
    public ExpectedException expcetionExpect = ExpectedException.none();
    
    @Test
    public void testHeroLevelUpHeroNotFound() {
        
        when(heroRepository.findById("1")).thenReturn(Optional.ofNullable(null));
        
        expcetionExpect.expect(ValidationException.class);
        expcetionExpect.expectMessage("hero.not.found");
        
        heroService.levelUp("1", "1");
        
    }
    
    @Test
    public void testHeroLevelUpUserIdNotOwnerOfHero() {
        
        when(heroRepository.findById("1")).thenReturn(Optional.of(createHero("1", "2")));
        
        expcetionExpect.expect(ValidationException.class);
        expcetionExpect.expectMessage("user.is.not.owner.of.this.hero");
        
        heroService.levelUp("1", "1");
        
    }
    
    @Test
    public void testHeroLevelUpMaxLevelReached() {
        
        when(heroRepository.findById("1")).thenReturn(Optional.of(createHeroMaxLevel("1", "1")));
        
        expcetionExpect.expect(ValidationException.class);
        expcetionExpect.expectMessage("hero.max.level.reached");
        
        heroService.levelUp("1", "1");
        
    }
    
    @Test
    public void testHeroLevelUp() {
        
        when(heroRepository.findById("1")).thenReturn(Optional.of(createHero("1", "1")));
        
        Hero heroRet = heroService.levelUp("1", "1");
        
        Assert.assertEquals("level must be 2", new Integer(2), heroRet.getLevel());
        
    }
}
