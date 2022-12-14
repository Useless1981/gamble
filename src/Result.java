import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Result-class
 */
public class Result {
    private final List<Integer> resultList;

    private final String usedStrategy;

    /**
     * Creates a Result instance containing the results of played Game-class
     * @param results List<Integer>: list of single rounds as int
     */
    public Result(List<Integer> results, String usedStrategy) {
        this.resultList = results;
        this.usedStrategy = usedStrategy;
    }

    /**
     * Calculates and returns the mean of the results
     * @return double: mean
     */
    public double getMean() {
        return resultList.stream().mapToDouble(x -> x).average().orElse(0.0);
    }

    /**
     * Calculates and returns the deviation of the results
     * @return double: deviation
     */
    public double getDeviation() {
        return Math.sqrt(getMean());
    }

    @Override
    public String toString() {
        return "Results:\n" +
                "\tStrategy: " + usedStrategy +
                "\n\tMean: " + getMean() +
                "\n\tDeviation: " + getDeviation() + "\n";
    }

    /**
     * Encodes a list of Results to a csv file
     * @param results List<Result>: List of Results
     * @return String: Results as valid csv String
     */
    public static String encodeToCsv(List<Result> results) {
        return "Strategy,Mean\n" + results.stream().map(result -> result.usedStrategy + "," + result.getMean()).reduce(((result1, result2) -> result1 + "\n" + result2)).orElse("");
    }

    /**
     * Encodes the values of list of Results to a csv file
     * @param results List<Result>: List of Results
     * @return String: Results as valid csv String
     */
    public static String encodeValuesToCsv(List<Result> results) {
        return "Values\n" + results.stream().map(Result::getValues).flatMap(List::stream).map(Object::toString).collect(Collectors.joining("\n"));
    }

    /**
     * Encodes and writes a results to a csv file
     * @param results List<Result>: Results to export
     * @param fileName String: Filename
     * @throws IOException
     */
    public static void writeValuesToCsv(List<Result> results, String fileName) throws IOException {
        writeToFile(encodeValuesToCsv(results), fileName);
    }

    public List<Integer> getValues() {
        return this.resultList;
    }

    /**
     * Writes a file to active dir
     * @param toWrite String: String to write
     * @param fileName String: Filename
     * @throws IOException
     */
    public static void writeToFile(String toWrite, String fileName) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        writer.write(toWrite);
        writer.close();
    }
}
