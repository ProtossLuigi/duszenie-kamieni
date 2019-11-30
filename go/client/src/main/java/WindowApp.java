import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

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
    }
}
