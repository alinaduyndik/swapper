package sweeper;

class Matrix
{
    private Box1 [] [] matrix;
    Matrix (Box1 defaultBox1)
    {
        matrix = new Box1[Ranges.getSize().x][Ranges.getSize().y];
        for (Coord coord : Ranges.getAllCoords())
            matrix [coord.x] [coord.y] = defaultBox1;
    }

    Box1 get (Coord coord)
    {
        if (Ranges.inRange (coord))
            return matrix [coord.x] [coord.y];
        return null;
    }

    void set (Coord coord, Box1 box)
    {
        if (Ranges.inRange (coord))
            matrix [coord.x] [coord.y] = box;
    }
}