package communication;

import java.io.IOException;
import java.net.Socket;

public class SocketConnection implements ServerConnection {

    private Socket socket = null;

    @Override
    public boolean connect(int destination) {
        try {
            socket = new Socket("localhost",destination);
            return true;
        }
        catch (IOException e) {
            return false;
        }
    }

    @Override
    public boolean placeStone(int x, int y) {
        return false;
    }
}
