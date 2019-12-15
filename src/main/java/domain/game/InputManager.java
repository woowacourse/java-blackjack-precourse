package domain.game;

import java.util.Scanner;

/**
 * 플레이어의 입력과 유효성 검사를 담당하는 객체
 */
public class InputManager {
	private static final double DEFAULT_BETTING_MONEY = 0.0;
	private static final int FIRST_INDEX = 0;
	private static final char HIT = 'y';
	private static final char STAY = 'n';
	
	Scanner input = new Scanner(System.in);

	/**
	 * 플레이어의 이름 입력 메서드
	 * @return
	 */
	public String[] inputPlayerNames() {
		input = new Scanner(System.in);
		String[] playerNames;
		try {
			System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉽표 기준으로 분리)");
			playerNames = input.next().split(",");
			validatePlayerNames(playerNames);
			return playerNames;
		} catch (Exception e) {
			System.out.println("잘못된 입력입니다.");
			return inputPlayerNames();
		}
	}

	private void validatePlayerNames(String[] playerNames) throws Exception {
		for (String name : playerNames) {
			if (name.contains(" ")) {
				throw new Exception();
			}
		}
	}

	/**
	 * 플레이어의 배팅 금액 입력 메서드
	 * @param name
	 * @return
	 */
	public double inputBettingMoney(String name) {
		input = new Scanner(System.in);
		double bettingMoney = DEFAULT_BETTING_MONEY;
		try {
			System.out.println(name + "의 배팅 금액은?");
			bettingMoney = input.nextDouble();
			validateBettingMoney(bettingMoney);
			return bettingMoney;
		} catch (Exception e) {
			System.out.println("잘못된 입력입니다.");
			return inputBettingMoney(name);
		}
	}

	private void validateBettingMoney(double bettingMoney) throws Exception {
		if (bettingMoney <= DEFAULT_BETTING_MONEY) {
			throw new Exception();
		}
	}

	/**
	 * 플레이어의 Hit 또는 Stay 입력 메서드
	 * @return
	 */
	public char chooseHitOrStay() {
		input = new Scanner(System.in);
		char choice = ' ';
		try {
			System.out.println("한 장의 카드를 더 받겠습니까? (예는 y, 아니오는 n)");
			choice = input.next().charAt(FIRST_INDEX);
			validateChoice(choice);
			return choice;
		} catch (Exception e) {
			System.out.println("잘못된 입력입니다.");
			return chooseHitOrStay();
		}
	}

	private void validateChoice(char choice) throws Exception {
		if (choice != HIT && choice != Character.toUpperCase(HIT) && choice != STAY && choice != Character.toUpperCase(STAY)) {
			throw new Exception();
		}
	}

}
