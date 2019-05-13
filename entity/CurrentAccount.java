/**
 * Current account class
 *
 * @author Zihan WEI
 */

public class CurrentAccount extends BankAccount {

    // Constructor

    public CurrentAccount(Customer customer, double overDraftLimit) {
        super(customer);
        this.setAccountType(0);
        this.setOverDraftLimit(overDraftLimit);
    }

}
