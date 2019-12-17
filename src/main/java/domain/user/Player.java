package domain.user;

import domain.card.Card;
import domain.ui.Utill;

import java.util.ArrayList;
import java.util.List;

/**
 * 게임 참여자를 의미하는 객체
 */
public class Player extends GameParticipant{
    private final double bettingMoney;

    public Player(String name, double bettingMoney) {
        super(name);
        this.bettingMoney = bettingMoney;
    }

    @Override
    public void addMoreCard(Card card) {
        Utill utill = new Utill();
        while(!this.isBust() && utill.askNeedMoreCard(this)){
            addCard(card);
            utill.printCardListOfGameParticipant(this);
        }
    }
}
