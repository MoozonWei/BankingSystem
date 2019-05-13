/**
 * The most basic controller class
 *
 * @authur Zihan WEI
 */

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class BankController {
    /*------------------------------------------------ Checking -------------------------------------------------------*/

    /**
     * Check if this account number is correct
     *
     * @param accNO Account number
     * @return If this account number is correct
     */
    public static boolean accountNOChecking(String accNO) {
        if (accNO.length() == 6)
            return true;
        else
            return false;
    }

    /**
     * Check if this account number is existing in database
     *
     * @param accNO Account number
     * @return If this account number is existing in database
     */
    public static boolean accountExistChecking(String accNO) {
        Database db = Database.getInstance();
        for (BankAccount bankAccount : db.bankAccountArrayList) {
            if (bankAccount.getAccountNo() == Integer.parseInt(accNO)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check if the password is correct
     *
     * @param accNO Account number
     * @param passwd Input password
     * @return If the password is correct
     */
    public static boolean accountPasswdChecking(String accNO, String passwd) {
        Database db = Database.getInstance();
        for (BankAccount bankAccount : db.bankAccountArrayList) {
            if (bankAccount.getAccountNo() == Integer.parseInt(accNO)) {
                if (bankAccount.getPin() == Integer.parseInt(passwd)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Check this account's activity
     *
     * @param accNO Account number
     * @return This account's activity
     */
    public static boolean accountActiveChecking(String accNO) {
        Database db = Database.getInstance();
        for (BankAccount bankAccount : db.bankAccountArrayList) {
            if (bankAccount.getAccountNo() == Integer.parseInt(accNO)) {
                return bankAccount.isActive();
            }
        }
        return false;
    }

    /**
     * check if this account has a debt
     *
     * @param accNO Account number
     * @return If this account hac a debt
     */
    public static boolean accountDebtChecking(String accNO) {
        Database db = Database.getInstance();
        for (BankAccount bankAccount : db.bankAccountArrayList) {
            if (bankAccount.getAccountNo() == Integer.parseInt(accNO)) {
                if (bankAccount.getBalance() >= 0.0) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * check if this account still has some funds(cleared/uncleared)
     *
     * @param accNO Account number
     * @return if this account still has some funds(cleared/uncleared)
     */
    public static boolean accountBalanceChecking(String accNO) {
        Database db = Database.getInstance();
        for (BankAccount bankAccount : db.bankAccountArrayList) {
            if (bankAccount.getAccountNo() == Integer.parseInt(accNO)) {
                if (bankAccount.getBalance() == 0.0 && bankAccount.getUnClearedBalance() == 0.0) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Check this account's Type
     *
     * @param accNO Account number
     * @return This account's type in integer-----0: current account   1: junior account   2: saver account
     */
    public static int accountTypeChecking(String accNO) {
        Database db = Database.getInstance();
        for (BankAccount bankAccount : db.bankAccountArrayList) {
            if (bankAccount.getAccountNo() == Integer.parseInt(accNO)) {
                return bankAccount.getAccountType();
            }
        }
        return -1;
    }

    /**
     * Transfer the type name into type number
     *
     * @param typeName Account type name
     * @return The type number
     */
    public static int accountTypeToInt(String typeName) {
        if (typeName.equals("Current Account"))
            return 0;
        else if (typeName.equals("Junior Account"))
            return 1;
        else if (typeName.equals("Saver Account"))
            return 2;
        return 3;
    }

    /**
     * Transfer the type number into type name
     *
     * @param typeNum Account type num
     * @return The type name
     */
    public static String accountTypeToString(int typeNum) {
        if (typeNum == 0)
            return "Current Account";
        else if (typeNum == 1)
            return "Junior Account";
        else if (typeNum == 2)
            return "Saver Account";
        return "ERROR";
    }

    /**
     * Get this account's index number bank account ArrayList
     *
     * @param accNO Account number
     * @return Index number
     */
    public static int indexOfBankArraylist(String accNO) {
        Database db = Database.getInstance();
        for (BankAccount bankAccount : db.bankAccountArrayList) {
            if (bankAccount.getAccountNo() == Integer.parseInt(accNO)) {
                return db.bankAccountArrayList.indexOf(bankAccount);
            }
        }
        return -1;
    }

    /**
     * Get this account's index number in current account ArrayList
     *
     * @param accNO Account number
     * @return Index number
     */
    public static int indexOfCurrentArraylist(String accNO) {
        Database db = Database.getInstance();
        for (CurrentAccount currentAccount : db.currentAccountArrayList) {
            if (currentAccount.getAccountNo() == Integer.parseInt(accNO)) {
                return db.currentAccountArrayList.indexOf(currentAccount);
            }
        }
        return -1;
    }

    /**
     * Get this account's index number in junior account ArrayList
     *
     * @param accNO Account number
     * @return Index number
     */
    public static int indexOfJuniorArraylist(String accNO) {
        Database db = Database.getInstance();
        for (JuniorAccount juniorAccount : db.juniorAccountArrayList) {
            if (juniorAccount.getAccountNo() == Integer.parseInt(accNO)) {
                return db.juniorAccountArrayList.indexOf(juniorAccount);
            }
        }
        return -1;
    }

    /**
     * Get this account's index number in saver account ArrayList
     *
     * @param accNO Account number
     * @return Index number
     */
    public static int indexOfSaverArraylist(String accNO) {
        Database db = Database.getInstance();
        for (SaverAccount saverAccount : db.saverAccountArrayList) {
            if (saverAccount.getAccountNo() == Integer.parseInt(accNO)) {
                return db.saverAccountArrayList.indexOf(saverAccount);
            }
        }
        return -1;
    }

    /*--------------------------------------------- Customer Operations -----------------------------------------------*/

    /**
     * Add a customer to the database according to the supplied information
     *
     * @param customerID Customer's ID number
     * @param name Customer's name
     * @param address Customer's address
     * @param dateOfBirth Customer' birthday in yyy-MM-dd
     * @return Created Customer class
     */
    public static Customer addCustomer(int customerID, String name, String address, String dateOfBirth) {
        Database db = Database.getInstance();
        Customer customer = new Customer(customerID, name, address, dateOfBirth);
        // 判断在customerArrayList中有没有
        for (Customer customer1 : db.customerArrayList) {
            if (customer1.getCustomerID() == customerID) {
                return customer;
            }
        }

        db.customerArrayList.add(customer);
        return customer;
    }

    /**
     * Get customer's index number in customer array list
     *
     * @param customerID Customer ID
     * @return Index number
     */
    public static int indexOfCustomerArraylist(String customerID) {
        Database db = Database.getInstance();
        for (Customer customer : db.customerArrayList) {
            if (customer.getCustomerID() == Integer.parseInt(customerID)) {
                return db.customerArrayList.indexOf(customer);
            }
        }
        return -1;
    }

    /**
     * Set customer credit status in the whole database
     *
     * @param customerID Customer ID
     * @param status Credit status you want to set
     */
    public static void setCustomerCreditStatus(String customerID, boolean status) {
        Database db = Database.getInstance();
        int custID = Integer.parseInt(customerID);
        for (Customer customer:db.customerArrayList) {
            if (customer.getCustomerID() == custID) {
                customer.setCreditStatus(status);
            }
        }

        for (BankAccount bankAccount:db.bankAccountArrayList) {
            if (bankAccount.getCustomer().getCustomerID() == custID) {
                bankAccount.getCustomer().setCreditStatus(status);
            }
        }

        for (CurrentAccount currentAccount:db.currentAccountArrayList) {
            if (currentAccount.getCustomer().getCustomerID() == custID) {
                currentAccount.getCustomer().setCreditStatus(status);
            }
        }

        for (JuniorAccount juniorAccount:db.juniorAccountArrayList) {
            if (juniorAccount.getCustomer().getCustomerID() == custID) {
                juniorAccount.getCustomer().setCreditStatus(status);
            }
        }

        for (SaverAccount saverAccount:db.saverAccountArrayList) {
            if (saverAccount.getCustomer().getCustomerID() == custID) {
                saverAccount.getCustomer().setCreditStatus(status);
            }
        }
    }

    /**
     * Check if this customer is in the database
     *
     * @param customerID Customer ID
     * @return If this customer is in the data base
     */
    public  static boolean thereIsACustomer(String customerID) {
        Database db = Database.getInstance();
        for (Customer customer : db.customerArrayList) {
            if (customer.getCustomerID() == Integer.parseInt(customerID)) {
                return true;
            }
        }
        return false;
    }

    /*------------------------------------------- Bank Accounts Operations --------------------------------------------*/

    /**
     * Open an account and put it into the database
     *
     * @param customer The customer wants to create an account
     * @param accountType Account type
     * @param amount Amount of balance you want to add to your account
     * @return Created bank account class
     */
    public static BankAccount openBankAccount(Customer customer, String accountType, String amount) {
        if (BankController.accountTypeToInt(accountType) == 0) {
            return BankController.openCurrentAccount(customer, amount);
        } else if (BankController.accountTypeToInt(accountType) == 1) {
            return BankController.openJuniorAccount(customer, amount);
        } else if (BankController.accountTypeToInt(accountType) == 2) {
            return BankController.openSaverAccount(customer, amount);
        }
        AlertController.errorAlert("ERROR", "ERROR!", "No account has been opened!!");
        return null;
    }

    /**
     * Open an account and put it into the database
     *
     * @param customer The customer wants to create an account
     * @param amount Amount of balance you want to add to your account
     * @return Created current account class
     */
    public static CurrentAccount openCurrentAccount(Customer customer, String amount) {
        Database db = Database.getInstance();
        CurrentAccount currentAccount = new CurrentAccount(customer, 1000.0);
        currentAccount.addBalance(Double.parseDouble(amount));
        db.bankAccountArrayList.add(currentAccount);
        db.currentAccountArrayList.add(currentAccount);
        return currentAccount;
    }

    /**
     * Open an account and put it into the database
     *
     * @param customer The customer wants to create an account
     * @param amount Amount of balance you want to add to your account
     * @return Created junior account class
     */
    public static JuniorAccount openJuniorAccount(Customer customer, String amount) {
        Database db = Database.getInstance();
        JuniorAccount juniorAccount = new JuniorAccount(customer);
        juniorAccount.addBalance(Double.parseDouble(amount));
        db.bankAccountArrayList.add(juniorAccount);
        db.juniorAccountArrayList.add(juniorAccount);
        return juniorAccount;
    }

    /**
     * Open an account and put it into the database
     *
     * @param customer The customer wants to create an account
     * @param amount Amount of balance you want to add to your account
     * @return Created saver account class
     */
    public static SaverAccount openSaverAccount(Customer customer, String amount) {
        Database db = Database.getInstance();
        SaverAccount saverAccount = new SaverAccount(customer);
        saverAccount.addBalance(Double.parseDouble(amount));
        db.bankAccountArrayList.add(saverAccount);
        db.saverAccountArrayList.add(saverAccount);
        return saverAccount;
    }

    /**
     * Remove this account form the database
     *
     * @param accNO Account number
     */
    public static void closeBankAccount(String accNO) {
        switch (BankController.accountTypeChecking(accNO)) {
            case 0:
                BankController.closeCurrentAccount(accNO);
                break;
            case 1:
                BankController.closeJuniorAccount(accNO);
                break;
            case 2:
                BankController.closeSaverAccount(accNO);
                break;
            default:
                AlertController.errorAlert("ERROR", "ERROR", "Didn't delete any account");
        }
    }

    /**
     * Remove this current account form the database
     *
     * @param accNO Account number
     */
    public static void closeCurrentAccount(String accNO) {
        Database db = Database.getInstance();
        db.bankAccountArrayList.remove(BankController.indexOfBankArraylist(accNO));
        db.currentAccountArrayList.remove(BankController.indexOfCurrentArraylist(accNO));
    }

    /**
     * Remove this junior account form the database
     *
     * @param accNO Account number
     */
    public static void closeJuniorAccount(String accNO) {
        Database db = Database.getInstance();
        db.bankAccountArrayList.remove(BankController.indexOfBankArraylist(accNO));
        db.juniorAccountArrayList.remove(BankController.indexOfJuniorArraylist(accNO));
    }

    /**
     * Remove this saver account form the database
     *
     * @param accNO Account number
     */
    public static void closeSaverAccount(String accNO) {
        Database db = Database.getInstance();
        db.bankAccountArrayList.remove(BankController.indexOfBankArraylist(accNO));
        db.saverAccountArrayList.remove(BankController.indexOfSaverArraylist(accNO));
    }

    /**
     * Suspend this account, set this account's activity into false
     *
     * @param accNO Account number
     */
    public static void suspendAccount(String accNO) {
        Database db = Database.getInstance();
        db.bankAccountArrayList.get(BankController.indexOfBankArraylist(accNO)).setActive(false);
        switch (BankController.accountTypeChecking(accNO)) {
            case 0:
                db.currentAccountArrayList.get(BankController.indexOfCurrentArraylist(accNO)).setActive(false);
                break;
            case 1:
                db.juniorAccountArrayList.get(BankController.indexOfJuniorArraylist(accNO)).setActive(false);
                break;
            case 2:
                db.saverAccountArrayList.get(BankController.indexOfSaverArraylist(accNO)).setActive(false);
                break;
            default:
                AlertController.errorAlert("ERROR", "ERROR", "ERROR IN BankController.suspendAccount()");
        }
    }

    /**
     * Reinstate this account, set this account's activity into true
     *
     * @param accNO Account number
     */
    public static void reinstateAccount(String accNO) {
        Database db = Database.getInstance();
        db.bankAccountArrayList.get(BankController.indexOfBankArraylist(accNO)).setActive(true);
        switch (BankController.accountTypeChecking(accNO)) {
            case 0:
                db.currentAccountArrayList.get(BankController.indexOfCurrentArraylist(accNO)).setActive(true);
                break;
            case 1:
                db.juniorAccountArrayList.get(BankController.indexOfJuniorArraylist(accNO)).setActive(true);
                break;
            case 2:
                db.saverAccountArrayList.get(BankController.indexOfSaverArraylist(accNO)).setActive(true);
                break;
            default:
                AlertController.errorAlert("ERROR", "ERROR", "ERROR IN BankController.reinstateAccount()");
        }
    }

    /*----------------------------------------------- Funds Operations ------------------------------------------------*/

    /**
     * Deposit a certain amount of cleared funds from this account
     *
     * @param accNO Account number
     * @param amount Amount of money
     */
    public static void depositFunds_Cl(String accNO, String amount) {
        switch (BankController.accountTypeChecking(accNO)) {
            case 0:
                BankController.depositCurrentFunds_Cl(accNO, amount);
                break;
            case 1:
                BankController.depositJuniorFunds_Cl(accNO, amount);
                break;
            case 2:
                BankController.depositSaverFunds_Cl(accNO, amount);
                break;
            default:
                AlertController.errorAlert("ERROR", "ERROR", "ERROR IN BankController.depositFunds_Cl()");
        }
    }

    /**
     * Deposit a certain amount of cleared funds from this current account
     *
     * @param accNO Account number
     * @param amount Amount of money
     */
    public static void depositCurrentFunds_Cl(String accNO, String amount) {
        Database db = Database.getInstance();
        db.bankAccountArrayList.get(BankController.indexOfBankArraylist(accNO)).addBalance(Double.parseDouble(amount));
        db.currentAccountArrayList.get(BankController.indexOfCurrentArraylist(accNO)).addBalance(Double.parseDouble(amount));
    }

    /**
     * Deposit a certain amount of cleared funds from this junior account
     *
     * @param accNO Account number
     * @param amount Amount of money
     */
    public static void depositJuniorFunds_Cl(String accNO, String amount) {
        Database db = Database.getInstance();
        db.bankAccountArrayList.get(BankController.indexOfBankArraylist(accNO)).addBalance(Double.parseDouble(amount));
        db.juniorAccountArrayList.get(BankController.indexOfJuniorArraylist(accNO)).addBalance(Double.parseDouble(amount));
    }

    /**
     * Deposit a certain amount of cleared funds from this saver account
     *
     * @param accNO Account number
     * @param amount Amount of money
     */
    public static void depositSaverFunds_Cl(String accNO, String amount) {
        Database db = Database.getInstance();
        db.bankAccountArrayList.get(BankController.indexOfBankArraylist(accNO)).addBalance(Double.parseDouble(amount));
        db.saverAccountArrayList.get(BankController.indexOfSaverArraylist(accNO)).addBalance(Double.parseDouble(amount));
    }

    /**
     * Deposit a certain amount of un-cleared funds from this account
     *
     * @param accNO Account number
     * @param amount Amount of money
     */
    public static void depositFunds_UnCl(String accNO, String amount) {
        switch (BankController.accountTypeChecking(accNO)) {
            case 0:
                BankController.depositCurrentFunds_UnCl(accNO, amount);
                break;
            case 1:
                BankController.depositJuniorFunds_UnCl(accNO, amount);
                break;
            case 2:
                BankController.depositSaverFunds_UnCl(accNO, amount);
                break;
            default:
                AlertController.errorAlert("ERROR", "ERROR", "ERROR IN BankController.depositFunds_UnCl()");
        }
    }

    /**
     * Deposit a certain amount of un-cleared funds from this current account
     *
     * @param accNO Account number
     * @param amount Amount of money
     */
    public static void depositCurrentFunds_UnCl(String accNO, String amount) {
        Database db = Database.getInstance();
        db.bankAccountArrayList.get(BankController.indexOfBankArraylist(accNO)).addUnClearedBalance(Double.parseDouble(amount));
        db.currentAccountArrayList.get(BankController.indexOfCurrentArraylist(accNO)).addUnClearedBalance(Double.parseDouble(amount));
    }

    /**
     * Deposit a certain amount of un-cleared funds from this junior account
     *
     * @param accNO Account number
     * @param amount Amount of money
     */
    public static void depositJuniorFunds_UnCl(String accNO, String amount) {
        Database db = Database.getInstance();
        db.bankAccountArrayList.get(BankController.indexOfBankArraylist(accNO)).addUnClearedBalance(Double.parseDouble(amount));
        db.juniorAccountArrayList.get(BankController.indexOfJuniorArraylist(accNO)).addUnClearedBalance(Double.parseDouble(amount));
    }

    /**
     * Deposit a certain amount of un-cleared funds from this saver account
     *
     * @param accNO Account number
     * @param amount Amount of money
     */
    public static void depositSaverFunds_UnCl(String accNO, String amount) {
        Database db = Database.getInstance();
        db.bankAccountArrayList.get(BankController.indexOfBankArraylist(accNO)).addUnClearedBalance(Double.parseDouble(amount));
        db.saverAccountArrayList.get(BankController.indexOfSaverArraylist(accNO)).addUnClearedBalance(Double.parseDouble(amount));
    }

    /**
     * Clear all the un-cleared funds in the whole database
     */
    public static void clearFunds() {
        Database db = Database.getInstance();
        for (BankAccount bankAccount:db.bankAccountArrayList) {
            Double unclearedBalance = bankAccount.getUnClearedBalance();
            if (unclearedBalance != 0.0) {
                bankAccount.setUnClearedBalance(0.0);
                bankAccount.addBalance(unclearedBalance);
            }
        }

        for (CurrentAccount currentAccount:db.currentAccountArrayList) {
            Double unclearedBalance = currentAccount.getUnClearedBalance();
            if (unclearedBalance != 0.0) {
                currentAccount.setUnClearedBalance(0.0);
                currentAccount.addBalance(unclearedBalance);
            }
        }

        for (JuniorAccount juniorAccount:db.juniorAccountArrayList) {
            Double unclearedBalance = juniorAccount.getUnClearedBalance();
            if (unclearedBalance != 0.0) {
                juniorAccount.setUnClearedBalance(0.0);
                juniorAccount.addBalance(unclearedBalance);
            }
        }

        for (SaverAccount saverAccount:db.saverAccountArrayList) {
            Double unclearedBalance = saverAccount.getUnClearedBalance();
            if (unclearedBalance != 0.0) {
                saverAccount.setUnClearedBalance(0.0);
                saverAccount.addBalance(unclearedBalance);
            }
        }
    }

    /**
     * Withdraw a certain amount of funds from this current or junior account
     *
     * @param accNO Account number
     * @param amount Amount of money
     * @return The BankAccount class that has been operated
     */
    public static BankAccount withdrawFunds_01(String accNO, String amount) {
        System.out.println("withdrawFunds_01");
        Database db = Database.getInstance();
        int index;
        BankAccount bankAccount;
        switch (BankController.accountTypeChecking(accNO)) {
            case 0:
                index = BankController.indexOfBankArraylist(accNO);
                bankAccount = db.bankAccountArrayList.get(index);
                bankAccount.setBalance(bankAccount.getBalance() - Double.parseDouble(amount));

                index = BankController.indexOfCurrentArraylist(accNO);
                bankAccount = db.currentAccountArrayList.get(index);
                bankAccount.setBalance(bankAccount.getBalance() - Double.parseDouble(amount));
                return bankAccount;
            case 1:
                index = BankController.indexOfBankArraylist(accNO);
                bankAccount = db.bankAccountArrayList.get(index);
                bankAccount.setBalance(bankAccount.getBalance() - Double.parseDouble(amount));

                index = BankController.indexOfJuniorArraylist(accNO);
                bankAccount = db.juniorAccountArrayList.get(index);
                bankAccount.setBalance(bankAccount.getBalance() - Double.parseDouble(amount));
                return bankAccount;
            default:
                AlertController.errorAlert("ERROR", "ERROR", "ERROR IN BankController.withdrawFunds_01()");
        }
        AlertController.errorAlert("ERROR", "ERROR", "ERROR IN BankController.withdrawFunds_01()");
        return null;
    }

    /**
     * Withdraw a certain amount of funds from this saver account
     *
     * @param accNO Account number
     * @param amount Amount of money
     * @return The BankAccount class that has been operated
     */
    public static BankAccount withdrawFunds_2(String accNO, String amount) {
        System.out.println("withdrawFunds_2\n");
        Database db = Database.getInstance();
        int index;
        BankAccount bankAccount;

        index = BankController.indexOfBankArraylist(accNO);
        bankAccount = db.bankAccountArrayList.get(index);
        bankAccount.setBalance(db.bankAccountArrayList.get(index).getBalance() - Double.parseDouble(amount));

        index = BankController.indexOfSaverArraylist(accNO);
        bankAccount = db.saverAccountArrayList.get(index);
        bankAccount.setBalance(db.saverAccountArrayList.get(index).getBalance() - Double.parseDouble(amount));
        return bankAccount;
    }

    /*------------------------------------------- Appointment Operations ----------------------------------------------*/

    /**
     * Add an appointment for this saver account according to the current time
     *
     * @param accNO Account number
     * @return Created SaverAppointment class
     */
    public static SaverAppointment addAnSavAppt(String accNO) {
        Database db = Database.getInstance();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 7);
        SaverAppointment saverAppointment = new SaverAppointment(Integer.parseInt(accNO), calendar);
        db.saverAppointmentArrayList.add(saverAppointment);
        return saverAppointment;
    }

    /**
     * Remove this appointment according to the account number
     *
     * @param accNO Account number
     */
    public static void removeAnSavAppt(String accNO) {
        Database db = Database.getInstance();
        db.saverAppointmentArrayList.remove(BankController.indexOfSavApptArraylist(accNO));
    }

    /**
     * Get the index of the certain appointment according to the account number
     *
     * @param accNO Account number
     * @return Index number
     */
    public static int indexOfSavApptArraylist(String accNO) {
        Database db = Database.getInstance();
        for (SaverAppointment saverAppointment : db.saverAppointmentArrayList) {
            if (saverAppointment.getAccountNo() == Integer.parseInt(accNO)) {
                return db.saverAppointmentArrayList.indexOf(saverAppointment);
            }
        }
        return -1;
    }

    /**
     * Check if this account has an appointment in database according to the supplied account number
     *
     * @param accNO Account number
     * @return If there is an appointment of this account
     */
    public static boolean thereIsAnAppt(String accNO) {
        Database db = Database.getInstance();
        for (SaverAppointment saverAppointment : db.saverAppointmentArrayList) {
            if (saverAppointment.getAccountNo() == Integer.parseInt(accNO)) {
                return true;
            }
        }
        return false;
    }

    /*----------------------------------------------- Other functions -------------------------------------------------*/

    /**
     * Get your age depending on your birthday
     *
     * @param birthDay Birthday
     * @return Your age
     */
    public static int getAge(Date birthDay) {
        Calendar cal = Calendar.getInstance();

        if (cal.before(birthDay)) {
            throw new IllegalArgumentException(
                    "The birthDay is before Now.It's unbelievable!");
        }
        int yearNow = cal.get(Calendar.YEAR);
        int monthNow = cal.get(Calendar.MONTH);
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
        cal.setTime(birthDay);

        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH);
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

        int age = yearNow - yearBirth;

        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth) {
                if (dayOfMonthNow < dayOfMonthBirth) age--;
            } else {
                age--;
            }
        }
        return age;
    }
}
