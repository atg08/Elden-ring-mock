package game.utils;

import java.util.Random;

/**
 * A random number generator
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 *
 */
public class RandomNumberGenerator {

    /**
     * Returns a random integer within the specified upper bound.
     *
     * @param bound the upper bound for the random integer
     * @return a random integer between 0 (inclusive) and bound (exclusive), or 0 if bound is less than or equal to 0
     */
    public static int getRandomInt(int bound) {
        return bound > 0 ? new Random().nextInt(bound) : 0;
    }


    /**
     * Returns a boolean indicating whether an event should occur based on a given probability rate.
     *
     * @param rate the probability rate, expressed as a percentage between 0 and 100
     * @return true if the event should occur, false otherwise
     */
    public static boolean getBooleanProbability(int rate){
        return new Random().nextInt(100)<=rate;
    }


    /**
     * Returns a random integer within the specified range.
     *
     * @param lowerBound the lower bound for the random integer
     * @param upperBound the upper bound for the random integer
     * @return a random integer between lowerBound (inclusive) and upperBound (inclusive)
     */
    public static int getRandomIntInRange(int lowerBound, int upperBound) {
        int range = upperBound - lowerBound + 1;
        return new Random().nextInt(range) + lowerBound;
    }
}
