package domain.user;

import domain.card.Card;
import jdk.nashorn.internal.objects.annotations.Constructor;

import java.util.ArrayList;
import java.util.List;

/**
 * Player는 게임 참여자를 의미하는 객체이다.
 * Dealer 객체를 상속받아 addCard 메소드와 cardList를 가지고 있으며,
 * Player는 별도로 이름, 배팅금액, 배팅 관련 메소드를 가진다.
 *
 * @author kafka
 * @version 1.1
 */
public class Player extends Dealer{
    private final String name;
    private final double bettingMoney;

    public Player(String name, double bettingMoney) {
        this.name = name;
        this.bettingMoney = bettingMoney;
    }

    // TODO 추가 기능 구현

}
