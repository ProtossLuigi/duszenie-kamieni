package communication;

import main.ConsoleWriter;

import java.io.IOException;
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
        ConsoleWriter.println("Server running on port " + socket.getLocalPort());
        while (true) {
            try {
                Socket newClient = socket.accept();
                ConnectedPlayer newPlayer = new ConnectedSocketPlayer(newClient);
                new Thread(newPlayer).start();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void setSocket(ServerSocket socket) {
        this.socket = socket;
    }
}
