package Engine;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;

/**
 *This class is the main driver class for the program
 * @author 455085
 */
public class Engine {
    
    private File levelFile;
    private int currentLevel = 0;
    private char[][] level;
    private GridPane gameGrid = new GridPane();
    private int[] playerXY = new int[2];
    private final int[] playerMovementModifier = new int[2];
    private int diamondCount = 0;
    private int coveredDiamonds = 0;
    private int moveCounter = 0;
    private final Level levelOps = new Level();
    private final Movement movement = new Movement();
    
     /**
     * This keeps track of the move count
     * @return moveCounter int that contains the amount of movements 
     */
    public int getMoveCounter(){
        return moveCounter;
    }
    
    /**
     * This gets the level pack based on the users choice
     * @param height this is the height of the grid pane
     * @param width this is the width of the grid pane
     * @return gameGrid this is the grid displayed to the user
     * @throws FileNotFoundException if a file is not found
     */
    public GridPane fileChooserManager(double height, double width) throws FileNotFoundException{
        
        
        currentLevel = 0;
        levelFile = levelOps.getLevelFile(levelFile);
        level = levelOps.loadLevel(level, currentLevel, levelFile);
        playerXY = levelOps.getPlayerLocation(level);
        diamondCount = levelOps.GetDiamondCount(level);
        gameGrid = levelOps.changeLevel(height, width, level, currentLevel, levelFile);
        moveCounter = 0;
          
        return gameGrid;
    }
    
    /**
     * This resets the level to its starting condition
     * @param height this is the height of the grid pane
     * @param width this is the width of the grid pane
     * @return gameGrid this is the grid displayed to the user
     * @throws FileNotFoundException  if the file is not found
     */
    public GridPane LevelReset(double height, double width) throws FileNotFoundException{
        try {
            level = levelOps.loadLevel(level, currentLevel, levelFile);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Engine.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        playerXY = levelOps.getPlayerLocation(level);
        diamondCount = levelOps.GetDiamondCount(level);
        gameGrid = levelOps.changeLevel(height, width, level, currentLevel, levelFile);
        moveCounter = 0;
        
        return gameGrid;
    }
    
    /**
     * This is the move north method
     * @param height this is the height of the grid pane
     * @param width this is the width of the grid pane
     * @return gameGrid this is the grid displayed to the user
     * @throws FileNotFoundException  if the file is not found
     */
    public GridPane MoveNorth(double height, double width) throws FileNotFoundException{
            
            playerMovementModifier[0] = -1;
            playerMovementModifier[1] = 0;
            level = movement.movement(level, playerMovementModifier, playerXY);
            coveredDiamonds = levelOps.GetCoveredDiamondCount(level);
            
            if(diamondCount == coveredDiamonds){
                currentLevel++;
                level = levelOps.loadLevel(level, currentLevel, levelFile);
                playerXY = levelOps.getPlayerLocation(level);
                diamondCount = levelOps.GetDiamondCount(level);
                gameGrid = levelOps.changeLevel(height, width, level, currentLevel, levelFile);
                coveredDiamonds = 0;
                moveCounter = 0;
            }else{
                playerXY = levelOps.getPlayerLocation(level);
                moveCounter ++;
            }
            
            this.gameGrid = levelOps.redraw(level, height, width);
            
            
        return gameGrid;
    }
    
    /**
     * This is the move south method
     * @param height this is the height of the grid pane
     * @param width this is the width of the grid pane
     * @return gameGrid this is the grid displayed to the user
     * @throws FileNotFoundException  if the file is not found
     */
    public GridPane MoveSouth(double height, double width) throws FileNotFoundException{
            
            playerMovementModifier[0] = 1;
            playerMovementModifier[1] = 0;
            level = movement.movement(level, playerMovementModifier, playerXY);
            coveredDiamonds = levelOps.GetCoveredDiamondCount(level);
            
           
            
            if(diamondCount == coveredDiamonds){
                currentLevel++;
                level = levelOps.loadLevel(level, currentLevel, levelFile);
                diamondCount = levelOps.GetDiamondCount(level);
                playerXY = levelOps.getPlayerLocation(level);
                gameGrid = levelOps.changeLevel(height, width, level, currentLevel, levelFile);
                coveredDiamonds = 0;
                moveCounter = 0;
            }else{
                playerXY = levelOps.getPlayerLocation(level);
                moveCounter ++;
            }
            
            this.gameGrid = levelOps.redraw(level, height, width);
            
        return gameGrid;
    }
   
     /**
     * This is the move east method
     * @param height this is the height of the grid pane
     * @param width this is the width of the grid pane
     * @return gameGrid this is the grid displayed to the user
     * @throws FileNotFoundException  if the file is not found
     */
    public GridPane MoveEast(double height, double width) throws FileNotFoundException{
            
            playerMovementModifier[0] = 0;
            playerMovementModifier[1] = 1;
            level = movement.movement(level, playerMovementModifier, playerXY);
            coveredDiamonds = levelOps.GetCoveredDiamondCount(level);
            
            if(diamondCount == coveredDiamonds){
                currentLevel++;
                level = levelOps.loadLevel(level, currentLevel, levelFile);
                diamondCount = levelOps.GetDiamondCount(level);
                playerXY = levelOps.getPlayerLocation(level);
                gameGrid = levelOps.changeLevel(height, width, level, currentLevel, levelFile);
                coveredDiamonds = 0;
                moveCounter = 0;
            }else{
                playerXY = levelOps.getPlayerLocation(level);
                moveCounter ++;
            }
            
            this.gameGrid = levelOps.redraw(level, height, width);            
            
        return gameGrid;
    }
    
     /**
     * This is the west north method
     * @param height this is the height of the grid pane
     * @param width this is the width of the grid pane
     * @return gameGrid this is the grid displayed to the user
     * @throws FileNotFoundException  if the file is not found
     */
    public GridPane MoveWest(double height, double width) throws FileNotFoundException{
            
            playerMovementModifier[0] = 0;
            playerMovementModifier[1] = -1;
            level = movement.movement(level, playerMovementModifier, playerXY);
            coveredDiamonds = levelOps.GetCoveredDiamondCount(level);
            
            if(diamondCount == coveredDiamonds){
                currentLevel++;
                level = levelOps.loadLevel(level, currentLevel, levelFile);
                playerXY = levelOps.getPlayerLocation(level);
                diamondCount = levelOps.GetDiamondCount(level);
                gameGrid = levelOps.changeLevel(height, width, level, currentLevel, levelFile);
                coveredDiamonds = 0;
                moveCounter = 0;
            }else{
                playerXY = levelOps.getPlayerLocation(level);
                moveCounter ++;
            }
            
            this.gameGrid = levelOps.redraw(level, height, width);
            
        return gameGrid;
    }
   
}
