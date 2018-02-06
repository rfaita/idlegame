package com.idle.game.tests;

import com.idle.game.model.mongo.Player;
import com.idle.game.server.repository.PlayerRepository;
import com.idle.game.server.service.PlayerService;
import static com.idle.game.tests.helper.TestHelper.createPlayer;
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
public class PlayerServiceTest {

    @MockBean
    private PlayerRepository playerRepository;

    @Autowired
    private PlayerService playerService;

    @Rule
    public ExpectedException expcetionExpect = ExpectedException.none();

    @Test
    public void testFindById() {

        when(this.playerRepository.findById("1")).thenReturn(createPlayer("1"));

        Player p1Expected = playerService.findById("1");

        Assert.assertEquals("1", p1Expected.getId());
        Assert.assertEquals(new Integer(1), p1Expected.getLevel());
        Assert.assertEquals("1", p1Expected.getLinkedUser());
        Assert.assertEquals("test1", p1Expected.getName());
    }

    @Test
    public void testFindByLinkedUser() {

        when(this.playerRepository.findByLinkedUser("2")).thenReturn(createPlayer("2"));

        Player p1Expected = playerService.findByLinkedUser("2");

        Assert.assertEquals("2", p1Expected.getId());
        Assert.assertEquals(new Integer(1), p1Expected.getLevel());
        Assert.assertEquals("2", p1Expected.getLinkedUser());
        Assert.assertEquals("test1", p1Expected.getName());
    }

}
