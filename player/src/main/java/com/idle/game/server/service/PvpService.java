package com.idle.game.server.service;

import static com.idle.game.constant.CacheConstants.PVPRATING_FIND_PVP_RATINGS;
import com.idle.game.core.battle.Battle;
import static com.idle.game.core.formation.type.FormationAllocation.PVP_DEFENSE;
import com.idle.game.core.util.DiceUtil;
import com.idle.game.helper.BattleHelper;
import com.idle.game.helper.FormationHelper;
import com.idle.game.helper.PlayerHelper;
import com.idle.game.model.mongo.Formation;
import com.idle.game.model.mongo.Player;
import com.idle.game.model.mongo.PvpRating;
import com.idle.game.model.mongo.Resource;
import com.idle.game.model.mongo.ResourceType;
import com.idle.game.server.repository.PvpRatingRepository;
import static com.idle.game.server.type.EloOutcome.LOSE;
import static com.idle.game.server.type.EloOutcome.WIN;
import com.idle.game.server.util.EloRating;
import java.util.ArrayList;
import java.util.List;
import javax.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class PvpService {

    @Autowired
    private PvpRatingRepository pvpRatingRepository;

    @Autowired
    private PlayerHelper playerHelper;

    @Autowired
    private FormationHelper formationHelper;

    @Autowired
    private BattleHelper battleHelper;

    @Value("${idle.config.player.pvpRating.price}")
    private Long pvpRatingPrice;

    @Cacheable(value = PVPRATING_FIND_PVP_RATINGS, key = "'" + PVPRATING_FIND_PVP_RATINGS + "' + #user")
    public List<PvpRating> findPvpRatings(String user) {

        Player player = playerHelper.getPlayerByLinkedUser(user);

        if (player != null) {

            PvpRating playerRating = pvpRatingRepository.findByPlayer(player.getId());

            if (playerRating == null) {
                playerRating = new PvpRating();
                playerRating.setRating(EloRating.INITIAL_RATING);
                playerRating.setPlayer(player.getId());
                playerRating.setPlayerLevel(player.getLevel());
                playerRating.setPlayerName(player.getName());

                Formation pvpForm = formationHelper.getFormationByPlayerAndFormationAllocation(player.getId(), PVP_DEFENSE.toString());

                if (pvpForm == null) {
                    throw new ValidationException("formation.pvp.not.found");
                }

                playerRating.setFormation(pvpForm.getId());
                playerRating = pvpRatingRepository.save(playerRating);

            }

            List<PvpRating> ret = new ArrayList<>(4);

            int[] vhr = EloRating.veryHardRatingRange(playerRating.getRating());
            List<PvpRating> vhRatings = pvpRatingRepository.findAllByPlayerNotAndRatingBetween(player.getId(), vhr[0] - 1, vhr[1] + 1);

            if (vhRatings != null && !vhRatings.isEmpty()) {
                ret.add(vhRatings.get(DiceUtil.random(vhRatings.size() - 1)));
            }

            int[] hr = EloRating.hardRatingRange(playerRating.getRating());
            List<PvpRating> hrRatings = pvpRatingRepository.findAllByPlayerNotAndRatingBetween(player.getId(), hr[0] - 1, hr[1] + 1);

            if (hrRatings != null && !hrRatings.isEmpty()) {
                ret.add(hrRatings.get(DiceUtil.random(hrRatings.size() - 1)));
            }

            int[] nr = EloRating.normalRatingRange(playerRating.getRating());
            List<PvpRating> nrRatings = pvpRatingRepository.findAllByPlayerNotAndRatingBetween(player.getId(), nr[0] - 1, nr[1] + 1);

            if (nrRatings != null && !nrRatings.isEmpty()) {
                ret.add(nrRatings.get(DiceUtil.random(nrRatings.size() - 1)));
            }

            int[] er = EloRating.easyRatingRange(playerRating.getRating());
            List<PvpRating> erRatings = pvpRatingRepository.findAllByPlayerNotAndRatingBetween(player.getId(), er[0] - 1, er[1] + 1);

            if (erRatings != null && !erRatings.isEmpty()) {
                ret.add(erRatings.get(DiceUtil.random(erRatings.size() - 1)));
            }

            return ret;

        } else {
            throw new ValidationException("player.not.found");
        }

    }

    @CacheEvict(value = PVPRATING_FIND_PVP_RATINGS, key = "'" + PVPRATING_FIND_PVP_RATINGS + "' + #user")
    public void removePvpRatings(String user) {

        List<Resource> useResource = new ArrayList<>();
        useResource.add(new Resource(ResourceType.GEM, pvpRatingPrice));

        playerHelper.useResources(useResource);
    }

    @CacheEvict(value = PVPRATING_FIND_PVP_RATINGS, key = "'" + PVPRATING_FIND_PVP_RATINGS + "' + #user")
    public Battle battlePvpRattings(String user, String idPvpRating) {

        Player player = playerHelper.getPlayerByLinkedUser(user);

        if (player != null) {

            PvpRating ratPlayer = pvpRatingRepository.findByPlayer(player.getId());
            PvpRating ratTarget = pvpRatingRepository.findById(idPvpRating);

            if (ratTarget != null) {

                Battle battle = battleHelper.doBattle(ratPlayer.getFormation(), ratTarget.getFormation());

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
        } else {
            throw new ValidationException("player.not.found");
        }

    }

}
