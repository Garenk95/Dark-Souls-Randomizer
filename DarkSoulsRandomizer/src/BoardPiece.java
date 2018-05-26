import java.util.Random;

public class BoardPiece
{
    public boolean fillable = false;
    public static int counter = 0;
    public String name;
    public int n;
    public Random dooby = new Random();

    public BoardPiece()
    {
        n = dooby.nextInt(2);       //randomizes either a 0 or 1

        if (n == 0)                         //if n = 0, A side of tile is placed
            name = "A";
        else if (n == 1)
            name = "B";                     //if n = 1, B side of tile is placed
    }
    public BoardPiece(String s)
    {
        name = s;
    }
}
