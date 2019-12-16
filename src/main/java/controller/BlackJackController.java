package controller;

import domain.card.Card;
import exceptions.EmptyInputException;
import exceptions.NumberUnderZeroException;
import exceptions.WrongInputException;
import services.BlackJackUIService;
import utils.UtilityMethods;

import java.util.List;
import java.util.Scanner;

public class BlackJackController {
	public static List<String> getPlayersName() {
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
		while (betting <= 0) {
			catchUnderZeroInput(betting);
		}
		scanner.close();
		return betting;
	}

	private static double catchUnderZeroInput(double betting) {
		try {
			validateBetting(betting);
		} catch (EmptyInputException e) {
			BlackJackUIService.printUnderZeroInputMessage();
		}
		return betting;
	}

	private static void validateBetting(double betting) throws NumberUnderZeroException {
		if (betting <= 0) {
			throw new NumberUnderZeroException();
		}
	}

	public static Card popRandomCard(List<Card> cards) {
		int popIndex = UtilityMethods.generateRandomNumber(cards.size());
		return cards.remove(popIndex);
	}

	public static Boolean getMoreCardInput() {
		Scanner scanner = new Scanner(System.in);
		String moreCard = scanner.nextLine(); // 한글자가 아닌 입력을 핸들하기 위한 String 선언
		while (!moreCard.equals("Y") || !moreCard.equals("y")
			|| !moreCard.equals("N") || !moreCard.equals("n")) {
			catchWrongInput(moreCard);
		}
		if (moreCard.equals("Y") || moreCard.equals("y")) {
			return true;
		}
		return false ;
	}

	private static String catchWrongInput(String moreCard) {
		try {
			validateMoreCard(moreCard);
		} catch (WrongInputException e) {
			BlackJackUIService.printWrongInputMessage();
		}
		return moreCard;
	}

	private static void validateMoreCard(String moreCard) throws WrongInputException {
		if (!moreCard.equals("Y") || !moreCard.equals("y")
			|| !moreCard.equals("N") || !moreCard.equals("n")) {
			throw new WrongInputException();
		}
	}

}
