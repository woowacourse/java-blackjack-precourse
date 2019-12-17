/*
 * class: CardDrawing
 *
 * version: 1.0v
 *
 * date: 2019.12.16
 *
 * 이 프로그램의 저작권은 정은석에게 있습니다.
 * Copyright 2019.12.16
 */

package domain.input;

import domain.user.Player;

import java.util.Scanner;

/**
 * 플레이어가 드로우할지 결정하는 input기능
 *
 * @author joseph415
 * @version 1.0 2019.12.16
 */
public class InputDecesion {
    Scanner sc = new Scanner(System.in);

    /**
     * 플레이어가 드로우할지 결정하는 input메소드
     * @param player 플레이어의 정보
     * @return 드로우하면 true 안하면 false
     */
    public boolean decesion(Player player) {
        String decision;

        System.out.println(player.getName() + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
        decision = sc.nextLine();
        return decision.equals("y");
    }
}
