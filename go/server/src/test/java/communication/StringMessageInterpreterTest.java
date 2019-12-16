package communication;

import main.Player;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class StringMessageInterpreterTest {

    private StringMessageInterpreter interpreter;
    private ConnectedPlayer mockPlayer;

    @Before
    public void setUp() throws Exception {
        mockPlayer = mock(ConnectedPlayer.class);
        interpreter = new StringMessageInterpreter(mockPlayer);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getMessage() {
        interpreter.getMessage("");
    }

    @Test
    public void placePawn() {
        interpreter.placePawn(0,0,0);
    }

    @Test
    public void yourTurn() {
        interpreter.yourTurn();
    }

    @Test
    public void opponentTurn() {
        interpreter.opponentTurn();
    }

    @Test
    public void win() {
        interpreter.win();
    }

    @Test
    public void lose() {
        interpreter.lose();
    }
}