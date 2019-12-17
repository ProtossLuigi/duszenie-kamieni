package GUI;

import GUI.GUIController;
import GUI.Pawn;
import GUI.PawnColors;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class BoardCreation /*implements GUIController*/ {


    public Pawn[][] board;


    private void setBoard(Pawn[][] board) {
        this.board = board;
    }

    private Pawn[][] createBoard(int width, int height) {
        return new Pawn[width][height];
    }


    //@Override
    public void setSizeBoard(int width, int height) {
        setBoard(createBoard(width,height));
    }





}
