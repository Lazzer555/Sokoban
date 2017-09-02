package Engine;

import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;

/**
 *This is the class that contains the player tile
 * @author 455085
 */
public class PlayerTile extends Sprite{
    
    /**
     *This is the player tile
     * @param y The y coordinate of the player tile
     * @param x The x coordinate of the player tile
     * @param tileSize the size of the tile
     */
    protected PlayerTile(int y, int x, double tileSize){
        
        this.autosize();
        
        this.x = x;
        this.y = y;
        
        this.sprite = new BackgroundImage(new Image("/Resources/Playery1.png",tileSize,tileSize,false,true),BackgroundRepeat.REPEAT, 
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        this.setBackground(new Background(sprite));
       
    }
    
    public PlayerTile(){
        this.sprite = new BackgroundImage(new Image("/Resources/Playery1.png"),BackgroundRepeat.REPEAT, 
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        this.setBackground(new Background(sprite));
    }
}