package com.idle.game.constant;

import com.idle.game.model.Resource;
import com.idle.game.model.ResourceType;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rafael
 */
public class ResourceConstants {

    public static final Long INITIAL_GOLD_PS = 1L;
    public static final Long INITIAL_RUNE_PS = 1L;

    public static List<Resource> defaultResources() {

        List<Resource> ret = new ArrayList<>();

        ret.add(new Resource(ResourceType.ASHARD, 0L));
        ret.add(new Resource(ResourceType.RUNE, 0L));
        ret.add(new Resource(ResourceType.GOLD, 0L));
        ret.add(new Resource(ResourceType.RUNE_PS, INITIAL_GOLD_PS));
        ret.add(new Resource(ResourceType.GOLD_PS, INITIAL_RUNE_PS));

        return ret;

    }

}
