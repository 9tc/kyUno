package card;

public class Card {

    private final Color color;
    private final Mark mark;

    public enum Type{
        Number,
        Mark
    }

    public enum Color{
        Red,
        Blue,
        Green,
        Yellow,
        Black
    }

    public enum Mark{
        Zero,
        One,
        Two,
        Three,
        Four,
        Five,
        Six,
        Seven,
        Eight,
        Nine,
        Reverse,
        Skip,
        DrawTwo,
        Wild,
        WildDrawFour
    }


    public Card(Color color, Mark mark){
        this.color = color;
        this.mark = mark;
    }

    public Color getColor() {
        return color;
    }

    public Mark getMark() {
        return mark;
    }

    public int getCardScore(){
        return 1; //TODO
    }

    public boolean isDiscardable(Card placedCard){
        if (placedCard.getColor() == Color.Black || placedCard.getColor() == color || placedCard.getMark() == mark){
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return mark.toString() + " (" + color.toString() + ")";
    }
}
