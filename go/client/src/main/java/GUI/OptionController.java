package GUI;

import javafx.scene.control.*;
import javafx.stage.Stage;

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
        afterPressBotOrPlayerButton(true);
    }

    public void buttonBot() throws Exception {
        afterPressBotOrPlayerButton(false);
    }

    private void afterPressBotOrPlayerButton(boolean pvp) throws Exception {
        switch (((RadioButton) group.getSelectedToggle()).getId()) {
            case "board9":
                mainController.setSizeBoard(9, 9);
                break;
            case "board13":
                mainController.setSizeBoard(13, 13);
                break;
            case "board19":
                mainController.setSizeBoard(19, 19);
                break;
            default:
                throw new IllegalStateException();
        }

        waitingForOpponent(pvp);

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
    public void waitingForOpponent(boolean pvp) {
        //todo zmiana z okienka opcji na gre (tryb oczekiwania

        Stage owner = (Stage) board9.getScene().getWindow();
        owner.close();


        WindowApp windowApp = new WindowApp();
        try {
            windowApp.start(pvp, mainController);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
