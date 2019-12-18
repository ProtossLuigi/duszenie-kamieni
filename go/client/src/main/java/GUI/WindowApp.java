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

    public Pane makeMeBoard(int scale) {
        Pane pane = new Pane();

        pane.setPrefSize(6 * 10.0 * PAWN_SIZE, 6 * 10.0 * PAWN_SIZE);
        BoardCreation boardCreation = new BoardCreation();
        boardCreation.setSizeBoard(scale, scale);


        board = boardCreation.getBoard();
        makePiece(pane, scale, board);


        return pane;
    }


    private void makePiece(Pane pane, int scale, Pawn[][] board) {
        pane.setPrefSize(8 * 10.0 * PAWN_SIZE, 8 * 10.0 * PAWN_SIZE);
        pane.getChildren().addAll(pawnGroup);
        for (int i = 0; i < scale; i++) {
            for (int j = 0; j < scale; j++) {

                Pawn pawn = new Pawn(PawnColors.NONE, i, j);
                board[i][j] = pawn;
                pawnGroup.getChildren().add(pawn);

            }
        }
        for (Pawn[] p : board
        ) {
            for (Pawn pawn : p
            ) {
                pawn.setOnMouseClicked(event -> {

                    //todo zamiast kolor funkcja który gracz teraz ma ruch
                    pawn.drawPiece(pawn, Color.BLACK);
                });
            }

        }

    }


}
