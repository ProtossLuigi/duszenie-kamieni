package communication;

import java.io.IOException;
import java.net.ServerSocket;

public class DummyServer implements Runnable {

    private ServerSocket serverSocket;

    public DummyServer() throws IOException {
        serverSocket = new ServerSocket(0);
    }

    public int getPort(){
        return serverSocket.getLocalPort();
    }

    @Override
    public void run() {
        try {
            serverSocket.accept();
        }
        catch (IOException e) {

        }
    }
}
