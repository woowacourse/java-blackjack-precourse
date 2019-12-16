package domain.user;

import java.util.ArrayList;
import java.util.List;

import utils.NameInput;
import utils.NumberInput;

public class Gamers {
	private static final int INIT_DRAW_NUMBER = 2;
	private Dealer dealer = new Dealer();
	private List<Player> players = new ArrayList<>();
	private List<Gamer> gamers = new ArrayList<>();
	
	public Gamers() {
		setPlayers();
		setGamers();
	}
	
	private void setPlayers() {
		NameInput name = new NameInput();
		
		for (String string : name.getNames()) {
			NumberInput num = new NumberInput(string);
			this.players.add(new Player(string, num.getNumber()));
		}
	}
	
	private void setGamers() {
		this.gamers.addAll(this.players);
		this.gamers.add(this.dealer);
	}
	
	public List<Gamer> getGamers() {
		return this.gamers;
	}
	
	public List<Player> getPlayers() {
		return this.players;
	}
	
	public Dealer getDealer() {
		return this.dealer;
	}
	
	public List<String> getPlayerNames() {
		List<String> names = new ArrayList<>();
		
		for (Player player : players) {
			names.add(player.getName());
		}
		
		return names;
	}
	
	public void draw() {
		for (Gamer gamer : this.gamers) {
			gamer.draw();
		}
	}
	
	public void drawInit() {
		for (int i = 0; i < INIT_DRAW_NUMBER; i++) {
			draw();
		}
	}
}
