package sweeper;

import java.util.Random;

public class Bomb
{
    private Matrix bombMap;
    private int totalBombs;

    Bomb (int totalBombs)
    {
        this.totalBombs = totalBombs;
    }

    void start ()
    {
        bombMap = new Matrix(Box1.ZERO);
        for (int j = 0; j < totalBombs; j++)
            placeBomb ();
    }

    Box1 get (Coord coord)
    {
        return bombMap.get(coord);
    }

    private void placeBomb ()
    {
        while (true)
        {
            Coord coord = Ranges.getRandomCoord();
            if (Box1.BOMB == bombMap.get(coord))
                continue;
            bombMap.set (coord, Box1.BOMB);
            incNumbersAround(coord);
            break;
        }
    }

    private void incNumbersAround (Coord coord)
    {
        for (Coord around : Ranges.getCoodsAround(coord))
            if (Box1.BOMB != bombMap.get (around))
                bombMap.set (around, bombMap.get(around).getNextNum());
    }

    int getTotalBombs ()
    {
        return totalBombs;
    }
}