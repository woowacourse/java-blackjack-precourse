package application;

import java.util.List;
import java.util.ArrayList;

public class PlayersWinLoseInfo {
	List<WinLoseInfo> playersInfo = new ArrayList<WinLoseInfo>();
	
	public PlayersWinLoseInfo(int numPlayers) {
		for(int i = 0; i < numPlayers; i++) {
			WinLoseInfo info = WinLoseInfo.UNDETERMINED;
			this.playersInfo.add(info);
		}
	}
	
	public WinLoseInfo getInfoAt(int index) {
		return this.playersInfo.get(index);
	}
	
	public void setBlackjackAt(int index) {
		this.playersInfo.set(index,WinLoseInfo.BLACKJACK);
	}
	
	public void setWinAt(int index) {
		this.playersInfo.set(index,WinLoseInfo.WIN);
	}
	
	public void setLoseAt(int index) {
		this.playersInfo.set(index,WinLoseInfo.LOSE);
	}
	
	public void setDrawAt(int index) {
		this.playersInfo.set(index,WinLoseInfo.DRAW);
	}
}
