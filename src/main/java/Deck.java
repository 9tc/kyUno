import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private List<Card> deck;
    private int size;
    Deck(){
        deck = new ArrayList<>();
        for (Color c : Color.values()){
            if (c != Color.Black){
                add(new Card(c, Mark.Zero));
                for (int i = 0; i < 2; i++) {
                    add(new Card(c, Mark.One));
                    add(new Card(c, Mark.Two));
                    add(new Card(c, Mark.Three));
                    add(new Card(c, Mark.Four));
                    add(new Card(c, Mark.Five));
                    add(new Card(c, Mark.Six));
                    add(new Card(c, Mark.Seven));
                    add(new Card(c, Mark.Eight));
                    add(new Card(c, Mark.Nine));
                    add(new Card(c, Mark.DrawTwo, new DrawEvent(2)));
                    add(new Card(c, Mark.Reverse, new ReverseEvent()));
                    add(new Card(c, Mark.Skip, new SkipEvent()));
                }
            }else{
                for (int i = 0; i < 4; i++) {
                    add(new Card(c, Mark.Wild, new WildEvent()));
                    add(new Card(c, Mark.WildDrawFour, new WildEvent(new DrawEvent(4))));
                }
            }
        }
        Collections.shuffle(deck);
        size = deck.size();
    }

    private void add(Card card){
        deck.add(card);
    }

    Card draw(){
        size--;
        if (size < 0) return new Card(Color.Red, Mark.Zero);
        return deck.get(size);
    }

    int getSize() {
        return size;
    }

    public boolean isEmpty(){
        return (size == 0);
    }


}
