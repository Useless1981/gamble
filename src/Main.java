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
        List<Result> measuringThresholdResult0 = measuringThresholdGameSet0.stream().map(game -> Player.play(game, 1000)).toList();
        List<Result> measuringThresholdResult1 = measuringThresholdGameSet1.stream().map(game -> Player.play(game, 1000)).toList();
        List<Result> measuringThresholdResult2 = measuringThresholdGameSet2.stream().map(game -> Player.play(game, 1000)).toList();
/*
        try {
            Result.writeToFile(Result.encodeToCsv(measuringThresholdResult0), "GameSet0.csv");
            Result.writeToFile(Result.encodeToCsv(measuringThresholdResult1), "GameSet1.csv");
            Result.writeToFile(Result.encodeToCsv(measuringThresholdResult2), "GameSet2.csv");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
*/

        // Set up Games to Play
        List<Game> takeFirstValuesGame0 = Game.setUpGames(50, 100, 35, List.of(new TakeFirst()));
        List<Game> takeFirstValuesGame1 = Game.setUpGames(50, 200, 45, List.of(new TakeFirst()));
        List<Game> takeFirstValuesGame2 = Game.setUpGames(50, 50, 18, List.of(new TakeFirst()));

        // Play games
        List<Result> takeFirstValues0 = takeFirstValuesGame0.stream().map(game -> Player.play(game, 1000)).toList();
        List<Result> takeFirstValues1 = takeFirstValuesGame1.stream().map(game -> Player.play(game, 1000)).toList();
        List<Result> takeFirstValues2 = takeFirstValuesGame2.stream().map(game -> Player.play(game, 1000)).toList();
        try {
            Result.writeValuesToCsv(takeFirstValues0, "TakeFirstValues0.csv");
            Result.writeValuesToCsv(takeFirstValues1, "TakeFirstValues1.csv");
            Result.writeValuesToCsv(takeFirstValues2, "TakeFirstValues2.csv");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Set up Games to Play
        List<Game> onePointTwo = Game.setUpGames(50, 100, 35, List.of(new GTMPDWithMulti(25, 1.2)));
        List<Game> onePointFour = Game.setUpGames(50, 100, 35, List.of(new GTMPDWithMulti(25, 1.4)));
        List<Game> onePointSix = Game.setUpGames(50, 100, 35, List.of(new GTMPDWithMulti(25, 1.6)));
        List<Game> onePointEight = Game.setUpGames(50, 100, 35, List.of(new GTMPDWithMulti(25, 1.8)));
        List<Game> twoPointZero = Game.setUpGames(50, 100, 35, List.of(new GTMPDWithMulti(25, 2.0)));

        // Play games
        List<Result> onePointTwoValues = onePointTwo.stream().map(game -> Player.play(game, 1000)).toList();
        List<Result> onePointFourValues = onePointFour.stream().map(game -> Player.play(game, 1000)).toList();
        List<Result> onePointSixValues = onePointSix.stream().map(game -> Player.play(game, 1000)).toList();
        List<Result> onePointEightValues = onePointEight.stream().map(game -> Player.play(game, 1000)).toList();
        List<Result> twoPointZeroValues = twoPointZero.stream().map(game -> Player.play(game, 1000)).toList();

        try {
            Result.writeValuesToCsv(onePointTwoValues, "onePointTwoValues.csv");
            Result.writeValuesToCsv(onePointFourValues, "onePointFourValues.csv");
            Result.writeValuesToCsv(onePointSixValues, "onePointSixValues.csv");
            Result.writeValuesToCsv(onePointEightValues, "onePointEightValues.csv");
            Result.writeValuesToCsv(twoPointZeroValues, "twoPointZeroValues.csv");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}





