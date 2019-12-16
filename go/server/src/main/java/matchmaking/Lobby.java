package matchmaking;

import bot.DefaultBot;
import main.GameParameters;
import main.Player;
import rules.Game;

import java.util.ArrayList;

public class Lobby {

    private static ArrayList<GameRoom> rooms = new ArrayList<>();

    public static synchronized void newPlayer(Player player, GameParameters parameters){
        if (!parameters.pvp){
            Player[] players = new Player[2];
            players[0] = player;
            players[1] = new DefaultBot();
            new Game(parameters,players);
        }
        else {
            for (GameRoom room : rooms){
                if (room.getParameters().equals(parameters)){
                    Player[] players = new Player[2];
                    players[0] = player;
                    players[1] = room.getPlayer();
                    rooms.remove(room);
                    new Game(parameters,players);
                    return;
                }
            }
            rooms.add(new GameRoom(parameters,player));
        }
    }

    public static synchronized void removePlayer(GameRoom room){
        rooms.remove(room);
    }
}
