import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class DS_GUI extends Application
{
    private Scene scene;
    private Scene scene2;
    public Image black_tile = new Image("black_tile.png");
    public Image white_tile = new Image("white_tile.png");
    public BoardRandomizer board_creator;   //BoardRandomizer is a class that has all the methods to build the imaginary board
    public BoardPiece[][] gameboard;        //2d array of BoardPiece objects. Helpful to think of it as an actual board game with the slot being either empty, or a tile
    private ChoiceBox<Integer>choiceBox;

    public static void main (String[]args)
    {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage)
    {
        //set the title of the Stage
        primaryStage.setTitle("Dark-Souls-Randomizer");

        //Add a label that prompts user to use choicebox
        Label prompt = new Label("Number of tiles:");

        //Add a choicebox with options 2-8 to choose from
        choiceBox = new ChoiceBox <>();
        choiceBox.getItems().addAll(2,3,4,5,6,7,8);
        choiceBox.setValue(2);

        //Add a button that will return the number of tiles that the user chose
        Button button = new Button("Randomize!");

        //this is where most of the program will happen most probably.
        //the board_creator object is created here and this object will
        // put together the "imaginary" game board
        //then a seperate method will be called to create scene 2 (or scene 2 will just have to be created in this lambda)
        //then it will switch the scene to scene 2
        button.setOnAction(event -> {
                                        board_creator = new BoardRandomizer(getChoice(choiceBox));            //create board_creator and give it the int from choicebox (number_of_tiles)

                                        gameboard = new BoardPiece[board_creator.board_dimensions][board_creator.board_dimensions];     //create 2d array of BoardPiece objects

                                        board_creator.OriginalFillArray(gameboard);                                        //method that will fill array with all emptySlot objects, and one tile in the middle

                                        while (board_creator.counter < board_creator.number_of_tiles)              //while counter is less than the numberoftiles
                                        {
                                            board_creator.Availability(gameboard);                                  //go through the gameboard and check to see what slots are available
                                            board_creator.PlaceTile(gameboard);                                     //give every fillable slot a 1/100 chance of being filled with a tile, then fill it
                                        }

                                        board_creator.TestGameBoard(gameboard);

                                        GridPane grid = new GridPane();
                                        grid.setHgap(8);
                                        grid.setVgap(8);

                                        //create a 2d array of image view with same dimensions as the board
                                        ImageView[][] image_array = new ImageView[board_creator.board_dimensions][board_creator.board_dimensions];
                                        for (int y = 0; y < gameboard.length; y++)
                                        {
                                            for (int x = 0; x < gameboard.length; x++)
                                            {
                                                if (gameboard[y][x] instanceof Tile)
                                                {
                                                    //in here will assign a new image to a placeholder variable and then
                                                    //add it to the grid
                                                    image_array[y][x] = new ImageView(black_tile);
                                                    grid.add(image_array[y][x], x,y);
                                                }
                                                    else
                                                {
                                                    image_array[y][x] = new ImageView(white_tile);
                                                    grid.add(image_array[y][x],x , y);
                                                }
                                            }
                                        }
                                        int scene_dimensions = CalculateSceneTwoDimensions(8,board_creator.number_of_tiles);

                                        scene2 = new Scene(grid, scene_dimensions,scene_dimensions);

                                        primaryStage.setScene(scene2);



                                        //System.exit(0);
                                    });

        //add all the elements (prompt/choicebox/button) to a Vbox
        VBox layout = new VBox(10);
        layout.getChildren().addAll(prompt, choiceBox, button);
        layout.setAlignment(Pos.CENTER);

        //give the VBox layout an id to style with css
        layout.setId("layout");

        //Create the scene
        scene = new Scene(layout, 620, 349);


        //Add stylesheets to the scene
        scene.getStylesheets().addAll("MainMenu.css");

        //Set the scene to the stage (primaryStage)
        primaryStage.setScene(scene);


        //keep the menu from changing size (for background purposes)
        primaryStage.setResizable(false);

        //show the stage
        primaryStage.show();
    }
    private int getChoice(ChoiceBox<Integer> box)
    {
        //.getValue gets the currently selected option from the choice box
        //then stores it in an integer "n" and returns it to
        //number_of_tiles integer for the board randomizer class
        int n = choiceBox.getValue();
        return n;
    }
    public int CalculateSceneTwoDimensions(int total_padding, int total_tile)
    {
        int d;

        //total padding is the total amount of space between images displayed on the grid
        total_padding =(board_creator.number_of_tiles*2)*8;

        //total_tile is the total amount of space the tiles take up
        total_tile = board_creator.board_dimensions*50;

        //add those two together for the required dimensions for scene two
        d = total_padding + total_tile;
        return d;
    }


}
