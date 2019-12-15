/*
 * @(#)Main.java        0.1 2019.12.15
 *
 * Copyright (c) 2019 lxxjn0
 */

import domain.business.GameManager;

/**
 * 프로그램을 실행하기 위한 Main 클래스.
 */
public class Main {
    /**
     * GameManager 객체를 생성하여 게임을 실행하는 메소드.
     *
     * @param args 매개변수 존재하지 않음.
     * @throws Exception 카드 생성에 예외가 발생한 경우 생기는 예외.
     */
    public static void main(String[] args) throws Exception {
        GameManager gameManager = new GameManager();
        gameManager.run();
    }
}
