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
}
