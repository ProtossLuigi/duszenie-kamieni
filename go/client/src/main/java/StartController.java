import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Window;

public class StartController {
    @FXML
    private BorderPane layout;
    @FXML
    private Button start;
    @FXML
    private Button confirm;
    @FXML
    private Button reset;
    @FXML
    private Pane board;

    @FXML
    protected void handleStart(ActionEvent event){
        Window owner = layout.getScene().getWindow();
        AlertHelper.showAlert(Alert.AlertType.INFORMATION,owner,"Start","Start was clicked");
    }

    @FXML
    protected void handleConfirm(ActionEvent event){
        Window owner = layout.getScene().getWindow();
        AlertHelper.showAlert(Alert.AlertType.INFORMATION,owner,"Confirm","Confirm was clicked");
    }

    @FXML
    protected void handleReset(ActionEvent event){
        Window owner = layout.getScene().getWindow();
        AlertHelper.showAlert(Alert.AlertType.INFORMATION,owner,"Reset","Reset was clicked");
    }
}
