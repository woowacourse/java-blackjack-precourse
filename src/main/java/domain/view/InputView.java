package domain.view;

import java.util.Arrays;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import domain.user.Player;

public class InputView {
	private static final String GET_PLAYERS_NAME = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
	private static final String GET_BETTING_MONEY = "의 배팅 금액은?";
	private static final String ASK_GET_MORE_CARD = ", 한장의 카드를 더 받겠습니까? (예는 y, 아니오는 n)";
	private static final String INVALID_INPUT_ERROR_MESSAGE = "입력값 형식이 잘못 되었습니다.";
	private static final String INVALID_NUMBER_ERROR_MESSAGE = "숫자가 아닙니다. 배팅금액을 올바른 형식으로 입력해주세요(ex. 1000, 150.2 등)";
	private static final String NEGATIVE_NUMBER_ERROR_MESSAGE = "0 또는 음수는 입력할 수 없습니다.";
	private static final String DUPLICATED_NAME_ERROR_MESSAGE = "이름은 중복될 수 없습니다.";
	private static final String COMMA = ",";
	private static final String DOUBLE_COMMA = ",,";
	private static final String GET_MORE_CARD = "y";
	private static final String NO_MORE_CARD = "n";
	private static final double ZERO = 0.0;

	private static final Scanner scanner = new Scanner(System.in);

	public static List<String> inputPlayerNames() {
		System.out.println(GET_PLAYERS_NAME);
		try {
			String input = scanner.nextLine().trim();
			List<String> playerNames = Arrays.asList(input.replaceAll("\\s", "").split(COMMA));
			validateInput(input, playerNames);
			return playerNames;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return inputPlayerNames();
		}
	}

	private static void validateInput(String input, List<String> players) {
		isInvalidInput(input);
		isInvalidPlayerName(players);
		isDuplicatedPlayerName(players);
	}

	private static void isInvalidInput(String input) {
		if (input.isEmpty() || input.contains(DOUBLE_COMMA) || input.endsWith(COMMA)) {
			throw new InputMismatchException(INVALID_INPUT_ERROR_MESSAGE);
		}
	}

	private static void isInvalidPlayerName(List<String> players) {
		for (String player : players) {
			isInvalidInput(player);
		}
	}

	private static void isDuplicatedPlayerName(List<String> players) {
		if (players.size() != new HashSet<>(players).size()) {
			throw new InputMismatchException(DUPLICATED_NAME_ERROR_MESSAGE);
		}
	}

	private static boolean isPositiveNumber(double number) {
		if (number > ZERO) {
			return true;
		}
		System.out.println(NEGATIVE_NUMBER_ERROR_MESSAGE);
		return false;
	}

	public static double getBettingMoney(String player) {
		System.out.println(player + GET_BETTING_MONEY);
		try {
			double bettingMoney = Double.parseDouble(scanner.nextLine());
			if (!isPositiveNumber(bettingMoney)) {
				return getBettingMoney(player);
			}
			return bettingMoney;
		} catch (IllegalArgumentException e) {
			System.out.println(INVALID_NUMBER_ERROR_MESSAGE);
			return getBettingMoney(player);
		}
	}

	public static boolean getMoreCard(Player player) {
		System.out.println(player.getName() + ASK_GET_MORE_CARD);
		String answer = scanner.nextLine();
		if (!answer.equals(GET_MORE_CARD) && !answer.equals(NO_MORE_CARD)) {
			return getMoreCard(player);
		}
		return answer.equals(GET_MORE_CARD);
	}
}
