/**
 * GUI controller
 * FXML: Withdraw.fxml
 *
 * @authur Zihan WEI
 */

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.Calendar;

public class WithdrawFundsController {

    String tmpText;
    Database db = Database.getInstance();

    @FXML
    private TextField anTF, amTF, pinTF;

    /**
     * GUI initialization
     */
    public void initialize() {
        System.out.println("Withdraw View is now loaded!");
    }

    /**
     * Submit button action
     */
    public void submit() {
        switch (checking()) {
            case 0:
                AlertController.errorAlert("Invalid", "Invalid Information", "ID should be 6 digits!!");
                break;
            case 1:
                AlertController.errorAlert("Invalid", "Invalid Information", "This account doesn't exist!!");
                break;
            case 2:
                AlertController.errorAlert("Invalid", "Invalid Information", "Please complete all the fields!!");
                break;
            case 3:
                AlertController.errorAlert("Invalid", "Wrong", "Wrong password!!");
                break;
            case 4:
                AlertController.errorAlert("Invalid", "Invalid Operation", "This is a suspended account!!");
                break;
            case 5:
                AlertController.errorAlert("Invalid", "Invalid Operation", "This account doesn't have so much money!!");
                break;
            case 6:
                BankAccount bankAccount = BankController.withdrawFunds_01(anTF.getText(), amTF.getText());
                Stage stage = ((Stage) anTF.getScene().getWindow());
                stage.close();
                if (bankAccount.getBalance() >= 0) {
                    tmpText = "Balance: " + bankAccount.getBalance() + "\n" +
                            "Overdraft Usage: 0.0/" + bankAccount.getOverDraftLimit() + "\n" +
                            "Un-Cleared Balance: " + bankAccount.getUnClearedBalance();
                } else {
                    tmpText = "Balance: 0.0" + "\n" +
                            "Overdraft Usage: " + (0.0 - bankAccount.getBalance()) + "/" + bankAccount.getOverDraftLimit() + "\n" +
                            "Un-Cleared Balance: " + bankAccount.getUnClearedBalance();
                    String customerID = "" + db.bankAccountArrayList.get(BankController.indexOfBankArraylist(anTF.getText())).getCustomer().getCustomerID();
                    BankController.setCustomerCreditStatus(customerID, false);
                }

//                if (bankAccount.getBalance() >= 0) {
//                    tmpText = "Balance: " + bankAccount.getBalance() + "\n" + "Overdraft Usage: 0.0/" + bankAccount.getOverDraftLimit();
//                }else {
//                    tmpText = "Balance: 0.0" + "\n" + "Overdraft Usage: " + (0.0 - bankAccount.getBalance()) + "/" + bankAccount.getOverDraftLimit();
//                    String customerID = "" + db.bankAccountArrayList.get(BankController.indexOfBankArraylist(anTF.getText())).getCustomer().getCustomerID();
//                    BankController.setCustomerCreditStatus(customerID, false);
//                }
                AlertController.informationAlert("Success", "Success", tmpText);
                db.dbWrite();
                break;
            case 7: // no appointment
                if(AlertController.confirmationAlert("Confirmation", "Appointment Confirmation", "Make an appointment for you?")) {
                    SaverAppointment saverAppointment = BankController.addAnSavAppt(anTF.getText());
                    AlertController.informationAlert("Information", "Appointment Information", "Account NO: " + saverAppointment.getAccountNo() + "\n" +
                            "You can withdraw money after " + saverAppointment.getDateLimitString());
                }
                stage = ((Stage) anTF.getScene().getWindow());
                stage.close();
                db.dbWrite();
                break;
            case 8: // there is an appointment
                SaverAppointment saverAppointment = db.saverAppointmentArrayList.get(BankController.indexOfSavApptArraylist(anTF.getText()));
                if (Calendar.getInstance().after(saverAppointment.getDateLimitCalendar())){
                    // after limit time
                    BankController.removeAnSavAppt(anTF.getText());
                    BankAccount bankAccount1 = BankController.withdrawFunds_2(anTF.getText(), amTF.getText());
                    if (bankAccount1.getBalance() >= 0) {
                        tmpText = "Balance: " + bankAccount1.getBalance() + "\n" + "Overdraft Usage: 0.0/" + bankAccount1.getOverDraftLimit();
                    }else {
                        tmpText = "Balance: 0.0" + "\n" + "Overdraft Usage: " + (0.0 - bankAccount1.getBalance()) + "/" + bankAccount1.getOverDraftLimit();
                    }
                    AlertController.informationAlert("Success", "Success", tmpText);
                }else {
                    // before limit time
                    AlertController.informationAlert("Information", "Appointment Information", "Account NO: " + saverAppointment.getAccountNo() + "\n" +
                            "You can withdraw money after " + saverAppointment.getDateLimitString());
                }
                stage = ((Stage) anTF.getScene().getWindow());
                stage.close();
                db.dbWrite();
                break;
            default:
                AlertController.errorAlert("ERROR", "ERROR", this.toString());
        }
    }

    /**
     * Do most of the checking
     *
     * @return Different situation in an integer number
     */
    public int checking() {
        if (BankController.accountNOChecking(anTF.getText())) {
            if (BankController.accountExistChecking(anTF.getText())) {
                if (pinTF.getText().length() != 0 && amTF.getText().length() != 0) {
                    if (BankController.accountPasswdChecking(anTF.getText(), pinTF.getText())) {
                        if (BankController.accountActiveChecking(anTF.getText())) {
                            int index = BankController.indexOfBankArraylist(anTF.getText());
                            double amount = Double.parseDouble(amTF.getText());
                            double balance = db.bankAccountArrayList.get(index).getBalance();
                            double overDraftLimit = db.bankAccountArrayList.get(index).getOverDraftLimit();
                            if (amount <= (balance + overDraftLimit)) {
                                if (BankController.accountTypeChecking(anTF.getText()) == 2) {
                                    if (BankController.thereIsAnAppt(anTF.getText())){
                                        return 8;
                                    }
                                    return 7;
                                }
                                return 6;
                            }
                            return 5;
                        }
                        return 4;
                    }
                    return 3;
                }
                return 2;
            }
            return 1;
        }
        return 0;
    }

}
