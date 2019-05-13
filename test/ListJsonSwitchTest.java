import java.util.ArrayList;
import java.util.Calendar;

public class ListJsonSwitchTest {
    public static void main(String[] args) {
//        Customer customer1 = new Customer(000000, "God", "sky", "1998-02-03");
//        CurrentAccount currentAccount1 = new CurrentAccount(customer1,1000.0);
//        JuniorAccount juniorAccount1 = new JuniorAccount(customer1);
//        SaverAccount saverAccount1 = new SaverAccount(customer1);
//
//        ArrayList<Customer> customerArrayList = new ArrayList<>();
//        ArrayList<BankAccount> bankAccountArrayList = new ArrayList<>();
//        ArrayList<CurrentAccount> currentAccountArrayList = new ArrayList<>();
//        ArrayList<JuniorAccount> juniorAccountArrayList = new ArrayList<>();
//        ArrayList<SaverAccount> saverAccountArrayList = new ArrayList<>();
//
//        customerArrayList.add(customer1);
//        currentAccountArrayList.add(currentAccount1);
//        juniorAccountArrayList.add(juniorAccount1);
//        saverAccountArrayList.add(saverAccount1);
//        bankAccountArrayList.add(currentAccount1);
//        bankAccountArrayList.add(juniorAccount1);
//        bankAccountArrayList.add(saverAccount1);
//
//        ListJsonSwitch.CustomerToJson(customerArrayList);
//        ListJsonSwitch.BankAccountToJson(bankAccountArrayList);
//        ListJsonSwitch.CurrentAccountToJson(currentAccountArrayList);
//        ListJsonSwitch.JuniorAccountToJson(juniorAccountArrayList);
//        ListJsonSwitch.SaverAccountToJson(saverAccountArrayList);

        // Appointment
        SaverAppointment saverAppointment = new SaverAppointment(100000, Calendar.getInstance());
        ArrayList<SaverAppointment> saverAppointmentArrayList = new ArrayList<>();
        saverAppointmentArrayList.add(saverAppointment);
        ListJsonSwitch.SavApptToJson(saverAppointmentArrayList);

    }
}
