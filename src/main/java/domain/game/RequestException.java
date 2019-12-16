package domain.game;

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

	public void exceptionAddCard(String req) throws Exception {
		if(!req.equals("y") && !req.equals("n")){
			throw new Exception();
		}
	}
}
