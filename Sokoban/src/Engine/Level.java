package Engine;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.FileChooser;

/**
 *
 * @author 455085
 */
public class Level {
    
    private GridPane gameGrid = new GridPane();
    
    /**
     *This method gets the level pack that the user selects
     * 
     * @param levelFile This is the selected level pack
     * @return levelFile This is the level pack that the user selects 
     */
    protected File getLevelFile(File levelFile){
        
        //Create a new file chooser
        final FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Level Pack");
        
        //Sets the level file to the user selected text file
        levelFile = fileChooser.showOpenDialog(null);
        
        return levelFile;
    }
    
    /**
     * This method populates the game grid from a level file that the user has selected
     * whenever the current level is completed or the user opens a new level pack
     * 
     * @param level The grid that the game is displayed through
     * @param currentLevel integer used for what level is currently being played
     * @param levelFile the file that holds all the levels
     * @return level grid
     * @throws FileNotFoundException
     */
    protected char[][] loadLevel(char[][] level, int currentLevel, File levelFile) throws FileNotFoundException{
        
        //Variables to be used in reading the file
        String line;
        int levelCounter = 0;
        char[] charLine;
        int maxRows = 0;
        int maxColumns = 0;
        ArrayList x = new ArrayList();
        ArrayList<ArrayList> y = new ArrayList<>();
        String characterString = "";
        boolean firstWall = false;
        char character = ' ';
        
        try{
        //File reader and scanner used for reading file
        FileReader fr = new FileReader(levelFile);
        Scanner sc = new Scanner(fr);
                
        //if there is still text in the file
        if(sc.hasNext()){
            
            //do while levelCounter does not equal current level
            while(levelCounter != currentLevel){
                
                //take a line from the text file and store it
                line = sc.nextLine();  
                
                //if the line starts with ";" it is the end of a level
                if(line.startsWith(";")){
                    
                    //Add one to level counter
                    levelCounter++;
                }//end if
            }//end while
            
            //get the first or next line in the text file
            line = sc.nextLine();
            
            //If the string is a dividing string
            if(line.isEmpty()){
                // get the next line
                line = sc.nextLine();
     
            }//end if
            
            while(!line.startsWith(";")){
                charLine = line.toCharArray();
                
                if(line.length() > maxColumns){
                    maxColumns = line.length();
                }
                
                //add all the characters from the string into an arraylist
                for(int i = 0; i < line.length(); i++){
                    x.add(charLine[i]);
                }//end if
                
                y.add(x);
                line = sc.nextLine();
                maxRows++;
                x = new ArrayList<>();
            }
        }
        
        //Set size of level array
        level = new char[maxRows][maxColumns];
        
        //populate Level array from arraylist 
        for(int i = 0; i < maxRows; i++){
            firstWall = false;
            for(int j = 0; j < y.get(i).size(); j++){
                
                characterString = y.get(i).get(j).toString(); 
                character = characterString.charAt(0);
                
                if(i == 0 || i == maxRows -1){
                    level[i][j] = '#';
                }else if(character == '#' && firstWall == false){
                    firstWall = true;
                    level[i][j] = character;
                }else if(firstWall == false){
                    level[i][j] = '#';
                }else if(firstWall == true){
                    level[i][j] = character;
                }
            }
        }
        }catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Please load a level pack in a txt format with the standard sokoban level format.");
            alert.showAndWait();
        }
        return level;
               
    }
    
    /**
     *This method changes the level when the user completes the
     * level that they are currently playing.
     * 
     * @param height how tall the grid can be
     * @param width how wide the grid can be
     * @param level The grid that displays the game
     * @param currentLevel intager storing the currently open level
     * @param levelFile The file that has all the levels stored in
     * @return gamegrid which is then displayed to the user
     * @throws FileNotFoundException
     */
    protected GridPane changeLevel(double height, double width, char[][] level, int currentLevel, File levelFile) throws FileNotFoundException{
        
        //Get the appropriate level to be used in filling the grid
        level = loadLevel(level, currentLevel, levelFile);
        
        //Variables to be used in setting the size of the grid and the tiles
        int yLength = level.length;
        int xLength = level[0].length;
        double tileHeight = height / yLength; 
        double tileWidth = width / xLength;
        double tileSize = 0;
        
        //Set the size of the tile
        if (tileHeight < tileWidth){
            tileSize = tileHeight;
        }else if(tileWidth < tileHeight){
            tileSize = tileWidth;
        }
               
        //Clear the grid
        gameGrid = new GridPane();
        gameGrid.setPadding(new Insets(25,25,25,25));
               
        //Fill grid with apropriatetiles
        for(int i = 0; i<xLength; i++){
            for(int j = 0; j<yLength; j++){
                switch (level[j][i]) {
                    case '#':
                        gameGrid.getColumnConstraints().add(new ColumnConstraints(tileSize));
                        gameGrid.getRowConstraints().add(new RowConstraints(tileSize));
                        gameGrid.add(new WallTile(j,i,tileSize),i,j);
                        break;
                    case '$':
                        gameGrid.getColumnConstraints().add(new ColumnConstraints(tileSize));
                        gameGrid.getRowConstraints().add(new RowConstraints(tileSize));
                        gameGrid.add(new CrateTile(j,i,tileSize),i,j);
                        break;
                    case '@':
                        gameGrid.getColumnConstraints().add(new ColumnConstraints(tileSize));
                        gameGrid.getRowConstraints().add(new RowConstraints(tileSize));
                        gameGrid.add(new PlayerTile(j,i, tileSize),i,j);
                        break;
                    case '.':
                        gameGrid.getColumnConstraints().add(new ColumnConstraints(tileSize));
                        gameGrid.getRowConstraints().add(new RowConstraints(tileSize));
                        gameGrid.add(new DiamondTile(j,i, tileSize),i,j);
                        break;
                    case ' ':
                        gameGrid.getColumnConstraints().add(new ColumnConstraints(tileSize));
                        gameGrid.getRowConstraints().add(new RowConstraints(tileSize));
                        gameGrid.add(new FloorTile(j,i,tileSize),i,j);
                        break;
                    case '+':
                        gameGrid.getColumnConstraints().add(new ColumnConstraints(tileSize));
                        gameGrid.getRowConstraints().add(new RowConstraints(tileSize));
                        gameGrid.add(new PlyDiaTile(j,i,tileSize),i,j);
                        break;
                    case '*':
                        gameGrid.getColumnConstraints().add(new ColumnConstraints(tileSize));
                        gameGrid.getRowConstraints().add(new RowConstraints(tileSize));
                        gameGrid.add(new BoxDiaTile(j,i, tileSize),i,j);
                        break;
                    default:
                        gameGrid.getColumnConstraints().add(new ColumnConstraints(tileSize));
                        gameGrid.getRowConstraints().add(new RowConstraints(tileSize));
                        gameGrid.add(new WallTile(j,i,tileSize),i,j);
                        break;
                }//end switch
            }//end for
        }//end for
        
        //return grid with level
        return gameGrid;
    }
   
    /**
     *This gets the players X and Y location in the grid
     * 
     * @param level
     * @return PlayerXY an integer array of 2 spaces with the x and y cords stored
     */
    protected int[] getPlayerLocation(char[][] level){
        
        int yLength = level.length;
        int xLength = level[0].length;
        int[] playerXY = new int[2];
        
        for(int i = 0; i<xLength; i++){
            for(int j = 0; j<yLength; j++){
                if(level[j][i] == '@' || level[j][i] == '+'){
                    playerXY[0] = j;
                    playerXY[1] = i;
                }//end if
            }//end for
        }//end for
        return playerXY;
    }
   
    /**
     *This method redraws the game grid 
     * 
     * @param level This is a character array that stores the level
     * @param height This is the max height that the grid can be
     * @param width This is the max width that the grid can be
     * @return gamegrid which is then displayed to the user
     */
    protected GridPane redraw(char[][] level, double height, double width){
       
        int yLength = level.length;
        int xLength = level[0].length;
        double tileHeight = height / yLength; 
        double tileWidth = width / xLength;
        double tileSize = 0;
        
        //Gets the max size that the tile can be
        if (tileHeight < tileWidth){
            tileSize = tileHeight;
        }else if(tileWidth < tileHeight){
            tileSize = tileWidth;
        }
        
        //Clear the grid
        gameGrid = new GridPane();
        gameGrid.setPadding(new Insets(25,25,25,25));
               
        //Fill grid with apropriatetiles
        for(int i = 0; i<xLength; i++){
            for(int j = 0; j<yLength; j++){
                switch (level[j][i]) {
                    case '\0':
                        gameGrid.getColumnConstraints().add(new ColumnConstraints(tileSize));
                        gameGrid.getRowConstraints().add(new RowConstraints(tileSize));
                        gameGrid.add(new WallTile(j,i,tileSize),i,j);
                        break;
                    case '#':
                        gameGrid.getColumnConstraints().add(new ColumnConstraints(tileSize));
                        gameGrid.getRowConstraints().add(new RowConstraints(tileSize));
                        gameGrid.add(new WallTile(j,i,tileSize),i,j);
                        break;
                    case '$':
                        gameGrid.getColumnConstraints().add(new ColumnConstraints(tileSize));
                        gameGrid.getRowConstraints().add(new RowConstraints(tileSize));
                        gameGrid.add(new CrateTile(j,i,tileSize),i,j);
                        break;
                    case '@':
                        gameGrid.getColumnConstraints().add(new ColumnConstraints(tileSize));
                        gameGrid.getRowConstraints().add(new RowConstraints(tileSize));
                        gameGrid.add(new PlayerTile(j,i, tileSize),i,j);
                        break;
                    case '.':
                        gameGrid.getColumnConstraints().add(new ColumnConstraints(tileSize));
                        gameGrid.getRowConstraints().add(new RowConstraints(tileSize));
                        gameGrid.add(new DiamondTile(j,i, tileSize),i,j);
                        break;
                    case ' ':
                        gameGrid.getColumnConstraints().add(new ColumnConstraints(tileSize));
                        gameGrid.getRowConstraints().add(new RowConstraints(tileSize));
                        gameGrid.add(new FloorTile(j,i,tileSize),i,j);
                        break;
                    case '+':
                        gameGrid.getColumnConstraints().add(new ColumnConstraints(tileSize));
                        gameGrid.getRowConstraints().add(new RowConstraints(tileSize));
                        gameGrid.add(new PlyDiaTile(j,i,tileSize),i,j);
                        break;
                    case '*':
                        gameGrid.getColumnConstraints().add(new ColumnConstraints(tileSize));
                        gameGrid.getRowConstraints().add(new RowConstraints(tileSize));
                        gameGrid.add(new BoxDiaTile(j,i, tileSize),i,j);
                        break;
                    default:
                        gameGrid.getColumnConstraints().add(new ColumnConstraints(tileSize));
                        gameGrid.getRowConstraints().add(new RowConstraints(tileSize));
                        gameGrid.add(new WallTile(j,i,tileSize),i,j);
                        break;
                }//end switch
            }//end for
        }//end for
        
        //return grid with level
       return gameGrid;
   }
    
    /**
     *This method gets the amount of diamonds in a level
     * 
     * @param level Character array that stores the currently loaded level
     * @return diamondCount which is 
     */
    protected int GetDiamondCount(char[][] level){
       
       int diamondCount = 0;
       int yLength = level.length;
       int xLength = level[0].length;
       
       for(int i = 0; i<xLength; i++){
            for(int j = 0; j<yLength; j++){
                if(level[j][i] == '*' || level[j][i] == '.'){
                    diamondCount++;
                }
            }
       }
       
       return diamondCount;
   }
   
    /**
     *Gets the amount of diamonds that already have boxes on top of them
     * 
     * @param level Character grid that stores the currently loaded
     * @return diamondCount the amount of diamonds in the level
     */
    protected int GetCoveredDiamondCount(char[][] level){
       
       int diamondCount = 0;
       int yLength = level.length;
       int xLength = level[0].length;
       
       for(int i = 0; i<xLength; i++){
            for(int j = 0; j<yLength; j++){
                if(level[j][i] == '*'){
                    diamondCount++;
                }
            }
       }
       
       return diamondCount;
   }

}


