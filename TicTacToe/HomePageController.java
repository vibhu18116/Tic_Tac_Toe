package TicTacToe;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomePageController implements Initializable {
    public Button againstPlayer;
    public Button againstComputer;
    public Button okbutton;
    public TextField p2NameIn;
    public TextField p1NameIn;
    public Label p2Name;
    public Label p1Name;

    private String player2;
    private String player1;

    public void againstComputer(MouseEvent mouseEvent) {
        player2 = "Computer";
        activateOnePlayer();
    }

    public void againstPlayer(MouseEvent mouseEvent) {
        player2 = "none";
        activateBothPlayers();
    }

    private void activateOnePlayer(){
        deactivateOther();
        p1Name.setVisible(true);
        p1NameIn.setVisible(true);
        okbutton.setVisible(true);
    }

    private void deactivateOther(){
        p2NameIn.setVisible(false);
        p2Name.setVisible(false);
    }

    private void activateBothPlayers(){
        activateOnePlayer();
        p2NameIn.setVisible(true);
        p2Name.setVisible(true);
    }

    private void loadMainGame() throws IOException {

        FXMLLoader fl = new FXMLLoader(getClass().getResource("../fxml/mainGamePage.fxml"));
        Parent root = fl.load();
        ((MainGamePageController) fl.getController()).initialisePlayers(player1, player2);
        Stage current = (Stage) okbutton.getScene().getWindow();
        ((MainGamePageController) fl.getController()).setPrevious(current);
        current.setScene(new Scene(root));
        current.show();
    }

    public void doneWithInput(MouseEvent mouseEvent) throws IOException {
        player1 = p1NameIn.getText();
        if (player2.equals("none")){
            player2 = p2NameIn.getText();
        }

        loadMainGame();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        p1NameIn.setVisible(false);
        p2NameIn.setVisible(false);
        p1Name.setVisible(false);
        p2Name.setVisible(false);
        okbutton.setVisible(false);
    }
}
