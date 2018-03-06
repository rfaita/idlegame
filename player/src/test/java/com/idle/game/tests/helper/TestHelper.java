package com.idle.game.tests.helper;

import com.idle.game.model.Formation;
import com.idle.game.model.Friend;
import com.idle.game.model.Player;
import com.idle.game.model.PlayerResource;
import com.idle.game.model.PvpRating;
import com.idle.game.model.Resource;
import com.idle.game.model.ResourceType;
import java.util.ArrayList;
import java.util.List;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

/**
 *
 * @author rafael
 */
public class TestHelper {

    public static Player createPlayer(String id) {
        Player p1 = new Player();
        p1.setId(id);
        p1.setLevel(1);
        p1.setLinkedUser(id);
        p1.setName("test1");

        return p1;
    }

    public static PlayerResource createPlayerResource(String player) {
        PlayerResource ret = new PlayerResource();
        ret.setResources(createBasicResources());
        ret.setPlayer(player);
        return ret;
    }

    public static PlayerResource createPlayerResourceWith50OfEachResources(String id) {
        PlayerResource p1 = createPlayerResource(id);
        p1.setResources(create50OfEachResources());

        return p1;
    }

    public static PlayerResource createPlayerResourceWith100OfEachResources(String id) {
        PlayerResource p1 = createPlayerResource(id);
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

    public static PvpRating createPvpRatingWithId(String id, String playerId, Integer rat, String formId) {
        PvpRating ret = new PvpRating();
        ret.setId(id);
        ret.setPlayer(playerId);
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

    public static Answer<PlayerResource> createPlayerResourceAnswerForSomeInput() {
        return (InvocationOnMock invocation) -> {
            return (PlayerResource) invocation.getArguments()[0];
        };
    }

    public static Answer<Player> createPlayerAnswerForSomeInput() {
        return (InvocationOnMock invocation) -> {
            return (Player) invocation.getArguments()[0];
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

}
