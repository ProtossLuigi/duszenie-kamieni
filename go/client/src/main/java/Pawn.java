import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Pawn extends Circle {

    private double mouseX, mouseY;


    private PawnColors type;

    static final int PAWN_SIZE = 10;


    PawnColors getType() {
        return type;
    }

    public Pawn(PawnColors type, int x, int y) {
        this.type = type;
        setRadius(PAWN_SIZE);


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

        setOnMousePressed(event -> {

            mouseX = event.getSceneX();
            mouseY = event.getSceneY();
            relocate(mouseX, mouseY);

        });


    }
}
