package employee;

import client.Client;
import client.Individual;
import client.LegalPerson;

public class CommissionFactory {
    public CommissionType chooseCommission(Client client) {
        if (client instanceof Individual && client.getNumberParticipation() <= 5) {
            return new CommissionFirstCase();
        } else if (client instanceof Individual && client.getNumberParticipation() > 5) {
            return new CommissionSecondCase();
        } else if (client instanceof LegalPerson && client.getNumberParticipation() < 25) {
            return new CommissionThirdCase();
        } else if (client instanceof LegalPerson && client.getNumberParticipation() > 25) {
            return new CommissionForthCase();
        } else {
            return null;
        }
    }
}
