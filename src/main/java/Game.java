import java.util.*;

class Game {
    private Player[] player;
    private Deck deck;
    private List<Card> graveyard;
    private Card fieldCard;
    private Scanner sc = new Scanner(System.in);
    private int turn;
    private Player currentPlayer;
    private boolean isReversed;

    Game(int playerCount, int aiCount){
        player = new Player[playerCount + aiCount];
        for (int i = 0; i < playerCount; i++){
            System.out.print("(Input) Player" + (i+1) + "の名前 : ");
            player[i] = new Player(sc.next(),0);
            sc.nextLine();
        }
        for (int i = 0; i < aiCount; i++){
            System.out.print("AI[" + (i + 1) + "] のレベル (1: Easy, 2:Normal) : ");
            int t = 0;
            while (t == 0){
                String tmp = sc.nextLine();
                if (tmp.matches("[1-2]")){
                    t = Integer.parseInt(tmp);
                }else{
                    System.out.print("(Input) 不正 : ");
                }
            }
            player[i + playerCount] = new Player("AI[" + (i + 1) + "]", t);
        }
    }

    void run() {
        setUp();
        while (!checkAllPlayerHandsIsEmpty() && !checkDeckIsEmpty()){
            turn++;
            System.out.println(turn + "ターン目 " + currentPlayer.getName() +"のターンです [Field : "+ fieldCard.toString() +"]");

            if (!currentPlayer.isAI()) {
                System.out.println(currentPlayer.getHand().toString());
                System.out.print("(Input) 出すカードの番号(出せるカードがない場合0) : ");
                int i = -1;
                while (i == -1){
                    String tmp = sc.nextLine();
                    if (tmp.matches("[0-9]+") && (Integer.parseInt(tmp) <= currentPlayer.getHand().getSize()) &&
                            ((Integer.parseInt(tmp) == 0) || currentPlayer.getHand().get(Integer.parseInt(tmp) - 1).isDiscardable(fieldCard))){
                        i = Integer.parseInt(tmp);
                    }else{
                        System.out.print("(Input) 不正 : ");
                    }
                }
                i--;
                if (i == -1) {
                    currentPlayer.getHand().add(deck.draw());
                    System.out.println(currentPlayer.getHand().get(currentPlayer.getHand().getSize() - 1) + "をひきました\n");
                    currentPlayer = currentPlayer.getNextPlayer();
                }else{
                    graveyard.add(fieldCard);
                    Card c = currentPlayer.getHand().play(i);
                    c.getEvent().onPlay(c, currentPlayer, deck);
                    fieldCard = c.getEvent().getCard();
                    if(c.getMark().equals(Mark.Reverse)) {
                        reverseTask(isReversed);
                        isReversed = !isReversed;
                    }
                    if (currentPlayer.getHand().getSize() == 1)System.out.println(currentPlayer.getName() + " -> UNO!!");
                    System.out.print("(Input) カードを重ねますか?(Yes:1 No:0): ");
                    while (sc.nextLine().equals("1")){
                        System.out.println(currentPlayer.getHand().toString());
                        System.out.print("(Input) 出すカードの番号(カードを出さない:0) : ");
                        int il = -1;
                        while (il == -1){
                            String tmp = sc.nextLine();
                            if (tmp.matches("[0-9]+") && (Integer.parseInt(tmp) <= currentPlayer.getHand().getSize()) &&
                                    ((Integer.parseInt(tmp) == 0) ||
                                            (currentPlayer.getHand().get(Integer.parseInt(tmp) - 1).isNumber()&&
                                            currentPlayer.getHand().get(Integer.parseInt(tmp) - 1).getMark().equals(fieldCard.getMark())&&
                                            currentPlayer.getHand().get(Integer.parseInt(tmp) - 1).getColor().equals(fieldCard.getColor())))){
                                il = Integer.parseInt(tmp);
                            }else{
                                System.out.print("(Input) 不正 : ");
                            }
                        }
                        if (il == 0)System.out.println("ターンを終了します");
                        else{
                            il--;
                            graveyard.add(fieldCard);
                            c = currentPlayer.getHand().play(i);
                            c.getEvent().onPlay(c, currentPlayer, deck);
                            fieldCard = c.getEvent().getCard();
                            if(c.getMark().equals(Mark.Reverse)) {
                                reverseTask(isReversed);
                                isReversed = !isReversed;
                            }
                            if (currentPlayer.getHand().getSize() == 1)System.out.println(currentPlayer.getName() + " -> UNO!!");
                            if (fieldCard.isNumber())System.out.print("(Input) さらにカードを重ねますか?(Yes:1 No:0): ");
                            else break;
                        }
                    }
                    currentPlayer = c.getEvent().getNextPlayer();
                    deck = c.getEvent().getDeck();
                }
            }else{
                Boolean placed = false;
                for (int i = 0; i < currentPlayer.getHand().getSize(); i++){
                    if (currentPlayer.getHand().get(i).isDiscardable(fieldCard)){
                        graveyard.add(fieldCard);
                        Card c = currentPlayer.getHand().play(i);
                        c.getEvent().onPlay(c, currentPlayer, deck);
                        fieldCard = c.getEvent().getCard();
                        if(c.getMark().equals(Mark.Reverse)) {
                            reverseTask(isReversed);
                            isReversed = !isReversed;
                        }
                        System.out.println(currentPlayer.getName() + "が" + fieldCard.toString() + "を出しました (手札はあと" + currentPlayer.getHand().getSize() +"枚)");
                        if (currentPlayer.getHand().getSize() == 1)System.out.println(currentPlayer.getName() + " -> UNO!!");
                        currentPlayer = c.getEvent().getNextPlayer();
                        deck = c.getEvent().getDeck();
                        placed = true;
                        break;
                    }
                }
                if (!placed){
                    currentPlayer.getHand().add(deck.draw());
                    System.out.println(currentPlayer.getName() + "がデッキからカードを1枚ひきました\n");
                    currentPlayer = currentPlayer.getNextPlayer();
                }
            }
        }

        System.out.println("\n||||ゲーム終了||||\n");
        showResult(player);
    }

    private void showResult(Player[] player) {
        HashMap h = new HashMap();
        for (Player p : player){
            h.put(p.getName(), p.getHand().getScore());
        }
        List<Map.Entry<String, Integer>> list_entries = new ArrayList<Map.Entry<String, Integer>>(h.entrySet());
        Collections.sort(list_entries, Comparator.comparing(Map.Entry::getValue));

        System.out.println("結果");
        int i = 1;
        for(Map.Entry<String, Integer> entry : list_entries) {
            System.out.println(i++ + "位: " + entry.getKey() + " : " + entry.getValue()+ "点");
        }
    }

    private boolean checkDeckIsEmpty() {
        if (deck.isEmpty()){
            System.out.println("デッキが0枚になったためゲームを終了します");
            return true;
        }else return false;
    }

    private void reverseTask(boolean isReversed) {
        if (isReversed){
            for (int i = 0; i < player.length - 1; i++){
                player[i].setNextPlayer(player[i + 1]);
            }
            player[player.length - 1].setNextPlayer(player[0]);
        }else {
            for (int i = 0; i < player.length - 1; i++) {
                player[i + 1].setNextPlayer(player[i]);
            }
            player[0].setNextPlayer(player[player.length - 1]);
        }

        System.out.print("Reversed!  ");


    }

    private boolean checkAllPlayerHandsIsEmpty(){
        for (Player p: player){
            if(p.getHand().getSize() == 0) {
                System.out.println("手札が0枚のプレイヤーが出たためゲームを終了します");
                return true;
            }
        }
        return false;
    }

    private void setUp() {
        graveyard = new ArrayList<>();
        turn = 0;
        deck = new Deck();
        Collections.shuffle(Arrays.asList(player));
        for (Player p : player){
            for (int i = 0; i < 7; i++){
                p.getHand().add(deck.draw());
            }
        }
        do {
            fieldCard = deck.draw();
        }while (fieldCard.getColor() == Color.Black);

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