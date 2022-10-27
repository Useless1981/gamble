import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {

        // List of strategies for each game
        List<DecisionStrategy> strategies = List.of(new TakeFirst());
        List<DecisionStrategy> greaterThanMeanPlusDeviation = new LinkedList<>(GreaterThanMeanPlusDeviation.withAllResonalbeMeasuringThresholds());
        List<DecisionStrategy> withMultiplier = new LinkedList<>(GTMPDWithMulti.withMultipliers(25, List.of(0.8, 0.9, 1.0, 1.1, 1.2, 1.3, 1.4, 1.6, 1.7, 1.8)));
        // Concat lists
        strategies = Stream.of(strategies, greaterThanMeanPlusDeviation, withMultiplier).flatMap(Collection::stream).collect(Collectors.toList());

        // List of Games to Play
        List<Game> games = Stream.of(Game.setUpGames(50, 100, 35, strategies),
                Game.setUpGames(50, 200, 45, strategies),
                Game.setUpGames(50, 50, 18, strategies)).flatMap(Collection::stream).toList();

        // List of results
        List<Result> results = games.stream().map(game -> Player.play(game, 50)).toList();

        //Print results to console
        System.out.print(Result.encodeToCsv(results));
    }
}



