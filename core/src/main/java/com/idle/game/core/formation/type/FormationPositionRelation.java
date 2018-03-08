package com.idle.game.core.formation.type;

import static com.idle.game.core.formation.type.FormationPosition.B_0;
import static com.idle.game.core.formation.type.FormationPosition.B_1;
import static com.idle.game.core.formation.type.FormationPosition.B_2;
import static com.idle.game.core.formation.type.FormationPosition.F_0;
import static com.idle.game.core.formation.type.FormationPosition.F_1;
import static com.idle.game.core.formation.type.FormationPosition.F_2;
import static com.idle.game.core.formation.type.FormationPosition.M_0;
import static com.idle.game.core.formation.type.FormationPosition.M_1;
import static com.idle.game.core.formation.type.FormationPosition.M_2;
import com.idle.game.core.hero.type.HeroTypeSize;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author rafael
 */
public class FormationPositionRelation {

    public static final Map<FormationPosition, Map<HeroTypeSize, List<FormationPosition>>> DATA = new HashMap<>();

    static {
        DATA.put(F_0, new HashMap<>());
        DATA.get(F_0).put(HeroTypeSize.MEDIUM, Arrays.asList(M_0, M_1, F_1));
        DATA.put(F_1, new HashMap<>());
        DATA.get(F_1).put(HeroTypeSize.MEDIUM, Arrays.asList(M_1, M_2, F_2));
        DATA.put(M_0, new HashMap<>());
        DATA.get(M_0).put(HeroTypeSize.MEDIUM, Arrays.asList(B_0, B_1, M_1));
        DATA.put(M_1, new HashMap<>());
        DATA.get(M_1).put(HeroTypeSize.MEDIUM, Arrays.asList(B_1, B_2, M_2));
        DATA.get(M_1).put(HeroTypeSize.LARGE, Arrays.asList(B_0, B_1, B_2, M_0, M_2, F_0, F_1, F_2));
    }

}
