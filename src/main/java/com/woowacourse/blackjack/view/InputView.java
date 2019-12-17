package com.woowacourse.blackjack.view;

import java.util.List;
import java.util.Scanner;

import com.woowacourse.blackjack.domain.user.HitSelectType;
import com.woowacourse.blackjack.domain.user.player.BettingMoney;
import com.woowacourse.blackjack.domain.user.player.Name;
import com.woowacourse.blackjack.domain.user.player.NameFactory;
import com.woowacourse.blackjack.domain.user.player.Player;

/**
 * 콘솔을 통해 유저의 입력을 받는 유틸리티
 *
 * @version 1.0.0
 * @author KSKIM
 * @since 2019-12-15
 */
public class InputView {
	private static final String USER_NAME_INPUT_GUIDE_MESSAGE =
			"게임에 참여할 사람의 이름을 입력하세요.\n(쉼표 기준으로 분리, 이름은 1~5자, 플레이어 수 1~8명)";
	private static final String BETTING_MONEY_INPUT_GUIDE_MESSAGE = "%s의 베팅 금액은?";
	public static final String DRAW_MORE_CARD_INPUT_GUIDE_MESSAGE =
			"%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)";
	private static final Scanner SCANNER = new Scanner(System.in);

	// 인스턴스가 생성되는 것을 방지
	private InputView() {}

	public static List<Name> getNames() {
		try {
			System.out.println(USER_NAME_INPUT_GUIDE_MESSAGE);
			String names = SCANNER.nextLine();
			return NameFactory.createDistinctPlayerNames(names);
		} catch (IllegalArgumentException e) {
			return getNames();
		}
	}

	public static BettingMoney getBettingMoney(Name name) {
		try {
			String message = String.format(BETTING_MONEY_INPUT_GUIDE_MESSAGE, name);
			System.out.println(message);
			String bettingMoney = SCANNER.nextLine();
			return BettingMoney.valueOf(bettingMoney);
		} catch (IllegalArgumentException e){
			return getBettingMoney(name);
		}
	}

	public static HitSelectType getHitSelect(Player player) {
		try {
			String message = String.format(DRAW_MORE_CARD_INPUT_GUIDE_MESSAGE, player.getName());
			System.out.println(message);
			String hitSelect = SCANNER.nextLine();
			return HitSelectType.of(hitSelect);
		} catch (IllegalArgumentException e) {
			return getHitSelect(player);
		}
	}
}
