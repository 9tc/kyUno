import card.Card;
import card.Card.Color;
import card.Card.Mark;

public class Main {
    public static void main(String[] args){

        Deck deck = new Deck();
        for (int i = 0; i < 7; i++) {
            System.out.println(deck.draw().toString());
        }

    }
}