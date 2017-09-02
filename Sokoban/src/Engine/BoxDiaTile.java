package Engine;

import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;

/**
 *This is the class for a box on a diamond object
 * 
 * @author 455085
 */
public class BoxDiaTile extends Sprite{
    
    /**
     *This creates a box on a diamond tile and sets the sprite
     * 
     * @param x this is the x coordinate of the tile
     * @param y this is the y coordinate of the tile
     * @param tileSize this is the size of the tile
     */
    public BoxDiaTile(int x, int y, double tileSize){
        
        this.autosize();
        this.x = x;
        this.y = y;
       this.sprite = new BackgroundImage(new Image("/Resources/CrateOnDiamond.png",tileSize,tileSize,false,true),BackgroundRepeat.REPEAT, 
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        this.setBackground(new Background(sprite));
    }
    
}
