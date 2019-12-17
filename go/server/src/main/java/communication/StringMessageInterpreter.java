package communication;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringMessageInterpreter implements MessageInterpreter {

    private ConnectedPlayer player;
    private Pattern pawnPattern = Pattern.compile("PAWN (\\d+) (\\d+)");

    public StringMessageInterpreter(ConnectedPlayer player){
        this.player = player;
    }

    @Override
    public void getMessage(String message) {
        Matcher matcher;
        matcher = pawnPattern.matcher(message);
        if (matcher.find()) {
            player.attemptPlacePawn(Integer.parseInt(matcher.group(1)),Integer.parseInt(matcher.group(2)));
            return;
        }
        if (message.equals("PASS")) {
            player.pass();
        }
        if (message.equals("DISCONNECT")) {
            player.disconnect();
        }
    }

    @Override
    public void sendChatMessage(String message) {
        player.sendMessage("MESSAGE " + message);
    }

    @Override
    public void placePawn(int x, int y, int color) {
        player.sendMessage("PAWN " + x + " " + y + " " + color);
    }

    @Override
    public void yourTurn() {
        player.sendMessage("TURN YOUR");
    }

    @Override
    public void opponentTurn() {
        player.sendMessage("TURN OPPONENT");
    }

    @Override
    public void win() {
        player.sendMessage("WIN");
    }

    @Override
    public void lose() {
        player.sendMessage("LOSS");
    }
}
