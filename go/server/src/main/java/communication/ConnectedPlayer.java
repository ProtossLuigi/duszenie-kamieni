package communication;

import main.GameParameters;
import main.Player;
import matchmaking.GameRoom;

public interface ConnectedPlayer extends Runnable, Player {

    void sendMessage(String message);
    void join(GameParameters parameters);
    void disconnect();
}
