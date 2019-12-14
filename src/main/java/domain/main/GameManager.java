package domain.main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

import domain.card.Card;
import domain.card.CardFactory;
import domain.user.Dealer;
import domain.user.Player;

public class GameManager {
	private BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private List<Player> playerList = new ArrayList<>();
	private List<Card> cardset = new ArrayList<>(CardFactory.create());
	private Dealer dealer = new Dealer();

	public void write(String str) {
		try {
			bw.write(str);
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String read() {
		try {
			return bf.readLine();
		} catch (IOException e) {
			return null;
		}
	}

	public void betting(String userList) {
		StringTokenizer st = new StringTokenizer(userList, ",");
		while (st.hasMoreTokens()) {
			String name = st.nextToken();
			write(name + "의 배팅 금액은?\n");

			// 음수를 입력할 때 예외처리 필요
			int bettingMoney = Integer.parseInt(read());
			Player player = new Player(name, bettingMoney);
			playerList.add(player);
		}
	}

	public void settingGame() {
		write("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)\n");
		String userList = read();
		betting(userList);
		shuffle();
	}

	public void start() {
		settingGame();
		for (int i = 0; i < 2; i++) {
			initialize();
		}
		printMsg();
		printCards();
		playTurn();
	}

	public void printMsg() {
		String msg = "딜러와 ";
		for (int i = 0; i < playerList.size() - 1; i++) {
			msg += playerList.get(i).getName() + ", ";
		}
		msg += playerList.get(playerList.size() - 1).getName();
		msg += "에게 2장의 카드를 나누었습니다.\n";
		write(msg);
	}

	public void shuffle() {
		Collections.shuffle(cardset);
	}

	public void initialize() {
		for (int i = 0; i < playerList.size(); i++) {
			playerList.get(i).addCard(handOutCard());
		}
		dealer.addCard(handOutCard());
	}

	public void addExtraCard() {
		if (dealer.sumScore() <= 16) {
			dealer.addCard(handOutCard());
		}
	}

	public Card handOutCard() {
		return cardset.remove(cardset.size() - 1);
	}

	public void playTurn() {
		for (int i = 0; i < playerList.size(); i++) {
			Player currentPlayer = playerList.get(i);
			String name = currentPlayer.getName();
			String yReg = "^[yY]$";
			write(name + "는 한 장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)\n");
			boolean flag = read().matches(yReg);
			iterCard(flag, currentPlayer);
		}
		exitGame(true);
	}

	public void iterCard(boolean flag, Player currentPlayer) {
		while (flag) {
			String yReg = "^[yY]$";
			currentPlayer.addCard(handOutCard());
			write(currentPlayer.toString() + "\n");
			exitGame(isFinished(currentPlayer));
			write(currentPlayer.getName() + "는 한 장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)\n");
			flag = read().matches(yReg);
		}
	}

	public void printCards() {
		for (int i = 0; i < playerList.size(); i++) {
			write(playerList.get(i).toString() + "\n");
		}
	}

	public boolean isFinished(Player currentPlayer) {
		return currentPlayer.sumScore() >= 21;
	}

	public void exitGame(boolean flag) {
		if (flag) {
			printResult();
			System.exit(0);
		}
	}

	public void printResult() {
		if (dealer.neededExtra()) {
			write("딜러는 16 이하라 한 장의 카드를 더 받았습니다.\n");
		}
		write(dealer.toString() + " - 결과: " + dealer.sumScore() + "\n");
		for (int i = 0; i < playerList.size(); i++) {
			write(playerList.get(i).toString() + " - 결과: " + playerList.get(i).sumScore() + "\n");
		}
	}
}
