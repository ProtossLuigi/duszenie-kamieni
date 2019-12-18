package rules.territory;

import rules.point.Point;

import java.util.ArrayList;
import java.util.Objects;

public class Territory {
    public Territory(ArrayList<Point> points, OwnerTerritory owner) {
        this.points = Objects.requireNonNullElseGet(points, ArrayList::new);
        this.owner = Objects.requireNonNullElse(owner, OwnerTerritory.EMPTY);
    }

    public ArrayList<Point> points;
    public  OwnerTerritory owner;



}
