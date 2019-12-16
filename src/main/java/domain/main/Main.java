package domain.main;

import java.util.ArrayList;
import java.util.List;

import domain.card.Card;
import domain.card.CardFactory;
import domain.user.Player;
import domain.user.Dealer;

public class Main {
	
	private static List<Card> cardSet = new ArrayList<>();

	public static void main(String[] args) {
		
		InputHandler.nameHandler();
		InputHandler.bettingMoneyHandler(InputHandler.nameStringList);
		List<Player> playerList = InputHandler.makePlayer();
		Dealer dealer = new Dealer();

		cardSet = CardFactory.cardShuffled();
		basicRation(dealer, playerList);
		
		Template.interimResult(playerList, dealer);
		
		InputHandler.oneMoreCardOrNot(playerList);
		
		dealerMoreCard(dealer);
		
		Template.finalResult(playerList, dealer);
		MoneyCalculator.calculate(playerList, dealer);
		
	}
	
	public static void basicRation(Dealer dealer, List<Player> playerList) {
		giveOneCardToDealer(dealer);
		giveOneCardToDealer(dealer);
		for (Player player : playerList) {
			giveOneCardToPlayer(player);
			giveOneCardToPlayer(player);
		}
	}
	
	public static void dealerMoreCard(Dealer dealer) {
		if (dealer.showScore() <= 16) {
			Template.dealerOneMoreCard();
			giveOneCardToDealer(dealer);
			dealerMoreCard(dealer);
		}
	}
	
	public static void giveOneCardToDealer(Dealer dealer) {
		Card oneCard = CardFactory.pickOneCard(cardSet);
		dealer.addCard(oneCard);
	}
	
	public static void giveOneCardToPlayer(Player player) {
		Card oneCard = CardFactory.pickOneCard(cardSet);
		player.addCard(oneCard);
	}

}
