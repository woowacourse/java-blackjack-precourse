package domain.user;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlayerFactory {
	public static List<Gambler> create(List<String> names, List<Double> bettings) {
		List<Gambler> players = new ArrayList<>();
		for (int i = 0; i < names.size(); i++) {
			players.add(new Player(names.get(i), bettings.get(i)));
		}
		return players;
	}
}
