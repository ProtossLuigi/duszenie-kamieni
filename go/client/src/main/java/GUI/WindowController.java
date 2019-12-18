package GUI;

public interface WindowController {
    void setJavaFXController(JavaFXController controller);
    void placePawn(int x,int y,int color);
    void yourTurn();
    void opponentTurn();
    void youWin();
    void youLose();
    void displayMessage(String message);
    void startGame();
    void waitingForOpponent();
}
