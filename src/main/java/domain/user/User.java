package domain.user;

import domain.card.Card;

import java.util.List;

public interface User {

    public void addCard(Card card);
    public String getCards();
    public List<Card> getCardsList();
    public int getSumOfCards();
}
