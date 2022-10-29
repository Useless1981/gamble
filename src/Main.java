import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {

        // List of strategies for each game
        List<DecisionStrategy> strategies = new LinkedList<>();
        strategies.add(new TakeFirst());
        strategies.add(new GreaterThanMeanPlusDeviation(25));
        List<DecisionStrategy> withMultiplier = new LinkedList<>(GTMPDWithMulti.withMultipliers(25, List.of(1.2, 1.4, 1.6, 1.8, 2.0)));
        strategies = Stream.of(strategies, withMultiplier).flatMap(Collection::stream).collect(Collectors.toList());

        // Set up Games to Play
        List<Game> measuringThresholdGameSet0 = Game.setUpGames(50, 100, 35, strategies);
        List<Game> measuringThresholdGameSet1 = Game.setUpGames(50, 200, 45, strategies);
        List<Game> measuringThresholdGameSet2 = Game.setUpGames(50, 50, 18, strategies);

        // Play games
        List<Result> measuringThresholdResult0 = measuringThresholdGameSet0.stream().map(game -> Player.play(game, 100)).toList();
        List<Result> measuringThresholdResult1 = measuringThresholdGameSet1.stream().map(game -> Player.play(game, 100)).toList();
        List<Result> measuringThresholdResult2 = measuringThresholdGameSet2.stream().map(game -> Player.play(game, 100)).toList();

        try {
            Result.writeToFile(Result.encodeToCsv(measuringThresholdResult0), "GameSet0.csv");
            Result.writeToFile(Result.encodeToCsv(measuringThresholdResult1), "GameSet1.csv");
            Result.writeToFile(Result.encodeToCsv(measuringThresholdResult2), "GameSet2.csv");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}



