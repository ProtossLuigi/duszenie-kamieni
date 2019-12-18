package GUI;



public enum PawnColors {
    BLACK,
    WHITE,
    NONE;



    public static PawnColors fromInt(int x) {
        switch (x) {
            case 0:
                return NONE;
            case 1:
                return BLACK;
            case 2:
                return WHITE;

        }
        return NONE;
    }
}
