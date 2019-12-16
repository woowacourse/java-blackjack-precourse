/*
 * class: ConstructPlayerAndDealer
 *
 * version: 1.0v
 *
 * date: 2019.12.16
 *
 * 이 프로그램의 저작권은 정은석에게 있습니다.
 * Copyright 2019.12.16
 */

package domain.construtor;

import domain.input.InputMoney;
import domain.input.InputName;
import domain.user.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 플레이어와 딜러를 생성하는 기능을 하는 클래
 *
 * @author joseph415
 * @version 1.0 2019.12.16
 */
public class ConstructPlayerAndDealer {
    List<String> names;
    List<Player> playerList = new ArrayList<>();
    InputName input = new InputName();
    InputMoney money = new InputMoney();

    /**
     * 플레이어 객체를 생성하는 기능을 하는 메소드
     *
     * @return 플레이어객체를 담은 list
     */
    public List<Player> constructor() {
        names = input.inputName();
        Map<String, Double> maps = money.inputMoney(names);
        maps.forEach((x, y) -> {
            playerList.add(new Player(x, y));
        });
        return playerList;
    }

}
