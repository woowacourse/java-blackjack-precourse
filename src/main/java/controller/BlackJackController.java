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
	final static int BUSTED_SCORE = 21;

	public static List<String> getPlayersName() {
		String names = "";

		Scanner scanner = new Scanner(System.in);
		while (names.equals("")) {
			names = scanner.nextLine();
			names = catchEmptyInput(names);
		}
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
		double betting = 0;

		Scanner scanner = new Scanner(System.in);
		while (betting <= 0) {
			betting = scanner.nextDouble();
			catchUnderZeroInput(betting);
		}
		return betting;
	}

	private static double catchUnderZeroInput(double betting) {
		try {
			validateBetting(betting);
		} catch (NumberUnderZeroException e) {
			BlackJackUIService.printUnderZeroInputMessage();
		}
		return betting;
	}

	private static void validateBetting(double betting) throws NumberUnderZeroException {
		if (betting <= 0) {
			throw new NumberUnderZeroException();
		}
	}

	public static Boolean getMoreCardInput() {
		String moreCard = ""; // 한글자가 아닌 입력을 핸들하기 위한 String 선언

		Scanner scanner = new Scanner(System.in);
		while (!moreCard.equals("Y") || !moreCard.equals("y")
			|| !moreCard.equals("N") || !moreCard.equals("n")) {
			moreCard = scanner.nextLine();
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
