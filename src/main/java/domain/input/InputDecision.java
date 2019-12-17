/*
 * class: InputDecesion
 *
 * version: 1.0v
 *
 * date: 2019.12.17
 *
 * 이 프로그램의 저작권은 정은석에게 있습니다.
 * Copyright 2019.12.17
 */

package domain.input;

import domain.user.Player;

import java.util.Scanner;

/**
 * 플레이어가 드로우할지 결정하는 input기능
 *
 * @author joseph415
 * @version 1.0 2019.12.17
 */
public class InputDecision {
    Scanner sc = new Scanner(System.in);

    /**
     * 플레이어가 드로우할지 결정하는 input메소드
     *
     * @param player 플레이어의 정보
     * @return 드로우하면 true 안하면 false
     */
    public boolean decideDraw(Player player) {
        String decision = "";
        boolean exceptionIO = true;

        while (exceptionIO) {
            System.out.println(player.getName() + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
            decision = sc.next();
            exceptionIO = exceptIO(decision);
        }
        return decision.equals("y");
    }

    /**
     * y,n 이외의 입력 예외처리
     *
     * @param decision 카드를 드로우할지 결정하는 스트링
     * @return 예외처리 발생할경우 true
     */
    public boolean exceptIO(String decision) {
        if (decision.equals("y") || decision.equals("n")) {
            return false;
        }
        System.out.println("입력오류 재입력!!");
        return true;
    }
}
