package com.woowacourse.blackjack.view;

import static com.woowacourse.blackjack.domain.user.dealer.Dealer.*;

import java.util.List;
import java.util.stream.Collectors;

import com.woowacourse.blackjack.domain.card.Card;
import com.woowacourse.blackjack.domain.profit.Profit;
import com.woowacourse.blackjack.domain.user.dealer.Dealer;
import com.woowacourse.blackjack.domain.user.player.Name;
import com.woowacourse.blackjack.domain.user.player.Player;
import com.woowacourse.blackjack.domain.user.Gamer;

/**
 * 게임의 진행에 따라 시스템 메시지를 출력해주는 유틸리티
 *
 * @version 1.0.0
 * @author KSKIM
 * @since 2019-12-15
 */
public class OutputView {
	private static final String INITIAL_DRAW_MESSAGE = "딜러와 %s에게 2장의 카드를 분배했습니다.";
	private static final String CURRENT_CARD_MESSAGE = "%s 카드: %s";
	private static final String RESULT_CARD_MESSAGE = "%s 카드: %s - 결과: %d %s";
	private static final String RESULT_CARD_BUST_MESSAGE = "(버스트)";
	private static final String RESULT_BLACKJACK_MESSAGE = "(블랙잭)";
	private static final String RESULT_NORMAL_MESSAGE = "";
	private static final String RESULT_PROFIT_MESSAGE = "## 최종 수익";
	private static final String DEALER_DRAW_MESSAGE = "딜러는 16 이하라서 한 장의 카드를 더 받았습니다.";
	private static final String COMMA = ", ";

	// 인스턴스가 생성되는 것을 방지
	private OutputView() {}

	public static void showFirstDrawMessage(List<Player> players) {
		showNewLine();
		String names = players.stream()
				.map(Player::getName)
				.map(Name::toString)
				.collect(Collectors.joining(COMMA));
		String message = String.format(INITIAL_DRAW_MESSAGE, names);
		System.out.println(message);
	}

	// 규칙에 의해 처음 뽑은 카드 한 장은 공개하지 않는다.
	public static void showFirstCard(Dealer dealer) {
		int lastCardIndex = dealer.getCardCount() - 1;
		Card card = dealer.getCard(lastCardIndex);
		showCurrentCards(NAME, card.toString());
	}

	public static void showDealerDrawMessage() {
		System.out.println(DEALER_DRAW_MESSAGE);
	}

	public static void showCurrentCards(List<Player> players) {
		players.forEach(OutputView::showCurrentCards);
		showNewLine();
	}

	public static void showCurrentCards(Player player) {
		Name name = player.getName();
		String cardNames = player.getCardNames();
		showCurrentCards(name, cardNames);
	}

	private static void showCurrentCards(Name name, String cards) {
		String message = String.format(CURRENT_CARD_MESSAGE, name, cards);
		System.out.println(message);
	}

	public static void showResultCards(List<Player> players) {
		players.forEach(OutputView::showResultCards);
		showNewLine();
	}

	private static void showResultCards(Player player) {
		Name name = player.getName();
		showResultCards(name, player);
	}

	public static void showResultCards(Dealer dealer) {
		showResultCards(NAME, dealer);
	}

	private static void showResultCards(Name name, Gamer gamer) {
		String cardNames = gamer.getCardNames();
		int score = gamer.getScore();
		String suffixMessage = getSuffixMessage(gamer);
		String message = String.format(RESULT_CARD_MESSAGE, name, cardNames, score, suffixMessage);
		System.out.println(message);
	}

	private static String getSuffixMessage(Gamer gamer) {
		if (gamer.isBust()) {
			return RESULT_CARD_BUST_MESSAGE;
		}
		if (gamer.isBlackjack()) {
			return RESULT_BLACKJACK_MESSAGE;
		}
		return RESULT_NORMAL_MESSAGE;
	}

	public static void showNewLine() {
		System.out.println();
	}

	public static void showProfits(List<Profit> profits) {
		System.out.println(RESULT_PROFIT_MESSAGE);
		profits.forEach(System.out::println);
	}
}
