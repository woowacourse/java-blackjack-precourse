package domain.game;

import domain.user.Player;

/**
 * RequestException
 * 버전 : 1.0
 * 예외상황 처리를 위한 클래스
 */
public class RequestException {
	private final static int NAME_LENGTH = 5;
	private final static int MONEY_VALUE = 0;

	public RequestException() {
	}

	public void exceptionName(String name) throws Exception {
		if (name.length() > NAME_LENGTH) {
			throw new Exception();
		}
	}

	public void exceptionMoney(Double money) throws Exception {
		if (money <= MONEY_VALUE) {
			throw new Exception();
		}
	}

	public void exceptionAddCard(String req, Player player) throws Exception {
		if(!req.equals("y") && !req.equals("n")){
			player.setAddCardCheck(false);
			throw new Exception();
		}
		player.setAddCardCheck(true);
	}
}
