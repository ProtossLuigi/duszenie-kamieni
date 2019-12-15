import javafx.scene.layout.Pane;

public class BoardCreation {

    public BoardCreation(int scale) {
        setBoard(createBoard(scale));
    }

    public int scale;
    public Pawn[][] board;

    public int numberOfObjects = 0;

    public void setBoard(Pawn[][] board) {
        this.board = board;
    }

    public Pawn[][] createBoard(int scale) {
        return new Pawn[scale][scale];
    }


}
