package GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Window;

import static GUI.Pawn.PAWN_SIZE;

public class StartController implements WindowController {
    @FXML
    public Text errorText;
    @FXML
    private BorderPane layout;
    @FXML
    private Button start;
    @FXML
    private Button confirm;
    @FXML
    private Button reset;
    @FXML
    private Pane board;

    private JavaFXController mainController;

    public void setBoard(Pane boardChild) {
        board.getChildren().add(boardChild);

    }

    @FXML
    protected void handleStart(ActionEvent event) {
        Window owner = layout.getScene().getWindow();
        AlertHelper.showAlert(Alert.AlertType.INFORMATION, owner, "Start", "Start was clicked");
    }

    @FXML
    protected void handleConfirm(ActionEvent event) {
        Window owner = layout.getScene().getWindow();
        AlertHelper.showAlert(Alert.AlertType.INFORMATION, owner, "Confirm", "Confirm was clicked");
    }

    @FXML
    protected void handleReset(ActionEvent event) {
        Window owner = layout.getScene().getWindow();
        AlertHelper.showAlert(Alert.AlertType.INFORMATION, owner, "Reset", "Reset was clicked");
    }

    @Override
    public void setJavaFXController(JavaFXController controller) {
        mainController = controller;
    }

    @Override
    public void placePawn(int x, int y, int color) {

        Pawn[][] board = mainController.boardCreation.getBoard();

        board[x][y].setColor(board[x][y], PawnColors.fromInt(color));


    }

    @Override
    public void yourTurn() {
        displayMessage("Twoja tura");

    }

    @Override
    public void opponentTurn() {
        displayMessage("Tura przeciwnika");

    }

    @Override
    public void youWin() {
        displayMessage("Wygrałeś");


    }

    @Override
    public void youLose() {
        displayMessage("Przegrałeś");
    }

    @Override
    public void displayMessage(String message) {
        errorText.setText(message);
    }

    @Override
    public void startGame() {


        setBoard(makePiece(mainController.boardCreation.getBoard().length, mainController.boardCreation.getBoard()));


    }

    private Group pawnGroup = new Group();

    private Pane makePiece(int scale, Pawn[][] board) {
        Pane pane = new Pane();
        pane.setPrefSize(8 * 10.0 * PAWN_SIZE, 8 * 10.0 * PAWN_SIZE);
        pane.getChildren().addAll(pawnGroup);
        for (int i = 0; i < scale; i++) {
            for (int j = 0; j < scale; j++) {

                Pawn pawn = new Pawn(PawnColors.NONE, i, j);
                board[i][j] = pawn;
                pawnGroup.getChildren().add(pawn);

                int finalI = i;
                int finalJ = j;
                pawn.setOnMouseClicked(event -> {
                    mainController.attemptSetPawn(finalI, finalJ);


                });

            }
        }


        return pane;

    }


    @Override
    public void waitingForOpponent() {
//todo while nie ma dwoch graczy
        //if true, czekaj na przeciwnika
        // jesli nie odpal bota i startGame


        startGame();


    }
}
