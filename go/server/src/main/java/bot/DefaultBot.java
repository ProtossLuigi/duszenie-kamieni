package bot;

import main.Player;
import matchmaking.GameRoom;
import rules.Game;

import java.util.Random;

public class DefaultBot implements Player {

    private Game game;
    private int color;
    private int width;
    private int height;
    private int[][] boardFields;
    private boolean possibleKo = false;
    private int lastX;
    private int lastY;

    @Override
    public void pawnPlaced(int x, int y, int color) {
        possibleKo = x == lastX && y == lastY && color == 0;
        boardFields[y][x] = color;
    }

    @Override
    public void setWaitingRoom(GameRoom waitingRoom) { }

    @Override
    public void setGame(Game game) {
        this.game = game;
    }

    @Override
    public void notifWin() { }

    @Override
    public void setScore(int yourScore, int opponentScore) { }

    @Override
    public void notifLoss() { }

    @Override
    public void notifDraw() {

    }

    @Override
    public void sendChatMessage(String line) { }

    @Override
    public void yourTurn() {
        int totalRank = 0;
        int[][] ranks = new int[height][width];
        for (int y=0; y<height; y++) {
            for (int x=0; x<width; x++) {
                ranks[y][x] = rankField(x,y);
                totalRank += ranks[y][x];
            }
        }
        if (totalRank == 0) {
            pass();
        } else {
            Random rand = new Random();
            int r = rand.nextInt(totalRank);
            for (int y=0; y<height; y++) {
                for (int x=0; x<width; x++) {
                    r -= ranks[y][x];
                    if (r < 0) {
                        attemptPlacePawn(x,y);
                        return;
                    }
                }
            }
        }
    }

    @Override
    public void opponentTurn() { }

    @Override
    public void startGame(int width,int height,int color) {
        this.width = width;
        this.height = height;
        boardFields = new int[height][width];
        this.color = color;
    }

    @Override
    public void waiting() { }

    @Override
    public void attemptPlacePawn(int x, int y) {
        lastX = x;
        lastY = y;
        game.placePawn(this,x,y);
    }

    @Override
    public void pass() {
        game.pass(this);
    }

    private int[] countNeighbourColors(int x,int y){
        int[] colors = new int[3];
        if (x < width-1) {
            colors[boardFields[y][x+1]]++;
        }
        if (x > 0) {
            colors[boardFields[y][x-1]]++;
        }
        if (y < height-1) {
            colors[boardFields[y+1][x]]++;
        }
        if (y > 0) {
            colors[boardFields[y-1][x]]++;
        }
        return colors;
    }

    private int rankField(int x,int y){
        if (boardFields[y][x] != 0 || (possibleKo && x == lastX && y == lastY)) {
            return 0;
        }
        int[] colors = countNeighbourColors(x,y);
        if (colors[0] == 0) {
            return 0;
        }
        int rank = 1;
        rank += colors[0]*10;
        if (colors[color] > 0) {
            rank += 100;
        }
        if (colors[3-color] == 1) {
            rank += 100;
        }
        return rank;
    }
}
