package com.woowacourse.blackjack.controller;

import static com.woowacourse.blackjack.domain.user.Gamer.INITIAL_DRAW_COUNT;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.woowacourse.blackjack.domain.profit.Profit;
import com.woowacourse.blackjack.domain.profit.Result;
import com.woowacourse.blackjack.domain.user.HitSelectType;
import com.woowacourse.blackjack.domain.card.Card;
import com.woowacourse.blackjack.domain.card.CardDeck;
import com.woowacourse.blackjack.domain.user.player.BettingMoney;
import com.woowacourse.blackjack.domain.user.dealer.Dealer;
import com.woowacourse.blackjack.domain.user.player.Name;
import com.woowacourse.blackjack.domain.user.player.Player;
import com.woowacourse.blackjack.domain.user.player.PlayerFactory;
import com.woowacourse.blackjack.view.InputView;
import com.woowacourse.blackjack.view.OutputView;

/**
 * 게임의 흐름을 담당하는 객체
 *
 * @version 1.0.0
 * @author KSKIM
 * @since 2019-12-15
 */
public class BlackjackGame {
	private final CardDeck cardDeck;
	private final Dealer dealer;
	private List<Player> players;

	public BlackjackGame(CardDeck cardDeck, Dealer dealer) {
		this.cardDeck = Objects.requireNonNull(cardDeck);
		this.dealer = Objects.requireNonNull(dealer);
	}

	public void play() {
		initPlayers();
		draw();
		showResults();
		showProfits();
	}

	private void initPlayers() {
		players = getPlayers();
	}

	private List<Player> getPlayers() {
		List<Name> names = InputView.getNames();
		List<BettingMoney> bettingMonies = getBettingMonies(names);
		return PlayerFactory.createPlayers(names, bettingMonies);
	}

	private List<BettingMoney> getBettingMonies(List<Name> names) {
		return names.stream()
				.map(InputView::getBettingMoney)
				.collect(Collectors.toList());
	}

	private void draw() {
		drawFirstTurn();
		if (dealer.isBlackjack()) { // game over
			return;
		}
		drawAdditionalTurns();
	}

	private void drawFirstTurn() {
		drawPlayersAtFirstTurn();
		drawDealerAtFirstTurn();
		showFirstTurnMessage();
	}

	private void drawPlayersAtFirstTurn() {
		for (Player player: players) {
			List<Card> cards = cardDeck.draw(INITIAL_DRAW_COUNT);
			player.addCards(cards);
		}
	}

	private void drawDealerAtFirstTurn() {
		List<Card> cards = cardDeck.draw(INITIAL_DRAW_COUNT);
		dealer.addCards(cards);
	}

	private void showFirstTurnMessage() {
		OutputView.showFirstDrawMessage(players);
		OutputView.showFirstCard(dealer);
		OutputView.showCurrentCards(players);
	}

	private void drawAdditionalTurns() {
		drawPlayers();
		drawDealer();
	}

	private void drawPlayers() {
		players.forEach(this::draw);
	}

	private void draw(Player player) {
		while (canDraw(player)) {
			Card card = cardDeck.draw();
			player.addCard(card);
			OutputView.showCurrentCards(player);
			OutputView.showNewLine();
		}
	}

	private boolean canDraw(Player player) {
		if (player.isBust() || player.isMaxScore()){
			return false;
		}
		HitSelectType userSelect = InputView.getHitSelect(player);
		return HitSelectType.HIT.equals(userSelect);
	}

	private void drawDealer() {
		while (dealer.canDraw()) {
			OutputView.showDealerDrawMessage();
			Card card = cardDeck.draw();
			dealer.addCard(card);
		}
	}

	private void showResults() {
		OutputView.showNewLine();
		OutputView.showResultCards(dealer);
		OutputView.showResultCards(players);
	}

	private void showProfits() {
		Result result = new Result(dealer, players);
		List<Profit> profits = result.getProfits();
		OutputView.showProfits(profits);
	}
}
