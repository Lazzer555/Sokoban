/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Engine;

import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;



/**
 *This is the class for the crate tile
 * @author 455085
 */
public class CrateTile extends Sprite{
    
    /**
     *This is a crate tile
     * 
     * @param x this is the x coordinate for the tile
     * @param y this is the y coordinate for the tile
     * @param tileSize this is the size of the tile
     */
    public CrateTile(int x, int y, double tileSize){
        
        this.autosize();
        this.x = x;
        this.y = y;
        
        this.sprite = new BackgroundImage(new Image("/Resources/Crate.png",tileSize,tileSize,false,true),BackgroundRepeat.REPEAT, 
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        this.setBackground(new Background(sprite));
       
    }

}
