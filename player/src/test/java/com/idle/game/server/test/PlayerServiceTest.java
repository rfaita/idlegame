package com.idle.game.server.test;

import com.idle.game.model.mongo.Player;
import com.idle.game.model.mongo.ResourceType;
import com.idle.game.server.repository.PlayerRepository;
import com.idle.game.server.service.PlayerService;
import static com.idle.game.server.test.TestHelper.createPlayer;
import static com.idle.game.server.test.TestHelper.createPlayerAnswerForSomeInput;
import static com.idle.game.server.test.TestHelper.createPlayerWith100OfEachResources;
import static com.idle.game.server.test.TestHelper.createPlayerWith50OfEachResources;
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
public class PlayerServiceTest {

    @MockBean
    private PlayerRepository playerRepository;

    @Autowired
    private PlayerService playerService;

    @Rule
    public ExpectedException expcetionExpect = ExpectedException.none();

    @Test
    public void testFindById1() {

        when(this.playerRepository.findById("1")).thenReturn(createPlayer("1"));

        Player p1Expected = playerService.findById("1");

        Assert.assertEquals("1", p1Expected.getId());
        Assert.assertEquals(new Integer(1), p1Expected.getLevel());
        Assert.assertEquals("1", p1Expected.getLinkedUser());
        Assert.assertEquals("test1", p1Expected.getName());
    }

    @Test
    public void testFindByLinkedUser1() {

        when(this.playerRepository.findByLinkedUser("2")).thenReturn(createPlayer("2"));

        Player p1Expected = playerService.findByLinkedUser("1");

        Assert.assertEquals("2", p1Expected.getId());
        Assert.assertEquals(new Integer(1), p1Expected.getLevel());
        Assert.assertEquals("2", p1Expected.getLinkedUser());
        Assert.assertEquals("test1", p1Expected.getName());
    }

    @Test
    public void testComputeResources1Second() {

        Player p1 = createPlayer("3");

        p1.setLastTimeResourcesCollected(Date.from(LocalDateTime.now().minus(1, ChronoUnit.SECONDS).atZone(ZoneId.systemDefault()).toInstant()));

        when(this.playerRepository.findByLinkedUser("3")).thenReturn(p1);

        Player p1Expected = playerService.computeResources("3");

        Assert.assertEquals("3", p1Expected.getId());
        Assert.assertEquals(new Integer(1), p1Expected.getLevel());
        Assert.assertEquals("3", p1Expected.getLinkedUser());
        Assert.assertEquals("test1", p1Expected.getName());

        Assert.assertEquals(new Long(0L), p1Expected.getResource(ResourceType.RUNE).getValue());
        Assert.assertEquals(new Long(0L), p1Expected.getResource(ResourceType.GOLD).getValue());
        Assert.assertEquals(new Long(0L), p1Expected.getResource(ResourceType.GEM).getValue());
        Assert.assertEquals(new Long(10L), p1Expected.getResource(ResourceType.RUNE_PS).getValue());
        Assert.assertEquals(new Long(100L), p1Expected.getResource(ResourceType.GOLD_PS).getValue());
        Assert.assertEquals(new Long(1L), p1Expected.getResource(ResourceType.GEM_PS).getValue());

    }

    @Test
    public void testComputeResources10Second() {

        Player p1 = createPlayer("4");

        p1.setLastTimeResourcesCollected(Date.from(LocalDateTime.now().minus(10, ChronoUnit.SECONDS).atZone(ZoneId.systemDefault()).toInstant()));

        when(this.playerRepository.findByLinkedUser("4")).thenReturn(p1);

        when(this.playerRepository.save(any(Player.class))).thenAnswer(createPlayerAnswerForSomeInput());

        Player p1Expected = playerService.computeResources("4");

        Assert.assertEquals("4", p1Expected.getId());
        Assert.assertEquals(new Integer(1), p1Expected.getLevel());
        Assert.assertEquals("4", p1Expected.getLinkedUser());
        Assert.assertEquals("test1", p1Expected.getName());

        Assert.assertEquals(new Long(100L), p1Expected.getResource(ResourceType.RUNE).getValue());
        Assert.assertEquals(new Long(1000L), p1Expected.getResource(ResourceType.GOLD).getValue());
        Assert.assertEquals(new Long(10L), p1Expected.getResource(ResourceType.GEM).getValue());
        Assert.assertEquals(new Long(10L), p1Expected.getResource(ResourceType.RUNE_PS).getValue());
        Assert.assertEquals(new Long(100L), p1Expected.getResource(ResourceType.GOLD_PS).getValue());
        Assert.assertEquals(new Long(1L), p1Expected.getResource(ResourceType.GEM_PS).getValue());

    }

    @Test
    public void testComputeResources5Second() {

        Player p1 = createPlayer("5");

        p1.setLastTimeResourcesCollected(Date.from(LocalDateTime.now().minus(5, ChronoUnit.SECONDS).atZone(ZoneId.systemDefault()).toInstant()));

        when(this.playerRepository.findByLinkedUser("5")).thenReturn(p1);

        when(this.playerRepository.save(any(Player.class))).thenAnswer(createPlayerAnswerForSomeInput());

        Player p1Expected = playerService.computeResources("5");

        Assert.assertEquals("5", p1Expected.getId());
        Assert.assertEquals(new Integer(1), p1Expected.getLevel());
        Assert.assertEquals("5", p1Expected.getLinkedUser());
        Assert.assertEquals("test1", p1Expected.getName());

        Assert.assertEquals(new Long(50L), p1Expected.getResource(ResourceType.RUNE).getValue());
        Assert.assertEquals(new Long(500L), p1Expected.getResource(ResourceType.GOLD).getValue());
        Assert.assertEquals(new Long(5L), p1Expected.getResource(ResourceType.GEM).getValue());
        Assert.assertEquals(new Long(10L), p1Expected.getResource(ResourceType.RUNE_PS).getValue());
        Assert.assertEquals(new Long(100L), p1Expected.getResource(ResourceType.GOLD_PS).getValue());
        Assert.assertEquals(new Long(1L), p1Expected.getResource(ResourceType.GEM_PS).getValue());

    }

    @Test
    public void testUserResourcesWithHaveValue() {

        when(this.playerRepository.findByLinkedUser("6")).thenReturn(createPlayerWith100OfEachResources("6"));

        when(this.playerRepository.save(any(Player.class))).thenAnswer(createPlayerAnswerForSomeInput());

        Player p1Expected = playerService.useResources("6", TestHelper.create50OfEachResources());

        Assert.assertEquals("6", p1Expected.getId());
        Assert.assertEquals(new Integer(1), p1Expected.getLevel());
        Assert.assertEquals("6", p1Expected.getLinkedUser());
        Assert.assertEquals("test1", p1Expected.getName());

        Assert.assertEquals(new Long(50L), p1Expected.getResource(ResourceType.RUNE).getValue());
        Assert.assertEquals(new Long(50L), p1Expected.getResource(ResourceType.GOLD).getValue());
        Assert.assertEquals(new Long(50L), p1Expected.getResource(ResourceType.GEM).getValue());

    }

    @Test
    public void testUserResourcesWithAllHaveValue() {

        when(this.playerRepository.findByLinkedUser("7")).thenReturn(createPlayerWith50OfEachResources("7"));

        when(this.playerRepository.save(any(Player.class))).thenAnswer(createPlayerAnswerForSomeInput());

        Player p1Expected = playerService.useResources("7", TestHelper.create50OfEachResources());

        Assert.assertEquals("7", p1Expected.getId());
        Assert.assertEquals(new Integer(1), p1Expected.getLevel());
        Assert.assertEquals("7", p1Expected.getLinkedUser());
        Assert.assertEquals("test1", p1Expected.getName());

        Assert.assertEquals(new Long(0L), p1Expected.getResource(ResourceType.RUNE).getValue());
        Assert.assertEquals(new Long(0L), p1Expected.getResource(ResourceType.GOLD).getValue());
        Assert.assertEquals(new Long(0L), p1Expected.getResource(ResourceType.GEM).getValue());

    }

    @Test
    public void testUserResourcesWithMoreHaveValue() {

        when(this.playerRepository.findByLinkedUser("8")).thenReturn(createPlayerWith50OfEachResources("8"));

        when(this.playerRepository.save(any(Player.class))).thenAnswer(createPlayerAnswerForSomeInput());

        expcetionExpect.expect(ValidationException.class);
        expcetionExpect.expectMessage("using.more.resource.you.have");

        playerService.useResources("8", TestHelper.create100OfEachResources());

    }

}
