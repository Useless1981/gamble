import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {

        // List of multiplier to use
        List<Double> multipliers = List.of(0.6, 0.7, 0.8, 0.9, 1.0, 1.1, 1.2, 1.3, 1.4);

        // list of strategies for each game
        List<DecisionStrategy> strategies = List.of(new TakeFirst(), new GreaterThanMeanPlusDeviation(25), new GTMPDwithMulti(25, 1.1));

        // List of Games to Play
                //Settings 1
        //List<Game> games = Stream.of(Game.setUpGames(50, 100, 35, strategies),
        //        Game.setUpGames(50, 200, 45, strategies),
        //        Game.setUpGames(50, 50, 18, strategies)).flatMap(Collection::stream).toList();

        List<Game> games = List.of(new Game(50, 100, 35, new GreaterThanMeanPlusDeviation(25)),
                new Game(50, 100, 35, new GTMPDwithMulti(25, 1.1)),
                new Game(50, 100, 35, new TakeFirst()),
                // Settings 2
                new Game(50, 200, 45, new GreaterThanMeanPlusDeviation(25)),
                new Game(50, 200, 45, new GTMPDwithMulti(25, 1.1)),
                new Game(50, 200, 45, new TakeFirst()),
                // Settings 3
                new Game(50, 50, 18, new GreaterThanMeanPlusDeviation(25)),
                new Game(50, 50, 18, new GTMPDwithMulti(25, 1.1)),
                new Game(50, 50, 18, new TakeFirst())
                );

        // List of results
        List<Result> results = games.stream().map(game -> Player.play(game, 50)).toList();
        System.out.print(results);
        //Print results to console
        System.out.print(results);
    }
}



