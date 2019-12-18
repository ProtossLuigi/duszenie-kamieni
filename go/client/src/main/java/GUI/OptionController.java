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

    private void afterPressBotOrPlayerButton(boolean pvp) {
        int temp;
        switch (((RadioButton) group.getSelectedToggle()).getId()) {
            case "board9":
                temp=9;
                mainController.setSizeBoard(temp, temp);
                break;
            case "board13":
                temp = 13;
                mainController.setSizeBoard(temp, temp);
                break;
            case "board19":
                temp=19;
                mainController.setSizeBoard(temp, temp);
                break;
            default:
                throw new IllegalStateException();


        }
        mainController.findGame(Integer.parseInt(fieldAddress.getText()),pvp,temp,temp);
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
        //todo zmiana z okienka opcji na gre (tryb oczekiwania

        Stage owner = (Stage) board9.getScene().getWindow();
        owner.close();


        WindowApp windowApp = new WindowApp();
        try {
            windowApp.start(true, mainController);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
