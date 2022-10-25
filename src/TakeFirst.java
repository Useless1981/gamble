/**
 * This strategy always takes the win of the first roll of the game.
 */
public final class TakeFirst implements DecisionStrategy {

    final String name = "Take first win";
    public boolean decide(int roll) { return true;}

    public String getName() {
        return name;
    }
}
