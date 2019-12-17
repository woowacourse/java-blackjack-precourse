package domain.user;

import domain.card.Card;
import domain.main.GamePlay;

import java.util.ArrayList;
import java.util.List;

/**
 * 게임 참여자를 의미하는 객체
 */
public class Player {
    private final String name;
    private final double bettingMoney;
    private final List<Card> cards = new ArrayList<>();
    public final int NUMBER_OF_START_CARDS = 2;

    public Player(String name, double bettingMoney) {
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
    public void printPlayer() {
        System.out.println(name + "의 배팅금액 : " + bettingMoney);
    }
    public void getStartCard() {
        for (int i = 0; i < NUMBER_OF_START_CARDS; i++) {
            cards.add(GamePlay.addNewCard());
        }
    }
    public void haveCardList(){
        System.out.print(name+"님의 카드 : ");
        for (Card card : cards){
            System.out.print(card.cardToString()+"\t");
        }
        System.out.println();
    }

}
