import domain.game.BlackJack;

public class BlackJackGame {
	private static BlackJack blackJack;
	public static void main(String[] args) {
		BlackJackGame blackJackGame = new BlackJackGame();
		blackJack=blackJackGame.setUp();

		// 첫째 라운드 if(blackJackGame.firstRound()){
		//		blackJackGame.finish();
		//		return;
		//	}

		// 패돌리고 blackJackGame.secondRound();
		// 종료페이즈 blackJackGame.finish();
	}
	private BlackJack setUp(){
		BlackJack blackJack=new BlackJack();
		blackJack.setUp();
		return blackJack;
	}
	private boolean firstRound(){
		// blackJack
	}
}
