/*
 * @(#)Main.java        0.2 2019.12.16
 *
 * Copyright (c) 2019 lxxjn0
 */

import domain.business.GameManager;

/**
 * 프로그램을 실행하기 위한 Main 클래스.
 *
 * @author JUNYOUNG LEE (lxxjn0)
 * @version 0.2 2019.12.16
 */
public class Main {
    /**
     * GameManager 객체를 생성하여 게임을 실행하는 메소드.
     *
     * @param args 매개변수 존재하지 않음.
     */
    public static void main(String[] args) {
        GameManager gameManager = new GameManager();
        gameManager.run();
    }
}
