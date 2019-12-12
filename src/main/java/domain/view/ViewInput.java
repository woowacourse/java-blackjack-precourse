package domain.view;

import java.util.Scanner;
import domain.game.Game;

public class ViewInput {
	private static Scanner scanner = new Scanner(System.in);
	static Game blackJack = Game.getInstance();
	
	public static void getPlayerNames() {
		System.out.println("게임에 참여할 사람의 이름을 입력하세.(쉼표 기준으로 분리)");
		blackJack.makePlayers(scanner.nextLine());
	}
}
