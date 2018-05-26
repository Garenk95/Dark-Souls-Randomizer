import java.util.Scanner;
import java.util.Random;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application
{
    Button b2;
    Button b3;
    Button b4;
    Button b5;
    Button b6;
    Button b7;
    Button b8;
    Stage window;
    Scene scene1, scene2;
    public static int numberOfTiles;                            //numberOfTiles is the # of tiles that will be placed on the board,
    public static int boardDimensions;                          //because of that board dimensions are equal to ((# of tiles)*2)+1
                                                                //in the unlikely case that the randomizer places 9 tiles in a row
    public static void main(String[]args)                       //in any direction
    {
        Scanner input = new Scanner(System.in);

        launch(args);                       //sets up program as a javafx application
                                            //then calls the 'start' method

        //System.out.println("Welcome to Garen's Dark Souls Board Game Randomizer 2: Electric Boogaloo!");

        //System.out.println("Enter number of tiles:");
        //numberOfTiles = input.nextInt();

        //boardDimensions = (numberOfTiles*2)+1;

        //BoardPiece[][] gameboard = new BoardPiece[boardDimensions][boardDimensions];

        //OriginalFillArray(gameboard);

        //while (BoardPiece.counter < numberOfTiles)
        //{
        //    Availability(gameboard);
        //    PlaceTile(gameboard);
        //}
        //DisplayFinalBoard(gameboard);

        //System.out.println("S = Starting Tile");
        //System.out.println("A = a side of tile");
        //System.out.println("B = b side of tile");
    }
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        window = primaryStage;

        primaryStage.setTitle("Dark Souls Board Game Randomizer");

        Label label1 = new Label("Choose number of tiles:");

        b2 = new Button("2");
        b2.setOnAction(e -> DoesItAll(2));
        b3 = new Button("3");
        b3.setOnAction(e -> DoesItAll(3));
        b4 = new Button("4");
        b4.setOnAction(e -> DoesItAll(4));
        b5 = new Button("5");
        b5.setOnAction(e -> DoesItAll(5));
        b6 = new Button("6");
        b6.setOnAction(e -> DoesItAll(6));
        b7 = new Button("7");
        b7.setOnAction(e -> DoesItAll(7));
        b8 = new Button("8");
        b8.setOnAction(e -> DoesItAll(8));

        //Scene 1 gui here
        VBox layout1 = new VBox(10);
        layout1.getChildren().addAll(label1, b2, b3, b4, b5, b6, b7, b8);
        layout1.setAlignment(Pos.CENTER);
        scene1 = new Scene(layout1, 500,500);




        window.setScene(scene1);
        window.show();

    }

    public static void OriginalFillArray(BoardPiece[][] board, int n)
    {
        //as the name states this is the first method called to fill the "board"
        //it will iterate through rows and columns and then place a "starting" tile in the
        //middle of the board

        for(int r = 0; r < board.length; r++)                   //iterate through rows
        {
            for(int c = 0; c < board.length; c++)               //iterate through columns
            {
                    board[r][c] = new EmptySlot();              //fill slot with object of EmptySlot();
            }
            System.out.println();                           //print new line at the end of every row
        }
        String s = "SS";
        board[n][n] = new Tile(s);        //put starting tile in middle of board
    }
    public static void Availability(BoardPiece[][] board)
    {
        //this method will iterate through board until it finds a "tile" object
        //once it finds a tile object it will change the boolean "fillable" of every
        //EmptySlot object around it

        for (int r = 0; r < board.length; r++)
        {
            for (int c = 0; c < board.length; c++)
            {
                if (board[r][c] instanceof Tile)                                //once an object of type Tile is found
                {
                    if (r == 0 && c == 0)           //top left corner
                    {
                        if (board[r+1][c] instanceof EmptySlot)     //make slot under fillable
                            board[r+1][c].fillable = true;
                        if (board[r][c+1] instanceof EmptySlot)     //make slot right fillable
                            board[r][c+1].fillable = true;
                    }
                    else if (r == boardDimensions -1 && c == boardDimensions-1)          //bottom left corner
                    {
                        if (board[r-1][c] instanceof EmptySlot)     //make slot above fillable
                            board[r-1][c].fillable = true;
                        if (board[r][c+1] instanceof EmptySlot)     //make slot right fillable
                            board[r][c+1].fillable = true;
                    }
                    else if (r == 0 && c == boardDimensions-1)        //top right corner
                    {
                        if (board[r+1][c] instanceof EmptySlot)     //make slot under fillable
                            board[r+1][c].fillable = true;
                        if (board[r][c-1] instanceof EmptySlot)     //make slot left fillable
                            board[r][c-1].fillable = true;
                    }
                    else if (r == boardDimensions-1 && c == boardDimensions-1)      //bottom right corner
                    {
                        if (board[r][c-1] instanceof EmptySlot)     //make slot left fillable
                            board[r][c-1].fillable = true;
                        if (board[r-1][c] instanceof EmptySlot)     //make slot above fillable
                            board[r-1][c].fillable = true;
                    }
                    else if(r == 0)                                //top row (excludes corners)
                    {
                        if (board[r+1][c] instanceof EmptySlot)     //make slot under fillable
                            board[r+1][c].fillable = true;
                        if (board[r][c-1] instanceof EmptySlot)     //make slot left fillable
                            board[r][c-1].fillable = true;
                        if (board[r][c+1] instanceof EmptySlot)     //make slot right fillable
                            board[r][c+1].fillable = true;
                    }
                    else if (r == boardDimensions-1)                       //bottom row (excludes corners)
                    {
                        if (board[r-1][c] instanceof EmptySlot)     //make slot above fillable
                            board[r-1][c].fillable = true;
                        if (board[r][c-1] instanceof EmptySlot)     //make slot left fillable
                            board[r][c-1].fillable = true;
                        if (board[r][c+1] instanceof EmptySlot)     //make slot right fillable
                            board[r][c+1].fillable = true;
                    }
                    else if (c == 0)                                     //first column (excludes corners)
                    {
                        if (board[r-1][c] instanceof EmptySlot)     //make slot above fillable
                            board[r-1][c].fillable = true;
                        if (board[r+1][c] instanceof EmptySlot)     //make slot under fillable
                            board[r+1][c].fillable = true;
                        if (board[r][c+1] instanceof EmptySlot)     //make slot right fillable
                            board[r][c+1].fillable = true;
                    }
                    else if (c == boardDimensions-1)                       //last column (excludes corners)
                    {
                        if (board[r-1][c] instanceof EmptySlot)     //make slot above fillable
                            board[r-1][c].fillable = true;
                        if (board[r+1][c] instanceof EmptySlot)     //make slot under fillable
                            board[r+1][c].fillable = true;
                        if (board[r][c-1] instanceof EmptySlot)     //make slot left fillable
                            board[r][c-1].fillable = true;
                    }
                    else
                    {
                        if (board[r-1][c] instanceof EmptySlot)     //make slot above fillable
                            board[r-1][c].fillable = true;
                        if (board[r+1][c] instanceof EmptySlot)     //make slot under fillable
                            board[r+1][c].fillable = true;
                        if (board[r][c-1] instanceof EmptySlot)     //make slot left fillable
                            board[r][c-1].fillable = true;
                        if (board[r][c+1] instanceof EmptySlot)     //make slot right fillable
                            board[r][c+1].fillable = true;
                    }
                }
            }
        }
    }
    public static void PlaceTile(BoardPiece[][] board)
    {
        Random dice = new Random();                         //create a random object

        for (int r = 0; r < board.length; r++)              //iterate through
        {
            for (int c = 0; c < board.length; c++)
            {
                if (board[r][c].fillable == true)            //if the tile can be filled
                {
                    int n = dice.nextInt(101);
                    if (n == 50)
                    {
                        board[r][c] = new Tile();
                        BoardPiece.counter++;
                        return;
                    }
                }
            }
        }
    }
    public static void DisplayFinalBoard(BoardPiece[][] board, int n)
    {
        for (int r = 0; r < board.length; r++)
        {
            for (int c = 0; c < board.length; c++)
            {
                if (board[r][c] instanceof EmptySlot)
                {
                    System.out.print("- ");
                }
                else if (board[r][c] instanceof Tile)
                {
                    if (board[n][n] == board[r][c])
                    {
                        System.out.print("S ");
                    }
                    else
                    {
                        System.out.print(board[r][c].name+" ");
                    }
                }
                else
                {
                }
            }
            System.out.println();
        }
    }
    public static void DoesItAll(int n)
    {
        boardDimensions = (n*2)+1;

        BoardPiece[][] gameboard = new BoardPiece[boardDimensions][boardDimensions];

        OriginalFillArray(gameboard, n);

        while (BoardPiece.counter < n)
        {
            Availability(gameboard);
            PlaceTile(gameboard);
        }
        DisplayFinalBoard(gameboard, n);

    }
}
