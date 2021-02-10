package employee;

import client.Client;
import client.Individual;
import client.LegalPerson;

/**
 * Factory for the commission that the broker will when the auction ends.
 */
public class CommissionFactory {
    /**
     * Calculates the commission depending on the winner client.
     * @param client the winner client.
     * @return commission type.
     */
    public CommissionType chooseCommission(Client client) {
        if (client instanceof Individual && client.getNumberParticipation() <= 5) {
            return new CommissionFirstCase();
        } else if (client instanceof Individual && client.getNumberParticipation() > 5) {
            return new CommissionSecondCase();
        } else if (client instanceof LegalPerson && client.getNumberParticipation() < 25) {
            return new CommissionThirdCase();
        } else if (client instanceof LegalPerson && client.getNumberParticipation() > 25) {
            return new CommissionFourthCase();
        } else {
            return null;
        }
    }
}
