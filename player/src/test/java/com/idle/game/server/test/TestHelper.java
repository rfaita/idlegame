package com.idle.game.server.test;

import com.idle.game.model.mongo.Friend;
import com.idle.game.model.mongo.Player;
import com.idle.game.model.mongo.Resource;
import com.idle.game.model.mongo.ResourceType;
import java.util.ArrayList;
import java.util.List;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

/**
 *
 * @author rafael
 */
public class TestHelper {

    public static Player createPlayer() {
        Player p1 = new Player();
        p1.setId("1");
        p1.setLevel(1);
        p1.setLinkedUser("1");
        p1.setName("test1");

        p1.setResources(createBasicResources());

        return p1;
    }

    public static Player createPlayerWith50OfEachResources() {
        Player p1 = new Player();
        p1.setId("1");
        p1.setLevel(1);
        p1.setLinkedUser("1");
        p1.setName("test1");

        p1.setResources(create50OfEachResources());

        return p1;
    }

    public static Player createPlayerWith100OfEachResources() {
        Player p1 = new Player();
        p1.setId("1");
        p1.setLevel(1);
        p1.setLinkedUser("1");
        p1.setName("test1");

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

    public static Answer<Player> createPlayerAnswerForSomeInput() {
        return (InvocationOnMock invocation) -> {
            return (Player) invocation.getArguments()[0];
        };
    }

    public static Answer<Friend> createFriendAnswerForSomeInput() {
        return (InvocationOnMock invocation) -> {
            return (Friend) invocation.getArguments()[0];
        };
    }

}
