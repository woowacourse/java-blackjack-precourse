package domain.project;

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
}
