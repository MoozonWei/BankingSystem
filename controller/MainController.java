/**
 * GUI controller
 * FXML: Main.fxml
 *
 * @authur Zihan WEI
 */

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Calendar;

public class MainController {

    /**
     * GUI initialization
     */
    public void initialize() {
        System.out.println("Main View is now loaded!");

        if (Calendar.getInstance().get(Calendar.DAY_OF_MONTH)%2 == 0) {
            BankController.clearFunds();
        }
    }

    /**
     * Open deposit-funds GUI
     */
    public void openDF() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("DepositFunds.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Deposit Funds");
            stage.setScene(new Scene(root, 400, 200));
            stage.show();
        } catch (IOException e) {
            System.out.println("Cannot found file");
        }
    }

    /**
     * Open withdraw-funds GUI
     */
    public void openWF() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("WithdrawFunds.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Withdraw Funds");
            stage.setScene(new Scene(root, 400, 200));
            stage.show();
        } catch (IOException e) {
            System.out.println("Cannot found file");
        }
    }

    /**
     * Open open-account GUI
     */
    public void openOA() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("OpenAccount.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Open Account");
            stage.setScene(new Scene(root, 400, 300));
            stage.show();
        } catch (IOException e) {
            System.out.println("Cannot found file");
        }
    }

    /**
     * Open close-account GUI
     */
    public void openCA() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("CloseAccount.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Close Account");
            stage.setScene(new Scene(root, 400, 200));
            stage.show();
        } catch (IOException e) {
            System.out.println("Cannot found file");
        }
    }

    /**
     * Open suspend-account GUI
     */
    public void openSA() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("SuspendAccount.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Suspend Account");
            stage.setScene(new Scene(root, 400, 200));
            stage.show();
        } catch (IOException e) {
            System.out.println("Cannot found file");
        }
    }

    /**
     * Open reinstate-account GUI
     */
    public void openRA() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("ReinstateAccount.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Reinstate Account");
            stage.setScene(new Scene(root, 400, 200));
            stage.show();
        } catch (IOException e) {
            System.out.println("Cannot found file");
        }
    }

}