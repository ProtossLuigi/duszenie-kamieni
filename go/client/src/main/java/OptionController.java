import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.Window;

public class OptionController {
    public RadioButton board9;
    public RadioButton board13;
    public RadioButton board19;
    public Button buttonBot;
    public Button buttonPlayer;
    public ToggleGroup group;
    public TextField fieldAddress;


    public void buttonPlayer() throws Exception {
        Stage owner = (Stage) board9.getScene().getWindow();
        owner.close();


        WindowApp windowApp = new WindowApp();
        windowApp.start(((RadioButton)group.getSelectedToggle()).getId(),fieldAddress.getText());
    }

    public void buttonBot(ActionEvent actionEvent) {
        Window owner = board9.getScene().getWindow();
        AlertHelper.showAlert(Alert.AlertType.INFORMATION,owner,"Start",((RadioButton)group.getSelectedToggle()).getId());
    }
}
