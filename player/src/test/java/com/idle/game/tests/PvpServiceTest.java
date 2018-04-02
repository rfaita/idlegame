package com.idle.game.tests;

import com.idle.game.core.battle.Battle;
import static com.idle.game.core.formation.type.FormationAllocation.PVP_DEFENSE;
import com.idle.game.helper.client.battle.BattleClient;
import com.idle.game.helper.client.battle.FormationClient;
import com.idle.game.helper.client.user.UserClient;
import com.idle.game.model.Formation;
import com.idle.game.model.PvpRating;
import com.idle.game.server.dto.Envelope;
import com.idle.game.server.repository.PvpRatingRepository;
import com.idle.game.server.service.PvpService;
import static com.idle.game.tests.helper.TestHelper.createFormation;
import static com.idle.game.tests.helper.TestHelper.createPvpRating1000;
import static com.idle.game.tests.helper.TestHelper.createPvpRatingAnswerForSomeInput;
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
import static com.idle.game.tests.helper.TestHelper.createListPvpRating1050;
import static com.idle.game.tests.helper.TestHelper.createListPvpRating1150;
import static com.idle.game.tests.helper.TestHelper.createListPvpRating1550;
import static com.idle.game.tests.helper.TestHelper.createListPvpRating950;
import static com.idle.game.tests.helper.TestHelper.createPvpRatingWithId;
import static com.idle.game.tests.helper.TestHelper.createUser;
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
    private BattleClient battleClient;

    @MockBean
    private FormationClient formationClient;

    @MockBean
    private UserClient userClient;

    @Autowired
    private PvpService pvpService;

    @Rule
    public ExpectedException expcetionExpect = ExpectedException.none();

    @Test
    public void testFindPvpRatings() {

        when(this.pvpRatingRepository.findByUserId("1")).thenReturn(createPvpRating1000("1"));

        when(this.pvpRatingRepository.findAllByUserIdNotAndRatingBetween("1", 1499, 1651)).thenReturn(createListPvpRating1550("2"));
        when(this.pvpRatingRepository.findAllByUserIdNotAndRatingBetween("1", 1099, 1211)).thenReturn(createListPvpRating1150("3"));
        when(this.pvpRatingRepository.findAllByUserIdNotAndRatingBetween("1", 999, 1101)).thenReturn(createListPvpRating1050("4"));
        when(this.pvpRatingRepository.findAllByUserIdNotAndRatingBetween("1", 899, 991)).thenReturn(createListPvpRating950("5"));

        List<PvpRating> ratings = pvpService.findPvpRatings("1");

        Assert.assertEquals(4, ratings.size());

        Assert.assertEquals("player must be 2", "2", ratings.get(0).getUserId());
        Assert.assertEquals("player ratting must be 1550", new Integer(1550), ratings.get(0).getRating());

        Assert.assertEquals("player must be 3", "3", ratings.get(1).getUserId());
        Assert.assertEquals("player ratting must be 1150", new Integer(1150), ratings.get(1).getRating());

        Assert.assertEquals("player must be 4", "4", ratings.get(2).getUserId());
        Assert.assertEquals("player ratting must be 1050", new Integer(1050), ratings.get(2).getRating());

        Assert.assertEquals("player must be 5", "5", ratings.get(3).getUserId());
        Assert.assertEquals("player ratting must be 950", new Integer(950), ratings.get(3).getRating());
    }

    @Test
    public void testFindPvpRatingsPvpFormationNotFound() {

        when(this.pvpRatingRepository.findByUserId("3")).thenReturn(null);

        when(this.formationClient.findByUserIdAndFormationAllocation("3", PVP_DEFENSE.toString())).thenReturn(new Envelope((Formation) null));

        when(userClient.findById("3")).thenReturn(new Envelope(createUser("3")));
        
        expcetionExpect.expect(ValidationException.class);
        expcetionExpect.expectMessage("formation.pvp.not.found");

        pvpService.findPvpRatings("3");

    }

    @Test
    public void testFindPvpRatingsRatingNotFound() {

        when(pvpRatingRepository.findByUserId("1")).thenReturn(null);

        when(formationClient.findByUserIdAndFormationAllocation("1", PVP_DEFENSE.toString())).thenReturn(new Envelope(createFormation("123")));

        when(pvpRatingRepository.save(any(PvpRating.class))).thenAnswer(createPvpRatingAnswerForSomeInput());

        when(pvpRatingRepository.findAllByUserIdNotAndRatingBetween("1", 1499, 1651)).thenReturn(createListPvpRating1550("2"));
        when(pvpRatingRepository.findAllByUserIdNotAndRatingBetween("1", 1099, 1211)).thenReturn(createListPvpRating1150("3"));
        when(pvpRatingRepository.findAllByUserIdNotAndRatingBetween("1", 999, 1101)).thenReturn(createListPvpRating1050("4"));
        when(pvpRatingRepository.findAllByUserIdNotAndRatingBetween("1", 899, 991)).thenReturn(createListPvpRating950("5"));

        when(userClient.findById("1")).thenReturn(new Envelope(createUser("1")));

        List<PvpRating> ratings = pvpService.findPvpRatings("1");

        Assert.assertEquals(4, ratings.size());

        Assert.assertEquals("player must be 2", "2", ratings.get(0).getUserId());
        Assert.assertEquals("player ratting must be 1550", new Integer(1550), ratings.get(0).getRating());

        Assert.assertEquals("player must be 3", "3", ratings.get(1).getUserId());
        Assert.assertEquals("player ratting must be 1150", new Integer(1150), ratings.get(1).getRating());

        Assert.assertEquals("player must be 4", "4", ratings.get(2).getUserId());
        Assert.assertEquals("player ratting must be 1050", new Integer(1050), ratings.get(2).getRating());

        Assert.assertEquals("player must be 5", "5", ratings.get(3).getUserId());
        Assert.assertEquals("player ratting must be 950", new Integer(950), ratings.get(3).getRating());
    }

    @Test
    public void testPvpBattleTargetNotFound() {

        when(this.pvpRatingRepository.findOne("1234")).thenReturn(null);

        expcetionExpect.expect(ValidationException.class);
        expcetionExpect.expectMessage("pvpRating.not.found");

        pvpService.battlePvpRattings("1", "1234");

    }

    @Test
    public void testPvpBattle() {

        when(this.pvpRatingRepository.findByUserId("1")).thenReturn(createPvpRating1000("1", "5432"));
        when(this.pvpRatingRepository.findOne("1234")).thenReturn(createPvpRatingWithId("2", "1234", 1000, "4321"));

        Battle ret = mock(Battle.class);

        when(this.battleClient.doBattle("5432", "4321")).thenReturn(new Envelope(ret));

        when(ret.isFormationAttackWinner()).thenReturn(Boolean.TRUE);

        when(this.pvpRatingRepository.save(any(PvpRating.class))).thenAnswer(createPvpRatingAnswerForSomeInput());

        pvpService.battlePvpRattings("1", "1234");

    }

}
