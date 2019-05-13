/**
 * Bank account, father class of CurrentAccount, JuniorAccount and SaverAccount
 *
 * @author Zihan WEI
 */

import java.util.Random;

public class BankAccount {

    private int accountNo;
    private int pin;
    private Customer customer;
    private double balance;
    private double unClearedBalance;
    private double overDraftLimit;
    private boolean isSuspended;
    private boolean isActive;
    private boolean noticeNeeded;
    private int accountType;

    // Constructor

    public BankAccount(Customer customer) {
        setAccountNo();
        this.customer = customer;
        this.balance = 0.0;
        this.unClearedBalance = 0.0;
        this.overDraftLimit = 0.0;
        this.isActive = true;
        this.noticeNeeded = false;
        setPin();
    }

    // Getters and Setters

    public int getAccountNo() {
        return accountNo;
    }

    public void setAccountNo() {
        if (Database.getInstance().bankAccountArrayList.size() == 0) {
            this.accountNo = 100000;
        } else {
            this.accountNo = 1 + Database.getInstance().bankAccountArrayList.get(Database.getInstance().bankAccountArrayList.size() - 1).getAccountNo();
        }
    }

    public int getPin() {
        return pin;
    }

    public void setPin() {
        Random r = new Random();
        this.pin = (100000 + r.nextInt(900000));
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void addBalance(double addBalance) {
        this.balance += addBalance;
    }

    public double getUnClearedBalance() {
        return unClearedBalance;
    }

    public void setUnClearedBalance(double unClearedBalance) {
        this.unClearedBalance = unClearedBalance;
    }

    public void addUnClearedBalance(double addUnClBalance) {
        this.unClearedBalance += addUnClBalance;
    }

    public double getOverDraftLimit() {
        return overDraftLimit;
    }

    public void setOverDraftLimit(double overDraftLimit) {
        this.overDraftLimit = overDraftLimit;
    }

    public boolean isSuspended() {
        return isSuspended;
    }

    public void setSuspended(boolean suspended) {
        isSuspended = suspended;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isNoticeNeeded() {
        return noticeNeeded;
    }

    public void setNoticeNeeded(boolean noticeNeeded) {
        this.noticeNeeded = noticeNeeded;
    }

    public int getAccountType() {
        return accountType;
    }

    public void setAccountType(int accountType) {
        this.accountType = accountType;
    }

}
