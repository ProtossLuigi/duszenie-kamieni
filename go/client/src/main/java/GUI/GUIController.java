package GUI;

import communication.ServerConnection;

public interface GUIController {

    // methods invoked outside GUI
    void setSizeBoard(int width, int height);
    void setServerConnection(ServerConnection serverConnection);
    void showWindow();
    void placePawn(int x,int y,int color);
    void yourTurn();
    void opponentTurn();
    void youWin();
    void youLose();
    void draw();
    void displayMessage(String message);
    void startGame();
    void waitingForOpponent();

    // methods invoked inside GUI
    void findGame(int serverPort,boolean pvp,int boardWidth,int boardHeight);
    void attemptSetPawn(int x,int y);
    void pass();
    void disconnect();
}
