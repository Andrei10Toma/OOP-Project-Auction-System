package licitation_strategies;

/**
 * One quarter strategy increases the sum by 25%.
 */
public class OneQuarter implements Strategy {
    /**
     * calculates the bid from the current step with the one quarter strategy.
     * @param previousMaxSum the max sum from the last step.
     * @return bid for the current step.
     */
    @Override
    public double bid(double previousMaxSum) {
        return 0.25 * previousMaxSum + previousMaxSum;
    }

    @Override
    public String toString() {
        return "OneQuarter";
    }
}
