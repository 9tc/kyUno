public class SkipEvent implements IEvent {
    Card c;
    Player p;
    Deck deck;
    @Override
    public void onPlay(Card c, Player currentPlayer, Deck deck) {
        this.c = c;
        p = currentPlayer;
        this.deck = deck;
    }

    @Override
    public Card getCard() {
        return c;
    }

    @Override
    public Player getNextPlayer() {
        System.out.println(p.getNextPlayer().getName() + "をSkipしました");
        return p.getNextPlayer().getNextPlayer();
    }

    @Override
    public Deck getDeck() {
        return deck;
    }
}
