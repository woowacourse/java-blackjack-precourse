package com.woowacourse.blackjack;

import com.woowacourse.blackjack.controller.BlackjackGame;
import com.woowacourse.blackjack.domain.card.CardDeck;
import com.woowacourse.blackjack.domain.card.CardFactory;
import com.woowacourse.blackjack.domain.user.dealer.Dealer;

/**
 * 블랙잭 어플리케이션의 시작부
 *
 * @version 1.0.0
 * @author KSKIM
 * @since 2019-12-15
 */
public class BlackjackApplication {
	public static void main(String[] args) {
		CardDeck cardDeck = new CardDeck(CardFactory.create());
		Dealer dealer = new Dealer();
		BlackjackGame blackjackGame = new BlackjackGame(cardDeck, dealer);
		blackjackGame.play();
	}
}
