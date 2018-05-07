package com.idle.game.model.actionhouse;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.idle.game.model.Resource;
import java.util.Date;
import javax.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author rafael
 */
@Document(collection = "auctionbid")
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class AuctionBid extends Resource {

    @Id
    private String id;

    @Indexed
    private String userId;
    @Indexed
    @NotNull(message = "auction.id.must.be.set")
    private String auctionId;

    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAuctionId() {
        return auctionId;
    }

    public void setAuctionId(String auctionId) {
        this.auctionId = auctionId;
    }

}
