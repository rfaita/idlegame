package com.idle.game.tests;

import com.idle.game.helper.client.mail.MailClient;
import com.idle.game.helper.client.resource.UserResourceClient;
import com.idle.game.helper.client.shop.InventoryClient;
import com.idle.game.model.Mail;
import com.idle.game.model.actionhouse.Auction;
import com.idle.game.model.actionhouse.AuctionBid;
import com.idle.game.model.shop.Inventory;
import com.idle.game.model.shop.InventoryItem;
import com.idle.game.server.dto.Envelope;
import com.idle.game.server.producer.AuctionExpireProducer;
import com.idle.game.server.repository.AuctionBidRepository;
import com.idle.game.server.repository.AuctionRepository;
import com.idle.game.server.service.AuctionService;
import static com.idle.game.tests.helper.TestHelper.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.validation.ValidationException;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
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
public class AuctionServiceTest {

    @Rule
    public ExpectedException expcetionExpect = ExpectedException.none();

    @MockBean
    private UserResourceClient userResourceClient;

    @MockBean
    private MailClient mailClient;

    @MockBean
    private InventoryClient inventoryClient;

    @MockBean
    private AuctionRepository auctionRepository;

    @MockBean
    private AuctionBidRepository auctionBidRepository;

    @MockBean
    private AuctionExpireProducer auctionExpireProducer;

    @Autowired
    private AuctionService auctionService;

    @Test
    public void testErrorInventoryNotFound() {

        when(inventoryClient.findByUserId()).thenReturn(new Envelope((Inventory) null));

        expcetionExpect.expect(ValidationException.class);
        expcetionExpect.expectMessage("inventory.not.found");

        auctionService.create(createBasicAuction("1000"));

    }

    @Test
    public void testErrorYouNeedOneItemToCreateAAuction() {

        when(inventoryClient.findByUserId()).thenReturn(new Envelope(createEmptyInventory("1000")));

        expcetionExpect.expect(ValidationException.class);
        expcetionExpect.expectMessage("you.need.one.item.to.create.a.auction");

        auctionService.create(createEmptyRewardAuction("1000"));

    }

    @Test
    public void testErrorYouMustBeOwnerOfItem() {

        when(inventoryClient.findByUserId()).thenReturn(new Envelope(createEmptyInventory("1000")));

        expcetionExpect.expect(ValidationException.class);
        expcetionExpect.expectMessage("you.must.be.owner.of.item");

        auctionService.create(createBasicAuction("1000"));

    }

    @Test
    public void testErrorYouCanNotUseMoreItemsYouHave() {

        when(inventoryClient.findByUserId()).thenReturn(new Envelope(create1ItemInventory("1000")));

        expcetionExpect.expect(ValidationException.class);
        expcetionExpect.expectMessage("you.can.not.use.more.items.you.have");

        auctionService.create(createBasicAuction("1000"));

    }

    @Test
    public void testErrorMinResourceCanNotBeGreaterThanBuyoutResource() {

        when(inventoryClient.findByUserId()).thenReturn(new Envelope(create5ItemInventory("1000")));

        expcetionExpect.expect(ValidationException.class);
        expcetionExpect.expectMessage("min.resource.can.not.be.greater.than.buyout.resource");

        auctionService.create(create900GoldBuyoutAuction("1000"));

    }

    @Test
    public void testErrorSuccess1() {

        when(inventoryClient.findByUserId()).thenReturn(new Envelope(create5ItemInventory("1000")));

        when(inventoryClient.removeItems(any(List.class))).thenReturn(new Envelope(createInventoryItem("1000")));

        when(userResourceClient.useResources(any(List.class))).thenReturn(new Envelope(createUserResource("1000")));

        doNothing().when(this.auctionExpireProducer).sendMessage(any(Auction.class));

        when(auctionRepository.save(any(Auction.class))).thenAnswer(createAuctionAnswerForSomeInput());

        auctionService.create(create1000GoldBuyoutAuction("1000"));

    }

    @Test
    public void testErrorSuccess2() {

        when(inventoryClient.findByUserId()).thenReturn(new Envelope(create5ItemInventory("1000")));

        when(inventoryClient.removeItems(any(List.class))).thenReturn(new Envelope(createInventoryItem("1000")));

        when(userResourceClient.useResources(any(List.class))).thenReturn(new Envelope(createUserResource("1000")));

        doNothing().when(this.auctionExpireProducer).sendMessage(any(Auction.class));

        when(auctionRepository.save(any(Auction.class))).thenAnswer(createAuctionAnswerForSomeInput());

        auctionService.create(create1100GoldBuyoutAuction("1000"));

    }

    @Test
    public void testMakeBidErrorAuctionNotFound() {
        when(auctionRepository.findById("1000")).thenReturn(Optional.empty());

        expcetionExpect.expect(ValidationException.class);
        expcetionExpect.expectMessage("auction.not.found");

        auctionService.makeBid(createBasicAuctionBid("123", "1000"));
    }

    @Test
    public void testMakeBidErrorAuctionCanceled() {
        when(auctionRepository.findById("1000")).thenReturn(Optional.of(createCanceledAuction("321")));

        expcetionExpect.expect(ValidationException.class);
        expcetionExpect.expectMessage("auction.canceled");

        auctionService.makeBid(createBasicAuctionBid("123", "1000"));
    }

    @Test
    public void testMakeBidErrorAuctionBidResourceCanNotBeDifferenteThanMinResource() {
        when(auctionRepository.findById("1000")).thenReturn(Optional.of(createBasicAuction("321")));

        expcetionExpect.expect(ValidationException.class);
        expcetionExpect.expectMessage("auction.bid.resource.can.not.be.different.than.min.resource");

        auctionService.makeBid(create100AshardAuctionBid("123", "1000"));
    }

    @Test
    public void testMakeBidErrorAuctionBidResourceMustBeGreaterOrEqualThanMinResource() {
        when(auctionRepository.findById("1000")).thenReturn(Optional.of(createBasicAuction("321")));

        expcetionExpect.expect(ValidationException.class);
        expcetionExpect.expectMessage("auction.bid.resource.must.be.greater.or.equal.than.min.resource");

        auctionService.makeBid(createBasicAuctionBid("123", "1000"));
    }

    @Test
    public void testMakeBidSuccess() {
        when(auctionRepository.findById("1000")).thenReturn(Optional.of(createBasicAuction("321")));

        when(userResourceClient.useResources(any(List.class))).thenReturn(new Envelope(createUserResource("123")));

        when(auctionBidRepository.save(any(AuctionBid.class))).thenAnswer(createAuctionBidAnswerForSomeInput());

        when(auctionRepository.save(any(Auction.class))).thenAnswer(createAuctionAnswerForSomeInput());

        Auction ret = auctionService.makeBid(create1100GoldAuctionBid("123", "1000"));

        Assert.assertEquals(new Long(1100l), ret.getCurrentAuctionBid().getValue());
        Assert.assertEquals("123", ret.getCurrentAuctionBid().getUserId());
    }

    @Test
    public void testMakeBidErrorAuctionBidResourceCanNotBeDifferenteThanCurrentBidResource() {
        when(auctionRepository.findById("1000")).thenReturn(Optional.of(createAuctionBidAuction("321")));

        expcetionExpect.expect(ValidationException.class);
        expcetionExpect.expectMessage("auction.bid.resource.can.not.be.different.than.current.bid.resource");

        auctionService.makeBid(create100AshardAuctionBid("123", "1000"));
    }

    @Test
    public void testMakeBidErrorAuctionBidResourceMustBeGreaterOrEqualThanCurrentBidResource() {
        when(auctionRepository.findById("1000")).thenReturn(Optional.of(createAuctionBidAuction("321")));

        expcetionExpect.expect(ValidationException.class);
        expcetionExpect.expectMessage("auction.bid.resource.must.be.greater.or.equal.than.current.bid.resource");

        auctionService.makeBid(createBasicAuctionBid("123", "1000"));
    }

    @Test
    public void testMakeBidWithBidSuccess() {
        when(auctionRepository.findById("1000")).thenReturn(Optional.of(createAuctionBidAuction("321")));

        when(userResourceClient.useResources(any(List.class))).thenReturn(new Envelope(createUserResource("123")));

        doNothing().when(this.mailClient).sendPrivateInternalMail(any(Mail.class));

        when(auctionBidRepository.save(any(AuctionBid.class))).thenAnswer(createAuctionBidAnswerForSomeInput());

        when(auctionRepository.save(any(Auction.class))).thenAnswer(createAuctionAnswerForSomeInput());

        Auction ret = auctionService.makeBid(create1100GoldAuctionBid("123", "1000"));

        Assert.assertEquals(new Long(1100l), ret.getCurrentAuctionBid().getValue());
        Assert.assertEquals("123", ret.getCurrentAuctionBid().getUserId());
    }

    @Test
    public void testCancelAuctionNotFound() {

        when(auctionRepository.findById("1000")).thenReturn(Optional.empty());

        expcetionExpect.expect(ValidationException.class);
        expcetionExpect.expectMessage("auction.not.found");

        auctionService.cancel("1000");
    }

    @Test
    public void testCancelAuctionCanceled() {

        when(auctionRepository.findById("1000")).thenReturn(Optional.of(createCanceledAuction("321")));

        expcetionExpect.expect(ValidationException.class);
        expcetionExpect.expectMessage("auction.canceled");

        auctionService.cancel("1000");
    }

    @Test
    public void testCancelAuctionAlreadyExpire() {

        when(auctionRepository.findById("1000")).thenReturn(Optional.of(createExpiredAuction("321")));

        expcetionExpect.expect(ValidationException.class);
        expcetionExpect.expectMessage("auction.already.expire");

        auctionService.cancel("1000");
    }

    @Test
    public void testCancelAuctionAlreadyHaveABid() {

        when(auctionRepository.findById("1000")).thenReturn(Optional.of(createAuctionBidAuction("321")));

        expcetionExpect.expect(ValidationException.class);
        expcetionExpect.expectMessage("auction.already.have.a.bid");

        auctionService.cancel("1000");
    }

    @Test
    public void testCancelSuccess() {

        when(auctionRepository.findById("1000")).thenReturn(Optional.of(createBasicAuction("321")));

        List<InventoryItem> items = new ArrayList<>();

        items.add(createInventoryItem("123"));

        when(inventoryClient.addItems("321", items)).thenReturn(new Envelope((Inventory) null));

        doNothing().when(this.mailClient).sendPrivateInternalMail(any(Mail.class));

        when(auctionRepository.save(any(Auction.class))).thenAnswer(createAuctionAnswerForSomeInput());

        Auction ret = auctionService.cancel("1000");

        Assert.assertNotNull(ret.getCancelAt());
        Assert.assertNull(ret.getBuyoutAt());
        Assert.assertNull(ret.getDoneAt());
    }

    @Test
    public void textExpireAuctionNotFound() {

        when(auctionRepository.findById("1000")).thenReturn(Optional.empty());

        expcetionExpect.expect(ValidationException.class);
        expcetionExpect.expectMessage("auction.not.found");

        auctionService.expire("1000");

    }

    @Test
    public void textExpireAuctionCanceled() {

        when(auctionRepository.findById("1000")).thenReturn(Optional.of(createCanceledAuction("321")));

        when(auctionRepository.save(any(Auction.class))).thenAnswer(createAuctionAnswerForSomeInput());

        Auction ret = auctionService.expire("1000");

        Assert.assertNull(ret.getDoneAt());

    }

}
