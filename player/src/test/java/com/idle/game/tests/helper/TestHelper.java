package com.idle.game.tests.helper;

import com.idle.game.core.formation.type.FormationAllocation;
import com.idle.game.model.Formation;
import com.idle.game.model.Friend;
import com.idle.game.model.UserResource;
import com.idle.game.model.PvpRating;
import com.idle.game.model.Resource;
import com.idle.game.model.ResourceType;
import com.idle.game.model.User;
import com.idle.game.model.battle.BattleField;
import com.idle.game.model.battle.BattleSite;
import com.idle.game.model.campaign.CampaignPath;
import com.idle.game.model.campaign.UserCampaignPath;
import java.util.ArrayList;
import java.util.List;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

/**
 *
 * @author rafael
 */
public class TestHelper {

    public static Formation createPveFormation(String id, String userId) {
        Formation ret = new Formation();
        ret.setUserId(userId);
        ret.setFormationAllocation(FormationAllocation.PVE);
        ret.setId(id);
        return ret;
    }

    public static UserCampaignPath createUserCampaignPath(String userId, String campaignPathId) {
        return createUserCampaignPath(userId, campaignPathId, null);
    }

    public static UserCampaignPath createUserCampaignPath(String userId, String campaignPathId, String formationId) {
        UserCampaignPath ret = new UserCampaignPath();

        ret.setUserId(userId);
        ret.setCampaignPathId(campaignPathId);
        ret.setFormationId(formationId);

        return ret;
    }

    public static BattleField createBattleField(String id) {
        BattleField ret = new BattleField();

        ret.setId(id);

        ret.addInitialLayers();

        ret.getLayer(0).addSite(new BattleSite("01", null));

        ret.getLayer(1).addSite(new BattleSite("02", "01"));
        ret.getLayer(1).addSite(new BattleSite("03", "01"));
        ret.getLayer(1).addSite(new BattleSite("04", "01"));

        ret.getLayer(2).addSite(new BattleSite("05", "02"));
        ret.getLayer(2).addSite(new BattleSite("06", "02"));
        ret.getLayer(2).addSite(new BattleSite("07", "02"));

        ret.getLayer(2).addSite(new BattleSite("08", "03"));
        ret.getLayer(2).addSite(new BattleSite("09", "03"));
        ret.getLayer(2).addSite(new BattleSite("10", "03"));

        ret.getLayer(2).addSite(new BattleSite("11", "04"));
        ret.getLayer(2).addSite(new BattleSite("12", "04"));
        ret.getLayer(2).addSite(new BattleSite("13", "04"));

        return ret;
    }

    public static CampaignPath createCampaignPath(String id) {
        return createCampaignPath(id, null);
    }

    public static CampaignPath createCampaignPath(String id, String battleFieldId) {
        CampaignPath ret = new CampaignPath();

        ret.setId(id);
        ret.setBattleFieldId(battleFieldId);

        return ret;
    }

    public static User createUser(String id) {
        User p1 = new User();
        p1.setId(id);
        p1.setNickName("test1");

        return p1;
    }

    public static UserResource createUserResource(String userId) {
        UserResource ret = new UserResource();
        ret.setResources(createBasicResources());
        ret.setUserId(userId);
        return ret;
    }

    public static UserResource createUserResourceWith50OfEachResources(String id) {
        UserResource p1 = createUserResource(id);
        p1.setResources(create50OfEachResources());

        return p1;
    }

    public static UserResource createUserResourceWith100OfEachResources(String id) {
        UserResource p1 = createUserResource(id);
        p1.setResources(create100OfEachResources());

        return p1;
    }

    public static List<Resource> create50OfEachResources() {
        List<Resource> ret = new ArrayList<>();
        ret.add(create50GemResource());
        ret.add(create50RuneResource());
        ret.add(create50GoldResource());
        return ret;
    }

    public static List<Resource> create100OfEachResources() {
        List<Resource> ret = new ArrayList<>();
        ret.add(create100GemResource());
        ret.add(create100RuneResource());
        ret.add(create100GoldResource());
        return ret;
    }

    public static List<Resource> createBasicResources() {
        List<Resource> ret = new ArrayList<>();
        ret.add(createGemResource());
        ret.add(createRuneResource());
        ret.add(createGoldResource());
        ret.add(createGemPSResource());
        ret.add(createRunePSResource());
        ret.add(createGoldPSResource());
        return ret;
    }

    public static Resource createGoldPSResource() {
        return new Resource(ResourceType.GOLD_PS, 100L);
    }

    public static Resource createRunePSResource() {
        return new Resource(ResourceType.RUNE_PS, 10L);
    }

    public static Resource createGemPSResource() {
        return new Resource(ResourceType.GEM_PS, 1L);
    }

    public static Resource createGoldResource() {
        return new Resource(ResourceType.GOLD, 0L);
    }

    public static Resource createRuneResource() {
        return new Resource(ResourceType.RUNE, 0L);
    }

    public static Resource createGemResource() {
        return new Resource(ResourceType.GEM, 0L);
    }

    public static Resource create100GoldResource() {
        return new Resource(ResourceType.GOLD, 100L);
    }

    public static Resource create100RuneResource() {
        return new Resource(ResourceType.RUNE, 100L);
    }

    public static Resource create100GemResource() {
        return new Resource(ResourceType.GEM, 100L);
    }

    public static Resource create50GoldResource() {
        return new Resource(ResourceType.GOLD, 50L);
    }

    public static Resource create50RuneResource() {
        return new Resource(ResourceType.RUNE, 50L);
    }

    public static Resource create50GemResource() {
        return new Resource(ResourceType.GEM, 50L);
    }

    public static PvpRating createPvpRatingWithId(String id, String userId, Integer rat, String formId) {
        PvpRating ret = new PvpRating();
        ret.setId(id);
        ret.setUserId(userId);
        ret.setRating(rat);
        ret.setFormation(formId);
        return ret;
    }

    public static PvpRating createPvpRating(String playerId, Integer rat, String formId) {
        return createPvpRatingWithId(null, playerId, rat, formId);
    }

    public static PvpRating createPvpRating(String id, Integer rat) {
        return createPvpRating(id, rat, null);
    }

    public static PvpRating createPvpRating1000(String id) {
        return createPvpRating1000(id, null);
    }

    public static PvpRating createPvpRating1000(String id, String formId) {
        return createPvpRating(id, 1000, formId);
    }

    public static List<PvpRating> createListPvpRating1550(String id) {
        List<PvpRating> ret = new ArrayList<>();
        ret.add(createPvpRating(id, 1550));
        return ret;
    }

    public static List<PvpRating> createListPvpRating1150(String id) {
        List<PvpRating> ret = new ArrayList<>();
        ret.add(createPvpRating(id, 1150));
        return ret;
    }

    public static List<PvpRating> createListPvpRating1050(String id) {
        List<PvpRating> ret = new ArrayList<>();
        ret.add(createPvpRating(id, 1050));
        return ret;
    }

    public static List<PvpRating> createListPvpRating950(String id) {
        List<PvpRating> ret = new ArrayList<>();
        ret.add(createPvpRating(id, 950));
        return ret;
    }

    public static Formation createFormation(String id) {
        Formation ret = new Formation();
        ret.setId(id);
        return ret;
    }

    public static Answer<UserResource> createUserResourceAnswerForSomeInput() {
        return (InvocationOnMock invocation) -> {
            return (UserResource) invocation.getArguments()[0];
        };
    }

    public static Answer<PvpRating> createPvpRatingAnswerForSomeInput() {
        return (InvocationOnMock invocation) -> {
            return (PvpRating) invocation.getArguments()[0];
        };
    }

    public static Answer<Friend> createFriendAnswerForSomeInput() {
        return (InvocationOnMock invocation) -> {
            return (Friend) invocation.getArguments()[0];
        };
    }

    public static Answer<UserCampaignPath> createUserCampaignPathAnswerForSomeInput() {
        return (InvocationOnMock invocation) -> {
            return (UserCampaignPath) invocation.getArguments()[0];
        };
    }

}
