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
        return 1; //TODO
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
