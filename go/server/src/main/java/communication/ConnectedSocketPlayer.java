package communication;

import java.net.Socket;

public class ConnectedSocketPlayer implements ConnectedPlayer {

    private Socket socket;
    private MessageInterpreter messageInterpreter;

    public ConnectedSocketPlayer(Socket socket){
        this.socket = socket;
        messageInterpreter = new StringMessageInterpreter(this);
    }

    @Override
    public void run() {
        //TODO
    }
}
