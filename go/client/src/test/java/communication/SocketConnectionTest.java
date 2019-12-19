package communication;

import GUI.GUIController;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class SocketConnectionTest {

    private SocketConnection socketConnection;
    private GUIController mockGUIController;

    @Before
    public void setUp() throws Exception {
        socketConnection = new SocketConnection();
        mockGUIController = mock(GUIController.class);
        socketConnection.setGUIController(mockGUIController);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void setGUIController() {
        socketConnection.setGUIController(mock(GUIController.class));
    }

    @Test
    public void getGUIController() {
        GUIController mockController = mock(GUIController.class);
        socketConnection.setGUIController(mockController);
        assertSame(mockController,socketConnection.getGUIController());
    }

    @Test
    public void setMessageInterpreter() {
        socketConnection.setMessageInterpreter(mock(MessageInterpreter.class));
    }

    @Test
    public void connect() throws IOException {
        assertFalse(socketConnection.connect(123));
        DummyServer server = new DummyServer();
        int port = server.getPort();
        assertTrue(socketConnection.connect(port));
    }

    @Test
    public void placeStone() {
        socketConnection.setMessageInterpreter(mock(MessageInterpreter.class));
        socketConnection.placeStone(0,0);
    }

    @Test
    public void pass() {
        socketConnection.setMessageInterpreter(mock(MessageInterpreter.class));
        socketConnection.pass();
    }

    @Test
    public void sendMessage() {
        PrintWriter mockWriter = mock(PrintWriter.class);
        socketConnection.setOut(mockWriter);
        socketConnection.sendMessage("test");
        verify(mockWriter,times(1)).println("test");
    }

    @Test
    public void disconnect() throws IOException {
        BufferedReader reader = mock(BufferedReader.class);
        when(reader.readLine()).thenThrow(new IOException());
        socketConnection.setIn(reader);
        new Thread(socketConnection).start();
    }

    @Test
    public void newChatMessage() {
        socketConnection.setGUIController(mock(GUIController.class));
        socketConnection.newChatMessage("test");
    }

    @Test
    public void setPawn() {
        socketConnection.setGUIController(mock(GUIController.class));
        socketConnection.setPawn(0,0,0);
    }

    @Test
    public void playerTurn() {
        socketConnection.setGUIController(mock(GUIController.class));
        socketConnection.playerTurn();
    }

    @Test
    public void opponentTurn() {
        socketConnection.setGUIController(mock(GUIController.class));
        socketConnection.opponentTurn();
    }

    @Test
    public void win() {
        socketConnection.win();
        verify(mockGUIController,times(1)).youWin();
    }

    @Test
    public void loss() {
        socketConnection.setGUIController(mock(GUIController.class));
        socketConnection.loss();
    }

    @Test
    public void draw() {
        socketConnection.setGUIController(mock(GUIController.class));
        socketConnection.draw();
    }

    @Test
    public void startGame() {
        socketConnection.setGUIController(mock(GUIController.class));
        socketConnection.startGame();
    }

    @Test
    public void lfg() {
        socketConnection.setMessageInterpreter(mock(MessageInterpreter.class));
        socketConnection.lfg(false,0,0);
    }

    @Test
    public void waitingForOpponent() {
        socketConnection.setGUIController(mock(GUIController.class));
        socketConnection.waitingForOpponent();
    }

    @Test
    public void run() throws IOException {
        BufferedReader reader = mock(BufferedReader.class);
        when(reader.readLine()).thenReturn("test").thenThrow(new IOException());
        socketConnection.setIn(reader);
        socketConnection.setMessageInterpreter(mock(MessageInterpreter.class));
        new Thread(socketConnection).start();
    }
}