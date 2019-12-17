package com.woowacourse.blackjack.domain.user.player;

import java.util.Objects;

import com.woowacourse.blackjack.domain.profit.ResultType;
import com.woowacourse.blackjack.domain.user.Gamer;
import com.woowacourse.blackjack.domain.user.dealer.Dealer;

/**
 * 게임 참여자를 의미하는 객체
 *
 * @version 1.0.0
 * @author KSKIM
 * @since 2019-12-16
 */
public class Player extends Gamer {
    private final Name name;
    private final BettingMoney bettingMoney;

    public Player(Name name, BettingMoney bettingMoney) {
        this.name = Objects.requireNonNull(name);
        this.bettingMoney = Objects.requireNonNull(bettingMoney);
    }

    public Name getName() {
        return name;
    }

    // 우선순위: 딜러가 블랙잭 > 플레이어가 블랙잭 > 딜러가 버스트 > 이외의 경우
    public ResultType isWin(Dealer dealer) {
        if (dealer.isBlackjack()) {
            return compareByDealerIsBlackjack();
        }
        if (isBlackjack()) {
            return ResultType.BLACKJACK;
        }
        if (dealer.isBust()) {
            return compareByDealerIsBust();
        }
        return compareByNormalCase(dealer);
    }

    private ResultType compareByDealerIsBlackjack() {
        if (isBlackjack()) {
            return ResultType.DRAW;
        }
        return ResultType.LOSE;
    }

    private ResultType compareByDealerIsBust() {
        if (isBust()) {
            return ResultType.LOSE;
        }
        return ResultType.WIN;
    }

    // 딜러가 블랙잭 또는 버스트가 아닌 경우
    private ResultType compareByNormalCase(Dealer dealer) {
        if (isBust()) {
            return ResultType.LOSE;
        }
        return compareByScore(dealer);
    }

    private ResultType compareByScore(Dealer dealer) {
        int playerScore = getScore();
        int dealerScore = dealer.getScore();
        if (playerScore > dealerScore) {
            return ResultType.WIN;
        }
        if (playerScore < dealerScore) {
            return ResultType.LOSE;
        }
        return ResultType.DRAW;
    }

    public double getProfit(ResultType resultType) {
        return resultType.getProfit(bettingMoney);
    }
}
