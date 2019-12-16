package utils;

import domain.user.Dealer;
import domain.user.Player;

public class CardsPrinter {
	public static void printWithName(Player player) {
		System.out.println(player.getName() + " 카드 : " + player.getCards().toString());
	}
	
	public static void printWithName(Dealer dealer) {
		System.out.println("딜러 카드 : " + dealer.getCards().toString());
	}
}
