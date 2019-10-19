class Player {
    private int aiLevel;
    private Hand hand;
    private String name;
    private Player nextPlayer;

    Player(String name, int aiLevel){
        this.name = name;
        this.aiLevel = aiLevel;
        hand = new Hand();
    }

    Hand getHand() {
        return hand;
    }

    boolean isAI() {
        return aiLevel != 0;
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
