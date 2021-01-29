package licitation_strategies;

public class OneQuarter implements Strategy {
    @Override
    public double bid(double previousMaxSum) {
        return 0.25 * previousMaxSum + previousMaxSum;
    }

    @Override
    public String toString() {
        return "OneQuarter";
    }
}
