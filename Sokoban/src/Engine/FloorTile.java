package Engine;

import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;

/**
 *this is the class for the floor tile
 * 
 * @author 455085
 */
public class FloorTile extends Sprite{
    
    /**
     *This creates the floor tile at the given x and y coordinate
     * 
     * @param x this is the x coordinate
     * @param y this is the y coordinate
     * @param tileSize this is the size of the tile
     */
    public FloorTile(int x, int y, double tileSize){
     
        this.autosize();
        this.x = x;
        this.y = y;
        this.sprite = new BackgroundImage(new Image("/Resources/Floor.png",tileSize,tileSize,false,true),BackgroundRepeat.REPEAT, 
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        this.setBackground(new Background(sprite));
       
    }
    
}
