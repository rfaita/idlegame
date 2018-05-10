package com.idle.game.server.service;

import com.idle.game.helper.client.hero.HeroClient;
import com.idle.game.helper.client.mail.MailClient;
import com.idle.game.helper.client.resource.UserResourceClient;
import com.idle.game.helper.client.shop.InventoryClient;
import com.idle.game.model.Mail;
import com.idle.game.model.Resource;
import com.idle.game.model.ResourceType;
import com.idle.game.model.Reward;
import com.idle.game.model.RewardValue;
import com.idle.game.model.actionhouse.Auction;
import com.idle.game.model.actionhouse.AuctionBid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.idle.game.model.shop.Inventory;
import com.idle.game.model.shop.InventoryItem;
import com.idle.game.server.producer.AuctionExpireProducer;
import com.idle.game.server.repository.AuctionBidRepository;
import javax.validation.ValidationException;
import com.idle.game.server.repository.AuctionRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

/**
 *
 * @author rafael
 */
@Service
public class AuctionService {

    @Autowired
    private Validator validator;

    @Autowired
    private AuctionRepository auctionRepository;

    @Autowired
    private AuctionBidRepository auctionBidRepository;

    @Autowired
    private UserResourceClient userResourceClient;

    @Autowired
    private InventoryClient inventoryClient;

    @Autowired
    private MailClient mailClient;

    @Autowired
    private HeroClient heroClient;

    @Autowired
    private AuctionExpireProducer auctionExpireProducer;

    public Auction cancel(String auctionId) {
        Auction auction = findById(auctionId);

        if (auction != null) {

            if (auction.getCancelAt() != null) {
                throw new ValidationException("auction.canceled");
            }

            if (auction.getExpireAt().before(new Date())) {
                throw new ValidationException("auction.already.expire");
            }

            if (auction.getCurrentAuctionBid() != null) {
                throw new ValidationException("auction.already.have.a.bid");
            }

            auction.setCancelAt(new Date());

            inventoryClient.addItems(auction.getUserId(), auction.getAuctionReward().getItems());

            Mail m = new Mail();
            m.setToUserId(auction.getUserId());
            m.setText("your.auction.has.been.canceled");

            mailClient.sendPrivateInternalMail(m);

            return auctionRepository.save(auction);

        } else {
            throw new ValidationException("auction.not.found");
        }
    }

    public Auction create(Auction auction) {

        Set<ConstraintViolation<Auction>> violations = validator.validate(auction);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(new HashSet<>(violations));
        }

        Inventory inventory = inventoryClient.findByUserId().getData();

        if (inventory == null) {
            throw new ValidationException("inventory.not.found");
        }

        if (auction.getAuctionReward().getItems() != null && !auction.getAuctionReward().getItems().isEmpty()) {
            for (InventoryItem inventoryItem : auction.getAuctionReward().getItems()) {
                if (!inventory.containsItem(inventoryItem)) {
                    throw new ValidationException("you.must.be.owner.of.item");
                }
                if (!inventory.canItemAmountPutInUse(inventoryItem)) {
                    throw new ValidationException("you.can.not.use.more.items.you.have");
                }
            }
        } else {
            throw new ValidationException("you.need.one.item.to.create.a.auction");
        }

        if (auction.getAuctionBuyout() != null) {

            if (!auction.getAuctionBuyout().getType().equals(auction.getMinAuctionBid().getType())) {
                throw new ValidationException("min.resource.can.not.be.different.than.buyout.resource");
            }
            if (auction.getAuctionBuyout().getValue() < auction.getMinAuctionBid().getValue()) {
                throw new ValidationException("min.resource.can.not.be.greater.than.buyout.resource");
            }

        }

        inventoryClient.removeItems(auction.getAuctionReward().getItems());

        List<Resource> auctionPrice = new ArrayList<>();
        auctionPrice.add(new Resource(ResourceType.ASHARD, 100L));
        userResourceClient.useResources(auctionPrice);

        auctionExpireProducer.sendMessage(auction);

        return auctionRepository.save(auction);

    }

    public Auction findById(String id) {
        Optional<Auction> ret = auctionRepository.findById(id);

        if (ret.isPresent()) {
            return ret.get();
        } else {
            return null;
        }
    }

    public Auction makeBid(AuctionBid auctionBid) {
        Auction auction = findById(auctionBid.getAuctionId());

        if (auction != null) {

            if (auction.getCancelAt() != null) {
                throw new ValidationException("auction.canceled");
            }

            if (auction.getExpireAt().before(new Date())) {
                throw new ValidationException("auction.already.expire");
            }

            List<Resource> resources = new ArrayList<>();
            resources.add(auctionBid);

            if (auction.getCurrentAuctionBid() == null) {

                if (!auctionBid.getType().equals(auction.getMinAuctionBid().getType())) {
                    throw new ValidationException("auction.bid.resource.can.not.be.different.than.min.resource");
                }

                if (auctionBid.getValue() <= auction.getMinAuctionBid().getValue()) {
                    throw new ValidationException("auction.bid.resource.must.be.greater.or.equal.than.min.resource");
                }

                userResourceClient.useResources(resources);

            } else {

                AuctionBid currAuctionBid = auction.getCurrentAuctionBid();

                if (!auctionBid.getType().equals(currAuctionBid.getType())) {
                    throw new ValidationException("auction.bid.resource.can.not.be.different.than.current.bid.resource");
                }

                if (auctionBid.getValue() <= currAuctionBid.getValue()) {
                    throw new ValidationException("auction.bid.resource.must.be.greater.or.equal.than.current.bid.resource");
                }

                userResourceClient.useResources(resources);

                Mail m = new Mail();
                m.setToUserId(currAuctionBid.getUserId());
                Reward reward = new Reward();
                reward.addReward(new RewardValue(currAuctionBid.getType(), currAuctionBid.getValue()));
                m.setReward(reward);
                m.setText("your.bid.has.been.exceeded");

                mailClient.sendPrivateInternalMail(m);

            }

            auctionBid.setDate(new Date());

            auctionBidRepository.save(auctionBid);

            auction.setCurrentAuctionBid(auctionBid);

            return auctionRepository.save(auction);
        } else {
            throw new ValidationException("auction.not.found");
        }

    }

    public Auction buyout(String auctionId, String userId) {
        Auction auction = findById(auctionId);

        if (auction != null) {

            if (auction.getCancelAt() != null) {
                throw new ValidationException("auction.canceled");
            }

            if (auction.getExpireAt().before(new Date())) {
                throw new ValidationException("auction.already.expire");
            }

            if (auction.getAuctionBuyout() == null) {
                throw new ValidationException("auction.do.not.have.buyout");
            }

            List<Resource> resources = new ArrayList<>();
            resources.add(auction.getAuctionBuyout());

            userResourceClient.useResources(resources);

            if (auction.getCurrentAuctionBid() != null) {

                AuctionBid currAuctionBid = auction.getCurrentAuctionBid();

                Mail m = new Mail();
                m.setToUserId(currAuctionBid.getUserId());
                Reward reward = new Reward();
                reward.addReward(new RewardValue(currAuctionBid.getType(), currAuctionBid.getValue()));
                m.setReward(reward);
                m.setText("your.bid.has.been.exceeded");

                mailClient.sendPrivateInternalMail(m);

            }

            AuctionBid auctionBid = new AuctionBid();
            auctionBid.setAuctionId(auctionId);
            auctionBid.setDate(new Date());
            auctionBid.setType(auction.getAuctionBuyout().getType());
            auctionBid.setValue(auction.getAuctionBuyout().getValue());
            auctionBid.setUserId(userId);

            auction.setCurrentAuctionBid(auctionBid);

            auctionBidRepository.save(auctionBid);

            auction.setBuyoutAt(new Date());
            auction.setDoneAt(new Date());

            auctionRepository.save(auction);

            return expire(auctionId);
        } else {
            throw new ValidationException("auction.not.found");
        }

    }

    public Auction expire(String auctionId) {
        Auction auction = findById(auctionId);

        if (auction != null) {

            if (auction.getCancelAt() == null) {
                if (auction.getCurrentAuctionBid() != null) {

                    AuctionBid currAuctionBid = auction.getCurrentAuctionBid();

                    inventoryClient.addItems(currAuctionBid.getUserId(), auction.getAuctionReward().getItems());

                    Mail m = new Mail();
                    m.setToUserId(currAuctionBid.getUserId());
                    m.setText("you.win.auction.reward.already.add.to.your.inventory");

                    mailClient.sendPrivateInternalMail(m);

                    m = new Mail();
                    m.setToUserId(auction.getUserId());
                    m.setText("your.resources.from.auction.sell");

                    Reward r = new Reward();
                    r.addReward(new RewardValue(currAuctionBid.getType(), currAuctionBid.getValue()));
                    m.setReward(r);

                    mailClient.sendPrivateInternalMail(m);

                } else {
                    inventoryClient.addItems(auction.getUserId(), auction.getAuctionReward().getItems());

                    Mail m = new Mail();
                    m.setToUserId(auction.getUserId());
                    m.setText("your.auction.end.without.a.bid.reward.already.add.to.your.inventory");

                    mailClient.sendPrivateInternalMail(m);
                }

                auction.setDoneAt(new Date());

            }

            return auctionRepository.save(auction);
        } else {
            throw new ValidationException("auction.not.found");
        }
    }

}
