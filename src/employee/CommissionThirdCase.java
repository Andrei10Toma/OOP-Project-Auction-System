package employee;


public class CommissionThirdCase implements CommissionType{
    @Override
    public double calculateCommission(double winnerBid) {
        return 0.25 * winnerBid;
    }
}
