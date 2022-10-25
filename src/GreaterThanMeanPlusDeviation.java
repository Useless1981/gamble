import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This strategy  takes the win, if it is greater than mean + deviation of the current game.
 */
public class GreaterThanMeanPlusDeviation implements DecisionStrategy{

    List<Integer> rolls = new LinkedList<>();
    int measuringThreshold;

    public GreaterThanMeanPlusDeviation(int measuringThreshold) {
        this.measuringThreshold = measuringThreshold;
    }

    final String name = "Greater than mean plus deviation";
    public boolean decide(int roll) {
        rolls.add(roll);
        return rolls.size() <= measuringThreshold ? false : roll > getMean() + getDeviation(); }

    public String getName() {
        return name;
    }

    double getMean() { return rolls.stream().collect(Collectors.averagingInt(x -> x)); }

    double getDeviation() {
            Double deviationSum = rolls.stream().map(x -> Math.pow(x - getMean(), 2)).reduce(Double::sum).orElse(0.0);
            return rolls.isEmpty() ? 100000.0 : Math.sqrt(deviationSum / rolls.size());
        }

}
