package sweeper;
//хранение всех элементов
public enum Box1
{
    ZERO,
    NUM1,
    NUM2,
    NUM3,
    NUM4,
    NUM5,
    NUM6,
    NUM7,
    NUM8,
    BOMB,
    OPENED,
    CLOSED,
    FLAGED,
    BOMBED,
    NOBOMB;

    public Object image;
    Box1 getNextNum ()
    {
        return Box1.values()[this.ordinal() + 1];
    }
}