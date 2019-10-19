import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Game g = new Game(1,1);
        Scanner sc = new Scanner(System.in);
        while (true){
            g.run();
            System.out.print("もう一度プレイする? (Yes:1, No:0) : ");
            if (sc.nextLine() != "1")break;
        }
        System.out.println("ゲームを終了しました");
    }
}