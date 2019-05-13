/**
 * Customer class
 *
 * @author Zihan WEI
 */

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Customer {

    private int customerID;
    private String name;
    private String address;
    private Calendar dateOfBirth;
    private boolean creditStatus;

    // Constructor

    public Customer(int customerID, String name, String address, String dateOfBirth) {
        this.customerID = customerID;
        this.name = name;
        this.address = address;
        setDateOfBirth(dateOfBirth);
        this.creditStatus = true;
    }

    // Getters and Setters

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDateOfBirth() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = sdf.format(this.dateOfBirth.getTime());
        return dateStr;
    }

    public void setDateOfBirth(String dateStr) {
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        this.dateOfBirth = calendar;
    }

    public boolean isCreditStatus() {
        return creditStatus;
    }

    public void setCreditStatus(boolean creditStatus) {
        this.creditStatus = creditStatus;
    }

}
