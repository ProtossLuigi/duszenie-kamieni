import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Pawn extends Circle {

    private double mouseX, mouseY;


    private PawnColors type;

    static final int PAWN_SIZE = 10;

    //serwer musi podać klientowi kolor
    public void changeColor (Pawn pawn,Color color){
        pawn.setFill(color);
    }


    PawnColors getType() {
        return type;
    }

    public Pawn(PawnColors type, int x, int y) {
        this.type = type;
        setRadius(PAWN_SIZE);
        relocate(x*(2*PAWN_SIZE), y*(2*PAWN_SIZE));
        setStrokeWidth(0.2*PAWN_SIZE);
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
}
