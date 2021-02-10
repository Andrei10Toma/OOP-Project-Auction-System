package employee;


public interface CommissionType {
    /**
     * calculates the commission of the broker.
     * @param winnerBid the price at which the product was purchased.
     * @return the value of the commission.
     */
    double calculateCommission(double winnerBid);
}
