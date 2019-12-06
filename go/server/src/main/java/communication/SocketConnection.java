package communication;

import java.net.ServerSocket;

public class SocketConnection implements ClientConnection{

    private ServerSocket socket = null;

    @Override
    public boolean setupAccess(int port) {
        return false;
    }
}
