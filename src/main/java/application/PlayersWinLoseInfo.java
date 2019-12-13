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
}
