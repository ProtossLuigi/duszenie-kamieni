import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class WindowApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        //fxml musi być w resource/fxml bo maven jest niemądry
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/startView.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Go");
        stage.setScene(scene);
        stage.show();
        ArrayList<String> args = new ArrayList<>(getParameters().getRaw());
        try {
            int port = Integer.parseInt(args.get(0));
            System.out.println("Attempting to connect on port " + port);
            Socket socket = new Socket("localhost",port);
            System.out.println("Connected");
            PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out.println("Client says hello!");
            System.out.println(in.readLine());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
