package services;

import exceptions.NegativeNumberException;
import utils.UtilityMethods;

import java.util.List;
import java.util.Scanner;

public class BlackJackInputService {
	public static List<String> getPlayersName() {
		System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
		Scanner scanner = new Scanner(System.in);
		String names = scanner.nextLine();
		if (names.equals("")){
			throw new NegativeNumberException();
		}
		scanner.close();
		return UtilityMethods.sliceStringToList(names);
	}
}
