public interface IEvent {
    void onPlay(Card c, Player currentPlayer, Deck deck);

    Card getCard();

    Player getNextPlayer();

    Deck getDeck();
}
