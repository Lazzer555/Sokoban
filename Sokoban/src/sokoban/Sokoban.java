package sokoban;

import Engine.Engine;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *This class is the User interface for the program
 * @author 455085
 */
public class Sokoban extends Application {
    
    //Create a game engine
    private final Engine engine = new Engine();
    
    //Create the variables for the user interface
    private Stage primaryStage;
    private Scene scene;
    private GridPane gameGrid;    
    private int moveCounter = 0;
    
    @Override
    public void start(Stage primaryStage) {
         
        //sets the stage
        this.primaryStage = primaryStage;
        
        //Create the logo image
        Label lblLogo = new Label();
        ImageView logoView = new ImageView("/Resources/sokobanLogo1.png");
        lblLogo.setGraphic(logoView);
        lblLogo.setMaxSize(1920, 1080);
        
        //Create the label for movment counter    
        Label lblMovCounter = new Label();
        lblMovCounter.setText("Moves: " + moveCounter);
        
        //Create the buttons to be used in the menu
        Button btnNewGame = new Button("New Game");
        Button btnReset = new Button("Reset");
        Button btnExit = new Button("Exit");
        
        //Set the size of the buttons
        btnNewGame.setPrefSize(100 ,20);
        btnReset.setPrefSize(100 ,20);
        btnExit.setPrefSize(100 ,20);
        
        
        //Creating a VBox pane to store the buttons in
        VBox menu = new VBox();
        menu.setPadding(new Insets(25,25,25,25));
        menu.setSpacing(10);
        
        //Adding the buttons to the VBox menu
        menu.getChildren().addAll(btnNewGame, btnReset, btnExit, lblMovCounter);
                
        //Creates a borderpane layout to be used as the main container
        BorderPane border = new BorderPane();
         
        //Creates a new scene and adds the layout to it
        this.scene = new Scene(border, 700, 500);
        
        //Create a pane to hold the grid
        Pane gridContainer = new Pane();
        
        //Create the grid to run the game in
        this.gameGrid = new GridPane();
        gameGrid.setPadding(new Insets(25,25,25,25));
        gameGrid.setGridLinesVisible(true);
        gameGrid.getColumnConstraints().add(new ColumnConstraints(border.getWidth() - 200));
        gameGrid.getRowConstraints().add(new RowConstraints(border.getHeight() - 50));
        gameGrid.getChildren().add(lblLogo);
        logoView.setFitHeight(border.getHeight() - 50);
        logoView.setFitWidth(border.getWidth() - 200);
        
        //Adds the gridpane to the gridContainer pane
        gridContainer.getChildren().add(gameGrid);
        
        //Add the components to the boarder pane
        border.setLeft(menu);
        border.setCenter(gridContainer);
        
        //Set the title of the window
        primaryStage.setTitle("Sokoban");
        
        //Sets the primary stage to be resizeable
        primaryStage.setMinHeight(520);
        primaryStage.setMinWidth(700);
        primaryStage.setResizable(true);
        
        //Adds the scene to the stage and then displays
        primaryStage.setScene(scene);
        primaryStage.show();
        
        //Sets the button listener for the new game feature
        btnNewGame.setOnAction((ActionEvent e) -> {
            try {
                this.gameGrid = engine.fileChooserManager(border.getHeight() - 50, border.getWidth() - 200);
                gridContainer.getChildren().clear();
                gridContainer.getChildren().add(gameGrid);
                moveCounter = engine.getMoveCounter();
                lblMovCounter.setText("Moves: " + moveCounter);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Sokoban.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        //Sets the button listener for the exit button
        btnReset.setOnAction((ActionEvent e) -> {
            try {
                this.gameGrid = engine.LevelReset(border.getHeight() - 50, border.getWidth() - 200);
                gridContainer.getChildren().clear();
                gridContainer.getChildren().add(gameGrid);
                moveCounter = engine.getMoveCounter();
                lblMovCounter.setText("Moves: " + moveCounter);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Sokoban.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        btnExit.setOnAction((ActionEvent e) -> {
           primaryStage.close();
        });
        
        
        //Calls the movement depending on keyboard key presses
        scene.setOnKeyPressed((KeyEvent event) -> {
            if (null != event.getCode())switch (event.getCode()) {
                //If W is pressed on the keyboard call the north movement
                case W:
                {
                    try {
                        gameGrid = engine.MoveNorth(border.getHeight() - 50, border.getWidth() - 200);
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(Sokoban.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                gridContainer.getChildren().clear();
                gridContainer.getChildren().add(gameGrid);
                break;
                //If S is pressed on the keyboard call the south movement
                case S:
                {
                    try {
                        gameGrid = engine.MoveSouth(border.getHeight() - 50, border.getWidth() - 200);
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(Sokoban.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                gridContainer.getChildren().clear();
                gridContainer.getChildren().add(gameGrid);
                break;
                //If D is pressed on the keyboard call the east movement
                case D:
                {
                    try {
                        gameGrid = engine.MoveEast(border.getHeight() - 50, border.getWidth() - 200);
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(Sokoban.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                gridContainer.getChildren().clear();
                gridContainer.getChildren().add(gameGrid);
                break;
                //If A is pressed on the keyboard call the west movement
                case A:
                {
                    try {
                        gameGrid = engine.MoveWest(border.getHeight() - 50, border.getWidth() - 200);
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(Sokoban.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                gridContainer.getChildren().clear();
                gridContainer.getChildren().add(gameGrid);
                break;
                default:
                    break;
            }
            moveCounter = engine.getMoveCounter();
            lblMovCounter.setText("Moves: " + moveCounter);
        });
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
