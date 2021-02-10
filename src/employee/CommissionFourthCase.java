package employee;

/**
 * Fourth case commission.
 */
public class CommissionFourthCase implements CommissionType {
    /**
     * calculates commission in the fourth case.
     * @param winnerBid the price at which the product was purchased.
     * @return fourth case commission.
     */
    @Override
    public double calculateCommission(double winnerBid) {
        return 0.1 * winnerBid;
    }
}
