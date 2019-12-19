package rules;

import main.GameParameters;
import main.Player;
import matchmaking.Lobby;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class GameTest {


    private Game game;
    private Player[] players;


    @Before
    public void setUp() throws Exception {
        players = new Player[2];
        players[0] = mock(Player.class);
        players[1] = mock(Player.class);
        int[][] boardSizes = new int[2][2];
        boardSizes[0] = new int[]{9, 9};
        GameParameters params1 = new GameParameters(true, boardSizes[0]);
        game = new Game(params1, players);
    }

    @After
    public void tearDown() throws Exception {
    }


    @Test
    public void pass() {
        game.pass(players[0]);


    }



}