package com.idle.game.tests;

import com.idle.game.model.battle.BattleSite;
import com.idle.game.server.repository.BattleFieldRepository;
import com.idle.game.server.service.BattleFieldService;
import static com.idle.game.tests.helper.TestHelper.createBattleField;
import java.util.List;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
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
public class BattleFieldServiceTest {

    @MockBean
    private BattleFieldRepository battleFieldRepository;

    @Autowired
    private BattleFieldService battleFieldService;

    @Rule
    public ExpectedException expcetionExpect = ExpectedException.none();

    @Test
    public void test() {

        when(battleFieldRepository.findOne("1")).thenReturn(createBattleField("1"));

        List<BattleSite> ret = battleFieldService.showPath("1", "13");

        Assert.assertEquals("13", ret.get(0).getFormationId());
        Assert.assertEquals("04", ret.get(1).getFormationId());
        Assert.assertEquals("01", ret.get(2).getFormationId());

    }

}
