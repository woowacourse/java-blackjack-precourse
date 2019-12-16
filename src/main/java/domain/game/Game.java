package domain.game;

import java.util.List;

import domain.card.Card;
import domain.card.CardFactory;
import domain.user.User;
import domain.user.UserRepository;
import domain.validity.ValidityCheck;
import domain.view.ViewInput;
import domain.view.ViewOutput;

public class Game {
	private final int firstCardCnt = 2;
	
	private static Game blackJack = new Game();
	private UserRepository userRepository = new UserRepository();
	private List<Card> deck = CardFactory.create();
	private ValidityCheck validity = new ValidityCheck();

	public static Game getInstance() {
		return blackJack;
	}

	public void run() {
		userRepository.makePlayerName(ViewInput.getPlayerNames());
		userRepository.makeUserList();
		firstDealOut();
		secondDealOut();
		userRepository.showAllResult();
		userRepository.makeProfitResult();
		ViewOutput.showEachProfit(userRepository.getUserList(), userRepository.getProfit());
	}
	
	public void firstDealOut() {
		giveUserCard();
		ViewOutput.showFirstResult(userRepository);
	}
	
	public void giveUserCard() {
		for (User user : userRepository.getUserList()) {
			giveTwoCard(user);
		}
	}
	
	public void giveTwoCard(User user) {
		for (int i = 0; i < firstCardCnt; i++) {
			user.addCard(selectedCard());
		}
	}
	
	public Card selectedCard() {
		return deck.get(getCardIndex());
	}
	
	public int getCardIndex() {
		try {
			return validity.cardIsUsed(selectCard(CardFactory.cardSize));
		} catch (Exception e) {
			return getCardIndex();
		}
	}
	
	public int selectCard(int cardSize) {
		return (int)(Math.random() * cardSize);
	}
	
	public void secondDealOut() {
		userRepository.secondPlayerDealOut();
		System.out.println();
		userRepository.secondDealerDealOut();
	}
 }
