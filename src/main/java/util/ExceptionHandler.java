package util;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ExceptionHandler {
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
		if (input < 0) {
			throw new InputMismatchException();
		}
		return input;
	}
}
