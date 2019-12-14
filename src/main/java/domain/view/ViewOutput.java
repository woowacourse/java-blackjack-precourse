package domain.view;

import domain.user.UserRepository;

public class ViewOutput {
	public static void showFirstResult(UserRepository userRepository) {
		String players = userRepository.getPlayerList();
		System.out.println("딜러와 " + players + "에게 2장의 카드를 나누었습니다.");
		
		userRepository.showFirstUserCard();
	}
}
