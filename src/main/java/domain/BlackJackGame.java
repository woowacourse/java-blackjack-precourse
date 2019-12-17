package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import domain.card.CardFactory;
import domain.user.Dealer;
import domain.user.Player;

public class BlackJackGame {
	Scanner scanner = new Scanner(System.in);
	private List<String> playerNames = new ArrayList<String>();
	private List<Double> bettingMoney = new ArrayList<Double>();
	private int numberOfPlayers = 0;
	private List<Player> players = new ArrayList<Player>();
	private Dealer dealer = new Dealer();
	private CardFactory deck = new CardFactory();
	public BlackJackGame() {
	}
	public void run() {
		setPlayerNames();
		setBettingMoney();
		setPlayers();
		
		firstDraw();
		printAllHands();
		for(int i=0;i<numberOfPlayers;i++) {
			drawCard(players.get(i));
		}
		setGameResult();
		checkDealerScore();
		printFinalState();
		printFinalBenefit();
		return;
	}
	public void setDealer() {
		
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
	public void setBettingMoney() {
		int tmpBettingMoney;
		for(int i=0;i<getNumberOfPlayers();i++) {
			System.out.println(getPlayerNameByIndex(i)+"의 배팅 금액은?");
			tmpBettingMoney = scanner.nextInt();
			bettingMoney.add((double)tmpBettingMoney);
		}
	}
	public void setNumberOfPlayers() {
		this.numberOfPlayers = playerNames.size();
	}
	public void setPlayers() {
		for(int i=0;i<getNumberOfPlayers();i++) {
			System.out.println(getPlayerNameByIndex(i)+"의 배팅 금액은?");
			Player tmpPlayer = new Player(getPlayerNameByIndex(i), getBettingMoneyByIndex(i));
			players.add(tmpPlayer);
		}
	}
	
	public int getNumberOfPlayers() {
		return this.numberOfPlayers;
	}
	public String getPlayerNameByIndex(int index) {
		return this.playerNames.get(index);
	}
	public double getBettingMoneyByIndex(int index) {
		return this.bettingMoney.get(index);
	}
	public void firstDraw() {
		System.out.println("딜러와 "+getPlayerNames()+"에게 카드를 두장씩 나누었습니다.");
		dealer.addCard(deck.drawCard());
		dealer.addCard(deck.drawCard());
		
		for(int i=0;i<getNumberOfPlayers();i++) {
			players.get(i).addCard(deck.drawCard());
			players.get(i).addCard(deck.drawCard());
		}
		setScore();
	}
	public void drawCard(Player player) {
		//String input = null;
		while(player.getScore()<21) {
			System.out.println(player.getName()+"는 카드를 추가로 받으시겠습니까? (y/n)");
			char input = scanner.next().trim().charAt(0);
			if(input=='y') {
				player.addCard(deck.drawCard());
				player.setScore();
			}else {
				return;
			}
		}
	}
	public String getPlayerNames() {
		String result = "";
		for(int i=0;i<getNumberOfPlayers()-1;i++) {
			result = result + getPlayerNameByIndex(i) +", ";
		}
		result = result+getPlayerNameByIndex(getNumberOfPlayers()-1);
		return result;
	}
	public void setScore() {
		dealer.setScore();
		for(int i=0;i<numberOfPlayers;i++) {
			players.get(i).setScore();
		}
	}
	public int findBlackJackPlayerIndex() {
		int index = -1;
		
		return index;
	}
	public void checkDealerScore() {
		if(dealer.getScore()<17) {
			System.out.println("딜러의 점수가 16이하이므로 한장을 더 받습니다");
			dealer.addCard(deck.drawCard());
			dealer.printHands();
			dealer.setScore();
		}
	}
	public void printFinalBenefit() {
		int result = 0;
		System.out.println("##최종 수익##");
		System.out.println("딜러 : "+(int)dealer.getBettingMoney());
		for(int i=0;i<numberOfPlayers;i++) {
			result = (int)(players.get(i).getBettingMoney()-bettingMoney.get(i));
			System.out.println(getPlayerNameByIndex(i)+" : "+result);
		}
	}
	public void setGameResult() {
		
	}
	public void printFinalState() {
		System.out.println();
		System.out.println("-------------최종결과----------------");
		System.out.println("총 배팅액수 : "+getTotalBetting());
		System.out.println("-----------------------------");
		dealer.printHands();
		System.out.println("딜러의 점수 : "+dealer.getScore());
		System.out.println("-----------------------------");
		for(int i=0;i<numberOfPlayers;i++) {
			players.get(i).printHands();
			System.out.println(getPlayerNameByIndex(i)+"의 점수 : "+players.get(i).getScore());
			System.out.println("-----------------------------");
		}
	}
	public int getTotalBetting() {
		int sum =0;
		for(int i=0;i<numberOfPlayers;i++) {
			sum += bettingMoney.get(i);
		}
		return sum;
	}
	public void printAllHands() {
		dealer.printHands();
		for(int i=0;i<numberOfPlayers;i++) {
			players.get(i).printHands();
		}
	}
}
