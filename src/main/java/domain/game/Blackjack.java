package domain.game;

import domain.card.Card;
import domain.card.CardFactory;
import domain.user.Dealer;
import domain.user.Player;

import java.util.List;
import java.util.Scanner;

public class Blackjack {
    public final int MAX_PLAYER_NUMBER = 5;
    public final int MAX_NAME_LENGTH = 5;
    public Dealer dealer;
    public List<Player> players;
    public Scanner sc = new Scanner(System.in);
    private final List<Card> cards;

    public Blackjack() {
        cards = CardFactory.create();
        dealer = new Dealer();
    }

    public void ready() {
        String[] nameArr = getNames();
    }

    public String[] getNames() {
        String names;
        String[] nameArr;
        do {
            System.out.printf("게임에 참여할 사람의 이름을 입력하세요. (최대 %d명, %d글자 이내, 쉼표로 분리)%n",
                    MAX_PLAYER_NUMBER, MAX_NAME_LENGTH);
            names = sc.nextLine();
            nameArr = names.split(",", -1);
        } while (!checkNumber(nameArr) || !validateNames(nameArr));
        return nameArr;
    }

    public boolean checkNumber(String[] nameArr) {
        return nameArr.length <= MAX_PLAYER_NUMBER;
    }

    public boolean validateNames(String[] nameArr) {
        for (int i = 0; i < nameArr.length; i++) {
            nameArr[i] = nameArr[i].trim();
            if ((nameArr[i].length() == 0) || (nameArr[i].length() > MAX_NAME_LENGTH)) {
                return false;
            }
        }
        return true;
    }
}
