package domain.user;

import domain.card.Card;
import domain.main.GamePlay;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 * 게임 딜러를 의미하는 객체
 */
public class Dealer {
    private final List<Card> cards = new ArrayList<>();
    private String name;
    private double bettingMoney;
    public final int NUMBER_OF_START_CARDS = 2;
    public static final int STANDARD_POINT = 21;
    public final int DRAW_STANDARD_POINT = 16;
    public final int ACE_POINT = 10;
    public final int FAIL_RATE = -1;
    public final double SUCCESS_RATE = 1.5;
    public List<Boolean> aceList = new ArrayList<Boolean>();
    public int playerScore = 0;
    public int NumberOfAce = 0;

    public Dealer(String name, double bettingMoney) {
        this.name = name;
        this.bettingMoney = bettingMoney;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    // TODO 추가 기능 구현

    public void printPlayerBettingMoney() {
        System.out.println(name + "의 배팅금액 : " + bettingMoney);
    }

    public void getStartCard() {
        for (int i = 0; i < NUMBER_OF_START_CARDS; i++) {
            cards.add(GamePlay.addNewCard());
        }
    }

    public void printHaveCardList() {
        System.out.print(name + "님의 카드 : ");
        for (Card card : cards) {
            System.out.print(card.cardToString() + "\t");
        }
        System.out.println();
    }


    public void cardDrawOrPass() {
        if (chooseCalculateMethod() <= DRAW_STANDARD_POINT) {
            System.out.println("딜러는 16이하로 한 장을 더 받습니다.");
            addCard(GamePlay.addNewCard());
        }
    }

    public void checkAceCard(Card card) {
        aceList.add(card.getSymbol().getExpression().equals("A"));
    }

    public void getNumberOfAceCard(Card card) {
        if (card.getSymbol().getExpression().equals("A")) {
            NumberOfAce++;
        }
    }

    public void checkAceInCardList() {
        for (Card card : cards) {
            checkAceCard(card);
            getNumberOfAceCard(card);
        }
    }

    public int chooseCalculateMethod() {
        playerScore = 0;
        if (aceList.contains(true)) {
            return calculateScoreWithAce();
        }
        return calculateScoreWithoutAce();
    }

    public int calculateScoreWithoutAce() {
        for (Card card : cards) {
            playerScore += card.getSymbol().getScore();
        }
        return playerScore;
    }

    public int calculateScoreWithAce() {
        playerScore = calculateScoreWithoutAce();
        for (int i = 0; i < NumberOfAce; i++) {
            playerScore = selectElevenOrOne(playerScore);
        }
        return playerScore;
    }

    public int selectElevenOrOne(int playerScore) {
        if ((Math.abs(STANDARD_POINT - (playerScore+ACE_POINT)) < Math.abs(STANDARD_POINT - playerScore)) &&
                (playerScore+ACE_POINT <= STANDARD_POINT)){
            return playerScore + ACE_POINT;
        }
        return playerScore;
    }

    public boolean isDealerSuccess(){
        return playerScore == STANDARD_POINT;
    }
    public boolean isDealerFail(){
        return playerScore > Dealer.STANDARD_POINT;
    }

    public double result(){
        if (isDealerFail()){
            return bettingMoney * FAIL_RATE;
        }
        if (isDealerSuccess()){
            return bettingMoney * SUCCESS_RATE;
        }
        return bettingMoney;
    }
}
