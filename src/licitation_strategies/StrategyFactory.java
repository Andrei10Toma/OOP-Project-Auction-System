package licitation_strategies;

public class StrategyFactory {
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
