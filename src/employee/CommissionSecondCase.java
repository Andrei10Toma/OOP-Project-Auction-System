package employee;


public class CommissionSecondCase implements CommissionType {
    @Override
    public double calculateCommission(double winnerBid) {
        return 0.15 * winnerBid;
    }
}
