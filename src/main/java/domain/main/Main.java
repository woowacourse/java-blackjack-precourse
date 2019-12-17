package domain.main;

import domain.game.Play;

public class Main {
	public static void main(String[] args) {
		Play playGame;
		playGame = new Play();
		playGame.requestUser();
		playGame.giveCard();
		playGame.allPlayerAddCard();
		playGame.dealerAddCard();
	}
}
