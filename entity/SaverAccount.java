/**
 * Saver account class
 *
 * @author Zihan WEI
 */

import java.util.Date;

public class SaverAccount extends BankAccount {

    private Date noticeDate;
    private double noticeAmount;

    // Constructor

    public SaverAccount(Customer customer) {
        super(customer);
        this.setNoticeNeeded(true);
        this.setAccountType(2);
    }

    // Getters and Setters

    public Date getNoticeDate() {
        return noticeDate;
    }

    public void setNoticeDate(Date noticeDate) {
        this.noticeDate = noticeDate;
    }

    public double getNoticeAmount() {
        return noticeAmount;
    }

    public void setNoticeAmount(double noticeAmount) {
        this.noticeAmount = noticeAmount;
    }

}
