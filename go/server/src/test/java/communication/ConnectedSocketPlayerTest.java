package communication;

import main.GameParameters;
import main.Player;
import matchmaking.GameRoom;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import rules.Game;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ConnectedSocketPlayerTest {

    private ConnectedSocketPlayer player;
    @Mock private Socket mockSocket;

    @Before
    public void setUp() throws Exception {
        mockSocket = mock(Socket.class);
        when(mockSocket.getInputStream()).thenReturn(mock(InputStream.class));
        when(mockSocket.getOutputStream()).thenReturn(mock(OutputStream.class));
        player = new ConnectedSocketPlayer(mockSocket);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void run() throws IOException {
        when(mockSocket.getInputStream()).thenReturn(new ByteArrayInputStream("\0".getBytes()));
        when(mockSocket.getOutputStream()).thenReturn(mock(OutputStream.class));
        player.run();
        when(mockSocket.getInputStream()).thenThrow(new IOException());
        player.run();
    }

    @Test
    public void join() throws IOException {
        player.join(new GameParameters(true,new int[]{9,9}));
    }

    @Test
    public void sendMessage() {
        player.setOut(mock(PrintWriter.class));
        player.sendMessage("test");
    }

    @Test
    public void disconnect() {
        player.setWaitingRoom(mock(GameRoom.class));
        player.setGame(mock(Game.class));
        player.disconnect();
    }

    @Test
    public void pawnPlaced() {
        player.setMessageInterpreter(mock(MessageInterpreter.class));
        player.pawnPlaced(0,0,0);
    }

    @Test
    public void setWaitingRoom() {
        player.setWaitingRoom(null);
    }
}