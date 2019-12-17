package com.woowacourse.blackjack.domain.user.dealer;

import com.woowacourse.blackjack.domain.user.Gamer;
import com.woowacourse.blackjack.domain.user.player.Name;

/**
 * 게임 딜러를 의미하는 객체
 *
 * @version 1.0.0
 * @author KSKIM
 * @since 2019-12-16
 */
public class Dealer extends Gamer {
    public static final Name NAME = new Name("딜러");
    private static final int MAX_DRAWABLE_SCORE = 16;

    public Dealer() {
        super();
    }

    public boolean canDraw() {
        return getScore() <= MAX_DRAWABLE_SCORE;
    }
}
