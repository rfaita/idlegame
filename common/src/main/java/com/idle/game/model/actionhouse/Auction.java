package com.idle.game.model.actionhouse;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.io.Serializable;
import java.util.Date;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author rafael
 */
@Document(collection = "auction")
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Auction implements Serializable {

    @Id
    private String id;

    @Indexed
    private String userId;

    @Future(message = "expire.at.must.be.in.future")
    private Date expireAt;
    private Date buyoutAt;
    private Date doneAt;
    private Date cancelAt;

    @NotNull(message = "min.auction.bid.must.be.set")
    private MinAuctionBid minAuctionBid;
    private AuctionBuyout auctionBuyout;
    @NotNull(message = "auction.reward.must.be.set")
    private AuctionReward auctionReward;

    private AuctionBid currentAuctionBid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCancelAt() {
        return cancelAt;
    }

    public void setCancelAt(Date cancelAt) {
        this.cancelAt = cancelAt;
    }

    public Date getBuyoutAt() {
        return buyoutAt;
    }

    public void setBuyoutAt(Date buyoutAt) {
        this.buyoutAt = buyoutAt;
    }

    public Date getDoneAt() {
        return doneAt;
    }

    public void setDoneAt(Date doneAt) {
        this.doneAt = doneAt;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getExpireAt() {
        return expireAt;
    }

    public void setExpireAt(Date expireAt) {
        this.expireAt = expireAt;
    }

    public AuctionBid getCurrentAuctionBid() {
        return currentAuctionBid;
    }

    public void setCurrentAuctionBid(AuctionBid currentAuctionBid) {
        this.currentAuctionBid = currentAuctionBid;
    }

    public MinAuctionBid getMinAuctionBid() {
        return minAuctionBid;
    }

    public void setMinAuctionBid(MinAuctionBid minAuctionBid) {
        this.minAuctionBid = minAuctionBid;
    }

    public AuctionBuyout getAuctionBuyout() {
        return auctionBuyout;
    }

    public void setAuctionBuyout(AuctionBuyout auctionBuyout) {
        this.auctionBuyout = auctionBuyout;
    }

    public AuctionReward getAuctionReward() {
        return auctionReward;
    }

    public void setAuctionReward(AuctionReward auctionReward) {
        this.auctionReward = auctionReward;
    }

}
