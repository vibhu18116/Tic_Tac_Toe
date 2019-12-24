package TicTacToe;

import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

public class TicTacToe {

    private GridLayout currentLayout;
    private static int turn = 0;   //0 for player 1; 1 for player 2
    private Player p1;
    private Player p2;
    private boolean gameTerminated = false;
    private Player winner;

    TicTacToe(GridPane gpane, Player p1, Player p2){
        currentLayout = new GridLayout(gpane);
        this.p1 = p1;
        this.p2 = p2;
        this.turn = 0;
        p1.setLayout(currentLayout);
        p2.setLayout(currentLayout);
    }

    public void update(){

        try{
            if (!gameTerminated){
                if (turn == 0) {
                    p1.move();
                    turn = 1;

                    if (p2.isComputer()){
                        p2.move();
                        turn = 0;
                    }
                }
                else {
                    p2.move();
                    turn = 0;
                }
            }
        }catch (GameWonException ex){
            gameOver();
        }catch (GameOverException ex){
            this.gameTerminated = true;
            this.winner = null;
        }catch (CellAlreadyOccupiedException ex){}

    }

    public void gameOver(){
        this.gameTerminated = true;
        if (turn == 0){
            this.winner = p1;
        }else {
            this.winner = p2;
        }
    }

    public boolean checkIfGameOver(){
        return gameTerminated;
    }

    public Player getWinner(){
        return winner;
    }
}
