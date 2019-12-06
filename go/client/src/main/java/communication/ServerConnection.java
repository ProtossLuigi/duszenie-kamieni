package communication;

public interface ServerConnection {
    boolean connect(int destination);
    boolean placeStone(int x, int y);
    boolean pass();
}
