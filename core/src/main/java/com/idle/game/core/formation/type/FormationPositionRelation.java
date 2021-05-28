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
import com.idle.game.core.hero.type.HeroSize;

import java.util.*;

public class FormationPositionRelation {

    public static final Map<FormationPosition, Map<HeroSize, List<FormationPosition>>> DATA = new HashMap<>();

    static {
        DATA.put(F_0, Collections.singletonMap(HeroSize.MEDIUM, Arrays.asList(M_0, M_1, F_1)));
        DATA.put(F_1, Collections.singletonMap(HeroSize.MEDIUM, Arrays.asList(M_1, M_2, F_2)));
        DATA.put(M_0, Collections.singletonMap(HeroSize.MEDIUM, Arrays.asList(B_0, B_1, M_1)));
        DATA.put(M_1, new HashMap<>());
        DATA.get(M_1).put(HeroSize.MEDIUM, Arrays.asList(B_1, B_2, M_2));
        DATA.get(M_1).put(HeroSize.LARGE, Arrays.asList(B_0, B_1, B_2, M_0, M_2, F_0, F_1, F_2));
    }

}
