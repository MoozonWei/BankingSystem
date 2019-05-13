/**
 * Database class
 * This class only has one instance
 *
 * @authur Zihan WEI
 */

import java.util.ArrayList;

public class Database {
    private static Database instance;
    ArrayList<BankAccount> bankAccountArrayList;
    ArrayList<CurrentAccount> currentAccountArrayList;
    ArrayList<JuniorAccount> juniorAccountArrayList;
    ArrayList<SaverAccount> saverAccountArrayList;
    ArrayList<Customer> customerArrayList;
    ArrayList<SaverAppointment> saverAppointmentArrayList;

    /**
     * Constructor
     * Read all the data from .json file
     */
    public Database() {
        bankAccountArrayList = ListJsonSwitch.JsonToBankAccount();
        currentAccountArrayList = ListJsonSwitch.JsonToCurrentAccount();
        juniorAccountArrayList = ListJsonSwitch.JsonToJuniorAccount();
        saverAccountArrayList = ListJsonSwitch.JsonToSaverAccount();
        customerArrayList = ListJsonSwitch.JsonToCustomer();
        saverAppointmentArrayList = ListJsonSwitch.JsonToSavAppt();
    }

    /**
     * Get instance
     *
     * @return Return the only instance of this class
     */
    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    /**
     * Read all the data from .json file
     */
    public static void dbRead() {
        instance.bankAccountArrayList = ListJsonSwitch.JsonToBankAccount();
        instance.currentAccountArrayList = ListJsonSwitch.JsonToCurrentAccount();
        instance.juniorAccountArrayList = ListJsonSwitch.JsonToJuniorAccount();
        instance.saverAccountArrayList = ListJsonSwitch.JsonToSaverAccount();
        instance.customerArrayList = ListJsonSwitch.JsonToCustomer();
        instance.saverAppointmentArrayList = ListJsonSwitch.JsonToSavAppt();
    }

    /**
     * Write all the data into .json file
     */
    public static void dbWrite() {
        ListJsonSwitch.BankAccountToJson(instance.bankAccountArrayList);
        ListJsonSwitch.CurrentAccountToJson(instance.currentAccountArrayList);
        ListJsonSwitch.JuniorAccountToJson(instance.juniorAccountArrayList);
        ListJsonSwitch.SaverAccountToJson(instance.saverAccountArrayList);
        ListJsonSwitch.CustomerToJson(instance.customerArrayList);
        ListJsonSwitch.SavApptToJson(instance.saverAppointmentArrayList);
    }
}
