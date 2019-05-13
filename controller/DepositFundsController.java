/**
 * GUI controller
 * FXML: DepositFunds.fxml
 *
 * @authur Zihan WEI
 */

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DepositFundsController {

    Database db = Database.getInstance();
    @FXML
    private TextField anTF, amTF;
    @FXML
    private ChoiceBox<String> fundsType;

    /**
     * GUI initialization
     */
    public void initialize() {
        System.out.println("Deposit is now loaded!");
        fundsType.setItems(FXCollections.observableArrayList("Cleared Funds", "Un-Cleared Funds"));
        fundsType.setValue("Cleared Funds");
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
                AlertController.errorAlert("Invalid", "Invalid Information", "Please complete all the text fields!!");
                break;
            case 3:
                AlertController.errorAlert("Invalid", "Invalid Operation", "This is a suspended account!!");
                break;
            case 4:
                BankController.depositFunds_Cl(anTF.getText(), amTF.getText());
                Stage stage = ((Stage) anTF.getScene().getWindow());
                stage.close();
                String tmpStr;
                BankAccount bankAccount = db.bankAccountArrayList.get(BankController.indexOfBankArraylist(anTF.getText()));
                if (bankAccount.getBalance() >= 0) {
                    tmpStr = "Balance: " + bankAccount.getBalance() + "\n" +
                            "Overdraft Usage: 0.0/" + bankAccount.getOverDraftLimit() + "\n" +
                            "Un-Cleared Balance: " + bankAccount.getUnClearedBalance();
                } else {
                    tmpStr = "Balance: 0.0" +
                            "Overdraft Usage: " + (0.0 - bankAccount.getBalance()) + "/" + bankAccount.getOverDraftLimit() + "\n" +
                            "Un-Cleared Balance: " + bankAccount.getUnClearedBalance();
                }
                AlertController.informationAlert("Success", "Deposit Success", tmpStr);
                db.dbWrite();
                break;
            case 5:
                BankController.depositFunds_UnCl(anTF.getText(), amTF.getText());
                stage = ((Stage) anTF.getScene().getWindow());
                stage.close();
                bankAccount = db.bankAccountArrayList.get(BankController.indexOfBankArraylist(anTF.getText()));
                if (bankAccount.getBalance() >= 0) {
                    tmpStr = "Balance: " + bankAccount.getBalance() + "\n" +
                            "Overdraft Usage: 0.0/" + bankAccount.getOverDraftLimit() + "\n" +
                            "Un-Cleared Balance: " + bankAccount.getUnClearedBalance();
                } else {
                    tmpStr = "Balance: 0.0" +
                            "Overdraft Usage: " + (0.0 - bankAccount.getBalance()) + "/" + bankAccount.getOverDraftLimit() + "\n" +
                            "Un-Cleared Balance: " + bankAccount.getUnClearedBalance();
                }
                AlertController.informationAlert("Success", "Deposit Success", tmpStr);
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
                if ((anTF.getText().length() != 0) && (amTF.getText().length() != 0)) {
                    if (BankController.accountActiveChecking(anTF.getText())) {
                        if (fundsType.getValue().equals("Un-Cleared Funds")) {
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
