/**
 * Change all the data array list into .json file & change all the .json file into data array list
 *
 * @author Zihan WEI
 */

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class ListJsonSwitch {
    private static String BankAccountFileName = "database\\BankAccount.json";
    private static String CurrentAccountFileName = "database\\CurrentAccount.json";
    private static String JuniorAccountFileName = "database\\JuniorAccount.json";
    private static String SaverAccountFileName = "database\\SaverAccount.json";
    private static String CustomerFileName = "database\\Customer.json";
    private static String SaverAppointmentFileName = "database\\SaverAppointment.json";

    // read & write
    private static void writeStringToJson(String jsonString, String fileName) {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(fileName, false));
            out.write(jsonString);
            out.close();
        } catch (IOException e) {
        }
    }

    private static String readStringFromJson(String fileName) {
        String jsonString = "";
        File file = new File(fileName);
        try {
            FileReader fr = new FileReader(file);
            int ch = 0;
            while ((ch = fr.read()) != -1)
                jsonString += (char) ch;
            fr.close();
        } catch (IOException e) {
        }
        return jsonString;
    }

    // toJson
    public static void BankAccountToJson(ArrayList<BankAccount> bankAccountArrayList) {
        Gson gson = new Gson();
        String jsonString = gson.toJson(bankAccountArrayList);
        writeStringToJson(jsonString, BankAccountFileName);
    }

    public static void CurrentAccountToJson(ArrayList<CurrentAccount> currentAccountArrayList) {
        Gson gson = new Gson();
        String jsonString = gson.toJson(currentAccountArrayList);
        writeStringToJson(jsonString, CurrentAccountFileName);
    }

    public static void JuniorAccountToJson(ArrayList<JuniorAccount> juniorAccountArrayList) {
        Gson gson = new Gson();
        String jsonString = gson.toJson(juniorAccountArrayList);
        writeStringToJson(jsonString, JuniorAccountFileName);
    }

    public static void SaverAccountToJson(ArrayList<SaverAccount> saverAccountArrayList) {
        Gson gson = new Gson();
        String jsonString = gson.toJson(saverAccountArrayList);
        writeStringToJson(jsonString, SaverAccountFileName);
    }

    public static void CustomerToJson(ArrayList<Customer> customerArrayList) {
        Gson gson = new Gson();
        String jsonString = gson.toJson(customerArrayList);
        writeStringToJson(jsonString, CustomerFileName);
    }

    public static void SavApptToJson(ArrayList<SaverAppointment> saverAppointmentArrayList) {
        Gson gson = new Gson();
        String jsonString = gson.toJson(saverAppointmentArrayList);
        writeStringToJson(jsonString, SaverAppointmentFileName);
    }


    // fromJson
    public static ArrayList<BankAccount> JsonToBankAccount() {
        String jsonString = readStringFromJson(BankAccountFileName);
        Type type = new TypeToken<ArrayList<BankAccount>>() {}.getType();
        ArrayList<BankAccount> bankAccountArrayList = new Gson().fromJson(jsonString, type);
        return bankAccountArrayList;
    }

    public static ArrayList<CurrentAccount> JsonToCurrentAccount() {
        String jsonString = readStringFromJson(CurrentAccountFileName);
        Type type = new TypeToken<ArrayList<CurrentAccount>>() {}.getType();
        ArrayList<CurrentAccount> currentAccountArrayList = new Gson().fromJson(jsonString, type);
        return currentAccountArrayList;
    }

    public static ArrayList<JuniorAccount> JsonToJuniorAccount() {
        String jsonString = readStringFromJson(JuniorAccountFileName);
        Type type = new TypeToken<ArrayList<JuniorAccount>>() {}.getType();
        ArrayList<JuniorAccount> juniorAccountArrayList = new Gson().fromJson(jsonString, type);
        return juniorAccountArrayList;
    }

    public static ArrayList<SaverAccount> JsonToSaverAccount() {
        String jsonString = readStringFromJson(SaverAccountFileName);
        Type type = new TypeToken<ArrayList<SaverAccount>>() {}.getType();
        ArrayList<SaverAccount> saverAccountArrayList = new Gson().fromJson(jsonString, type);
        return saverAccountArrayList;
    }

    public static ArrayList<Customer> JsonToCustomer() {
        String jsonString = readStringFromJson(CustomerFileName);
        Type type = new TypeToken<ArrayList<Customer>>() {}.getType();
        ArrayList<Customer> customerArrayList = new Gson().fromJson(jsonString, type);
        return customerArrayList;
    }

    public static ArrayList<SaverAppointment> JsonToSavAppt() {
        String jsonString = readStringFromJson(SaverAppointmentFileName);
        Type type = new TypeToken<ArrayList<SaverAppointment>>() {}.getType();
        ArrayList<SaverAppointment> saverAppointmentArrayList = new Gson().fromJson(jsonString, type);
        return saverAppointmentArrayList;
    }

}