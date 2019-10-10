class Player {
    private boolean isAI;
    private Hand hand;
    private String name;
    private Player nextPlayer;

    Player(String name, boolean isAI){
        this.name = name;
        this.isAI = isAI;
        hand = new Hand();
    }

    Hand getHand() {
        return hand;
    }

    boolean isAI() {
        return isAI;
    }

    String getName() {
        return name;
    }

    void setNextPlayer(Player nextPlayer) {
        this.nextPlayer = nextPlayer;
    }

    Player getNextPlayer() {
        return nextPlayer;
    }
}
