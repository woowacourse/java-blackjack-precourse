package domain.user;

import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;

import domain.card.CardDeck;

public class Players {
	private List<Player> players = new ArrayList<Player>();
	
	public Players(List<String> names, List<Integer> money) {
		for (int i = 0; i < names.size(); i++) {
			players.add(new Player(names.get(i), money.get(i)));
		}
	}

	public static void checkValidNames(List<String> playerNames) {
		PlayersConstraints.checkValidNumOfNames(playerNames);
		PlayersConstraints.checkValidEachName(playerNames);
	}
	
	public static void checkValidBettingMoneys(List<Integer> bettingMoneys) {
		PlayersConstraints.checkValidEachBettingMoney(bettingMoneys);
	}
	
	public void drawCard(CardDeck deck) {
		this.players.stream()
				.forEach(player -> player.drawCard(deck));
	}
	
	public Player getPlayerAt(int index) {
		return this.players.get(index);
	}
	
	public String toString() {
		return this.players.stream()
				.map(player -> player.getName())
				.collect(Collectors.joining(", "));
	}
	
	public int getSize() {
		return this.players.size();
	}
}
