import controller.BlackjackController;
import controller.InputController;

public class Blackjack {
	public static void main(String[] args) {
		InputController inputController = new InputController();
		BlackjackController blackjackController = new BlackjackController(inputController.getPlayers());
		playBlackjack(inputController, blackjackController);
	}

	private static void playBlackjack(InputController inputController, BlackjackController blackjackController) {
		blackjackController.firstServe();


	}
}
