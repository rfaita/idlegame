package com.idle.game.server.rest;

import static com.idle.game.constant.URIConstants.AUCTION;
import com.idle.game.helper.TokenHelper;
import com.idle.game.server.service.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
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

}
