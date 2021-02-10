package employee;

/**
 * Second case commission.
 */
public class CommissionSecondCase implements CommissionType {
    /**
     * calculates commission in the second case.
     * @param winnerBid the price at which the product was purchased.
     * @return second case commission.
     */
    @Override
    public double calculateCommission(double winnerBid) {
        return 0.15 * winnerBid;
    }
}
