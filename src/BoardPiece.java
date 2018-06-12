import java.util.Random;

//A 2d array of objects is made from this class
//this class has to kids, tile and empty slot
//a tile is always placed in the middle of the array

public class BoardPiece
{
    public boolean fillable = false;        //this will only be true for EmptySlot objects that are adjacent to a tile
    public static int counter = 0;
    public String name;                     //the name of the object is to differntiate which side of the tile is placed, A or B (S is for starting tile)
    public int n;
    public Random random = new Random();

    public BoardPiece()
    {
        //when the tile is created it will randomize which
        //side of the tile to place
        n = random.nextInt(2);

        if (n == 0)
            name = "A";
        if (n == 1)
            name = "B";
    }
    public BoardPiece(String s)
    {
        name = "S";
    }

}
