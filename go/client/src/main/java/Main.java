import GUI.GUIController;
import GUI.JavaFXController;
import communication.ServerConnection;
import communication.SocketConnection;

public class Main {
    public static void main(String[] args) {
        GUIController guiController = new JavaFXController();
        ServerConnection serverConnection = new SocketConnection();
        guiController.setServerConnection(serverConnection);
        serverConnection.setGUIController(guiController);
        guiController.showWindow();
    }
}
