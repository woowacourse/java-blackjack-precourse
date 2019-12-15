import domain.game.BlackJack;

public class BlackJackGame {
	private static BlackJack blackJack;

	public static void main(String[] args) {
		BlackJackGame blackJackGame = new BlackJackGame();
		blackJack = blackJackGame.setUp();

		if (blackJackGame.firstRound()) {
			blackJackGame.finish();
			return;
		}

		blackJackGame.secondRound();
		blackJackGame.finish();
	}

	private BlackJack setUp() {
		BlackJack blackJack = new BlackJack();
		blackJack.setUp();
		return blackJack;
	}

	private boolean firstRound() {
		// blackJack
	}

	private void secondRound(){

	}
	private void finish(){
		
	}
}
