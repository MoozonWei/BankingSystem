/**
 * GUI controller
 * FXML: ReinstateAccount.fxml
 *
 * @authur Zihan WEI
 */

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ReinstateAccountController {
    Database db = Database.getInstance();
    @FXML
    private TextField anTF;

    /**
     * GUI initialization
     */
    public void initialize() {
        System.out.println("Suspend View is now loaded!");
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
                AlertController.errorAlert("Invalid", "Invalid Operation", "This account is already been activated!!");
                break;
            case 3:
                BankController.reinstateAccount(anTF.getText());
                db.dbWrite();
                AlertController.informationAlert("Success", "Success", "Account " + anTF.getText() + " has been activated.");
                Stage stage = ((Stage)anTF.getScene().getWindow());
                stage.close();
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
                if (!BankController.accountActiveChecking(anTF.getText())) {
                    return 3;
                }
                return 2;
            }
            return 1;
        }
        return 0;
    }
}
