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
            if (!p.getNextPlayer().isAI()) System.out.println(p.getNextPlayer().getName() + "が" + tmp.toString() + "を引きました(" + (i + 1) + "/" + drawCount + ")");
            else System.out.println(p.getNextPlayer().getName() + "がカードを1枚引きました(" + (i + 1) + "/" + drawCount + ")");
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
