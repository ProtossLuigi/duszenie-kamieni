package communication;

public interface MessageInterpreter {

    void getMessage(String message);
    void placePawn(int x,int y,int color);
    void yourTurn();
    void opponentTurn();
    void win();
    void lose();
}
