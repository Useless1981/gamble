/**
 * Interface for handling different decision strategies
 */
interface DecisionStrategy {

    String name = "Name of Strategy";
    /**
     * Method to determent to take or leave the current win
     * @param roll int: the current roll of the win
     * @return bool: ture to take the win, false to leave it
     */
    boolean decide(int roll);

    default String getName() {
        return name;
    }
}
