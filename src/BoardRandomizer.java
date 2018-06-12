import java.util.Random;

public class BoardRandomizer
{
    public Random random = new Random();
    public int counter = 0;
    public int number_of_tiles;      //this variable is static and assigned from the users choice in the GUI class
    public int board_dimensions;    // this is the dimenisons of the board.
                                    //it is (number of tiles*2)+1
                                    //in the unlikely case that all the tiles are place in a row in one directions

    public BoardRandomizer()
    {
        //this is the default constructor for the BoardRandomizer
        System.out.println(number_of_tiles);

    }
    public BoardRandomizer(int n)
    {
        number_of_tiles = n;

        board_dimensions = (number_of_tiles*2)+1;
        //System.out.println("this was made in the secondary constructor of Board_Randomizer. this is the placeholder object. number of tiles: "+number_of_tiles);
    }
    public void OriginalFillArray(BoardPiece[][] array)
    {
        for (int r = 0; r < array.length; r++)              //iterate through rows
        {
            for (int c = 0; c < array.length; c++)          //through columns
                array[r][c] = new EmptySlot();              //assign slot to EmptySlot

        }
        array[number_of_tiles][number_of_tiles] = new Tile("s");      //assign middle of array to a tile
    }
    public void Availability(BoardPiece[][] board)
    {
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
                    else if (r == board_dimensions -1 && c == board_dimensions-1)          //bottom left corner
                    {
                        if (board[r-1][c] instanceof EmptySlot)     //make slot above fillable
                            board[r-1][c].fillable = true;
                        if (board[r][c+1] instanceof EmptySlot)     //make slot right fillable
                            board[r][c+1].fillable = true;
                    }
                    else if (r == 0 && c == board_dimensions-1)        //top right corner
                    {
                        if (board[r+1][c] instanceof EmptySlot)     //make slot under fillable
                            board[r+1][c].fillable = true;
                        if (board[r][c-1] instanceof EmptySlot)     //make slot left fillable
                            board[r][c-1].fillable = true;
                    }
                    else if (r == board_dimensions-1 && c == board_dimensions-1)      //bottom right corner
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
                    else if (r == board_dimensions-1)                       //bottom row (excludes corners)
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
                    else if (c == board_dimensions-1)                       //last column (excludes corners)
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
    public void PlaceTile(BoardPiece[][] board)
    {
        int placeholder;                //placeholder that will store the randomized number from 0-100

        for (int r = 0; r < board.length; r++)
        {
            for (int c = 0; c < board.length; c++)
            {
                if (board[r][c].fillable == true)       //if the slot can be filled
                {
                    placeholder = random.nextInt(101);      //randomizer between 0 - 100
                    if (placeholder == 50)                          //if randomized number is
                    {
                        board[r][c] = new Tile();                   //fill the tile
                        counter++;                                  //add one to counter.
                        return;                                     //exit method
                    }
                }
            }
        }
    }
    public void TestGameBoard(BoardPiece[][] board)
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
                    if (board[number_of_tiles][number_of_tiles] == board[r][c])
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
}
