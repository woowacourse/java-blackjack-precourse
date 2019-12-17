package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import domain.user.Player;

public class BlackJackGame {
	Scanner scanner = new Scanner(System.in);
	private List<String> playerNames = new ArrayList<String>();
	private int numberOfPlayers = 0;
	private List<Player> players = new ArrayList<Player>();
	
	public BlackJackGame() {
	}
	public void run() {
		setPlayerNames();
		setPlayers();
		System.out.println(players.size()+"명의 플레이어가 존재합니다");
		for(int i=0;i<numberOfPlayers;i++) {
			System.out.println(getPlayerNameByIndex(i));
		}
	}
	public void setPlayerNames() {
		String[] nameArray;
		String nameString;
		System.out.println("게임에 참여할 사람의 이름을 입력하세요. (쉼표 기준으로 분리)");
		nameString = scanner.nextLine();
		nameArray = nameString.split(",");
		for(int i=0;i<nameArray.length;i++) {
			playerNames.add(nameArray[i]);
		}
		setNumberOfPlayers();
	}
	public void setNumberOfPlayers() {
		this.numberOfPlayers = playerNames.size();
	}
	public void setPlayers() {
		int bettingMoney = 0;
		for(int i=0;i<getNumberOfPlayers();i++) {
			System.out.println(getPlayerNameByIndex(i)+"의 배팅 금액은?");
			bettingMoney = scanner.nextInt();
			Player tmpPlayer = new Player(getPlayerNameByIndex(i), bettingMoney);
			players.add(tmpPlayer);
		}
	}
	public int getNumberOfPlayers() {
		return this.numberOfPlayers;
	}
	public String getPlayerNameByIndex(int index) {
		return this.playerNames.get(index);
	}
}
