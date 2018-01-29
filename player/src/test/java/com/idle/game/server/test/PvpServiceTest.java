package com.idle.game.server.test;

import com.idle.game.core.battle.Battle;
import static com.idle.game.core.formation.type.FormationAllocation.PVP;
import static com.idle.game.core.formation.type.FormationAllocation.PVP_DEFENSE;
import com.idle.game.helper.BattleHelper;
import com.idle.game.helper.FormationHelper;
import com.idle.game.helper.PlayerHelper;
import com.idle.game.model.mongo.PvpRating;
import com.idle.game.server.repository.PvpRatingRepository;
import com.idle.game.server.service.PvpService;
import static com.idle.game.server.test.TestHelper.createFormation;
import static com.idle.game.server.test.TestHelper.createPlayer;
import static com.idle.game.server.test.TestHelper.createPvpRating1000;
import static com.idle.game.server.test.TestHelper.createPvpRatingAnswerForSomeInput;
import java.util.List;
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
import static com.idle.game.server.test.TestHelper.createListPvpRating1050;
import static com.idle.game.server.test.TestHelper.createListPvpRating1150;
import static com.idle.game.server.test.TestHelper.createListPvpRating1550;
import static com.idle.game.server.test.TestHelper.createListPvpRating950;
import static com.idle.game.server.test.TestHelper.createPvpRatingWithId;
import static org.mockito.Mockito.mock;

/**
 *
 * @author rafael
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PvpServiceTest {

    @MockBean
    private PvpRatingRepository pvpRatingRepository;
    @MockBean
    private BattleHelper battleHelper;
    @MockBean
    private PlayerHelper playerHelper;
    @MockBean
    private FormationHelper formationHelper;

    @Autowired
    private PvpService pvpService;

    @Rule
    public ExpectedException expcetionExpect = ExpectedException.none();

    @Test
    public void testFindPvpRatingsPlayerNotFound() {

        when(this.playerHelper.getPlayerByLinkedUser("2")).thenReturn(null);

        expcetionExpect.expect(ValidationException.class);
        expcetionExpect.expectMessage("player.not.found");

        pvpService.findPvpRatings("2");

    }

    @Test
    public void testFindPvpRatings() {

        when(this.playerHelper.getPlayerByLinkedUser("1")).thenReturn(createPlayer("1"));

        when(this.pvpRatingRepository.findByPlayer("1")).thenReturn(createPvpRating1000("1"));

        when(this.pvpRatingRepository.findAllByPlayerNotAndRatingBetween("1", 1499, 1651)).thenReturn(createListPvpRating1550("2"));
        when(this.pvpRatingRepository.findAllByPlayerNotAndRatingBetween("1", 1099, 1211)).thenReturn(createListPvpRating1150("3"));
        when(this.pvpRatingRepository.findAllByPlayerNotAndRatingBetween("1", 999, 1101)).thenReturn(createListPvpRating1050("4"));
        when(this.pvpRatingRepository.findAllByPlayerNotAndRatingBetween("1", 899, 991)).thenReturn(createListPvpRating950("5"));

        List<PvpRating> ratings = pvpService.findPvpRatings("1");

        Assert.assertEquals(4, ratings.size());

        Assert.assertEquals("player must be 2", "2", ratings.get(0).getPlayer());
        Assert.assertEquals("player ratting must be 1550", new Integer(1550), ratings.get(0).getRating());

        Assert.assertEquals("player must be 3", "3", ratings.get(1).getPlayer());
        Assert.assertEquals("player ratting must be 1150", new Integer(1150), ratings.get(1).getRating());

        Assert.assertEquals("player must be 4", "4", ratings.get(2).getPlayer());
        Assert.assertEquals("player ratting must be 1050", new Integer(1050), ratings.get(2).getRating());

        Assert.assertEquals("player must be 5", "5", ratings.get(3).getPlayer());
        Assert.assertEquals("player ratting must be 950", new Integer(950), ratings.get(3).getRating());
    }

    @Test
    public void testFindPvpRatingsPvpFormationNotFound() {

        when(this.playerHelper.getPlayerByLinkedUser("3")).thenReturn(createPlayer("3"));

        when(this.pvpRatingRepository.findByPlayer("3")).thenReturn(null);

        when(this.formationHelper.getFormationByPlayerAndFormationAllocation("3", PVP_DEFENSE.toString())).thenReturn(null);

        expcetionExpect.expect(ValidationException.class);
        expcetionExpect.expectMessage("formation.pvp.not.found");

        pvpService.findPvpRatings("3");

    }

    @Test
    public void testFindPvpRatingsRatingNotFound() {

        when(this.playerHelper.getPlayerByLinkedUser("1")).thenReturn(createPlayer("1"));

        when(this.pvpRatingRepository.findByPlayer("1")).thenReturn(null);

        when(this.formationHelper.getFormationByPlayerAndFormationAllocation("1", PVP_DEFENSE.toString())).thenReturn(createFormation("123"));

        when(this.pvpRatingRepository.save(any(PvpRating.class))).thenAnswer(createPvpRatingAnswerForSomeInput());

        when(this.pvpRatingRepository.findAllByPlayerNotAndRatingBetween("1", 1499, 1651)).thenReturn(createListPvpRating1550("2"));
        when(this.pvpRatingRepository.findAllByPlayerNotAndRatingBetween("1", 1099, 1211)).thenReturn(createListPvpRating1150("3"));
        when(this.pvpRatingRepository.findAllByPlayerNotAndRatingBetween("1", 999, 1101)).thenReturn(createListPvpRating1050("4"));
        when(this.pvpRatingRepository.findAllByPlayerNotAndRatingBetween("1", 899, 991)).thenReturn(createListPvpRating950("5"));

        List<PvpRating> ratings = pvpService.findPvpRatings("1");

        Assert.assertEquals(4, ratings.size());

        Assert.assertEquals("player must be 2", "2", ratings.get(0).getPlayer());
        Assert.assertEquals("player ratting must be 1550", new Integer(1550), ratings.get(0).getRating());

        Assert.assertEquals("player must be 3", "3", ratings.get(1).getPlayer());
        Assert.assertEquals("player ratting must be 1150", new Integer(1150), ratings.get(1).getRating());

        Assert.assertEquals("player must be 4", "4", ratings.get(2).getPlayer());
        Assert.assertEquals("player ratting must be 1050", new Integer(1050), ratings.get(2).getRating());

        Assert.assertEquals("player must be 5", "5", ratings.get(3).getPlayer());
        Assert.assertEquals("player ratting must be 950", new Integer(950), ratings.get(3).getRating());
    }

    @Test
    public void testPvpBattlePlayerNotFound() {

        when(this.playerHelper.getPlayerByLinkedUser("1")).thenReturn(null);

        expcetionExpect.expect(ValidationException.class);
        expcetionExpect.expectMessage("player.not.found");

        pvpService.battlePvpRattings("1", "1234");

    }

    @Test
    public void testPvpBattleTargetNotFound() {

        when(this.playerHelper.getPlayerByLinkedUser("1")).thenReturn(createPlayer("1"));

        when(this.pvpRatingRepository.findById("1234")).thenReturn(null);

        expcetionExpect.expect(ValidationException.class);
        expcetionExpect.expectMessage("pvpRating.not.found");

        pvpService.battlePvpRattings("1", "1234");

    }

    @Test
    public void testPvpBattle() {

        when(this.playerHelper.getPlayerByLinkedUser("1")).thenReturn(createPlayer("1"));

        when(this.pvpRatingRepository.findByPlayer("1")).thenReturn(createPvpRating1000("1", "5432"));
        when(this.pvpRatingRepository.findById("1234")).thenReturn(createPvpRatingWithId("2", "1234", 1000, "4321"));

        Battle ret = mock(Battle.class);

        when(this.battleHelper.doBattle("5432", "4321")).thenReturn(ret);

        when(ret.isFormationAttackWinner()).thenReturn(Boolean.TRUE);

        when(this.pvpRatingRepository.save(any(PvpRating.class))).thenAnswer(createPvpRatingAnswerForSomeInput());

        pvpService.battlePvpRattings("1", "1234");

    }

}
