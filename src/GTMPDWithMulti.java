import java.util.List;

public class GTMPDWithMulti extends GreaterThanMeanPlusDeviation implements DecisionStrategy {

    private final double multiplier;
    final String name = "Greater than mean plus deviation with multiplicator";

    public GTMPDWithMulti(int measuringThreshold, double multiplier) {
        super(measuringThreshold);
        this.multiplier = multiplier;
    }

    public boolean decide(int roll) {
        rolls.add(roll);
        if(measuringThreshold == rolls.size()) {
            mean = getMean();
            deviation = getDeviation();
        }
        if(rolls.size() > measuringThreshold && roll > mean + deviation * multiplier) {
            rolls.clear();
            return true;
        }
        return false;
    }

    public String getName() { return name +": " + multiplier; }

    public static List<GTMPDWithMulti> withMultipliers(int measuringThreshold, List<Double> multipliers) {
        return multipliers.stream().map(multiplier -> new GTMPDWithMulti(measuringThreshold, multiplier)).toList();
    }
}
