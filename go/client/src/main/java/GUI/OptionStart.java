package GUI;

import GUI.JavaFXController;
import GUI.WindowController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class OptionStart extends Application {

    public static void startWindow(JavaFXController javaFXController){
        mainController = javaFXController;
        launch();
    }

    private static JavaFXController mainController;

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/optionView.fxml"));
        Parent root = fxmlLoader.load();
        WindowController optionController = fxmlLoader.getController();
        optionController.setJavaFXController(mainController);
        mainController.setCurrentWindowController(optionController);
        Scene scene = new Scene(root);
        stage.setTitle("Go");
        stage.setScene(scene);
        stage.show();
    }
}
