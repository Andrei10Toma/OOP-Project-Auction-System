package employee;

/**
 * Third case commission.
 */
public class CommissionThirdCase implements CommissionType {
    /**
     * calculates the third case commission.
     * @param winnerBid the price at which the product was purchased.
     * @return third case commission.
     */
    @Override
    public double calculateCommission(double winnerBid) {
        return 0.25 * winnerBid;
    }
}
