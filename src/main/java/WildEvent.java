import java.util.Scanner;

public class WildEvent implements IEvent {
    Card c;
    Player p;
    Deck deck;
    IEvent nextEvent;
    Scanner sc = new Scanner(System.in);
    public WildEvent(){
        nextEvent = new NoneEvent();
    }
    public WildEvent(IEvent event) {
        nextEvent = event;
    }

    @Override
    public void onPlay(Card c, Player currentPlayer, Deck deck) {
        this.c = c;
        p = currentPlayer;
        this.deck = deck;

        System.out.print("(Input) Select Color(1 = Red, 2 = Blue, 3 = Green, 4 = Yellow)");
        int i = -1;
        while (i == -1){
            String tmp = sc.nextLine();
            if (tmp.matches("[1-4]")){
                i = Integer.parseInt(tmp);
            }else{
                System.out.print("(Input)不正 : ");
            }
        }
        switch (i){
            case 1: c.setColor(Color.Red); break;
            case 2: c.setColor(Color.Blue); break;
            case 3: c.setColor(Color.Green); break;
            case 4: c.setColor(Color.Yellow); break;
        }
        nextEvent.onPlay(c, p, deck);
    }

    @Override
    public Card getCard() {
        return c;
    }

    @Override
    public Player getNextPlayer() {
        return nextEvent.getNextPlayer();
    }

    @Override
    public Deck getDeck() {
        return nextEvent.getDeck();
    }
}
