package com.idle.game.tests;

import com.idle.game.helper.client.resource.UserResourceClient;
import com.idle.game.helper.client.shop.LootRollClient;
import com.idle.game.model.shop.Inventory;
import com.idle.game.model.shop.InventoryItem;
import com.idle.game.model.shop.LootRoll;
import com.idle.game.model.shop.LootRollItem;
import com.idle.game.server.dto.Envelope;
import com.idle.game.server.repository.InventoryRepository;
import com.idle.game.server.service.InventoryService;
import static com.idle.game.tests.helper.TestHelper.*;
import java.util.List;
import javax.validation.ValidationException;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import static org.mockito.ArgumentMatchers.any;
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
public class InventoryServiceTests {

    @MockBean
    private LootRollClient lootRollClient;

    @MockBean
    private InventoryRepository inventoryRepository;

    @MockBean
    private UserResourceClient userResourceClient;

    @Autowired
    private InventoryService inventoryService;

    @Rule
    public ExpectedException expcetionExpect = ExpectedException.none();

    @Test
    public void testBuyItemLootRollNotFound() {

        when(lootRollClient.findById("321")).thenReturn(new Envelope((LootRoll) null));

        expcetionExpect.expect(ValidationException.class);
        expcetionExpect.expectMessage("loot.roll.not.found");

        inventoryService.buyItem("123", "321");

    }

    @Test
    public void testBuyItemLootRollMustBeItem() {

        when(lootRollClient.findById("321")).thenReturn(new Envelope(createLootRollToHero("456")));

        expcetionExpect.expect(ValidationException.class);
        expcetionExpect.expectMessage("loot.roll.type.must.be.item");

        inventoryService.buyItem("123", "321");

    }

    @Test
    public void testBuyItemLootSuccess() {

        when(lootRollClient.findById("321")).thenReturn(new Envelope(createLootRollToItem("456")));

        when(userResourceClient.useResources(any(List.class))).thenReturn(new Envelope());
        when(inventoryRepository.save(any(Inventory.class))).thenAnswer(createInventoryAnswerForSomeInput());

        Inventory inv = inventoryService.buyItem("123", "321");

        Assert.assertEquals(new Integer(1), new Integer(inv.getItems().size()));

        InventoryItem item = inv.getItems().get(0);

        Assert.assertEquals("teste", item.getItemClassName());
        Assert.assertEquals("456", item.getItemId());

    }

    @Test
    public void testRollItemItemNotFound() {

        when(inventoryRepository.findById("123")).thenReturn(null);
        when(inventoryRepository.save(any(Inventory.class))).thenAnswer(createInventoryAnswerForSomeInput());

        expcetionExpect.expect(ValidationException.class);
        expcetionExpect.expectMessage("item.not.found");

        inventoryService.rollItem("123", new LootRollItem("001"));

    }

    @Test
    public void testRollItemItemMustBeALootRoll() {

        Inventory inv = createInventory("123");

        inv.addItem(createLootRoll("001"));

        when(inventoryRepository.findByUserId("123")).thenReturn(inv);

        expcetionExpect.expect(ValidationException.class);
        expcetionExpect.expectMessage("to.roll.a.item.must.be.a.loot.roll");

        inventoryService.rollItem("123", new InventoryItem("teste", "001"));

    }

    @Test
    public void testRollItemSuccess() {

        Inventory inv = createInventory("123");

        InventoryItem ii = createLootRollWithLootRollItem("001");

        ii.setAmount(1L);

        inv.addItem(ii);

        when(inventoryRepository.findByUserId("123")).thenReturn(inv);

        when(lootRollClient.findById("001")).thenReturn(new Envelope(createLootRollToItem("456")));
        when(userResourceClient.useResources(any(List.class))).thenReturn(new Envelope());

        when(inventoryRepository.save(any(Inventory.class))).thenAnswer(createInventoryAnswerForSomeInput());

        Inventory ret = inventoryService.rollItem("123", new LootRollItem("001"));

        Assert.assertEquals(new Integer(2), new Integer(ret.getItems().size()));

        InventoryItem item = ret.getItems().get(0);

        Assert.assertEquals(LootRoll.class.getName(), item.getItemClassName());
        Assert.assertEquals("001", item.getItemId());
        Assert.assertEquals(new Long(0L), item.getAmount());

        item = ret.getItems().get(1);

        Assert.assertEquals("teste", item.getItemClassName());
        Assert.assertEquals("456", item.getItemId());
        Assert.assertEquals(new Long(1L), item.getAmount());

    }

}
