import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {

        // list of strategies for each game
        List<DecisionStrategy> strategies = new ArrayList<>(GTMPDWithMulti.withMultipliers(25, List.of(0.6, 0.7, 0.8, 0.9, 1.0, 1.1, 1.2, 1.3, 1.4)));
        strategies.add(new TakeFirst());
        strategies.add(new GreaterThanMeanPlusDeviation(25));

        // List of Games to Play
        List<Game> games = Stream.of(Game.setUpGames(50, 100, 35, strategies),
                Game.setUpGames(50, 200, 45, strategies),
                Game.setUpGames(50, 50, 18, strategies)).flatMap(Collection::stream).toList();

        // List of results
        List<Result> results = games.stream().map(game -> Player.play(game, 50)).toList();
        System.out.print(results);
        //Print results to console
        System.out.print(results);
    }
}



