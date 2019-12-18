package rules.board;

import main.Player;
import rules.point.Point;
import rules.point.PointState;

import java.util.ArrayList;
import java.util.Arrays;

public class GameState {
    public GameState(int boardWidth, int boardHeight, Player playerBlack, Player playerWhite, GameStatus status) {
        this.board = new ArrayList<>();
        this.boardHeight = boardHeight;
        this.boardWidth = boardWidth;
        for (int i = 0; i < boardHeight; i++) {
            ArrayList<PointState> temp = new ArrayList<>();
            board.set(i, temp);
            for (int j = 0; j < boardWidth; j++) {
                board.get(i).set(j, PointState.EMPTY);
            }
        }
        this.playerBlack = playerBlack;
        this.playerWhite = playerWhite;
        playerBlack.startGame(boardWidth,boardHeight,1);
        playerWhite.startGame(boardWidth,boardHeight,2);
        if (status == null) {
            status = GameStatus.ACTIVE;
        }
        this.status = status;

    }

    public int boardWidth;
    public int boardHeight;
    public ArrayList<ArrayList<PointState>> board;
    public Player playerBlack;
    public Player playerWhite;
    public Player current;
    public GameStatus status;
    public String moveError;

    public PointState getPlayerColor() {
        if (current == playerBlack) {
            return PointState.BLACK;
        } else
            return PointState.WHITE;
    }


    public PointState getPointState(Point point) {
        return board.get(point.getX()).get(point.getY());
    }

    public void setPointState(Point point, PointState pointState) {
        board.get(point.getX()).set(point.getY(), pointState);
    }

    public String MoveError(String code) {

        switch (code) {
            case "REPEAT":
                return "The attempted move would result in a repeated board state.";
            case "OCCUPIED":
                return "The selected intersection is occupied";
            case "SUICIDE":
                return "The attepted move would result in a suicide.";

            default:
                throw new IllegalStateException("Unexpected value: " + code);
        }

    }

    public ArrayList<ArrayList<PointState>> getBoardCopy() {

        ArrayList<ArrayList<PointState>> boardCopy = new ArrayList<>();

        for (ArrayList<PointState> p :
                board) {
            ArrayList<PointState> temp = new ArrayList<>();

            for (PointState s :
                    p
            ) {
                temp.add(PointState.valueOf(s.toString()));
            }
            boardCopy.add(temp);

        }
        return boardCopy;

    }

    public void setBoardCopy(ArrayList<ArrayList<PointState>> board) {
        this.board.clear();
        this.board = board;
    }


}
