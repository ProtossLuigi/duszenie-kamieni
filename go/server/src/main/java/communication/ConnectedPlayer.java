package communication;

import main.GameParameters;
import main.Player;
import matchmaking.GameRoom;

public interface ConnectedPlayer extends Runnable, Player {

    void sendMessage(String message);
    void join(GameParameters parameters);
    void attemptPlacePawn(int x,int y);
    void disconnect();
    void pass();
    void startGame();
    void waiting();
}
