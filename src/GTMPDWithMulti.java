import java.util.List;

public class GTMPDWithMulti extends GreaterThanMeanPlusDeviation implements DecisionStrategy {

    private final double multiplier;
    final String name = "Greater than mean plus deviation with multiplicator";

    public GTMPDWithMulti(int measuringThreshold, double multiplier) {
        super(measuringThreshold);
        this.multiplier = multiplier;
    }

    public boolean decide(int roll) {
        super.rolls.add(roll);
        return super.rolls.size() <= super.measuringThreshold ? false : roll > (super.getMean() + super.getDeviation()) * multiplier;
    }

    public String getName() { return name +": " + multiplier; }

    public static List<GTMPDWithMulti> withMultipliers(int measuringThreshold, List<Double> multipliers) {
        return multipliers.stream().map(multiplier -> new GTMPDWithMulti(measuringThreshold, multiplier)).toList();
    }
}
