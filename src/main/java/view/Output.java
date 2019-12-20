package view;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import domain.user.*;
import domain.card.*;

public class Output {
	private static final boolean BLACKJACK = true;
	private static final boolean NOT_BLACKJACK = false;
	
	public void showDrawCards(List<Player> players, Dealer dealer) {
		System.out.print("딜러와 ");
		System.out.print(players.stream()
							.map(player -> player.getName())
							.collect(Collectors.joining(",")));
		System.out.println("에게 카드를 2장씩 나누었습니다.");
		dealer.getSymbolAndType(dealer);
		for (Player player : players) {
			player.getSymbolAndType();
		}
	}
	
	public void finalProfit(Player player, double profit) {
		System.out.println(player.getName() + " : " + (int)(profit));
	}
}
