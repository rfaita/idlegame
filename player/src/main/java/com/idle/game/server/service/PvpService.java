package com.idle.game.server.service;

import static com.idle.game.constant.CacheConstants.PVPRATING_FIND_PVP_RATINGS;
import com.idle.game.core.battle.Battle;
import static com.idle.game.core.formation.type.FormationAllocation.PVP_DEFENSE;
import com.idle.game.core.util.DiceUtil;
import com.idle.game.helper.client.battle.BattleClient;
import com.idle.game.helper.client.battle.FormationClient;
import com.idle.game.helper.client.resource.UserResourceClient;
import com.idle.game.helper.client.user.UserClient;
import com.idle.game.model.Formation;
import com.idle.game.model.PvpRating;
import com.idle.game.model.Resource;
import com.idle.game.model.ResourceType;
import com.idle.game.model.User;
import com.idle.game.model.campaign.CampaignPath;
import com.idle.game.server.repository.PvpRatingRepository;
import static com.idle.game.server.type.EloOutcome.LOSE;
import static com.idle.game.server.type.EloOutcome.WIN;
import com.idle.game.server.util.EloRating;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class PvpService {

    @Autowired
    private PvpRatingRepository pvpRatingRepository;

    @Autowired
    private UserClient userClient;

    @Autowired
    private UserResourceClient userResourceClient;

    @Autowired
    private FormationClient formationClient;

    @Autowired
    private BattleClient battleClient;

    @Value("${idle.config.pvpRating.price}")
    private Long pvpRatingPrice;

    public List<PvpRating> findAllByOrderByRatingDescLimit50() {
        return pvpRatingRepository.findAllByOrderByRatingDesc(new PageRequest(0, 50));
    }

    @Cacheable(value = PVPRATING_FIND_PVP_RATINGS,
            key = "'" + PVPRATING_FIND_PVP_RATINGS + "' + #user",
            unless = "#result.isEmpty()")
    public List<PvpRating> findPvpRatings(String userId) {

        PvpRating playerRating = pvpRatingRepository.findByUserId(userId);

        if (playerRating == null) {
            playerRating = new PvpRating();
            playerRating.setRating(EloRating.INITIAL_RATING);
            playerRating.setUserId(userId);

            User user = userClient.findById(userId).getData();

            playerRating.setUserNickName(user.getNickName());

            Formation pvpForm = formationClient.findByUserIdAndFormationAllocation(userId, PVP_DEFENSE.toString()).getData();

            if (pvpForm == null) {
                throw new ValidationException("formation.pvp.not.found");
            }

            playerRating.setFormation(pvpForm.getId());
            playerRating = pvpRatingRepository.save(playerRating);

        }

        List<PvpRating> ret = new ArrayList<>(4);

        int[] vhr = EloRating.veryHardRatingRange(playerRating.getRating());
        List<PvpRating> vhRatings = pvpRatingRepository.findAllByUserIdNotAndRatingBetween(userId, vhr[0] - 1, vhr[1] + 1);

        if (vhRatings != null && !vhRatings.isEmpty()) {
            ret.add(vhRatings.get(DiceUtil.random(vhRatings.size() - 1)));
        }

        int[] hr = EloRating.hardRatingRange(playerRating.getRating());
        List<PvpRating> hrRatings = pvpRatingRepository.findAllByUserIdNotAndRatingBetween(userId, hr[0] - 1, hr[1] + 1);

        if (hrRatings != null && !hrRatings.isEmpty()) {
            ret.add(hrRatings.get(DiceUtil.random(hrRatings.size() - 1)));
        }

        int[] nr = EloRating.normalRatingRange(playerRating.getRating());
        List<PvpRating> nrRatings = pvpRatingRepository.findAllByUserIdNotAndRatingBetween(userId, nr[0] - 1, nr[1] + 1);

        if (nrRatings != null && !nrRatings.isEmpty()) {
            ret.add(nrRatings.get(DiceUtil.random(nrRatings.size() - 1)));
        }

        int[] er = EloRating.easyRatingRange(playerRating.getRating());
        List<PvpRating> erRatings = pvpRatingRepository.findAllByUserIdNotAndRatingBetween(userId, er[0] - 1, er[1] + 1);

        if (erRatings != null && !erRatings.isEmpty()) {
            ret.add(erRatings.get(DiceUtil.random(erRatings.size() - 1)));
        }

        return ret;

    }

    @CacheEvict(value = PVPRATING_FIND_PVP_RATINGS, key = "'" + PVPRATING_FIND_PVP_RATINGS + "' + #user")
    public void removePvpRatings(String user) {

        List<Resource> useResource = new ArrayList<>();
        useResource.add(new Resource(ResourceType.ASHARD, pvpRatingPrice));

        userResourceClient.useResources(useResource);
    }

    private PvpRating findById(String idPvpRating) {
        Optional<PvpRating> ret = pvpRatingRepository.findById(idPvpRating);

        if (ret.isPresent()) {
            return ret.get();
        } else {
            return null;
        }
    }

    @CacheEvict(value = PVPRATING_FIND_PVP_RATINGS, key = "'" + PVPRATING_FIND_PVP_RATINGS + "' + #user")
    public Battle battlePvpRattings(String userId, String idPvpRating) {

        PvpRating ratPlayer = pvpRatingRepository.findByUserId(userId);
        PvpRating ratTarget = findById(idPvpRating);

        if (ratTarget != null) {

            Battle battle = battleClient.doBattle(ratPlayer.getFormation(), ratTarget.getFormation()).getData();

            Boolean playerWin = battle.isFormationAttackWinner();

            Integer newPlayerRating = EloRating.calculate2PlayersRating(ratPlayer.getRating(), ratTarget.getRating(), playerWin ? WIN : LOSE);
            ratTarget.setRating(EloRating.calculate2PlayersRating(ratTarget.getRating(), ratPlayer.getRating(), playerWin ? LOSE : WIN));
            ratPlayer.setRating(newPlayerRating);

            pvpRatingRepository.save(ratPlayer);
            pvpRatingRepository.save(ratTarget);

            return battle;

        } else {
            throw new ValidationException("pvpRating.not.found");

        }

    }

}
