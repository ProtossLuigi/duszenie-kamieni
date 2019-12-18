package GUI;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.Window;

public class OptionController implements WindowController {
    public RadioButton board9;
    public RadioButton board13;
    public RadioButton board19;
    public Button buttonBot;
    public Button buttonPlayer;
    public ToggleGroup group;
    public TextField fieldAddress;

    private JavaFXController mainController;

    public void buttonPlayer() throws Exception {

        switch (((RadioButton)group.getSelectedToggle()).getId()) {
            case "board9":
                mainController.setSizeBoard(9,9);
                break;
            case "board13":
                mainController.setSizeBoard(13,13);
                break;
            case "board19":
                mainController.setSizeBoard(19,19);
                break;
            default:
                throw new IllegalStateException();
        }
        Stage owner = (Stage) board9.getScene().getWindow();
        owner.close();


        WindowApp windowApp = new WindowApp();
        windowApp.start(((RadioButton)group.getSelectedToggle()).getId(),fieldAddress.getText());
    }

    public void buttonBot(ActionEvent actionEvent) {
        Window owner = board9.getScene().getWindow();
        AlertHelper.showAlert(Alert.AlertType.INFORMATION,owner,"Start",((RadioButton)group.getSelectedToggle()).getId());
    }

    @Override
    public void setJavaFXController(JavaFXController controller) {
        mainController = controller;
    }

    @Override
    public void placePawn(int x, int y, int color) {

    }

    @Override
    public void yourTurn() {

    }

    @Override
    public void opponentTurn() {

    }

    @Override
    public void youWin() {

    }

    @Override
    public void youLose() {

    }

    @Override
    public void displayMessage(String message) {

    }

    @Override
    public void startGame() {

    }

    @Override
    public void waitingForOpponent() {

    }
}
