import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This strategy  takes the win, if it is greater than mean + deviation of the current game.
 */
public class GreaterThanMeanPlusDeviation implements DecisionStrategy {

    final String name = "Greater than mean plus deviation";

    List<Integer> rolls = new LinkedList<>();
    int measuringThreshold;
    double mean;
    double deviation;

    public GreaterThanMeanPlusDeviation(int measuringThreshold) {
        this.measuringThreshold = measuringThreshold;
    }

    public boolean decide(int roll) {
        rolls.add(roll);
        if(rolls.size() == measuringThreshold) {
            mean = getMean();
            deviation = getDeviation();
        }
        return rolls.size() > measuringThreshold && roll > mean + deviation;
    }

    public void reset() {
        rolls.clear();
    }

    public String getName() {
        return name;
    }

    /**
     * Returns the mean of all seen rolls
     * @return double: mean of seen rolls
     */
    double getMean() {
        return rolls.stream().collect(Collectors.averagingInt(x -> x));
    }

    /**
     * Returns the deviation of all seen rolls
     * @return double: deviation of seen rolls
     */
    double getDeviation() {
            Double deviationSum = rolls.stream().map(x -> Math.pow(x - getMean(), 2)).reduce(Double::sum).orElse(0.0);
            return Math.sqrt(deviationSum / rolls.size());
    }
}
