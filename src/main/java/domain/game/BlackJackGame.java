package domain.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import domain.user.Dealer;
import domain.user.Player;

public class BlackJackGame {
	private static final int INITIAL_CARDS = 2;
	
	private CardShoe cardShoe;
	private Dealer dealer;
	private List<Player> playerList = new ArrayList<>();
	private String[] playerNames;
	
	Scanner input = new Scanner(System.in);
	
	public BlackJackGame() {
		cardShoe = new CardShoe();
		dealer = new Dealer();
	}
	
	public void play() {
		inputPlayerInfos();
		initialDeal();
		showInitialDeal();
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
	
	private void initialDeal() {
		for (int i = 0; i < INITIAL_CARDS; i++) {
			dealer.addCard(cardShoe.getOneCard());
			playerList.stream()
					.forEach(player -> player.addCard(cardShoe.getOneCard()));
		}
	}
	
	private void showInitialDeal() {
		System.out.println("딜러와 " + String.join(",", Arrays.asList(playerNames)) + "에게 " + INITIAL_CARDS + "장의 카드를 나누었습니다."); 
		System.out.print("딜러 : ");
		System.out.println(dealer.showCardInfo());
		for (Player player : playerList) {
			System.out.print(player.getName() + " : ");
			System.out.println(player.showCardInfo());
		}
		System.out.println();
	}
	
}
