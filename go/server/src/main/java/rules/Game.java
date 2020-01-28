package rules;

import database.DatabaseAccess;
import main.GameParameters;
import main.Player;
import rules.board.GameState;

import java.util.Random;

public class Game {

    private Player[] players;
    Logic logic;
    private int id;

    public Game(GameParameters parameters, Player[] players) {
        for (Player player : players) {
            player.setGame(this);
        }
        Random random = new Random();
        boolean a = random.nextBoolean();
        boolean b = !a;
        logic = new Logic();
        id = DatabaseAccess.databaseAdapter.newGame();
        logic.newGame(parameters.boardSize[0], parameters.boardSize[1], players[a ? 1 : 0], players[b ? 1 : 0]);
    }

    public void leave(Player player) {
        Player temp = (player == logic.gameState.playerBlack) ? logic.gameState.playerWhite : logic.gameState.playerBlack;
        temp.setScore(0, 0);

        temp.notifWin();

        String playerName = temp == logic.gameState.playerBlack ? "black" : "white";
        DatabaseAccess.databaseAdapter.finishGame(id,playerName);
    }

    public void placePawn(Player player, int x, int y) {
        logic.move(x, y, player, id);


    }

    public void pass(Player player) {
        logic.pressPass(player,id);

    }
}
