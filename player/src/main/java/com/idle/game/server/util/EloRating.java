package com.idle.game.server.util;

import com.idle.game.server.type.EloOutcome;

/**
 *
 * @author rafael
 */
public class EloRating {

    public static final int INITIAL_RATING = 1000;
    public static final double MIN_RANGE = 0.9;
    public static final double MAX_RANGE = 1.1;

    /**
     * Calculate rating for 2 players
     *
     * @param playerRequestRating The rating of Player Request
     * @param playerTargetRating The rating of Player Target
     * @param outcome
     * @return New player request rating
     */
    public static int calculate2PlayersRating(int playerRequestRating, int playerTargetRating, EloOutcome outcome) {

        double actualScore = 0;

        // winner
        switch (outcome) {
            case WIN:
                actualScore = 1.0;
                break;
            case LOSE:
                actualScore = 0;
                break;
        }

        // calculate expected outcome
        double exponent = (double) (playerTargetRating - playerRequestRating) / 400;
        double expectedOutcome = (1 / (1 + (Math.pow(10, exponent))));

        // K-factor
        int K = determineK(playerRequestRating);

        // calculate new rating
        int newRating = (int) Math.round(playerRequestRating + K * (actualScore - expectedOutcome));

        return newRating;
    }

    /**
     * Determine the rating constant K-factor based on current rating
     *
     * @param rating Player rating
     * @return K-factor
     */
    private static int determineK(int rating) {
        int K;
        if (rating < 2000) {
            K = 32;
        } else if (rating < 2400) {
            K = 24;
        } else {
            K = 16;
        }
        return K;
    }

    public static int[] veryHardRatingRange(int rating) {
        return new int[]{(int) (rating * 1.5d * MIN_RANGE), (int) (rating * 1.5d * MAX_RANGE)};
    }

    public static int[] hardRatingRange(int rating) {
        return new int[]{(int) (rating * 1.1d * MIN_RANGE), (int) (rating * 1.1d * MAX_RANGE)};
    }

    public static int[] normalRatingRange(int rating) {
        return new int[]{(int) (rating * MIN_RANGE), (int) (rating * MAX_RANGE)};
    }

    public static int[] easyRatingRange(int rating) {
        return new int[]{(int) (rating * .9d * MIN_RANGE), (int) (rating * .9d * MAX_RANGE)};
    }

}
