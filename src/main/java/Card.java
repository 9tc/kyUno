public class Card {

    private Color color;
    private final Mark mark;
    private IEvent event;


    public Card(Color color, Mark mark){
        this.color = color;
        this.mark = mark;
        this.event = new NoneEvent();
    }
    public Card(Color color, Mark mark, IEvent event){
        this.color = color;
        this.mark = mark;
        this.event = event;
    }

    public Color getColor() {
        return color;
    }
    public void setColor(Color c) {this.color = c;}

    public Mark getMark() {
        return mark;
    }

    public int getCardScore(){
        switch (mark){
            case One: return 1;
            case Two: return 2;
            case Three: return 3;
            case Four: return 4;
            case Five: return 5;
            case Six: return 6;
            case Seven: return 7;
            case Eight: return 8;
            case Nine: return 9;
            case Zero: return 0;
            case DrawTwo:
            case Skip:
            case Reverse: return 20;
            case Wild:
            case WildDrawFour: return 50;
        }
        return 0;
    }

    public boolean isDiscardable(Card placedCard){
        return color == Color.Black || placedCard.getColor() == color || placedCard.getMark() == mark;
    }

    @Override
    public String toString() {
        return mark.toString() + " (" + color.toString() + ")";
    }

    public IEvent getEvent() {
        return event;
    }
}
