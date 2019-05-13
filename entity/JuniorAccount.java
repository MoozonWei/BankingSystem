/**
 * Junior account class
 *
 * @author Zihan WEI
 */

public class JuniorAccount extends BankAccount {

    // Constructor

    public JuniorAccount(Customer customer) {
        super(customer);
        this.setAccountType(1);
    }

}
