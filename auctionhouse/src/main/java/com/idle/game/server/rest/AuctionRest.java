package com.idle.game.server.rest;

import static com.idle.game.constant.URIConstants.AUCTION;
import com.idle.game.helper.TokenHelper;
import com.idle.game.model.actionhouse.Auction;
import com.idle.game.model.actionhouse.AuctionBid;
import com.idle.game.server.dto.Envelope;
import com.idle.game.server.service.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author rafael
 */
@RestController
@RequestMapping(AUCTION)
public class AuctionRest {

    @Autowired
    private AuctionService auctionService;

    @Autowired
    private TokenHelper tokenHelper;

    @RequestMapping(path = "", method = RequestMethod.POST)
    public @ResponseBody
    Envelope<Auction> create(@RequestBody Auction auction) {

        auction.setUserId(tokenHelper.getUserId());

        Envelope<Auction> ret = new Envelope<>();
        ret.setData(auctionService.create(auction));

        return ret;

    }

    @RequestMapping(path = "", method = RequestMethod.PUT)
    public @ResponseBody
    Envelope<Auction> makeBid(@RequestBody AuctionBid auctionBid) {

        auctionBid.setUserId(tokenHelper.getUserId());

        Envelope<Auction> ret = new Envelope<>();
        ret.setData(auctionService.makeBid(auctionBid));

        return ret;

    }

    @RequestMapping(path = "/{auctionId}", method = RequestMethod.PUT)
    public @ResponseBody
    Envelope<Auction> makeBid(@PathVariable("auctionId") String auctionId) {

        Envelope<Auction> ret = new Envelope<>();
        ret.setData(auctionService.buyout(auctionId, tokenHelper.getUserId()));

        return ret;

    }

    @RequestMapping(path = "/{auctionId}", method = RequestMethod.DELETE)
    public @ResponseBody
    Envelope<Auction> cancelAuction(@PathVariable("auctionId") String auctionId) {

        Envelope<Auction> ret = new Envelope<>();
        ret.setData(auctionService.cancel(auctionId));

        return ret;

    }

}
