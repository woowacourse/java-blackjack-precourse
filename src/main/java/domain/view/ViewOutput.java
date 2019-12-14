package domain.view;

import domain.user.UserRepository;

public class ViewOutput {
	public static void showFirstResult(UserRepository userRepository) {
		String playerNames = userRepository.getPlayerNames();
		System.out.println("딜러와 " + playerNames + "에게 2장의 카드를 나누었습니다.");
		
		userRepository.showFirstUserCard();
	}
}
