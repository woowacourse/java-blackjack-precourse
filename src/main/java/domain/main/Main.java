package domain.main;

public class Main {

	public static void main(String[] args) {
		
		InputHandler.nameHandler();
		InputHandler.bettingMoneyHandler(InputHandler.nameStringList);
		System.out.println(InputHandler.bettingMoneyDoubleList);
		InputHandler.makePlayer();

	}

}
