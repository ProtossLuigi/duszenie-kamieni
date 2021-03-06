package communication;

import main.ConsoleWriter;
import main.GameParameters;
import matchmaking.GameRoom;
import matchmaking.Lobby;
import rules.Game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;

public class ConnectedSocketPlayer implements ConnectedPlayer {

    private Socket socket;
    private MessageInterpreter messageInterpreter;
    private PrintWriter out;
    private BufferedReader in;
    private GameRoom waitingRoom = null;
    private Game game = null;

    public ConnectedSocketPlayer(Socket socket) {
        this.socket = socket;
        messageInterpreter = new StringMessageInterpreter(this);
        try {
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException ignore) {
        }
    }

    @Override
    public void run() {
        try {
            String message;
            //ConsoleWriter.println("running");
            while (!Thread.currentThread().isInterrupted() && (message = in.readLine()) != null) {
                ConsoleWriter.println("IN: " + message);
                messageInterpreter.getMessage(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //ConsoleWriter.println("closing");
        if (!Thread.currentThread().isInterrupted()) {
            disconnect();
        }
    }

    @Override
    public void join(GameParameters parameters) {
        Lobby.newPlayer(this, parameters);
    }

    @Override
    public void attemptPlacePawn(int x, int y) {
        if (game != null) {
            game.placePawn(this, x, y);
        }
    }

    @Override
    public void sendMessage(String message) {

        ConsoleWriter.println("OUT: " + message);
        out.println(message);
    }

    @Override
    public void disconnect() {
        if (waitingRoom != null) {
            Lobby.removePlayer(waitingRoom);
        }
        if (game != null) {
            game.leave(this);
        }
        Thread.currentThread().interrupt();
    }

    @Override
    public void pass() {
        if (game != null) {
            game.pass(this);
        }
    }

    @Override
    public void startGame(int width, int height, int color) {
        messageInterpreter.startGame();
    }

    @Override
    public void waiting() {
        messageInterpreter.waiting();
    }

    @Override
    public void pawnPlaced(int x, int y, int color) {
        messageInterpreter.placePawn(x, y, color);
    }

    @Override
    public void setWaitingRoom(GameRoom waitingRoom) {
        this.waitingRoom = waitingRoom;
    }

    @Override
    public void notifWin() {
        messageInterpreter.win();
    }

    @Override
    public void setScore(int yourScore, int opponentScore) {

    }

    @Override
    public void notifLoss() {
        messageInterpreter.lose();
    }

    @Override
    public void notifDraw() {
        messageInterpreter.draw();
    }

    @Override
    public void sendChatMessage(String line) {
        messageInterpreter.sendChatMessage(line);
    }

    @Override
    public void yourTurn() {
        messageInterpreter.yourTurn();
    }

    @Override
    public void opponentTurn() {
        messageInterpreter.opponentTurn();
    }

    public void setOut(PrintWriter out) {
        this.out = out;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public void setMessageInterpreter(MessageInterpreter messageInterpreter) {
        this.messageInterpreter = messageInterpreter;
    }
}
