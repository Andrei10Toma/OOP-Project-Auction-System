package licitation_strategies;

/**
 * Slowly but sure strategy increases the sum with 50.
 */
public class SlowlyButSure implements Strategy {
    /**
     * calculates the bid from the current step with the slowly but sure strategy.
     * @param previousMaxSum the max sum from the last step.
     * @return the bid from the actual step.
     */
    @Override
    public double bid(double previousMaxSum) {
        return previousMaxSum + 50;
    }

    @Override
    public String toString() {
        return "SlowlyButSure";
    }
}
