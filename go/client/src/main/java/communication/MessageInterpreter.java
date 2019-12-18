package communication;

public interface MessageInterpreter {

    void setServerConnection(ServerConnection serverConnection);
    void getMessage(String message);
    void placePawn(int x,int y);
    void pass();
    void disconnect();
    void lfg(boolean pvp,int boardWidth,int boardHeight);
}
