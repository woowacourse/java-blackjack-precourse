package controller;

import domain.card.Card;
import domain.user.Player;
import exceptions.EmptyInputException;
import exceptions.WrongNumberException;
import services.BlackJackUIService;
import utils.UtilityMethods;

import java.util.List;
import java.util.Scanner;

public class BlackJackController {
	public static List<String> getInputOfPlayersName() {
		Scanner scanner = new Scanner(System.in);
		String names = scanner.nextLine();
		while (names.equals("")) {
			names = catchEmptyInput(names);
		}
		scanner.close();
		return UtilityMethods.sliceStringToList(names);
	}

	private static String catchEmptyInput(String names) {
		try {
			validateNames(names);
		} catch (EmptyInputException e) {
			BlackJackUIService.printEmptyInputMessage();
		}
		return names;
	}

	private static void validateNames(String names) throws EmptyInputException {
		if (names.equals("")) {
			throw new EmptyInputException();
		}
	}

	public static double getBetting() {
		Scanner scanner = new Scanner(System.in);
		double betting = scanner.nextDouble();
		while (betting <= 0){
			catchWrongInput(betting);
		}
		scanner.close();
		return betting;
	}

	private static double catchWrongInput(double betting) {
		try {
			validateBetting(betting);
		} catch (EmptyInputException e) {
			BlackJackUIService.printEmptyInputMessage();
		}
		return betting;
	}

	private static void validateBetting(double betting) throws WrongNumberException {
		if (betting <= 0) {
			throw new WrongNumberException();
		}
	}

	public static Card popRandomCard(List<Card> cards) {
		int popIndex = UtilityMethods.generateRandomNumber(cards.size());
		return cards.remove(popIndex);
	}
	/*

	*/
}
