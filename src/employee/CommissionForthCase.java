package employee;


public class CommissionForthCase implements CommissionType {
    @Override
    public double calculateCommission(double winnerBid) {
        return 0.1 * winnerBid;
    }
}
