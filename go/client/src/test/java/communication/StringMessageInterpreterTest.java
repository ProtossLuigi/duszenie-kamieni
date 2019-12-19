package communication;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class StringMessageInterpreterTest {

    StringMessageInterpreter interpreter;
    ServerConnection mockServerConnection;

    @Before
    public void setUp() throws Exception {
        interpreter = new StringMessageInterpreter();
        mockServerConnection = mock(ServerConnection.class);
        interpreter.setServerConnection(mockServerConnection);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void setServerConnection() {
    }

    @Test
    public void getMessage() {
        interpreter.getMessage("MESSAGE test");
        verify(mockServerConnection).newChatMessage("test");
        interpreter.getMessage("PAWN 1 2 3");
        verify(mockServerConnection).setPawn(1,2,3);
        interpreter.getMessage("TURN YOUR");
        verify(mockServerConnection).playerTurn();
        interpreter.getMessage("TURN OPPONENT");
        verify(mockServerConnection).opponentTurn();
        interpreter.getMessage("WIN");
        verify(mockServerConnection).win();
        interpreter.getMessage("LOSS");
        verify(mockServerConnection).loss();
        interpreter.getMessage("DRAW");
        verify(mockServerConnection).draw();
        interpreter.getMessage("GAME START");
        verify(mockServerConnection).startGame();
        interpreter.getMessage("WAITING");
        verify(mockServerConnection).waitingForOpponent();
    }

    @Test
    public void placePawn() {
        interpreter.placePawn(0,0);
    }

    @Test
    public void pass() {
        interpreter.pass();
    }

    @Test
    public void disconnect() {
        interpreter.disconnect();
    }

    @Test
    public void lfg() {
        interpreter.lfg(false,0,0);
    }
}