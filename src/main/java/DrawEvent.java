public class DrawEvent implements IEvent {
    Card c;
    Player p;
    int drawCount;
    Deck deck;

    public DrawEvent(int drawCount) {
        this.drawCount = drawCount;
    }

    @Override
    public void onPlay(Card c, Player currentPlayer, Deck deck) {
        this.c = c;
        p = currentPlayer;
        this.deck = deck;

        for (int i = 0; i < drawCount; i++){
            Card tmp = deck.draw();
            p.getNextPlayer().getHand().add(tmp);
            if (!p.isAI())System.out.println(tmp.toString() + "を引きました(" + (i + 1) + "/" + drawCount + ")");
        }
    }

    @Override
    public Card getCard() {
        return c;
    }

    @Override
    public Player getNextPlayer() {
        return p.getNextPlayer();
    }

    public Deck getDeck() {
        return deck;
    }
}
