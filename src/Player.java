import java.util.LinkedList;
import java.util.List;

/**
 * Player plays a given game for a given number of times and collects the results.
 */
public final class Player {

    /**
     * Plays game for numberOfPlays and returns the results of all games a Result-class.
     * @param game Game: game to play
     * @param numberOfPlays int: number of times to play the game
     * @return Result: collected results of all played games
     */
    public static Result play(Game game, int numberOfPlays) {
        List<Integer> singleGameResults = new LinkedList<>();
        for (int play = 1; play <= numberOfPlays; play++) {
            singleGameResults.add(game.play());
        }
        return new Result(singleGameResults, game.getStrategy().getName());
    }
}
