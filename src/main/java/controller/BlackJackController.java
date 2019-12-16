package controller;

import domain.card.Card;
import domain.user.Player;
import exceptions.EmptyInputException;
import exceptions.NegativeNumberException;
import utils.UtilityMethods;

import java.util.List;
import java.util.Scanner;

public class BlackJackController {
	public static List<String> getPlayersName() {
		System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
		Scanner scanner = new Scanner(System.in);
		String names = scanner.nextLine();
		try {
			validateNames(names);
		} catch (EmptyInputException e) {
			System.out.println("플레이어를 입력하여야 합니다");
		}
		scanner.close();
		return UtilityMethods.sliceStringToList(names);
	}

	public static List<Double> getEachBettings() {
	}

	public static double getBetting(String player) {
		System.out.println(player + "의 배팅 금액은?");
		Scanner scanner = new Scanner(System.in);
		double betting = scanner.nextDouble();
		try {
			validateBetting(betting);
		} catch (NegativeNumberException e) {
			System.out.println("양수를 입력해야 합니다");
		}
		scanner.close();
		return betting;
	}

	public void playGames() {

	}

	private static String validateNames(String names) throws EmptyInputException {
		if (names.equals("")) {
			throw new EmptyInputException();
		}
		return names;
	}

	private static double validateBetting(double betting) throws NegativeNumberException {
		if (betting < 0) {
			throw new NegativeNumberException();
		}
		return betting;

		private void drawFromCardDeck () {
			for (Player player : this.players) {
				player.addCard(popRandomCard(this.cardDeck));
			}
			dealer.addCard(popRandomCard(this.cardDeck));
		}
	}

	public Card popRandomCard(List<Card> cards) {
		int popIndex = UtilityMethods.generateRandomNumber(cards.size());
		return cards.remove(popIndex);
	}
}
