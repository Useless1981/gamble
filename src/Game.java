import java.util.Random;
import java.util.random.RandomGenerator;

/**
 * Representation af a game. Can be Played via Player-class.
 */
public class Game {
    final private int rounds;
    final private int winMean;
    final private int winDeviation;
    final RandomGenerator randomGenerator = new Random();

    public int getWinMean() {
        return winMean;
    }

    public int getWinDeviation() {
        return winDeviation;
    }

    final private DecisionStrategy strategy;

    /**
     * Representation of a gambling game
     * @param rounds int: rounds a game to last
     * @param winMean int: mean of the win in each round
     * @param winDeviation int: deviation of the win in each round
     * @param strategy DecisionStrategy: strategy, how to play the game
     */
    public Game(int rounds, int winMean, int winDeviation, DecisionStrategy strategy) {
        this.rounds = rounds;
        this.winMean = winMean;
        this.winDeviation = winDeviation;
        this.strategy = strategy;
    }

    /**
     * Starts a play and returns the win of that play
     * @return int: win
     */
    public int play() {
        for (int round = 1; round <= rounds; round++) {
            int roll = rollWin();
            if (strategy.decide(this, roll) || round == rounds) {
                return roll;
            }
        }
        return -1; // Should never be returned. ToDO: better solution to skip this return
    }

    /**
     * Rolls a win of a round with winMean and winDeviation
     * @return double: win
     */
    private int rollWin() {
        return (int) (randomGenerator.nextGaussian(winMean, winDeviation));
    }

    /**
     * Decision-making
     * @return boolean: ture ends the game and takes the win of the round
     */
    private boolean takeIt(double currentWin) {
        return true;
    }

    public DecisionStrategy getStrategy() {
        return strategy;
    }
}


