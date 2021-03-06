package GUI;

import javafx.scene.control.Alert;
import javafx.stage.Window;

//klasa, która odpowiada za wyświetlanie prostych alertów (error czy informacja dla użytkownika)
public class AlertHelper {

    public static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }
}