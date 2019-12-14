package domain.game;

import java.util.List;

import domain.user.Gambler;

public class BlackJack {
	public void run(){
		PlayerPhase playerPhase =new PlayerPhase();
		List<Gambler> gamblerList=playerPhase.gatherPlayers();

		CasinoPhase casinoPhase=new CasinoPhase();
		
	}
}
