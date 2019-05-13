/**
 * JavaFX alert view creation
 *
 * @authur Zihan WEI
 */

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class AlertController {

    /**
     * Information alert
     *
     * @param title Title of this alert
     * @param header Header of this alert
     * @param content Content of this alert
     */
    public static void informationAlert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    /**
     * Error alert
     *
     * @param title Title of this alert
     * @param header Header of this alert
     * @param content Content of this alert
     */
    public static void errorAlert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    /**
     * Confirmation alert
     *
     * @param title Title of this alert
     * @param header Header of this alert
     * @param content Content of this alert
     * @return True(Yes) or False(No)
     */
    public static boolean confirmationAlert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            return true;
        } else {
            return false;
        }
    }

    /**
     * Warning alert
     *
     * @param title Title of this alert
     * @param header Header of this alert
     * @param content Content of this alert
     */
    public static void warningAlert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

}
