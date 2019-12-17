/**
 * Copyright (c) 2019 Seungwan Park,
 * All rights reserved.
 */

import domain.controller.BlackJackGameController;

/**
 * @author Seungwan Park, github.com/toneyparky
 * @version 1.0
 * @apiNote 블랙잭 게임을 실행할 메인클래스입니다.
 * @since : 2019.12.17 화요일
 */
public class Application {

    public static void main(String[] args) {
        playBlackJack();
    }

    public static void playBlackJack() {
        BlackJackGameController blackJackGameController = new BlackJackGameController();
        blackJackGameController.blackJackGameController();
    }
}
