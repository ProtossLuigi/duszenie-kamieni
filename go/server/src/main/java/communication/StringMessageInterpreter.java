package communication;

public class StringMessageInterpreter implements MessageInterpreter {

    private ConnectedPlayer player;

    public StringMessageInterpreter(ConnectedPlayer player){
        this.player = player;
    }

    @Override
    public void getMessage(String message) {
        //TODO
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
