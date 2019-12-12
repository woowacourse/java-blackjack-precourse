package view;

public class InputView {
	private static final String ASK_PLAYER_NAME = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
	private static final String ASK_PLAYER_BETTING = "의 배팅 금액은?";

	public static void printAskPlayerName() {
		System.out.println(ASK_PLAYER_NAME);
	}

	public static void printAskPlayerBettingAmount(String playerName) {
		System.out.println(playerName + ASK_PLAYER_BETTING);
	}
}
