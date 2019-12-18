package matchmaking;

import communication.ConnectedSocketPlayer;
import communication.SocketConnection;
import main.GameParameters;
import main.Player;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LobbyTest {

    Player mockPlayer = mock(Player.class);
    GameParameters mockParameters = mock(GameParameters.class);
    @Mock Socket mockSocket;

    @Before
    public void setUp() throws Exception {
        mockSocket = mock(Socket.class);
        when(mockSocket.getInputStream()).thenReturn(mock(InputStream.class));
        when(mockSocket.getOutputStream()).thenReturn(mock(OutputStream.class));
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
        ConnectedSocketPlayer player = new ConnectedSocketPlayer(mockSocket);
        Lobby.newPlayer(player,new GameParameters(true,new int[]{9,9}));
        player.disconnect();
    }
}