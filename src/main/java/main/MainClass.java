package main;

import domain.game.BlackJack;

/**
 * MainClass는 프로그램을 실행하기 위한 main 함수를 포함하는 클래스이다.
 *
 * @author kafka
 * @version 1.0
 */
public class MainClass {
    /**
     * main 함수는 프로그램 시작 시 호출되는 정작 메서드이다.
     * 싱글톤 객체인 블랙잭을 호출하여, 게임을 시작한다.
     * 본 프로젝트에서는 게임이 끝나면 프로그램이 종료되므로, 별도의 루프는 설정하지 않는다.
     *
     * @param args 기본 파라미터
     */
    public static void main(String[] args) {
        BlackJack blackJack = BlackJack.getInstance();

        blackJack.playBlackJack();
    }
}
