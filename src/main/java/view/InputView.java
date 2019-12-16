package view;

import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import domain.user.Player;

public class InputView {
	private static final Scanner SCANNER = new Scanner(System.in);
	private static final String NAMES_DELIMITER = ",";
	private static final String GET_PLAYER_NAMES_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
	private static final String GET_PLAYER_NAMES_ERROR = "중복된 이름이 존재합니다.";
	private static final String GET_BETTING_MONEY_MESSAGE = "의 베팅 금액은?";
	private static final String GET_MORE_CARD_MESSAGE = "는 한 장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)";
	private static final String GET_MORE_CARD_YES = "y";
	private static final String GET_MORE_CARD_NO = "n";
	private static final String GET_MORE_CARD_ERROR = "y 또는 n을 입력하지 않았다.";

	private static void validatePlayerNames(String[] names) {
		Set<String> distinctNames = new HashSet<>(List.of(names));
		if (distinctNames.size() != names.length) {
			throw new IllegalArgumentException(GET_PLAYER_NAMES_ERROR);
		}
		distinctNames.forEach(Player::validateName);
	}

	public static String[] getPlayerNames() {
		try {
			System.out.println(GET_PLAYER_NAMES_MESSAGE);
			String[] names = SCANNER.nextLine().split(NAMES_DELIMITER);
			validatePlayerNames(names);
			return names;
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
			return getPlayerNames();
		}
	}

	public static double getBettingMoney(String name) {
		try {
			System.out.println(name + GET_BETTING_MONEY_MESSAGE);
			double bettingMoney = Double.parseDouble(SCANNER.nextLine());
			Player.validateBettingMoney(bettingMoney);
			return bettingMoney;
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
			return getBettingMoney(name);
		}
	}

	private static void validateMoreCard(String moreCard) {
		if (!(GET_MORE_CARD_YES.equals(moreCard) || GET_MORE_CARD_NO.equals(moreCard))) {
			throw new IllegalArgumentException(GET_MORE_CARD_ERROR);
		}
	}

	public static boolean getMoreCard(String name) {
		try {
			System.out.println(name + GET_MORE_CARD_MESSAGE);
			String moreCard = SCANNER.nextLine().toLowerCase();
			validateMoreCard(moreCard);
			return moreCard.equals(GET_MORE_CARD_YES);
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
			return getMoreCard(name);
		}
	}
}
