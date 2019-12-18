package rules;

import main.Player;
import rules.point.Point;

import java.util.ArrayList;

public class MoveResult {
    Player player;
    Point point;
    ArrayList<Point> captured;

    MoveResult(Player player, Point point, ArrayList<Point> captured) {

        this.player = player;
        this.point = point;
        this.captured = captured;
    }


}
