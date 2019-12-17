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
		System.out.println("���ӿ� ������ ����� �̸��� �Է��ϼ���.(��ǥ �������� �и�)");
		String names = sc.next();
		splitName(names);
	}

	private void splitName(String names) {
		int count=0;
		for (String name : names.split(",")) {
			System.out.println(name + "�� ���ñݾ���?");
			players.add(new Player(name, sc.nextDouble()));
			count++;
		}
		 scoreResult= new Integer[count+1];
		drawCard();
		printResult();
	}

}
