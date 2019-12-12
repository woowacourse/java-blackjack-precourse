package domain.view;

import java.util.Scanner;

public class View {
	static Scanner scanner = new Scanner(System.in);
	
	public void getPlayerNames() {
		String name;
		
		System.out.println("게임에 참여할 사람의 이름을 입력하세.(쉼표 기준으로 분리)");
		name = scanner.nextLine();
	}
}
