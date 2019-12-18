package GUI;

import communication.ServerConnection;

public class JavaFXController implements GUIController {

    private ServerConnection serverConnection = null;
    private WindowController currentWindowController = null;

    public BoardCreation boardCreation;

    @Override
    public void setSizeBoard(int width, int height) {
        boardCreation = new BoardCreation();
        boardCreation.setSizeBoard(width, height);
    }

    @Override
    public void setServerConnection(ServerConnection serverConnection) {
        this.serverConnection = serverConnection;
    }

    @Override
    public void showWindow() {
        OptionStart.startWindow(this);
    }

    @Override
    public void placePawn(int x, int y, int color) {
        currentWindowController.placePawn(x, y, color);
    }

    @Override
    public void yourTurn() {
        currentWindowController.yourTurn();
    }

    @Override
    public void opponentTurn() {
        currentWindowController.opponentTurn();
    }

    @Override
    public void youWin() {
        currentWindowController.youWin();
    }

    @Override
    public void youLose() {
        currentWindowController.youLose();
    }

    @Override
    public void displayMessage(String message) {
        currentWindowController.displayMessage(message);
    }

    @Override
    public void startGame() {
        currentWindowController.startGame();
    }

    @Override
    public void waitingForOpponent() {
        currentWindowController.waitingForOpponent();
    }

    @Override
    public void findGame(int serverPort, boolean pvp, int boardWidth, int boardHeight) {
        if (serverConnection.connect(serverPort)) {

            serverConnection.lfg(pvp, boardWidth, boardHeight);
        } else {
            //TODO: powiadom gracza że nie można połączyć się z serwerem
        }
    }

    @Override
    public void attemptSetPawn(int x, int y) {
        serverConnection.placeStone(x, y);
    }

    @Override
    public void pass() {
        serverConnection.pass();
    }

    @Override
    public void disconnect() {
        serverConnection.disconnect();
    }

    public void setCurrentWindowController(WindowController currentWindowController) {
        this.currentWindowController = currentWindowController;
    }
}
