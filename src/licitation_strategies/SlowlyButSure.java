package licitation_strategies;

public class SlowlyButSure implements Strategy {
    @Override
    public double bid(double previousMaxSum) {
        return previousMaxSum + 50;
    }

    @Override
    public String toString() {
        return "SlowlyButSure";
    }
}
