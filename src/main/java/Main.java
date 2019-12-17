import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Game game;
        String nameInput;
        Scanner scan = new Scanner(System.in);

        System.out.println("Input the player's names (separate with ',')");
        nameInput = scan.nextLine();
        game = new Game(nameInput.split(","));
    }
}
