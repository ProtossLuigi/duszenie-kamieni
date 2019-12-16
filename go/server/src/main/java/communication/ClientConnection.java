package communication;

public interface ClientConnection {
    boolean setupAccess(int port);
    void listen();
}
