package sweeper;

public class Flag
{
    private Matrix flagMap;
    private int countClosed;

    void start ()
    {
        flagMap = new Matrix(Box1.CLOSED);
        countClosed = Ranges.getSize().x * Ranges.getSize().y;
    }

    Box1 get (Coord coord)
    {
        return flagMap.get (coord);
    }

    public void setOpenedToBox(Coord coord)
    {
        flagMap.set (coord, Box1.OPENED);
        countClosed --;
    }

    void toggledFlagedBox(Coord coord)
    {
        switch (flagMap.get(coord))
        {
            case FLAGED : setClosedToBox (coord); break;
            case CLOSED : setFlagedToBox(coord); break;
        }
    }

    public void setClosedToBox(Coord coord)
    {
        flagMap.set (coord, Box1.CLOSED);
    }
    public void setFlagedToBox(Coord coord)
    {
        flagMap.set (coord, Box1.FLAGED);
    }

    int getCountClozed ()
    {
        return countClosed;
    }

     void setBombedToBox (Coord coord)
    {
        flagMap.set (coord, Box1.BOMBED);
    }

    void setOpenToBomb (Coord coord)
    {
        if (flagMap.get (coord) ==  Box1.CLOSED)
            flagMap.set (coord, Box1.OPENED);
    }

    void setNoBombToFlag(Coord coord)
    {
        if (flagMap.get (coord) ==  Box1.FLAGED)
            flagMap.set (coord, Box1.NOBOMB);
    }
}