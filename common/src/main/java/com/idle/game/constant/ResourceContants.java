package com.idle.game.constant;

import com.idle.game.model.mongo.Resource;
import com.idle.game.model.mongo.ResourceType;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rafael
 */
public class ResourceContants {

    public static final Long INITIAL_GOLD_PS = 5L;
    public static final Long INITIAL_RUNE_PS = 5L;

    public static List<Resource> defaultResources() {

        List<Resource> ret = new ArrayList<>();

        ret.add(new Resource(ResourceType.RUNE_PS, INITIAL_GOLD_PS));
        ret.add(new Resource(ResourceType.GOLD_PS, INITIAL_RUNE_PS));

        return ret;

    }

}
