package domain.user;

import domain.card.Card;
import domain.main.GamePlay;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 게임 참여자를 의미하는 객체
 */
public class Player {
    private final String name;
    private final double bettingMoney;
    private final List<Card> cards = new ArrayList<>();
    public final int NUMBER_OF_START_CARDS = 2;
    public final String ANSWER_YES = "y";
    public final String ANSWER_NO = "n";
    public final int STANDARD_POINT = 21;
    public String playerChoice;

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

    public boolean checkInputPlayerChoice(String check) {
        return check.equals(ANSWER_YES) || check.equals(ANSWER_NO);
    }

    public String inputPlayerChoice() {
        Scanner s = new Scanner(System.in);
        do {
            System.out.println("카드를 뽑으시려면 y 넘기려면 n 을 입력하세요.");
            playerChoice = s.nextLine();
        } while (!checkInputPlayerChoice(playerChoice));
        return playerChoice;
    }

    public boolean checkPlayerChoice() {
        return inputPlayerChoice().equals(ANSWER_YES);
    }

    public void cardDrawOrPass() {
        while(checkPlayerChoice()) {
            addCard(GamePlay.addNewCard());
            printHaveCardList();
        }
    }

//    public
//    public int selectTenOrOne() {
//        if ()
//    }
//
//    public int calculateScore(){
//
//        return 1;
//    }

}
