package GUI;

import communication.ServerConnection;

public class JavaFXController implements GUIController {

    ServerConnection serverConnection = null;

    @Override
    public void setSizeBoard(int width, int height) {
        //TODO
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
        //TODO
    }

    @Override
    public void yourTurn() {
        //TODO
    }

    @Override
    public void opponentTurn() {
        //TODO
    }

    @Override
    public void youWin() {
        //TODO
    }

    @Override
    public void youLose() {
        //TODO
    }

    @Override
    public void displayMessage(String message) {
        //TODO
    }

    @Override
    public void startGame() {
        //TODO
    }

    @Override
    public void waitingForOpponent() {
        //TODO
    }

    @Override
    public void findGame(int serverPort, boolean pvp, int boardWidth, int boardHeight) {
        if (serverConnection.connect(serverPort)) {
            serverConnection.lfg(pvp, boardWidth, boardHeight);
            waitingForOpponent();
        }
        else {
            //TODO: powiadom gracza że nie można połączyć się z serwerem
        }
    }

    @Override
    public void attemptSetPawn(int x, int y) {
        serverConnection.placeStone(x,y);
    }

    @Override
    public void pass() {
        serverConnection.pass();
    }

    @Override
    public void disconnect() {
        serverConnection.disconnect();
    }
}
