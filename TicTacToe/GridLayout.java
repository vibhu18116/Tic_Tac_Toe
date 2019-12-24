package TicTacToe;

import javafx.animation.Timeline;
import javafx.scene.layout.GridPane;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class GridLayout {

    private Tile [][] board = new Tile[3][3];
    private int [][] board_im = {{-1,-1,-1}, {-1, -1, -1}, {-1, -1, -1}};
    private GridPane gridPane;
    /*[0,1,2] - 0
      [0,1,2] - 1
      [0,1,2] - 2
     */

    public GridLayout(GridPane gpane){
        gridPane = gpane;

        for (int i = 0; i<3; i++){
            for (int j = 0; j<3; j++){
                Tile tile = new Tile(i, j);
                tile.setPrefSize(100, 30);
                gpane.add(tile, j, i);
                board[i][j] = tile;
            }
        }
    }

    private boolean found(int so){
        for (int i = 0; i<3; i++){
            for (int j = 0; j<3; j++){
                if (board_im[i][j] == -1){
                    board_im[i][j] = so;
                    if (checkIfWon()){
                        board_im[i][j] = -1;
                        Tile.setActiveTile(board[i][j]);
                        return true;
                    }

                    board_im[i][j] = -1;
                }
            }
        }

        return false;
    }

    public void updateBoard(Player p) {


        if (p.isComputer()){


            boolean tileSet;

            tileSet = found(1);

            if (!tileSet){
                tileSet = found(0);
            }


            if (!tileSet){
                if (!(board[1][1]).getFilledStatus()){
                    Tile.setActiveTile(board[1][1]);
                    tileSet = true;
                }
            }

            if (!tileSet){
                Random rand = new Random();

                while (true){
                    int row = rand.nextInt(3);
                    int col = rand.nextInt(3);

                    if (!(board[row][col]).getFilledStatus()){
                        Tile.setActiveTile(board[row][col]);
                        break;
                    }
                }
            }

        }

        if (!Tile.getFilledStatusOfActiveTile()){
            gridPane.add(Tile.updateTile(p), Tile.getActiveTileY(), Tile.getActiveTileX());
            board_im[Tile.getActiveTileX()][Tile.getActiveTileY()] = p.getPlayerID();
            if (checkIfWon()){
                throw new GameWonException();
            }
        }else {
            throw new CellAlreadyOccupiedException();
        }

        if (checkIfAllCellsOccupied()){
            throw new GameOverException();
        }
    }

    private boolean checkIfAllCellsOccupied(){
        for (int i = 0; i<3; i++){
            for (int j = 0; j<3; j++){
                if (board_im[i][j]==-1){
                    return false;
                }
            }
        }return true;
    }

    private boolean checkIfWon(){

        for (int i = 0; i<3; i++){
            if (checkRow(i) || checkColumn(i)){
                return true;
            }
        }

        return checkDiagonal();

    }

    private boolean checkRow(int r){
        if (board_im[r][0] == board_im[r][1] && board_im[r][1] == board_im[r][2] && board_im[r][0]!=-1){
            return true;
        }return false;
    }

    private boolean checkColumn(int c){
        if (board_im[0][c] == board_im[1][c] && board_im[1][c] == board_im[2][c] && board_im[0][c]!=-1){
            return true;
        }return false;
    }

    private boolean checkDiagonal(){
        if (board_im[0][0] == board_im[1][1] && board_im[1][1] == board_im[2][2] && board_im[0][0]!=-1){
            return true;
        }else if (board_im[0][2] == board_im[1][1] && board_im[1][1] == board_im[2][0] && board_im[1][1]!=-1){
            return true;
        }return false;
    }

}

class GameWonException extends RuntimeException{

    GameWonException(){
        super();
    }
}

class GameOverException extends RuntimeException{

    GameOverException(){
        super();
    }
}

class CellAlreadyOccupiedException extends RuntimeException{

    CellAlreadyOccupiedException(){
        super();
    }
}
