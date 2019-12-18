package rules.point;

import java.util.ArrayList;

public class Point {

    public Point(int x, int y) {

        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    int x;
    int y;

    public Point getNeighbor(Direction side) {
        switch (side) {
            case NORTH:
                return new Point(x - 1, y);
            case SOUTH:
                return new Point(x + 1, y);
            case EAST:
                return new Point(x, y + 1);
            case WEST:
                return new Point(x, y - 1);
        }
        return null;
    }

    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    public boolean equals(Point other) {
        return (x == other.x && y == other.y);
    }

    public boolean isInArray(ArrayList<Point> arrayList) {
        for (Point p : arrayList
        ) {
            if (equals(p)) {
                return true;
            }
        }

        return false;
    }
}
