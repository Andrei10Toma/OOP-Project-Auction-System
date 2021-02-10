package licitation_strategies;

/**
 * Strategy factory.
 */
public class StrategyFactory {
    /**
     * returns the strategy based on the number given as parameter.
     * @param numberOfStrategy a number between 0 and 3.
     * @return the strategy.
     */
    public Strategy getStrategy(int numberOfStrategy) {
        if (numberOfStrategy == 0) {
            return new TheClassicOne();
        }
        else if (numberOfStrategy == 1) {
            return new OneQuarter();
        }
        else if (numberOfStrategy == 2) {
            return new SlowlyButSure();
        }
        return null;
    }
}
