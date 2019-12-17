package domain.project;

import domain.user.Person;
import domain.user.Player;
import domain.user.Dealer;

public class Printer {
	
	public void printInputPlayer() {
		System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
	}
	
	public void printContainComma() {
		System.out.println("사람의 이름을 쉼표(,)로 구분해 주세요.");
	}
	
	public void printEmptyPlayerName() {
		System.out.println("비어있는 사람 이름이 있습니다. 다시 입력해 주세요.");
	}
	
	public void printPlayerSizeUnderTwo() {
		System.out.println("게임 참여자는 2명 이상이어야 합니다. 다시 입력해 주세요.");
	}
	
	public void printPlayerSizeOverEight() {
		System.out.println("게임 참여자는 8명 이하여야 합니다. 다시 입력해 주세요.");
	}
	
	public void printInputBettingMoney(String name) {
		System.out.println("\r\n" + name + "의 배팅 금액은?");
	}
	
	public void printInputNumber() {
		System.out.println("숫자를 입력해주세요.");
	}
	
	public void printNonZeroBettingMoney() {
		System.out.println("1 이상의 양의 정수를 입력해 주세요.");
	}
	
	public void printDealer() {
		System.out.print("딜러: ");
	}
	
	public void printPlayerName(String name) {
		System.out.print(name + "카드: ");
	}
	
	public void printCard(String result) {
		 System.out.println(result);
	}
	
	public void printNewLine() {
		System.out.println();
	}
	
	public void printChooseCard(String name) {
		System.out.println(name + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
	}
	
	public void printNotAllowedAnswer() {
		System.out.println("y 또는 n을 입력해 주세요.");
	}
	
	public void printStopGetCard(String name) {
		System.out.println(name + "의 카드의 합이 21을 넘었습니다. 더 이상 카드를 뽑을 수 없습니다.");
	}
	
	public void printDealerStopGetCard() {
		System.out.println("딜러는 17이상이라 카드를 더 받지 않았습니다.");
	}
	
	public void printDealerGetCard() {
		System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
	}
	
	public void printInitialCard(String nameString) {
		System.out.println("\r\n딜러와 " + nameString + "에게 2장의 카드를 나누었습니다.");
	}
}
