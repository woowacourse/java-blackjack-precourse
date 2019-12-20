
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
import domain.card.*;
import domain.user.*;

public class Input {
	private static final String INPUT_NAMES = "게임에 참여할 사람들의 이름을 입력해주세요.(쉼표를 기준으로 분리)";
	private static final String BETTING_MONEY = "의 베팅 금액을 입력하세요.";
	private static final String ONE_MORE_CARD = "은 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)";
	
	private String names;
	private double bettingMoney;
	private static final ExceptionHandler exception = new ExceptionHandler();
	static final Scanner sc = new Scanner(System.in);
	
	public String getPlayerNames() throws IllegalArgumentException {
		System.out.println(INPUT_NAMES);
		names = sc.nextLine();
		if (exception.isNull(names)) {
			throw new IllegalArgumentException("1명 이상의 플레이어를 입력해주세요.");
		} else if (exception.moreThanEight(names)){
			throw new IllegalArgumentException("8명 이하의 플레이어를 입력해주세요.");
		} else if (exception.isDuplicated(names)) {
			throw new IllegalArgumentException("플레이어 이름이 중복되었습니다.");
		}
		return names;
	}
	
	public double getBettingMoney(String name) {
		System.out.println(name + BETTING_MONEY);
		bettingMoney = Double.valueOf(getAnswer());
		if (exception.isMultipleOfTen(Double.valueOf(bettingMoney))) {
			throw new IllegalArgumentException("10의 배수가 아닙니다.");
		}
		return bettingMoney;
	}
	
	public void giveOneMoreCard(Player player, Deck deck) throws Exception {
		System.out.println(player.getName() + ONE_MORE_CARD);
		String answer = sc.nextLine();
		if (exception.isNotValidAnswer(answer)) {
			throw new IllegalArgumentException("예는 'y' 아니오는 'n'을 눌러주세요.");
		} else if (answer.equals("y")) {
			throw new Exception();
		}
	}
	
	private String getAnswer() {
		return sc.nextLine();
	}
}
