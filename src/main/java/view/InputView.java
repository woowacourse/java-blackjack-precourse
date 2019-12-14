package view;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
	private static final String[] SEPARATOR = {",", "쉼표"};
	private static final String PROMPT_NAMES = String.format(
		"게임에 참여할 사람의 이름을 입력하세요.(%s 기준으로분리)", SEPARATOR[1]);
	private static final String PROMPT_BETTING = "의 배팅 금액은?";
	private static Scanner scanner = new Scanner(System.in);

	public List<String> getNameList() {
		System.out.println(PROMPT_NAMES);
		String inputValue = scanner.nextLine();
		List<String> crudeList = Arrays.asList(inputValue.split(SEPARATOR[0]));
		return crudeList.stream()
			.map(String::trim)
			.collect(Collectors.toList());
	}

	public double getBettingMoney(String name) throws InputMismatchException {
		System.out.println(name + PROMPT_BETTING);
		double inputValue = scanner.nextDouble();
		return inputValue;
	}
}
