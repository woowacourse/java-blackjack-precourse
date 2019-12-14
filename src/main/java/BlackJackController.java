import view.dto.BlackJackGame;

public class BlackJackController {

    public static void main(String[] args) {
        BlackJackGame blackJackGame = BlackJackGame.init();
        blackJackGame.start();
    }
}
