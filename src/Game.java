import java.util.List;
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
            if (strategy.decide(roll) || round == rounds) {
                return roll;
            }
        }
        return -1;
    }

    /**
     * Rolls a win of a round with winMean and winDeviation
     * @return double: win
     */
    private int rollWin() {
        return (int) (randomGenerator.nextGaussian(winMean, winDeviation));
    }

    /**
     * Strategy getter
     * @return DecisionStrategy: used Strategy
     */
    public DecisionStrategy getStrategy() {
        return strategy;
    }

    /**
     *  Sets up multiple game with different strategies
     * @param rounds int: rounds a game to last
     * @param winMean int: mean of the win in each round
     * @param winDeviation int: deviation of the win in each round
     * @param strategies List<DecisionStrategy>: strategies, how to play the games
     * @return List<Game>: List of Games
     */
    public static List<Game> setUpGames(int rounds, int winMean, int winDeviation, List<DecisionStrategy> strategies) {
        return strategies.stream().map(strategy -> new Game(rounds, winMean, winDeviation, strategy)).toList();
    }
}


