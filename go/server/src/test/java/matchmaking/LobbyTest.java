package matchmaking;

import communication.ConnectedSocketPlayer;
import main.GameParameters;
import main.Player;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class LobbyTest {

    Player mockPlayer = mock(Player.class);
    GameParameters mockParameters = mock(GameParameters.class);

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void newPlayer() {
        Lobby.newPlayer(mock(Player.class),new GameParameters(false,new int[]{9,9}));
        Lobby.newPlayer(mock(Player.class),new GameParameters(true,new int[]{9,9}));
        Lobby.newPlayer(mock(Player.class),new GameParameters(true,new int[]{13,13}));
        Lobby.newPlayer(mock(Player.class),new GameParameters(true,new int[]{13,13}));
        Lobby.newPlayer(mock(Player.class),new GameParameters(true,new int[]{19,19}));
    }

    @Test
    public void removePlayer() {
        ConnectedSocketPlayer player = new ConnectedSocketPlayer(null);
        Lobby.newPlayer(player,new GameParameters(true,new int[]{9,9}));
        player.disconnect();
    }
}