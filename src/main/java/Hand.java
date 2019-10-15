import java.util.ArrayList;
import java.util.List;

public class Hand {
    private List<Card> hand;

    Hand(){
        hand = new ArrayList<>();
    }

    void add(Card card){
        hand.add(card);
    }

    Card get(int i){
        return hand.get(i);
    }

    Card play(int i){
        return hand.remove(i);
    }

    @Override
    public String toString() {
        StringBuilder r = new StringBuilder();
        for (int i = 0; i < hand.size(); i++){
            r.append("(").append(i + 1).append(") ").append(hand.get(i).toString()).append(i < hand.size() - 1 ? "\n" : "");
        }
        return r.toString();
    }

    public int getScore(){
        int r = 0;
        for (Card i : hand){
            r += i.getCardScore();
        }
        return r;
    }

    int getSize(){
        return hand.size();
    }
}
