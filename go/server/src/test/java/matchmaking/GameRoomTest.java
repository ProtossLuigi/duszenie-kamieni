package matchmaking;

import main.GameParameters;
import main.Player;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class GameRoomTest {

    private GameRoom gameRoom;

    @Before
    public void setUp() throws Exception {
        gameRoom = new GameRoom(mock(GameParameters.class),mock(Player.class));
    }

    @Test
    public void getParameters() {
        assertNotNull(gameRoom.getParameters());
    }

    @Test
    public void getPlayer() {
        assertNotNull(gameRoom.getPlayer());
    }
}