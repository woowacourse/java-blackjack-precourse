package domain.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import domain.user.Player;

public class BlackJackGame {
	private List<Player> playerList = new ArrayList<>();
	private String[] playerNames;
	Scanner input = new Scanner(System.in);
	
	public void play() {
		inputPlayerInfos();
	}
	
	private void inputPlayerInfos() {
		System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉽표 기준으로 분리)");
		playerNames = input.next().split(",");
	
		for (String name : playerNames) {
			System.out.println(name + "의 배팅 금액은?");
			double bettingMoney = input.nextDouble();
			playerList.add(new Player(name, bettingMoney));
		}
	}
	
}
