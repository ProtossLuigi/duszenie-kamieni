package communication;

import matchmaking.GameRoom;
import matchmaking.Lobby;
import rules.Game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ConnectedSocketPlayer implements ConnectedPlayer {

    private Socket socket;
    private MessageInterpreter messageInterpreter;
    private PrintWriter out;
    private BufferedReader in;
    private GameRoom waitingRoom = null;
    private Game game = null;

    public ConnectedSocketPlayer(Socket socket){
        this.socket = socket;
        messageInterpreter = new StringMessageInterpreter(this);
    }

    @Override
    public void run() {
        try {
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String message;
            while ((message = in.readLine()) != null) {
                messageInterpreter.getMessage(message);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        if (waitingRoom != null) {
            Lobby.removePlayer(waitingRoom);
        }
        if (game != null) {
            game.leave(this);
        }
    }

    @Override
    public void sendMessage(String message) {
        out.println(message);
    }
}
