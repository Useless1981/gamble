import java.util.List;

public class Main {
    public static void main(String[] args) {

        // List of Games to Play
        List<Game> games = List.of(new Game(50, 100, 35, new GreaterThanMeanPlusDeviation(25)),
                new Game(50, 100, 35, new GTMPDwithMulti(25, 1.1)),
                new Game(50, 100, 35, new TakeFirst()),
                new Game(50, 200, 45, new GreaterThanMeanPlusDeviation(25)),
                new Game(50, 200, 45, new GTMPDwithMulti(25, 1.1)),
                new Game(50, 200, 45, new TakeFirst()),
                new Game(50, 50, 18, new GreaterThanMeanPlusDeviation(25)),
                new Game(50, 50, 18, new GTMPDwithMulti(25, 1.1)),
                new Game(50, 50, 18, new TakeFirst())
                );

        // List of results
        List<Result> results = games.stream().map(game -> Player.play(game, 50)).toList();

        //Print results to console
        System.out.print(results);
    }
}



