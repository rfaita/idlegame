package com.idle.game.tests;

import com.idle.game.helper.PlayerHelper;
import com.idle.game.tests.helper.TestHelper;
import com.idle.game.model.mongo.Player;
import com.idle.game.model.mongo.PlayerResource;
import com.idle.game.model.mongo.ResourceType;
import com.idle.game.server.repository.PlayerRepository;
import com.idle.game.server.repository.PlayerResourceRepository;
import com.idle.game.server.service.PlayerResourceService;
import static com.idle.game.tests.helper.TestHelper.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import javax.validation.ValidationException;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import static org.mockito.Matchers.any;
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
public class PlayerResourceTest {

    @MockBean
    private PlayerHelper playerHelper;

    @MockBean
    private PlayerResourceRepository playerResourceRepository;

    @Autowired
    private PlayerResourceService playerResourceService;

    @Rule
    public ExpectedException expcetionExpect = ExpectedException.none();

    @Test
    public void testComputeResources1Second() {

        PlayerResource pr1 = createPlayerResource("3");

        pr1.setLastTimeResourcesCollected(Date.from(LocalDateTime.now().minus(1, ChronoUnit.SECONDS).atZone(ZoneId.systemDefault()).toInstant()));

        when(this.playerResourceRepository.findByPlayer("3")).thenReturn(pr1);
        
        when(this.playerHelper.getPlayerByLinkedUser("3")).thenReturn(createPlayer("3"));
        
        PlayerResource pr1Expected = playerResourceService.computeResources("3");

        Assert.assertEquals("3", pr1Expected.getPlayer());

        Assert.assertEquals(new Long(0L), pr1Expected.getResource(ResourceType.RUNE).getValue());
        Assert.assertEquals(new Long(0L), pr1Expected.getResource(ResourceType.GOLD).getValue());
        Assert.assertEquals(new Long(0L), pr1Expected.getResource(ResourceType.GEM).getValue());
        Assert.assertEquals(new Long(10L), pr1Expected.getResource(ResourceType.RUNE_PS).getValue());
        Assert.assertEquals(new Long(100L), pr1Expected.getResource(ResourceType.GOLD_PS).getValue());
        Assert.assertEquals(new Long(1L), pr1Expected.getResource(ResourceType.GEM_PS).getValue());

    }

    @Test
    public void testComputeResources10Second() {

        PlayerResource pr1 = createPlayerResource("4");

        pr1.setLastTimeResourcesCollected(Date.from(LocalDateTime.now().minus(10, ChronoUnit.SECONDS).atZone(ZoneId.systemDefault()).toInstant()));

        when(this.playerResourceRepository.findByPlayer("4")).thenReturn(pr1);
        
        when(this.playerHelper.getPlayerByLinkedUser("4")).thenReturn(createPlayer("4"));

        when(this.playerResourceRepository.save(any(PlayerResource.class))).thenAnswer(createPlayerResourceAnswerForSomeInput());

        PlayerResource pr1Expected = playerResourceService.computeResources("4");

        Assert.assertEquals("4", pr1Expected.getPlayer());

        Assert.assertEquals(new Long(100L), pr1Expected.getResource(ResourceType.RUNE).getValue());
        Assert.assertEquals(new Long(1000L), pr1Expected.getResource(ResourceType.GOLD).getValue());
        Assert.assertEquals(new Long(10L), pr1Expected.getResource(ResourceType.GEM).getValue());
        Assert.assertEquals(new Long(10L), pr1Expected.getResource(ResourceType.RUNE_PS).getValue());
        Assert.assertEquals(new Long(100L), pr1Expected.getResource(ResourceType.GOLD_PS).getValue());
        Assert.assertEquals(new Long(1L), pr1Expected.getResource(ResourceType.GEM_PS).getValue());

    }

    @Test
    public void testComputeResources5Second() {

        PlayerResource pr1 = createPlayerResource("5");

        pr1.setLastTimeResourcesCollected(Date.from(LocalDateTime.now().minus(5, ChronoUnit.SECONDS).atZone(ZoneId.systemDefault()).toInstant()));
        
        when(this.playerResourceRepository.findByPlayer("5")).thenReturn(pr1);

        when(this.playerHelper.getPlayerByLinkedUser("5")).thenReturn(createPlayer("5"));

        when(this.playerResourceRepository.save(any(PlayerResource.class))).thenAnswer(createPlayerResourceAnswerForSomeInput());

        PlayerResource pr1Expected = playerResourceService.computeResources("5");

        Assert.assertEquals("5", pr1Expected.getPlayer());

        Assert.assertEquals(new Long(50L), pr1Expected.getResource(ResourceType.RUNE).getValue());
        Assert.assertEquals(new Long(500L), pr1Expected.getResource(ResourceType.GOLD).getValue());
        Assert.assertEquals(new Long(5L), pr1Expected.getResource(ResourceType.GEM).getValue());
        Assert.assertEquals(new Long(10L), pr1Expected.getResource(ResourceType.RUNE_PS).getValue());
        Assert.assertEquals(new Long(100L), pr1Expected.getResource(ResourceType.GOLD_PS).getValue());
        Assert.assertEquals(new Long(1L), pr1Expected.getResource(ResourceType.GEM_PS).getValue());

    }

    @Test
    public void testUserResourcesWithHaveValue() {

        when(this.playerHelper.getPlayerByLinkedUser("6")).thenReturn(createPlayer("6"));
        when(this.playerResourceRepository.findByPlayer("6")).thenReturn(createPlayerResourceWith100OfEachResources("6"));

        when(this.playerResourceRepository.save(any(PlayerResource.class))).thenAnswer(createPlayerResourceAnswerForSomeInput());

        PlayerResource pr1Expected = playerResourceService.useResources("6", create50OfEachResources());

        Assert.assertEquals("6", pr1Expected.getPlayer());

        Assert.assertEquals(new Long(50L), pr1Expected.getResource(ResourceType.RUNE).getValue());
        Assert.assertEquals(new Long(50L), pr1Expected.getResource(ResourceType.GOLD).getValue());
        Assert.assertEquals(new Long(50L), pr1Expected.getResource(ResourceType.GEM).getValue());

    }

    @Test
    public void testUserResourcesWithAllHaveValue() {

        when(this.playerHelper.getPlayerByLinkedUser("7")).thenReturn(createPlayer("7"));
        when(this.playerResourceRepository.findByPlayer("7")).thenReturn(createPlayerResourceWith50OfEachResources("7"));

        when(this.playerResourceRepository.save(any(PlayerResource.class))).thenAnswer(createPlayerResourceAnswerForSomeInput());

        PlayerResource pr1Expected = playerResourceService.useResources("7", create50OfEachResources());

        Assert.assertEquals("7", pr1Expected.getPlayer());

        Assert.assertEquals(new Long(0L), pr1Expected.getResource(ResourceType.RUNE).getValue());
        Assert.assertEquals(new Long(0L), pr1Expected.getResource(ResourceType.GOLD).getValue());
        Assert.assertEquals(new Long(0L), pr1Expected.getResource(ResourceType.GEM).getValue());

    }

    @Test
    public void testUserResourcesWithMoreHaveValue() {

        when(this.playerHelper.getPlayerByLinkedUser("8")).thenReturn(createPlayer("8"));
        when(this.playerResourceRepository.findByPlayer("8")).thenReturn(createPlayerResourceWith50OfEachResources("8"));

        when(this.playerResourceRepository.save(any(PlayerResource.class))).thenAnswer(createPlayerResourceAnswerForSomeInput());
        
        expcetionExpect.expect(ValidationException.class);
        expcetionExpect.expectMessage("using.more.resource.you.have");

        playerResourceService.useResources("8", create100OfEachResources());

    }

}
