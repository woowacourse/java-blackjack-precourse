import static utils.UserInput.enrollPlayers;
import static utils.UserInput.enrollDealer;


public class Main {
    public static void main(String[] args) {
        BlackJackGame blackJackGame = new BlackJackGame(enrollPlayers(), enrollDealer());
    }
}
