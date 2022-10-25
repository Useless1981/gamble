public class GTMPDwithMulti extends GreaterThanMeanPlusDeviation implements DecisionStrategy {

    private final double multiplier;
    final String name = "Greater than mean plus deviation with multiplicator";

    public GTMPDwithMulti(int measuringThreshold, double multiplier) {
        super(measuringThreshold);
        this.multiplier = multiplier;
    }

    public boolean decide(int roll) {
        super.rolls.add(roll);
        return super.rolls.size() <= super.measuringThreshold ? false : roll > (super.getMean() + super.getDeviation()) * multiplier;
    }

    public String getName() { return name; }
}
