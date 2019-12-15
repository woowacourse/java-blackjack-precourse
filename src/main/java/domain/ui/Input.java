/*
 * @(#)Input.java       0.3 2019.12.15
 *
 * Copyright (c) 2019 lxxjn0
 */

package domain.ui;

import java.util.Scanner;

/**
 * 입력과 관련된 기능을 담당할 객체
 *
 * @author JUNYOUNG LEE (lxxjn0)
 * @version 0.3 2019.12.15
 */
public class Input {
    /**
     * 사용자로부터 입력을 받기 위한 Scanner 객체.
     */
    private Scanner scanner;

    /**
     * 사용자로부터 게임 참여자 이름을 입력받는 메소드.
     *
     * @return 사용자가 입력한 게임 참여자 이름 문자열.
     */
    public String receivePlayerNameInput() {
        scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    /**
     * 사용자로부터 해당 Player의 배팅 금액을 입력받는 메소드.
     *
     * @return 해당 Player의 배팅 금액.
     */
    public double receivePlayerBettingMoneyInput() {
        scanner = new Scanner(System.in);
        return scanner.nextDouble();
    }

    /**
     * 해당 Player가 한장의 카드를 더 받을지 여부를 입력받는 메소드.
     *
     * @return 한장의 카드를 더 받을지 여부(y 또는 n).
     */
    public String receivePlayerGetOneMoreCardInput() {
        scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
