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
		
		mainHelper(playerList, dealer);
	}
	
	public static void mainHelper(List<Player> playerList, Dealer dealer) {
		basicRation(playerList, dealer);
		Template.interimResult(playerList, dealer);
		InputHandler.oneMoreCardOrNot(playerList);
		dealerMoreCard(dealer);
		Template.finalResult(playerList, dealer);
		MoneyCalculator.calculate(playerList, dealer);
	}
	
	public static int maxPlayerNumber() {
    	return 8;
    }
	
	public static void basicRation(List<Player> playerList, Dealer dealer) {
		giveOneCardToDealer(dealer);
		giveOneCardToDealer(dealer);
		for (Player player : playerList) {
			giveOneCardToPlayer(player);
			giveOneCardToPlayer(player);
		}
	}
	
	public static int dealerMoreCardOrNotNumber() {
		return 16;
	}
	
	public static void dealerMoreCard(Dealer dealer) {
		if (dealer.showScore() <= dealerMoreCardOrNotNumber()) {
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
