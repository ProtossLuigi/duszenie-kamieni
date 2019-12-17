package communication;

import GUI.GUIController;

public interface ServerConnection extends Runnable {

    void setGUIController(GUIController guiController);
    GUIController getGUIController();
    void setMessageInterpreter(MessageInterpreter messageInterpreter);
    boolean connect(int destination);
    void placeStone(int x, int y);
    void pass();
    void sendMessage(String message);
    void disconnect();
    void newChatMessage(String line);
    void setPawn(int x,int y,int color);
    void playerTurn();
    void opponentTurn();
    void win();
    void loss();
}
