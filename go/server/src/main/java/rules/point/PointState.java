package rules.point;

public enum PointState {
    EMPTY,
    BLACK,
    WHITE;

    public int toInt() {
        switch (this) {
            case EMPTY:
                return 0;
            case BLACK:
                return 1;
            case WHITE:
                return 2;

        }
        return 0;
    }
}
