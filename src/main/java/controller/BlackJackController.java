package controller;

import domain.card.Card;
import domain.user.Player;
import exceptions.EmptyInputException;
import exceptions.NegativeNumberException;
import services.BlackJackUIService;
import utils.UtilityMethods;

import java.util.List;
import java.util.Scanner;

public class BlackJackController {
	public static List<String> getInputOfPlayersName() {
		String names = null;

		while (names == null) {
			names = catchEmptyInput();
		}
		return UtilityMethods.sliceStringToList(names);
	}

	private static String catchEmptyInput() {
		Scanner scanner = new Scanner(System.in);
		String names = scanner.nextLine();
		try {
			validateNames(names);
		} catch (EmptyInputException e) {
			BlackJackUIService.printEmptyInputMessage();
		}
		scanner.close();
		return names;
	}

	private static void validateNames(String names) throws EmptyInputException {
		if (names.equals("")) {
			throw new EmptyInputException();
		}
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



	private static void validateBetting(double betting) throws NegativeNumberException {
		if (betting < 0) {
			throw new NegativeNumberException();
		}
	}

	private void drawFromCardDeck() {
		for (Player player : this.players) {
			player.addCard(popRandomCard(this.cardDeck));
		}
		dealer.addCard(popRandomCard(this.cardDeck));
	}


	public Card popRandomCard(List<Card> cards) {
		int popIndex = UtilityMethods.generateRandomNumber(cards.size());
		return cards.remove(popIndex);
	}
}
