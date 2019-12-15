package domain.game;

import java.util.List;

import domain.card.Deck;
import domain.user.Dealer;
import domain.user.Gambler;
import domain.user.Player;
import domain.user.PlayerFactory;

public class Match {
	private Deck deck;
	private List<Gambler> playerList;
	private Gambler dealer;
	private Rule rule;

	public Match(List<String>players, List<Double>bettings){
		playerList= PlayerFactory.create(players,bettings);
		dealer=new Dealer();
		deck=new Deck();
		deck.shuffle();
		rule=new Rule();
	}
}
