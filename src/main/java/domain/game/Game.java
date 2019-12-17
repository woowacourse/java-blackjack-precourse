package domain.game;

import domain.card.Card;
import domain.card.CardFactory;
import domain.user.Player;
import domain.user.User;
import domain.user.UserRepository;
import domain.validity.ValidityCheck;
import domain.view.ViewInput;
import domain.view.ViewOutput;

public class Game {
	private final int firstCardCnt = 2;
	private final int dealerCriteria = 17;
	private final int playerFirstInx = 1;
	private final int Jack = 21;
	private final int dealerInx = 0;
	private final int bustCriteria = 22;
	
	private static Game blackJack = new Game();
	private UserRepository userRepository = new UserRepository();
	private ValidityCheck validity = new ValidityCheck();

	public static Game getInstance() {
		return blackJack;
	}
	
	public UserRepository getUserRepository() {
		return userRepository;
	}

	public void run() {
		userRepository.makeUserList();
		firstDealOut();
		secondDealOut();
		ViewOutput.showAllResult(userRepository.getUserList());
		userRepository.makeProfitList();
		ViewOutput.showEachProfit(userRepository.getUserList(), userRepository.getProfitList());
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
		return CardFactory.getDeck().get(getCardIndex());
	}
	
	public int getCardIndex() {
		try {
			return validity.cardIsUsed(selectCard(CardFactory.getDeck().size()));
		} catch (Exception e) {
			return getCardIndex();
		}
	}
	
	public int selectCard(int cardSize) {
		return (int)(Math.random() * cardSize);
	}
	
	public void secondDealOut() {
		secondPlayerDealOut();
		System.out.println();
		secondDealerDealOut();
	}
	
	public void secondPlayerDealOut() {
		for (int i = playerFirstInx; i < userRepository.getUserList().size(); i++) {
			User user = userRepository.getUserList().get(i);
			
			checkAnswer(user);
		}
	}
	
	public void checkAnswer(User user) {
		String answer = "y";
		
		while (answer.equals("y") && user.isBelow(Jack)) {
			answer = ViewInput.askGetCard((Player)user);
			ViewOutput.showEachResult(user);
			System.out.println();
		}
	}
	
	public void secondDealerDealOut() {
		User dealer = userRepository.getUserList().get(dealerInx);
		
		while (dealer.isBelow(dealerCriteria)) {
			dealer.addCard(Game.getInstance().selectedCard());
			ViewOutput.showDealerCheck();
		}
	}
	
	public void compareScore(int playerInx, int criteria) {		
		if (isBust(playerInx) || !isDraw(playerInx, criteria) || playerAlwaysWin(playerInx)) {
			modifyProfit(getWinnerInx(playerInx, criteria), 
					getLoserInx(getWinnerInx(playerInx, criteria), playerInx), 
					((Player)userRepository.getUserList().get(playerInx)).getBettingMoney());
		}
	}
	
	public boolean isBust(int playerInx) {
		if (userRepository.getUserList().get(playerInx).isBelow(bustCriteria)) {
			return false;
		}
		return true;
	}
	
	public boolean isDraw(int playerInx, int criteria) {
		if (userRepository.getUserList().get(playerInx).getScore() == criteria) {
			return true;
		}
		return false;
	}
	
	public boolean playerAlwaysWin(int playerInx) {
		if (userRepository.getUserList().get(playerInx).isBlackJack() 
				&& !userRepository.getUserList().get(dealerInx).isBlackJack()) {
			return true;
		}
		return false;
	}
	
	public void modifyProfit(int winnerInx, int loserInx, double money) {
		userRepository.getProfitList().set(winnerInx, userRepository.getProfitList().get(winnerInx) + money);
		userRepository.getProfitList().set(loserInx, userRepository.getProfitList().get(loserInx) - money);
	}
	
	public int getWinnerInx(int playerInx, int criteria) {
		if (playerWin(playerInx, criteria) || playerAlwaysWin(playerInx)) {
			return playerInx;
		}
		return dealerInx;
	}
	
	public boolean playerWin(int playerInx, int criteria) {
		int playerScore = userRepository.getUserList().get(playerInx).getScore();
		
		if (playerScore <= Jack && (playerScore > criteria || (criteria > Jack))) {
			return true;
		}
		return false;
	}
	
	public int getLoserInx(int winnerInx, int playerInx) {
		if (winnerInx == playerInx) {
			return dealerInx;
		}
		return playerInx;
	}
 }