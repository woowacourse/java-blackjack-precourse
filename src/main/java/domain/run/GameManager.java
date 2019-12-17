package domain.run;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import com.sun.javafx.scene.control.skin.FXVK.Type;

import domain.card.Card;
import domain.card.CardFactory;
import domain.card.Symbol;
import domain.user.Dealer;
import domain.user.Player;

public class GameManager {

	final Scanner sc = new Scanner(System.in);
	List<Card> cards = CardFactory.create();
	ArrayList<Player> players = new ArrayList<Player>();
	ArrayList<Card> playerCard = new ArrayList<Card>();
	Integer[] scoreResult;
	int maxScore=0;
	int winner = 0;
	Random random = new Random();

	Dealer dealer = new Dealer();

	public void printInputName() {
		System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
		String names = sc.next();
		splitName(names);
	}

	private void splitName(String names) {
		int count=0;
		for (String name : names.split(",")) {
			System.out.println(name + "의 배팅금액은?");
			players.add(new Player(name, sc.nextDouble()));
			count++;
		}
		 scoreResult= new Integer[count+1];
		drawCard();
		printResult();
	}

	public void printResult() {
		int i = 0;
		for (Player player : players) {
			System.out.print(player.getName() + ": ");
			printPlayerCard(player);
			System.out.print("-결과:");
			System.out.println(printCardResult(player, i));
			i++;
		}
		checkWinnerScore();
		printDealer();
	}

	private void drawCard() {
		for (Player player : players) {
			player.addCard(randomCard());
			player.addCard(randomCard());
		}
		printDealerCard();
		printPlayerName();
		addCardPlayer();
	}

	
}
