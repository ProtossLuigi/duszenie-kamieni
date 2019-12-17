package GUI;

import communication.ServerConnection;

public interface GUIController {

    void setSizeBoard(int width, int height);
    void setServerConnection(ServerConnection serverConnection);
    void showWindow();
    void placePawn(int x,int y,int color);
    void yourTurn();
    void opponentTurn();
    void youWin();
    void youLose();
    void displayMessage(String message);
}
