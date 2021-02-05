package licitation_strategies;

public class TheClassicOne implements Strategy{
    @Override
    public double bid(double previousMaxSum) {
        return previousMaxSum + 100;
    }

    @Override
    public String toString() {
        return "TheClassicOne";
    }
}
