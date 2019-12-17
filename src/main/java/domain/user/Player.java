package domain.user;

import domain.card.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * ���� �����ڸ� �ǹ��ϴ� ��ü
 */
public class Player {
    private final String name;
    private final double bettingMoney;
    private final List<Card> cards = new ArrayList<>();

    public Player(String name, double bettingMoney) {
        this.name = name;
        this.bettingMoney = bettingMoney;
    }

    public void addCard(Card card) {
        cards.add(card);
    }
    
    public List<Card> printCard() {
    	return cards;
    }
    public String getName() {
    	return name;
    }
    public int getBettingMoney() {
    	return (int)bettingMoney;
    }
    // TODO �߰� ��� ����

}
