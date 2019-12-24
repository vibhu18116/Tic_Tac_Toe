package TicTacToe;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Player {

    private String name;
    private int id;
    private static int _id = 0;
    private GridLayout layout;

    Player(String name){
        this.name = name;
        this.id = _id;
        _id++;
    }


    public void setName(String name){
        this.name = name;
    }

    public static void setInitialId(){
        _id = 0;
    }

    public void setLayout(GridLayout l){
        this.layout = l;
    }

    private ImageView assignTokens(){
        if (this.id == 0){
            return new ImageView(new Image("images/zero.png"));
        }else {
            return new ImageView(new Image("images/cross.png"));
        }
    }

    public ImageView getToken(){
        return assignTokens();
    }

    public void move(){
        layout.updateBoard(this);
    }

    public int getPlayerID(){
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public boolean isComputer(){
        if (this.getName().equals("Computer")){
            return true;
        }return false;
    }

}
