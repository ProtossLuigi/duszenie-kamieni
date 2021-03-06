package main;

import communication.ClientConnection;
import communication.SocketConnection;
import database.DatabaseAccess;
import database.HibernateMysqlDatabase;

public class Main {
    public static void main(String[] args) {
        ConsoleWriter.setPrintStream(System.out);
        int port = 0;
        for (int i = 0; i< args.length; i++) {
            if (args[i].equals("-p")) {
                port = Integer.parseInt(args[i+1]);
            }
        }
        ClientConnection connection = new SocketConnection();
        connection.setupAccess(port);
        DatabaseAccess.databaseAdapter = new HibernateMysqlDatabase();
        connection.listen();
    }
}
