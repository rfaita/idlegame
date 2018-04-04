package com.idle.game.tests;

import com.idle.game.core.battle.Battle;
import com.idle.game.core.formation.type.FormationAllocation;
import com.idle.game.helper.client.battle.BattleClient;
import com.idle.game.helper.client.battle.BattleFieldClient;
import com.idle.game.helper.client.battle.FormationClient;
import com.idle.game.helper.client.campaign.CampaignPathClient;
import com.idle.game.helper.client.mail.MailClient;
import com.idle.game.model.Formation;
import com.idle.game.model.battle.BattleField;
import com.idle.game.model.campaign.UserCampaignPath;
import com.idle.game.server.dto.Envelope;
import com.idle.game.server.repository.UserCampaignPathRepository;
import com.idle.game.server.service.UserCampaignPathService;
import static com.idle.game.tests.helper.TestHelper.*;
import javax.validation.ValidationException;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
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
public class UserCampaignPathServiceTest {

    @MockBean
    private UserCampaignPathRepository userCampaignPathRepository;

    @MockBean
    private CampaignPathClient campaignPathClient;

    @MockBean
    private FormationClient formationClient;

    @MockBean
    private BattleFieldClient battleFieldClient;

    @MockBean
    private BattleClient battleClient;

    @MockBean
    private MailClient mailClient;

    @Autowired
    private UserCampaignPathService userCampaignPathService;

    @Rule
    public ExpectedException expcetionExpect = ExpectedException.none();

    @Test
    public void testFindByUserIdNoCampaignPath() {

        when(userCampaignPathRepository.findByUserId("1")).thenReturn(null);
        when(campaignPathClient.getInitialPath()).thenReturn(new Envelope(createCampaignPath("123")));

        when(userCampaignPathRepository.save(any(UserCampaignPath.class))).thenAnswer(createUserCampaignPathAnswerForSomeInput());

        UserCampaignPath ret = userCampaignPathService.findByUserId("1");

        Assert.assertEquals("123", ret.getCampaignPathId());

    }

    @Test
    public void testBattleNoPveFormation() {

        when(formationClient.findByUserIdAndFormationAllocation("1", FormationAllocation.PVE.toString()))
                .thenReturn(new Envelope((Formation) null));

        expcetionExpect.expect(ValidationException.class);
        expcetionExpect.expectMessage("pve.formation.not.found");

        userCampaignPathService.battle("1");

    }

    @Test
    public void testBattleNoBattleField() {

        when(formationClient.findByUserIdAndFormationAllocation("1", FormationAllocation.PVE.toString()))
                .thenReturn(new Envelope(createPveFormation("123", "1")));

        when(userCampaignPathRepository.findByUserId("1")).thenReturn(createUserCampaignPath("1", "456"));

        when(campaignPathClient.findById("456")).thenReturn(new Envelope(createCampaignPath("456", "789")));
        when(battleFieldClient.findById("789")).thenReturn(new Envelope((BattleField) null));

        expcetionExpect.expect(ValidationException.class);
        expcetionExpect.expectMessage("battle.field.not.found");

        userCampaignPathService.battle("1");

    }

    @Test
    public void testBattleInvalidInitialFormation() {

        when(formationClient.findByUserIdAndFormationAllocation("1", FormationAllocation.PVE.toString()))
                .thenReturn(new Envelope(createPveFormation("123", "1")));

        when(userCampaignPathRepository.findByUserId("1")).thenReturn(createUserCampaignPath("1", "456"));

        when(campaignPathClient.findById("456")).thenReturn(new Envelope(createCampaignPath("456", "789")));
        when(battleFieldClient.findById("789")).thenReturn(new Envelope(createBattleField("789")));

        expcetionExpect.expect(ValidationException.class);
        expcetionExpect.expectMessage("invalid.initial.formation");

        userCampaignPathService.battle("04", "1");

    }

    @Test
    public void testBattle() {

        when(formationClient.findByUserIdAndFormationAllocation("1", FormationAllocation.PVE.toString()))
                .thenReturn(new Envelope(createPveFormation("123", "1")));

        when(userCampaignPathRepository.findByUserId("1")).thenReturn(createUserCampaignPath("1", "456"));

        when(campaignPathClient.findById("456")).thenReturn(new Envelope(createCampaignPath("456", "789")));
        when(battleFieldClient.findById("789")).thenReturn(new Envelope(createBattleField("789")));

        when(userCampaignPathRepository.save(any(UserCampaignPath.class))).thenAnswer(createUserCampaignPathAnswerForSomeInput());

        Battle ret = mock(Battle.class);

        when(this.battleClient.doBattle("123", "13")).thenReturn(new Envelope(ret));

        when(ret.isFormationAttackWinner()).thenReturn(Boolean.TRUE);

        userCampaignPathService.battle("13", "1");

    }

    @Test
    public void testBattleNextCampaignPath() {

        when(formationClient.findByUserIdAndFormationAllocation("1", FormationAllocation.PVE.toString()))
                .thenReturn(new Envelope(createPveFormation("123", "1")));

        when(userCampaignPathRepository.findByUserId("1")).thenReturn(createUserCampaignPath("1", "456", "01"));

        when(campaignPathClient.findById("456")).thenReturn(new Envelope(createCampaignPath("456", "789")));
        when(battleFieldClient.findById("789")).thenReturn(new Envelope(createBattleField("789")));

        when(userCampaignPathRepository.save(any(UserCampaignPath.class))).thenAnswer(createUserCampaignPathAnswerForSomeInput());

        Battle ret = mock(Battle.class);

        when(this.battleClient.doBattle("123", "01")).thenReturn(new Envelope(ret));

        when(ret.isFormationAttackWinner()).thenReturn(Boolean.TRUE);

        userCampaignPathService.battle("1");

    }

    @Test
    public void testResetFormation() {

        when(userCampaignPathRepository.findByUserId("1")).thenReturn(createUserCampaignPath("1", "456", "01"));

        when(userCampaignPathRepository.save(any(UserCampaignPath.class))).thenAnswer(createUserCampaignPathAnswerForSomeInput());

        UserCampaignPath ret = userCampaignPathService.resetPath("1");

        Assert.assertEquals(null, ret.getFormationId());

    }

}
