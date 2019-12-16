package domain.view;

import java.util.List;

import domain.user.User;
import domain.user.UserRepository;

public class ViewOutput {
	public static void showFirstResult(UserRepository userRepository) {
		String playerNames = userRepository.getPlayerNames();
		System.out.println("딜러와 " + playerNames + "에게 2장의 카드를 나누었습니다.");
		
		userRepository.showFirstUserCard();
		System.out.println();
	}
	
	public static void showEachResult(User user) {
		System.out.print(user.getName() + " 카드 : ");
		user.showResult();
	}
	
	public static void showEachProfit(List<User> userList, List<Integer> profit) {
		System.out.println("### 최종 수익");
		for (int i = 0; i < userList.size(); i++) {
			User user = userList.get(i);
			System.out.println(user.getName() + " : " + profit.get(i));
		}
	}
}
