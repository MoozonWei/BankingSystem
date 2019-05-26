/**
 * GUI controller
 * FXML: CloseAccount.fxml
 *
 * @authur Zihan WEI
 */

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CloseAccountController {

    @FXML
    private TextField anTF;
    @FXML
    private PasswordField pwTF;
    Database db = Database.getInstance();

    /**
     * GUI initialization
     */
    public void initialize() {
        System.out.println("Close-account View is now loaded!");
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
                AlertController.errorAlert("Invalid", "Invalid Information", "Please input password!!");
                break;
            case 3:
                AlertController.errorAlert("Invalid", "Wrong", "Wrong password!!");
                break;
            case 4:
                AlertController.errorAlert("Invalid", "Invalid Operation", "You have an unpaid debt!!");
                break;
            case 5:
                AlertController.errorAlert("Invalid", "Invalid Operation", "You still have some funds(cleared/un-cleared) in this account!!");
                break;
            case 6:

                if (AlertController.confirmationAlert("Confirmation", "Close Confirmation", "Do you want to close this account?")) {
                    BankController.closeBankAccount(anTF.getText());
                    Stage stage = ((Stage) anTF.getScene().getWindow());
                    stage.close();
                    AlertController.informationAlert("Success", "Success", "Account has been closed!!");
                    db.dbWrite();
                }
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
                if (pwTF.getText().length() != 0) {
                    if (BankController.accountPasswdChecking(anTF.getText(), pwTF.getText())) {
                        if (BankController.accountDebtChecking(anTF.getText())) {
                            if (BankController.accountBalanceChecking(anTF.getText())) {
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
