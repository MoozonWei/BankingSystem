/**
 * Appointment class
 *
 * @author Zihan WEI
 */

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class SaverAppointment {

    private int accountNo;
    private Calendar dateLimit;

    // Constructor

    public SaverAppointment(int accountNo, Calendar dateLimit) {
        this.accountNo = accountNo;
        this.dateLimit = dateLimit;
    }

    // Getters and Setters

    public int getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(int accountNo) {
        this.accountNo = accountNo;
    }

    public String getDateLimitString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateLimit = sdf.format(this.dateLimit.getTime());
        return dateLimit;
    }

    public Calendar getDateLimitCalendar() {
        return this.dateLimit;
    }

    public void setDateLimit(Calendar dateLimit) {
        this.dateLimit = dateLimit;
    }

}
