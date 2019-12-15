/*
 * Main
 *
 * ver 1.0
 *
 * 2019.12.15
 *
 * CopyRight
 *
 */
package domain.main;

import domain.game.Betting;


public class Main {
    /*
     * Main 클래스 입니다.
     * 실행을 담당합니다.
     */
    public static void main(String[] args) {
        Betting betting = new Betting();
        betting.game();
    }

}
