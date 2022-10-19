/**
 * Interface for handling different decision strategies
 */
interface DecisionStrategy {

    final String name = "Name of Strategy";
    /**
     * Method to determent to take or leave the current win
     * @param game Game: game that ist played
     * @param roll int: the current roll of the win
     * @return bool: ture to take the win, false to leave it
     */
    boolean decide(Game game, int roll);

    default String getName() {
        return name;
    }
}
