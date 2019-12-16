package services;

import domain.game.BlackJack;

public class BlackJackPlayService {
	public static void  startBlackJack() {
		BlackJack blackJack = new BlackJack();

		blackJack.drawCardDeckToAll();
		blackJack.drawCardDeckToAll();
		blackJack.showHandOfAllPlayer();
		//blackJack.drawCardInMidGame();
	}
}