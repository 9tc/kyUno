import card.Card;
import card.Card.Color;
import card.Card.Mark;

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
                    add(new Card(c, Mark.DrawTwo));
                    add(new Card(c, Mark.Reverse));
                    add(new Card(c, Mark.Skip));
                }
            }else{
                for (int i = 0; i < 4; i++) {
                    add(new Card(c, Mark.Wild));
                    add(new Card(c, Mark.WildDrawFour));
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
        return deck.get(size);
    }

    int getSize() {
        return size;
    }

    public boolean isEmpty(){
        return (size == 0);
    }
}
