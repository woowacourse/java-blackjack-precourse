package domain.user;

/**
 * 게임 딜러를 의미하는 객체
 */
public class Dealer extends User {
	private final int firstCard = 1;
	
    // TODO 추가 기능 구현
	
	public String getName() {
		return "딜러";
	}
	
	public String getFirstCard() {
		return getCards().get(firstCard).toString();
	}
}
