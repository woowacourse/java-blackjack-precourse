package domain.game;

import java.util.ArrayList;
import java.util.List;

import domain.card.Card;
import domain.card.CardFactory;
import domain.user.UserRepository;
import domain.view.ViewInput;;

public class Game {
	private static Game blackJack = new Game();
	private UserRepository userRepository = new UserRepository();
	private List<Card> deck = new ArrayList<Card>();

	public static Game getInstance() {
		return blackJack;
	}

	public void run() {
		userRepository.makePlayerName(ViewInput.getPlayerNames());
		userRepository.makeUserList();
	}
	
 }
