package domain.game;

public class RequestException {
	private static final int NAME_LENGTH = 5;

	public RequestException() {
	}

	public void exceptionName(String name) throws Exception{
		if (name.length() > NAME_LENGTH) {
			throw new Exception();
		}
	}
}
