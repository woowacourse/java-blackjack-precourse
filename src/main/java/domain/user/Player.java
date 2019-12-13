package domain.user;

import domain.card.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * 게임 참여자를 의미하는 객체
 */
public class Player extends Dealer {
    private final String name;
    private final double bettingMoney;

    public Player(String name, double bettingMoney) {
        this.name = name;
        this.bettingMoney = bettingMoney;
    }
    
    //입출력 실험 메소드
    public void inputTest(){
        System.out.println(this.name + " " + this.bettingMoney);
    }
    // TODO 추가 기능 구현

}
