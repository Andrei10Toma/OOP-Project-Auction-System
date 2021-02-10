package licitation_strategies;

public interface Strategy {
    /**
     * calculates the bid with the strategy.
     * @param previousMaxSum the max sum from the last step.
     * @return the bid for the actual step.
     */
    double bid(double previousMaxSum);
}
