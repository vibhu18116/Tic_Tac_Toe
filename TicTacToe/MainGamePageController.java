package TicTacToe;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainGamePageController implements Initializable {
    public GridPane gpane;
    public Label gameStatus;
    public Button restartButton;
    public Button backButton;
    private TicTacToe tttGame;
    private Player p1;
    private Player p2;

    private Stage previous;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Player.setInitialId();
        p1 = new Player("");
        p2 = new Player("");
      tttGame =  Main.getGameReference();
      tttGame = new TicTacToe(gpane, p1, p2);
    }

    public void initialisePlayers(String name1, String name2){
        p1.setName(name1);
        p2.setName(name2);
    }

    public void setPrevious(Stage stage){
        this.previous = stage;
    }



    public void startGame(MouseEvent mouseEvent) {
        tttGame.update();

        if (tttGame.checkIfGameOver()){
            Player p = tttGame.getWinner();

            if (p == null){
                gameStatus.setText("Game Drawn!!");
            }else {
                gameStatus.setText("Game Won by " + p.getName());
            }
        }
    }

    public void restartGame(MouseEvent mouseEvent) {
        Player.setInitialId();
        gpane.getChildren().clear();
        p1 = new Player(p1.getName());
        p2 = new Player(p2.getName());
        tttGame = new TicTacToe(gpane, p1, p2);
        gameStatus.setText("");
    }

    public void toLastScreen(MouseEvent mouseEvent) throws IOException {
       // Player.setInitialId();
        Parent p = FXMLLoader.load(getClass().getResource("../fxml/homePage.fxml"));
        Scene s = new Scene(p);
        previous.setScene(s);
        previous.show();
    }
}
