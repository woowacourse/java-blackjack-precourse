package controller;

import exceptions.EmptyInputException;
import exceptions.NumberUnderZeroException;
import exceptions.WrongInputException;
import services.BlackJackUIService;
import utils.UtilityMethods;

import java.util.List;
import java.util.Scanner;

public class BlackJackController {
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
			betting = catchUnderZeroInput(betting);
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
		while (judgeRightInput(moreCard)) {
			moreCard = scanner.nextLine();
			catchWrongInput(moreCard);
		}
		if (moreCard.equals("Y") || moreCard.equals("y")) {
			return true;
		}
		return false ;
	}

	private static Boolean judgeRightInput(String input) {
		if (input.equals("Y")) {
			return false;
		} else if (input.equals("y")) {
			return false;
		} else if (input.equals("N")) {
			return false;
		} else if (input.equals("n")) {
			return false;
		}
		return true;
	}

	private static void catchWrongInput(String moreCard) {
		try {
			validateMoreCard(moreCard);
		} catch (WrongInputException e) {
			BlackJackUIService.printWrongInputMessage();
		}
	}

	private static void validateMoreCard(String moreCard) throws WrongInputException {
		if (judgeRightInput(moreCard)){
			throw new WrongInputException();
		}
	}

}
