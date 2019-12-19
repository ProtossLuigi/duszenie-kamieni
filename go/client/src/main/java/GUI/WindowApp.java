package GUI;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import static GUI.Pawn.PAWN_SIZE;

public class WindowApp {


    private Group pawnGroup = new Group();
    private Pawn[][] board;
    static StartController startController;


    public void start(boolean pvp, JavaFXController mainController) throws Exception {

        //fxml musi być w resource/fxml bo maven jest niemądry


        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/startView.fxml"));


        Parent root = fxmlLoader.load();

        Stage stage = new Stage();


        WindowController startController = fxmlLoader.getController();
        startController.setJavaFXController(mainController);
        mainController.setCurrentWindowController(startController);
        Scene scene = new Scene(root);
        stage.setTitle("XD");
        stage.setScene(scene);
        stage.setResizable(false);



        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                Platform.exit();
                System.exit(0);
            }
        });



        stage.show();

        mainController.waitingForOpponent();


    }



}
