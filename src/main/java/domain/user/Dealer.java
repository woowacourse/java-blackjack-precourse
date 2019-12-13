package domain.user;

import domain.card.Card;
import domain.game.BlackJack;
import domain.game.Message;

import java.util.ArrayList;
import java.util.List;

/**
 * 게임 딜러를 의미하는 객체이다.
 *
 * @author kafka
 */
public class Dealer {
    /**
     * cards는 딜러가 가지는 카드(바닥에 깔아두는 패)를 나타내는 리스트이다.
     */
    private final List<Card> cards = new ArrayList<>();

    /**
     * 생성자 메서드에서는 별도의 동작을 취하지 않는다.
     */
    public Dealer() {
    }

    /**
     * addCard는 카드를 패(리스트)에 추가하는 메서드이다.
     * @param card 새로 리스트에 추가할 카드 객체이다.
     */
    public void addCard(Card card) {
        cards.add(card);
    }

    /**
     * 가지고 있는 카드들의 점수 합산을 계산해서 반환해준다.
     * 이때, ACE 카드는 가능한 11점으로, 불가할 경우 1점으로 계산한다.
     *
     * @return 계산된 점수를 반환해준다.
     */
    public int getScore() {
        int score = 0;
        for (Card card : cards) {
            score += card.getScore();
        }
        for (Card card : cards) {
            score += getAceScore(score, card);
        }
        return score;
    }

    /**
     * getAceScore는 점수 계산 도중 ACE 카드의 계산을 담당한다.
     * ACE 카드는 11점으로 계산되지만, 그것이 점수를 초과할 경우에는 1점으로 계산된다.
     *
     * @param score 현재 점수이다.
     * @param card 계산해야 할 카드이다. 이 카드가 ACE가 아니라면 함수는 0을 반환한다.
     * @return 계산된 추가 점수이다. 만약 카드가 ACE이고 11점을 부여해도 괜찮다면, 10을 반환해 이 값을 더해주도록 한다.(1점은 이미 이전에 더했으므로)
     */
    private int getAceScore(int score, Card card) {
        if (score <= BlackJack.MAX_SCORE - BlackJack.ACE_BONUS_SCORE && card.isAceCard()) {
            return BlackJack.ACE_BONUS_SCORE;
        }
        return 0;
    }
    /**
     * getCardListSize는 가지고 있는 패(카드 리스트)의 사이즈를 반환한다.
     *
     * @return 가지고 있는 카드의 수를 반환한다.
     */
    public int getCardListSize() {
        return cards.size();
    }

    /**
     * getScoreString은 점수를 양식에 맞춰 문자열로 반환해준다.
     *
     * @return 양식에 맞는 점수 문자열이 반환된다.
     */
    public String getScoreString() {
        return Message.PRINT_PlAYER_RESULT + Integer.toString(getScore());
    }

    /**
     * checkDrawMore는 드로우를 더 할 수 있는지(딜러의 경우, 특정 값 이하면 자동 드로우이다) 확인한다.
     * @return 드로우 가능 여부를 boolean 으로 반환한다.
     */
    public boolean checkDrawMore() {
        return BlackJack.DEALER_DRAW_SCORE >= getScore();
    }

    /**
     * getCardStringWithName은 카드와 이름을 합쳐 상위 객체에 전달하는 메서드이다.
     *
     * @return 이름과 카드 목록을 합친 문자열을 반환한다.
     */
    public String getCardStringWithName() {
        return "딜러 " + getCardString();
    }

    /**
     * getCardString은 가지고 있는 카드의 목록을 문자열로 합쳐 전달하는 메서드이다.
     *
     * @return 카드의 목록을 합친 문자열을 반환한다.
     */
    protected String getCardString() {
        List<String> nameList = new ArrayList<String>();
        String cardString = "카드: ";
        for (Card card : cards) {
            nameList.add(card.getCardName());
        }
        return cardString + String.join(",", nameList);
    }
}
