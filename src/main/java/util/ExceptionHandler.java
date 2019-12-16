package util;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ExceptionHandler {
	private static final int ZERO = 0;
	private static final int WANT_ONE_CARD = 1;
	private static final int WANT_NO_CARD = 0;
	private static Scanner scanner = new Scanner(System.in);

	public static String inputPlayerHandler() {
		try {
			return checkInputPlayer(scanner.next().trim());
		} catch (InputMismatchException e) {
			System.out.println("입력값을 확인해주세요.");
			return inputPlayerHandler();
		}
	}

	private static String checkInputPlayer(String input) {
		checkInputEndsWithComma(input);
		for (String name : input.split(",")) {
			checkNameEmpty(name);
		}
		return input;
	}

	private static void checkNameEmpty(String name) {
		if (name.isEmpty()) {
			throw new InputMismatchException();
		}
	}

	private static void checkInputEndsWithComma(String input) {
		if (input.endsWith(",")) {
			throw new InputMismatchException();
		}
	}

	public static double inputBettingMoneyHandler() {
		try {
			return checkInputBettingMoneyHandler(scanner.nextDouble());
		} catch (InputMismatchException e) {
			System.out.println("입력값을 확인해주세요.");
			scanner = new Scanner(System.in);
			return inputBettingMoneyHandler();
		}
	}

	private static double checkInputBettingMoneyHandler(double input) {
		if (input < ZERO) {
			throw new InputMismatchException();
		}
		return input;
	}

	public static int inputaskMoreCardsHandler() {
		try {
			return checkInputaskMoreCardsHandler(scanner.next().trim());
		} catch (InputMismatchException e) {
			System.out.println("입력값을 확인해주세요.");
			scanner = new Scanner(System.in);
			return inputaskMoreCardsHandler();
		}
	}

	private static int checkInputaskMoreCardsHandler(String next) {
		if (next.equals("y")) {
			return WANT_ONE_CARD;
		}
		if (next.equals("n")) {
			return WANT_NO_CARD;
		}
		throw new InputMismatchException();
	}
}
