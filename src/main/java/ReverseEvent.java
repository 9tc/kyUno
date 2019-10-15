public class ReverseEvent implements IEvent {
    Card c;
    Player p;
    Deck deck;
    @Override
    public void onPlay(Card c, Player currentPlayer, Deck deck) {
        this.c = c;
        p = currentPlayer;
        this.deck = deck;

        //GameClass
    }

    @Override
    public Card getCard() {
        return c;
    }

    @Override
    public Player getNextPlayer() {
        return p.getNextPlayer();
    }

    @Override
    public Deck getDeck() {
        return deck;
    }
}
