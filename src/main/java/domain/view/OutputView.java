package domain.view;

import java.util.List;

import domain.card.Card;
import domain.user.Dealer;
import domain.user.Player;

public class OutputView {

	public static void printPlayerCards(Player player) {
		String result = getPlayerCards(player);
		System.out.println(result);
	}

	private static String getPlayerCards(Player player) {
		StringBuffer sb = new StringBuffer();
		List<Card> cards = player.getCards();
		sb.append(player.getName());
		sb.append(": ");
		for (Card card : cards) {
			dividePlayerCardsWithComma(sb, player);
			printCardSymbolAndType(sb, card);
		}
		return sb.toString();
	}

	private static String getDealerCards(Dealer dealer) {
		StringBuffer sb = new StringBuffer();
		List<Card> cards = dealer.getCards();
		sb.append("딜러 카드");
		sb.append(": ");
		for (Card card : cards) {
			divideDealerCardsWithComma(sb);
			printCardSymbolAndType(sb, card);
		}
		return sb.toString();
	}

	private static void dividePlayerCardsWithComma(StringBuffer sb, Player player) {
		if (sb.length() > player.getName().length() + 2) {
			sb.append(",");
		}
	}

	private static void printCardSymbolAndType(StringBuffer sb, Card card) {
		sb.append(card.getSymbol());
		sb.append(" ");
		sb.append(card.getType());
	}

	public static void printDealerInitialCards(Dealer dealer) {
		String result = getDealerCards(dealer).split(",")[0];
		System.out.println(result);
	}

	private static void divideDealerCardsWithComma(StringBuffer sb) {
		if (sb.length() > 5 + 2) {
			sb.append(",");
		}
	}

	public static void printDealerCards(Dealer dealer) {
		String result = getDealerCards(dealer);
		System.out.println(result);
	}

	public static boolean isLessThanSeventeen(int dealerScore) {
		if (dealerScore <= 16) {
			System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
			return true;
		}
		System.out.println("딜러는 17이상이라 카드를 받지 않습니다.");
		return false;
	}

}
