import card.Card;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

class Game {
    private Player[] player;
    private Deck deck;
    private Card fieldCard;
    private Scanner sc = new Scanner(System.in);
    private int turn;
    private Player currentPlayer;
    Game(int playerCount, int aiCount){
        deck = new Deck();
        turn = 0;
        player = new Player[playerCount + aiCount];
        for (int i = 0; i < playerCount; i++){
            System.out.print("(Input) Player" + (i+1) + "'s name : ");
            player[i] = new Player(sc.next(),false);
            sc.nextLine();
        }
        for (int i = 0; i < aiCount; i++){
            player[i + playerCount] = new Player("AI[" + (i + 1) + "]",true);
        }
        Collections.shuffle(Arrays.asList(player));
    }

    void run() {
        setUp();
        while (checkHand() || deck.getSize() <= 0){
            turn++;
            System.out.println(turn + "ターン目 " + currentPlayer.getName() +"のターンです [Field : "+ fieldCard.toString() +"]");

            if (!currentPlayer.isAI()) {
                System.out.println(currentPlayer.getHand().toString());
                System.out.print("(Input)出すカードの番号(出せるカードがない場合0) : ");
                int i = -1;
                while (i == -1){
                    String tmp = sc.nextLine();
                    if (tmp.matches("[0-9]+") && Integer.parseInt(tmp) <= currentPlayer.getHand().getSize() &&
                            (Integer.parseInt(tmp) == 0 || currentPlayer.getHand().get(Integer.parseInt(tmp) - 1).isDiscardable(fieldCard))){
                        i = Integer.parseInt(tmp);
                    }else{
                        System.out.print("(Input)不正 : ");
                    }
                }
                i--;
                if (i == -1) {
                    currentPlayer.getHand().add(deck.draw());
                    System.out.println("Success!!" + currentPlayer.getHand().get(currentPlayer.getHand().getSize() - 1) + "をひきました\n");
                }else{
                    fieldCard = currentPlayer.getHand().play(i);
                    System.out.println("Success! \n");
                }
                currentPlayer = currentPlayer.getNextPlayer();
            }
        }

    }

    private boolean checkHand(){
        for (Player p: player){
            if(p.getHand().getSize() == 0) return false;
        }
        return true;
    }

    private void setUp() {
        for (Player p : player){
            for (int i = 0; i < 7; i++){
                p.getHand().add(deck.draw());
            }
        }
        do {
            fieldCard = deck.draw();
        }while (fieldCard.getColor() == Card.Color.Black);

        System.out.println();
        for (Player p : player){
            System.out.print("-> " + p.getName() + " ");
        }
        System.out.println("->");

        for (int i = 0; i < player.length - 1; i++){
            player[i].setNextPlayer(player[i + 1]);
        }
        player[player.length - 1].setNextPlayer(player[0]);
        currentPlayer = player[0];
    }
}
