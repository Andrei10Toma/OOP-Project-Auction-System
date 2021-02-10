package licitation_strategies;

/**
 * The classic one strategy increases the sum by 100.
 */
public class TheClassicOne implements Strategy{
    /**
     * calculates the bid from the current step with the classic one strategy.
     * @param previousMaxSum the max sum from the last step.
     * @return the bid from the current step
     */
    @Override
    public double bid(double previousMaxSum) {
        return previousMaxSum + 100;
    }

    @Override
    public String toString() {
        return "TheClassicOne";
    }
}
