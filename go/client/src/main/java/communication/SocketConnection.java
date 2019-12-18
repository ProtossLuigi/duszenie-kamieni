package communication;

import GUI.GUIController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketConnection implements ServerConnection {

    private Socket socket = null;
    private BufferedReader in;
    private PrintWriter out;
    private GUIController guiController;
    private MessageInterpreter messageInterpreter;
    private Thread thread;

    public SocketConnection() {
        messageInterpreter = new StringMessageInterpreter();
        messageInterpreter.setServerConnection(this);
    }

    @Override
    public void setGUIController(GUIController guiController) {
        this.guiController = guiController;
    }

    @Override
    public GUIController getGUIController() {
        return guiController;
    }

    @Override
    public void setMessageInterpreter(MessageInterpreter messageInterpreter) {
        this.messageInterpreter = messageInterpreter;
    }

    @Override
    public boolean connect(int destination) {
        try {
            socket = new Socket("localhost",destination);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream());
            return true;
        }
        catch (IOException e) {
            return false;
        }
    }

    @Override
    public void placeStone(int x, int y) {
        messageInterpreter.placePawn(x,y);
    }

    @Override
    public void pass() {
        messageInterpreter.pass();
    }

    @Override
    public void sendMessage(String message) {
        out.println(message);
    }

    @Override
    public void disconnect() {
        messageInterpreter.disconnect();
        socket = null;
        in = null;
        out = null;
        thread.interrupt();
    }

    @Override
    public void newChatMessage(String line) {
        guiController.displayMessage(line);
    }

    @Override
    public void setPawn(int x, int y, int color) {
        guiController.placePawn(x,y,color);
    }

    @Override
    public void playerTurn() {
        guiController.yourTurn();
    }

    @Override
    public void opponentTurn() {
        guiController.opponentTurn();
    }

    @Override
    public void win() {
        guiController.youWin();
    }

    @Override
    public void loss() {
        guiController.youLose();
    }

    @Override
    public void startGame() {
        guiController.startGame();
    }

    @Override
    public void lfg(boolean pvp, int boardWidth, int boardHeight) {
        guiController.waitingForOpponent();
        messageInterpreter.lfg(pvp, boardWidth, boardHeight);
    }

    @Override
    public void waitingForOpponent() { }

    @Override
    public void run() {
        thread = Thread.currentThread();
        try {
            String message;
            while (!Thread.currentThread().isInterrupted() && (message = in.readLine()) != null) {
                messageInterpreter.getMessage(message);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        if (!thread.isInterrupted()) {
            disconnect();
        }
    }
}
