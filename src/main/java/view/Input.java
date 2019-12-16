
/*
 * ClassName : Input
 * 
 * version : 0.1
 * 
 * date : 2019.12.16
 * 
 * author : ParkDooWon
 */

package view;

import java.util.Scanner;

import exceptionhandler.ExceptionHandler;

public class Input {
	private static final String INPUT_NAMES = "게임에 참여할 사람들의 이름을 입력해주세요.(쉼표를 기준으로 분리)";
	private static final String INPUT_BATTING_MONEY = "의 베팅 금액은?";	
	private static final ExceptionHandler exception = new ExceptionHandler();
	private String playerNames;
	private String battingMoney;
	static final Scanner sc = new Scanner(System.in);

	public String getPlayerNames() throws IllegalArgumentException {
		System.out.println(INPUT_NAMES);
		playerNames = sc.nextLine();
		if (exception.lessThanOne(playerNames)) {
			throw new IllegalArgumentException("1명 이상의 플레이어를 입력해주세요.");
		} else if (exception.moreThanEight(playerNames)) {
			throw new IllegalArgumentException("8명 이하의 플레이어를 입력해주세요.");
		} else if (exception.isDuplicated(playerNames)) {
			throw new IllegalArgumentException("플레이어 이름이 중복되었습니다.");
		}
		return playerNames;
	}
	
	public int getBattingMoney(String name) {
		System.out.println(name + INPUT_BATTING_MONEY);
		battingMoney = sc.nextLine();
		if (exception.isPositiveNum(battingMoney)) {
			throw new IllegalArgumentException("음수를 입력했습니다. 10의 배수인 양수를 입력해주세요.");
		} else if (exception.isNumber(battingMoney)) {
			throw new IllegalArgumentException("문자가 포함되었습니다. 숫자를 입력해주세요.");
		} else if (exception.isMultipleOfTen(battingMoney)) {
			throw new IllegalArgumentException("베팅 금액을 10의 배수로 입력해주세요.");
		}
		return Integer.valueOf(battingMoney);
	}
		
}
