package domain.user;

import domain.card.Card;
import domain.game.BlackJack;

import java.util.ArrayList;
import java.util.List;

/**
 * Player는 게임 참여자를 의미하는 객체이다.
 * 카드를 더하고 배팅을 하는 기능을 가지고 있다.
 *
 * 로직상 별도의 money 변수가 있다면, 배팅에 따른 이익/손실을 계산하기에 용이할 것으로 보인다.
 * 그러나 요구사항 상 인스턴스 필드를 추가할 수 없으므로,
 * 또한 베팅머니라는 변수의 값 변경이 불가(final) 하므로
 * 실제 구현은 승패 여부에 따라 결과를 반환하는 함수의 추가 정도로 마친다.
 *
 * @author kafka
 * @version 1.2
 */
public class Player extends Dealer{

    private final String name;
    private final double bettingMoney;

    public Player(String name, double bettingMoney) {
        this.name = name;
        this.bettingMoney = bettingMoney;
    }
    public String getName() {
        return name;
    }
    public double getResultMoney(int dealerScore) {
        if(isBlackJackInFirstHand()){
            return bettingMoney / 2.0;
        }
        if(dealerScore > BlackJack.MAX_SCORE || (getScore() <= BlackJack.MAX_SCORE && dealerScore < getScore())){
            return bettingMoney;
        }
        if(getScore() > BlackJack.MAX_SCORE || getScore() < dealerScore){
            return bettingMoney * -1.0;
        }
        return 0.0;
    }
    public String getResultMoneyString(int dealerScore){
        return name+": "+Integer.toString((int)getResultMoney(dealerScore));
    }

    private boolean isBlackJackInFirstHand() {
        return getCardListSize() == 2 && getScore() == BlackJack.MAX_SCORE;
    }

    @Override
    public String getCardStringWithName() {
        return name + getCardString();
    }

}
