import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Game g = new Game(1,2);

        Scanner sc = new Scanner(System.in);
        do {
            g.run();
            System.out.print("もう一度プレイする? (Yes:1, No:0) : ");
        } while (sc.nextLine().matches("1"));
        System.out.println("ゲームを終了しました");
    }
}