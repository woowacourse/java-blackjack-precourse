package services;

import domain.game.BlackJack;

public class BlackJackPlayService {
	public static void initiateBlackJack() {
		BlackJack blackJack = new BlackJack();

		blackJack.drawCardDeckToAll();
		blackJack.drawCardDeckToAll();
		blackJack.showHandOfAllPlayer();
		blackJack.setGameStopperIfBlackJack();
		while (blackJack.isGameStopperFalse()) {
			playMidGame(blackJack);
		}
	}

	private static void playMidGame(BlackJack blackJack) {
		blackJack.drawCardInMidGame();
		blackJack.showHandOfAllPlayer();
		blackJack.setGameStopperIfBlackJack();
		blackJack.setGameStopperIfBusted();
	}
}