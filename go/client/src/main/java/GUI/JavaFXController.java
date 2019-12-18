package GUI;

import communication.ServerConnection;

public class JavaFXController implements GUIController {

    ServerConnection serverConnection = null;

    @Override
    public void setSizeBoard(int width, int height) {

    }

    @Override
    public void setServerConnection(ServerConnection serverConnection) {
        this.serverConnection = serverConnection;
    }

    @Override
    public void showWindow() {
        OptionStart.main(null);
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
}
