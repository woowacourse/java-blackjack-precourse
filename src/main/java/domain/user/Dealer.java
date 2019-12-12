package domain.user;

import domain.card.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * 게임 딜러를 의미하는 객체
 */
public class Dealer {
    protected final List<Card> cards = new ArrayList<>();
    /**
     * 딜러는 돈을 배팅하거나 버는 일이 없으므로, money는 0으로 설정되어 변경되지 않는다.
     * 하지만 만약 유저가 잃은 돈을 딜러가 받아 수익으로 올리는 등의 로직이 필요하다면,
     * 이 변수를 바탕으로 재설계할 수 있다.
     */
    protected double money = 0;

    public Dealer() {
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public int getScore() {
        int score = 0;
        for(Card card : cards) {
            score += card.getScore();
        }
        for(Card card : cards) {
            score += card.getAceScore(score);
        }
        return score;
    }
    public String getOneCardString(){
        return "딜러: " + cards.get(0).getCardName();
    }
    public String getCardString() {
        List<String> nameList = new ArrayList<String>();
        String cardString = "딜러 카드: ";
        for(Card card : cards) {
            nameList.add(card.getCardName());
        }
        return cardString + String.join(",",nameList);
    }

    public String getScoreString() {
        return " - 결과: " + Integer.toString(getScore());
    }

    public void addMoney(int money){
        this.money += money;
    }

    /**
     * getMoneyString은 최종 수익을 계산해주는 메서드이다.
     * 딜러 객체는 배팅 기능이 없으므로, 이 메서드는 단순한 값만 출력하게 설계했다.
     * 만약 유저가 돈을 잃을 때 그만큼 그 돈이 딜러에게 가도록 재설계한다면,
     * 이 메서드를 다시 작성할 필요가 생긴다.
     *
     * @return
     */
    public String getMoneyString() {
        return "딜러: "+ Double.toString(money);
    }
}
