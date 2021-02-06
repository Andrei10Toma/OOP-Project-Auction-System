package employee;


public class CommissionFirstCase implements CommissionType{
    @Override
    public double calculateCommission(double winnerBid) {
        return 0.2 * winnerBid;
    }
}
