import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javax.xml.transform.sax.SAXResult;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class WindowApp  {


    private Group pawnGroup = new Group();
    private Pawn[][] board;
static StartController startController;




    public void start(String boardSize, String address) throws Exception {

        //fxml musi być w resource/fxml bo maven jest niemądry


        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/startView.fxml"));


        Parent root = fxmlLoader.load();

         startController = (StartController) fxmlLoader.getController();
        startController.setBoard(makeMeBoard(9));

        Scene scene = new Scene(root);

        //Scene scene = new Scene(makeMeBoard(9));
        Stage stage = new Stage();

        stage.setTitle("Go");
        stage.setScene(scene);

        stage.show();


        try {
            int port = Integer.parseInt(address);
            System.out.println("Attempting to connect on port " + port);
            Socket socket = new Socket("localhost", port);
            System.out.println("Connected");
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out.println("Client says hello!");
            System.out.println(in.readLine());

        } catch (IOException e) {
            e.printStackTrace();
        }



    }


    public Pane makeMeBoard(int scale) {
        Pane pane = new Pane();

        pane.setPrefSize(6 * 10.0 * Pawn.PAWN_SIZE, 6 * 10.0 * Pawn.PAWN_SIZE);
        BoardCreation boardCreation = new BoardCreation(scale);
        board = boardCreation.board;
        makePiece(pane, scale, board);


        return pane;
    }

    private void makePiece(Pane pane, int scale, Pawn[][] board) {
        pane.setPrefSize(8 * 10.0 * Pawn.PAWN_SIZE, 8 * 10.0 * Pawn.PAWN_SIZE);
        pane.getChildren().addAll(pawnGroup);
        for (int i = 0; i < scale; i++) {
            for (int j = 0; j < scale; j++) {

                Pawn pawn = new Pawn(PawnColors.NONE, i, j);
                board[i][j] = pawn;
                pawnGroup.getChildren().add(pawn);

            }
        }
        for (Pawn[] p :board
        ) {
            for (Pawn pawn:p
                 ) {
                pawn.setOnMouseClicked(event -> {
                    pawn.changeColor(pawn, Color.BLACK);
                });
            }

        }

    }
}
