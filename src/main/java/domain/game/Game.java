package domain.game;

import java.util.ArrayList;
import java.util.List;

import domain.card.Card;
import domain.card.CardFactory;
import domain.user.User;
import domain.user.UserRepository;
import domain.validity.ValidityCheck;
import domain.view.ViewInput;;

public class Game {
	private final int firstCardCnt = 2;
	
	private static Game blackJack = new Game();
	private UserRepository userRepository = new UserRepository();
	private List<Card> deck = new ArrayList<Card>();
	private ValidityCheck validity = new ValidityCheck();

	public static Game getInstance() {
		return blackJack;
	}

	public void run() {
		userRepository.makePlayerName(ViewInput.getPlayerNames());
		userRepository.makeUserList();
	}
	
	public void cardDealOut() {
		deck = CardFactory.create();
		
		userRepository.dealOutCard();
	}
	
 }
