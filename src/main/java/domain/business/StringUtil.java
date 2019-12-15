/*
 * @(#)StringUtil.java      0.4 2019.12.15
 *
 * Copyright (c) 2019 lxxjn0
 */

package domain.business;

import java.util.Arrays;
import java.util.List;

/**
 * 문자열을 처리하는 기능을 담당하는 클래스
 *
 * @author JUNYOUNG LEE (lxxjn0)
 * @version 0.4 2019.12.15
 */
public class StringUtil {
    /**
     * 게임 참여자 이름을 자르기 위한 쉼표(,) 구분자 상수.
     */
    private static final String COMMA_DELIMITER = ",";

    /**
     * 게임 참여자 이름을 쉼표(,)를 기준으로 자르고, 공백을 제거하는 메소드.
     *
     * @param userInput 입력받은 게임 참여자 이름 문자열.
     * @return 쉼표(,)를 기준으로 자르고, 공백을 제거하는 모든 처리가 완료된 Player 이름 List.
     */
    public static List<String> processPlayerName(String userInput) {
        List<String> playerNames;

        playerNames = splitPlayerName(userInput);
        trimPlayerName(playerNames);
        return playerNames;
    }

    /**
     * 사용자의 게임 참여자 이름 입력을 쉼표(,)를 기준으로 자르는 메소드.
     *
     * @param userInput 입력받은 게임 참여자 이름 문자열.
     * @return 쉼표(,)를 기준으로 잘려진 Player 이름.
     */
    private static List<String> splitPlayerName(String userInput) {
        return Arrays.asList(userInput.split(COMMA_DELIMITER));
    }

    /**
     * Player 이름의 앞, 뒤에 불필요한 공백을 제거하는 메소드.
     *
     * @param playerNames Player 이름 List.
     */
    private static void trimPlayerName(List<String> playerNames) {
        for (int i = 0; i < playerNames.size(); i++) {
            playerNames.set(i, playerNames.get(i).trim());
        }
    }
}
