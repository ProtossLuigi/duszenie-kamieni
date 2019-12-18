package GUI;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;


public class Pawn extends Circle implements PieceController {


    PawnColors type;

     public static int PAWN_SIZE =10;


    PawnColors getType() {
        return type;
    }

    public Pawn(PawnColors type, int x, int y) {
        this.type = type;
        setRadius(PAWN_SIZE);
        relocate(x * (2 * PAWN_SIZE), y * (2 * PAWN_SIZE));
        setStrokeWidth(0.2 * PAWN_SIZE);
        setFill(Color.YELLOW);
        setStroke(Color.YELLOW);


        switch (type) {

            case NONE:

                break;

            case BLACK:
                setFill(Color.BLACK);
                break;
            case WHITE:
                setFill(Color.WHITE);
                break;

        }


    }

    @Override
    public void setPieceRadius(int radius) {
        PAWN_SIZE = radius;
    }


    @Override
    public void drawPiece(Pawn pawn, Color color) {
        pawn.setFill(color);
    }



}
