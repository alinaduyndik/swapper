package sweeper;

public class Game
{
    private final Bomb bomb;
    private Flag flag;
    private GameState state;
    public GameState getState()
    {
        return state;
    }

    public Game (int cols, int rows, int bombs)
    {
        Ranges.setSize(new Coord(cols, rows));
        bomb = new Bomb(bombs);
        flag = new Flag();
    }

    public void start ()
    {
        bomb.start();
        flag.start();
        state = GameState.PLAYED;
    }

    public Box1 getBox (Coord coord)
    {
        if (flag.get(coord) == Box1.OPENED)
            return bomb.get(coord);
        else
            return flag.get(coord);
    }

    public void pressLeftButton (Coord coord)
    {
        if (gameOver ()) return;
        openBox(coord);
        checkWin();
    }

    private void checkWin()
    {
        if (state == GameState.PLAYED)
            if (flag.getCountClozed() == bomb.getTotalBombs())
                state = GameState.WINNER;
    }

    private void openBox (Coord coord)
    {
        switch (flag.get(coord))
        {
            case OPENED : return;
            case FLAGED : return;
            case CLOSED :
                switch (bomb.get (coord))
                {
                    case ZERO : openBoxesAround (coord); return;
                    case BOMB : openBombs (coord); return;
                    default : flag.setOpenedToBox(coord); return;
                }
        }
    }
    private void openBombs (Coord bombed)
    {
        state = GameState.BOMBED;
        flag.setBombedToBox (bombed);
        for (Coord coord : Ranges.getAllCoords())
            if (bomb.get(coord) == Box1.BOMB)
                flag.setOpenToBomb (coord);
            else
                flag.setNoBombToFlag (coord);
    }

    private void openBoxesAround (Coord coord)
    {
        flag.setOpenedToBox(coord);
        for (Coord around : Ranges.getCoodsAround(coord))
            openBox(around);
    }

    public void pressRightButton (Coord coord)
    {
        if (gameOver ()) return;
        flag.toggledFlagedBox(coord);
    }

    private boolean gameOver()
    {
        if (state ==  GameState.PLAYED)
            return false;
        start();
        return true;
    }
}