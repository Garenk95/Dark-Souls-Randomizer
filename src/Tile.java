import java.util.Random;




public class Tile extends BoardPiece
{
    public Random random = new Random();
    public String name;
    public int n;

    public Tile()
    {
        //when the tile is created it will randomize which
        //side of the tile to place
        n = random.nextInt(2);

        if (n == 0)
            name = "A";
        if (n == 1)
            name = "B";
    }
    public Tile(String s)
    {
        name = s;
    }
}
