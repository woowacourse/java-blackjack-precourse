/*
 * @(#)StringUtil.java      0.９ 2019.12.17
 *
 * Copyright (c) 2019 lxxjn0
 */

package domain.business;

import domain.card.Card;
import domain.user.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 문자열　처리를 담당하는 클래스.
 *
 * @author JUNYOUNG LEE (lxxjn0)
 * @version 0.９ 2019.12.17
 */
public class StringUtil {
    /**
     * 게임 참여자 이름을 자르기 위한 쉼표(,) 구분자 상수.
     */
    private static final String SPLIT_COMMA_DELIMITER = ",";

    /**
     * Player의 이름과　카드의　정보를 문자열로　합칠 때 사용할 쉼표＋공백(,　) 구분자 상수.
     */
    private static final String JOIN_COMMA_DELIMITER = ", ";

    /**
     * 게임 참여자 이름을 쉼표(,)를 기준으로 자르고, 공백을 제거하는　입력　처리 메소드.
     *
     * @param userInput 입력받은 게임 참여자 이름 문자열.
     * @return 쉼표(,)를 기준으로 자르고, 공백을 제거하는 모든 처리가 완료된 Player들의 이름.
     */
    public static List<String> processPlayerName(String userInput) {
        return trimPlayersName(splitToPlayersName(userInput));
    }

    /**
     * 사용자의 게임 참여자 이름 입력을 쉼표(,)를 기준으로 자르는 메소드.
     *
     * @param userInput 입력받은 게임 참여자 이름 문자열.
     * @return 쉼표(,)를 기준으로 잘려진 Player들의 이름.
     */
    private static List<String> splitToPlayersName(String userInput) {
        return Arrays.asList(userInput.split(SPLIT_COMMA_DELIMITER));
    }

    /**
     * Player들의 이름 앞, 뒤에 불필요한 공백을 제거하는 메소드.
     *
     * @param playerNames 불필요한 공백이 제거된 Player들의 이름.
     * @return 불필요한 공백이 제거된 Player들의 이름.
     */
    private static List<String> trimPlayersName(List<String> playerNames) {
        for (int i = 0; i < playerNames.size(); i++) {
            playerNames.set(i, playerNames.get(i).trim());
        }
        return playerNames;
    }

    /**
     * Player들의 이름을 받아와 join한 후 문자열로 반환하는 메소드.
     *
     * @param players Player들의 이름.
     * @return Player들의 이름을 합친 문자열.
     */
    public static String joinPlayersName(List<Player> players) {
        List<String> playerNames = new ArrayList<>();

        for (Player player : players) {
            playerNames.add(player.getName());
        }
        return String.join(JOIN_COMMA_DELIMITER, playerNames);
    }

    /**
     * Dealer 또는 Player의 card List를 받아와 해당 card들의 이름을 join한 문자열로 반환하는 메소드.
     *
     * @param cards card List.
     * @return card List를 합친 문자열.
     */
    public static String joinCardsName(List<Card> cards) {
        List<String> cardNames = new ArrayList<>();

        for (Card card : cards) {
            cardNames.add(card.toString());
        }
        return String.join(JOIN_COMMA_DELIMITER, cardNames);
    }
}
