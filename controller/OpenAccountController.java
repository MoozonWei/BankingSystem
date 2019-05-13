/**
 * GUI controller
 * FXML: OpenAccount.fxml
 *
 * @authur Zihan WEI
 */

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.ChoiceBox;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OpenAccountController {

    Database db = Database.getInstance();
    String tempString = "";
    @FXML
    private TextField idTF, nameTF, addressTF, dobTF, amTF;
    @FXML
    private ChoiceBox<String> accountType;

    /**
     * GUI initialization
     */
    public void initialize() {
        System.out.println("Open-account view is now loaded!");
        accountType.setItems(FXCollections.observableArrayList("Current Account", "Junior Account", "Saver Account"));
        accountType.setValue("Current Account");
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
                AlertController.errorAlert("Invalid", "Invalid Information", "Please complete all the text fields!!");
                break;
            case 2:
                AlertController.errorAlert("Invalid", "Invalid Operation", "Your have a bad credit now!!\nPlease pay all of your debts!!");
                Stage stage = ((Stage) idTF.getScene().getWindow());
                stage.close();
                break;
            case 3:
                AlertController.errorAlert("Invalid", "Invalid Operation", "Amount cannot be less than 20!!");
                break;
            case 4:
                AlertController.errorAlert("Invalid", "Invalid Operation", "Only people under the age of 16 can open this account!!");
                break;
            case 5:
                Customer customer = BankController.addCustomer(Integer.parseInt(idTF.getText()), nameTF.getText(), addressTF.getText(), dobTF.getText());
                BankAccount bankAccount = BankController.openBankAccount(customer, accountType.getValue(), amTF.getText());
                tempString = "Account Number: " + bankAccount.getAccountNo() + "\n" +
                        "Password: " + bankAccount.getPin() + "\n" +
                        "Account Type: " + BankController.accountTypeToString(bankAccount.getAccountType()) + "\n" +
                        "Balance: " + bankAccount.getBalance();
                stage = ((Stage) idTF.getScene().getWindow());
                stage.close();
                AlertController.informationAlert("Success", "Success", tempString);
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
        if (BankController.accountNOChecking(idTF.getText())) {
            if ((nameTF.getText().length() != 0) && (addressTF.getText().length() != 0) && (dobTF.getText().length() != 0) && (amTF.getText().length() != 0)) {
                if (!BankController.thereIsACustomer(idTF.getText()) || db.customerArrayList.get(BankController.indexOfCustomerArraylist(idTF.getText())).isCreditStatus()) {
                    if (Double.parseDouble(amTF.getText()) >= 20.0) {
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        Date date = null;
                        try {
                            date = sdf.parse(dobTF.getText());
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        if ((accountType.getValue().equals("Junior Account") && BankController.getAge(date) <= 16) || !(accountType.getValue().equals("Junior Account"))) {
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
