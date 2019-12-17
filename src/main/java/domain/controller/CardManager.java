package domain.controller;

import domain.card.Card;
import domain.card.CardFactory;
import domain.user.User;

import java.util.List;

public class CardManager {

    public void giveCard(User user, int numOfCard) {
        int CARD_NUMBER = user.getCards().size();
        List<Card> cardList = createCard();
        for (int i = CARD_NUMBER;
             i < (CARD_NUMBER + numOfCard); i++) {
            user.addCard(cardList.get(i));
        }
    }

    private List<Card> createCard() {
        return CardFactory.create();
    }

}
