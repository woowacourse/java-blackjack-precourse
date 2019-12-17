/*
 * @(#)Input.java       0.6 2019.12.17
 *
 * Copyright (c) 2019 lxxjn0
 */

package domain.ui;

import java.util.Scanner;

/**
 * 입력과 관련된 기능을 담당할 객체.
 *
 * @author JUNYOUNG LEE (lxxjn0)
 * @version 0.6 2019.12.17
 */
public class Input {
    /**
     * 사용자로부터 입력을 받기 위한 Scanner 객체.
     */
    private Scanner scanner;

    /**
     * 사용자로부터 게임 참여자들의 이름을 입력받는 메소드.
     *
     * @return 사용자가 입력한 게임 참여자들의 이름의 문자열.
     */
    public String receivePlayerNameInput() {
        scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    /**
     * 사용자로부터 배팅 금액을 입력받는 메소드.
     *
     * @return 사용자가 입력한 배팅 금액.
     */
    public double receiveBettingMoneyInput() {
        scanner = new Scanner(System.in);
        return scanner.nextDouble();
    }

    /**
     * Player가 한장의 card를 더 받을지 여부를 입력받는 메소드.
     *
     * @return 한장의 card를 더 받을지 여부(y 또는 n).
     */
    public String receivePlayerGetMoreCardInput() {
        scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
