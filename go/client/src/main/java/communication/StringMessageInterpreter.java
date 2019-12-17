package communication;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringMessageInterpreter implements MessageInterpreter {

    private ServerConnection serverConnection;
    private Pattern messagePattern = Pattern.compile("MESSAGE (.*)");
    private Pattern pawnPattern = Pattern.compile("PAWN (\\d+) (\\d+) (\\d+)");

    @Override
    public void setServerConnection(ServerConnection serverConnection) {
        this.serverConnection = serverConnection;
    }

    @Override
    public void getMessage(String message) {
        Matcher m;
        m = messagePattern.matcher(message);
        if (m.find()) {
            serverConnection.newChatMessage(m.group(1));
            return;
        }
        m = pawnPattern.matcher(message);
        if (m.find()) {
            serverConnection.setPawn(Integer.parseInt(m.group(1)),Integer.parseInt(m.group(2)),Integer.parseInt(m.group(3)));
            return;
        }
        if (message.equals("TURN YOUR")) {
            serverConnection.playerTurn();
            return;
        }
        if (message.equals("TURN OPPONENT")) {
            serverConnection.opponentTurn();
            return;
        }
        if (message.equals("WIN")) {
            serverConnection.win();
            return;
        }
        if (message.equals("LOSS")) {
            serverConnection.loss();
        }
    }

    @Override
    public void placePawn(int x, int y) {
        serverConnection.sendMessage("PAWN " + x + " " + y);
    }

    @Override
    public void pass() {
        serverConnection.sendMessage("PASS");
    }

    @Override
    public void disconnect() {
        serverConnection.sendMessage("DISCONNECT");
    }
}
