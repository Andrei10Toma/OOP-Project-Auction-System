package employee;

/**
 * First type of commission.
 */
public class CommissionFirstCase implements CommissionType {
    /**
     * calculates the commission in the first case.
     * @param winnerBid the price at which the product was purchased.
     * @return first case commission.
     */
    @Override
    public double calculateCommission(double winnerBid) {
        return 0.2 * winnerBid;
    }
}
