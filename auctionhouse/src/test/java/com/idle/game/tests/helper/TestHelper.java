package com.idle.game.tests.helper;

import com.idle.game.model.ResourceType;
import com.idle.game.model.UserResource;
import com.idle.game.model.actionhouse.Auction;
import com.idle.game.model.actionhouse.AuctionBid;
import com.idle.game.model.actionhouse.AuctionBuyout;
import com.idle.game.model.actionhouse.AuctionReward;
import com.idle.game.model.actionhouse.MinAuctionBid;
import com.idle.game.model.shop.Inventory;
import com.idle.game.model.shop.InventoryItem;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

/**
 *
 * @author rafael
 */
public class TestHelper {

    public static Auction createEmptyRewardAuction(String userId) {

        Auction ret = new Auction();

        ret.setUserId(userId);

        ret.setExpireAt(Date.from(LocalDateTime.now().plus(86400, ChronoUnit.SECONDS).atZone(ZoneId.systemDefault()).toInstant()));

        ret.setMinAuctionBid(createMinAuctionBid());
        ret.setAuctionReward(new AuctionReward());

        return ret;

    }

    public static Auction createCanceledAuction(String userId) {

        Auction ret = createEmptyRewardAuction(userId);

        ret.getAuctionReward().getItems().add(createInventoryItem("123"));

        ret.setCancelAt(new Date());

        return ret;

    }

    public static Auction createExpiredAuction(String userId) {

        Auction ret = createEmptyRewardAuction(userId);

        ret.getAuctionReward().getItems().add(createInventoryItem("123"));

        ret.setExpireAt(Date.from(LocalDateTime.now().minus(86400, ChronoUnit.SECONDS).atZone(ZoneId.systemDefault()).toInstant()));

        return ret;

    }

    public static Auction createAuctionBidAuction(String userId) {
        Auction ret = createBasicAuction(userId);

        ret.setCurrentAuctionBid(createBasicAuctionBid("123", ""));

        return ret;
    }

    public static Auction createBasicAuction(String userId) {

        Auction ret = createEmptyRewardAuction(userId);

        ret.getAuctionReward().getItems().add(createInventoryItem("123"));

        return ret;

    }

    public static Auction create1100GoldBuyoutAuction(String userId) {

        Auction ret = createBasicAuction(userId);

        ret.setAuctionBuyout(create1100GoldBuyout());

        return ret;

    }

    public static Auction create900GoldBuyoutAuction(String userId) {

        Auction ret = createBasicAuction(userId);

        ret.setAuctionBuyout(create900GoldBuyout());

        return ret;

    }

    public static Auction create1000GoldBuyoutAuction(String userId) {

        Auction ret = createBasicAuction(userId);

        ret.setAuctionBuyout(create1000GoldBuyout());

        return ret;

    }

    public static AuctionBuyout create900GoldBuyout() {
        AuctionBuyout ret = new AuctionBuyout();

        ret.setType(ResourceType.GOLD);

        ret.setValue(900L);

        return ret;
    }

    public static AuctionBuyout create1000GoldBuyout() {
        AuctionBuyout ret = new AuctionBuyout();

        ret.setType(ResourceType.GOLD);

        ret.setValue(1000L);

        return ret;
    }

    public static AuctionBuyout create1100GoldBuyout() {
        AuctionBuyout ret = new AuctionBuyout();

        ret.setType(ResourceType.GOLD);

        ret.setValue(1100L);

        return ret;
    }

    public static AuctionBid create100AshardAuctionBid(String userId, String auctionId) {
        AuctionBid ret = createBasicAuctionBid(userId, auctionId);

        ret.setType(ResourceType.ASHARD);
        ret.setValue(100l);

        return ret;
    }

    public static AuctionBid create1100GoldAuctionBid(String userId, String auctionId) {
        AuctionBid ret = new AuctionBid();

        ret.setAuctionId(auctionId);
        ret.setUserId(userId);
        ret.setType(ResourceType.GOLD);
        ret.setValue(1100l);

        return ret;
    }

    public static AuctionBid createBasicAuctionBid(String userId, String auctionId) {
        AuctionBid ret = new AuctionBid();

        ret.setAuctionId(auctionId);
        ret.setUserId(userId);
        ret.setType(ResourceType.GOLD);
        ret.setValue(900l);

        return ret;
    }

    public static MinAuctionBid createMinAuctionBid() {
        MinAuctionBid ret = new MinAuctionBid();

        ret.setType(ResourceType.GOLD);
        ret.setValue(1000l);

        return ret;
    }

    public static InventoryItem createInventoryItem(String itemId) {
        InventoryItem ret = new InventoryItem("teste", itemId);

        ret.setAmountInUse(2L);

        return ret;
    }

    public static UserResource createUserResource(String userId) {

        UserResource ret = new UserResource(userId, new ArrayList<>());
        return ret;
    }

    public static Inventory createEmptyInventory(String userId) {

        Inventory ret = new Inventory();

        ret.setUserId(userId);

        return ret;

    }

    public static Inventory create1ItemInventory(String userId) {

        Inventory ret = new Inventory();

        ret.setUserId(userId);

        ret.addItem(new InventoryItem("teste", "123", 1l));

        return ret;

    }

    public static Inventory create5ItemInventory(String userId) {

        Inventory ret = new Inventory();

        ret.setUserId(userId);

        ret.addItem(new InventoryItem("teste", "123", 5l));

        return ret;

    }

    public static Answer<Inventory> createInventoryAnswerForSomeInput() {
        return (InvocationOnMock invocation) -> {
            return (Inventory) invocation.getArguments()[0];
        };
    }

    public static Answer<UserResource> createUserResourceAnswerForSomeInput() {
        return (InvocationOnMock invocation) -> {
            return (UserResource) invocation.getArguments()[0];
        };
    }

    public static Answer<Auction> createAuctionAnswerForSomeInput() {
        return (InvocationOnMock invocation) -> {
            return (Auction) invocation.getArguments()[0];
        };
    }

    public static Answer<AuctionBid> createAuctionBidAnswerForSomeInput() {
        return (InvocationOnMock invocation) -> {
            return (AuctionBid) invocation.getArguments()[0];
        };
    }

}
