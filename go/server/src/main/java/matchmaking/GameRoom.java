package matchmaking;

import main.GameParameters;
import main.Player;

public class GameRoom {

    private GameParameters parameters;
    private Player player;

    public GameRoom(GameParameters parameters, Player player){
        this.parameters = parameters;
        this.player = player;
    }

    public GameParameters getParameters() {
        return parameters;
    }

    public Player getPlayer() {
        return player;
    }
}
