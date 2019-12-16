package communication;

import java.net.ServerSocket;
import java.net.Socket;

public class SocketConnection implements ClientConnection {

    private ServerSocket socket = null;

    @Override
    public boolean setupAccess(int port) {
        try {
            socket = new ServerSocket(port);
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void listen() {
        System.out.println("Server running on port " + socket.getLocalPort());
        while (true) {
            try {
                Socket newClient = socket.accept();
                ConnectedPlayer newPlayer = new ConnectedSocketPlayer(newClient);
                new Thread(newPlayer).start();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
