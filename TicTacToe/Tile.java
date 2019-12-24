package TicTacToe;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Tile extends Pane {

    private int row;
    private int col;
    private ImageView token;
    private static Tile activeTile;
    private boolean filled = false;

    public Tile(int row, int col){
        this.row = row;
        this.col = col;
        token = null;
        setOnMouseClicked(e->{
            activeTile = this;
        });
    }

    public static void setActiveTile(Tile tile){
        activeTile = tile;
    }

    public static ImageView updateTile(Player p){
        ImageView token = p.getToken();
        token.setFitHeight(50);
        token.setFitWidth(50);

        activeTile.token = token;
        activeTile.filled = true;
        return token;
    }

    public static int getActiveTileX(){
        return activeTile.row;
    }

    public static int getActiveTileY(){
        return activeTile.col;
    }

    public static boolean getFilledStatusOfActiveTile(){
        return activeTile.filled;
    }

    public boolean getFilledStatus(){
        return filled;
    }

}
