/**
 * This strategy  takes the win, if it is greater than mean + deviation of the current game.
 */
public class GreaterThanMeanPlusDeviation implements DecisionStrategy{



    final String name = "Greater than mean plus deviation";
    public boolean decide(Game game, int roll) {
        return roll > game.getWinMean() + game.getWinDeviation();
    }

    public String getName() {
        return name;
    }
}
