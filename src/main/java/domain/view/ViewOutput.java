package domain.view;

import domain.user.User;
import domain.user.UserRepository;

public class ViewOutput {
	public static void showFirstResult(UserRepository userRepository) {
		String playerNames = userRepository.getPlayerNames();
		System.out.println("딜러와 " + playerNames + "에게 2장의 카드를 나누었습니다.");
		
		userRepository.showFirstUserCard();
	}
	
	public static void showEachResult(User user) {
		System.out.print(user.getName() + " 카드 : ");
		user.showResult();
	}
}
