package GUI;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;


public class Pawn extends Circle implements PieceController {


    PawnColors type;

    public static int PAWN_SIZE = 40;


    PawnColors getType() {
        return type;
    }

    public Pawn(PawnColors type, int x, int y) {
        this.type = type;
        setRadius(PAWN_SIZE);
        relocate(x * (2 * PAWN_SIZE), y * (2 * PAWN_SIZE));
        setStrokeWidth(0.2 * PAWN_SIZE);

        setStroke(Color.YELLOW);

        setColor(this, type);


    }

    public void setColor(Pawn pawn, PawnColors type) {
        switch (type) {

            case NONE:
                setFill(Color.YELLOW);
                break;

            case BLACK:
                pawn.setFill(Color.BLACK);
                break;
            case WHITE:
                pawn.setFill(Color.WHITE);
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
