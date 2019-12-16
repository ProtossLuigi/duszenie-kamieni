package communication;

import main.Player;

public interface ConnectedPlayer extends Runnable, Player {

    void sendMessage(String message);
}
