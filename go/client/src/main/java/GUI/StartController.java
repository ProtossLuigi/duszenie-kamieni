package GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Window;

public class StartController implements WindowController {
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

    private JavaFXController mainController;

    public void setBoard(Pane boardChild) {
        board.getChildren().add(boardChild);

    }

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
