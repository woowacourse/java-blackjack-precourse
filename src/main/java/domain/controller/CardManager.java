package domain.controller;

import domain.card.Card;
import domain.card.CardFactory;
import domain.user.Player;

import java.util.List;

public class CardManager {


    public void giveCard(Player player, int numOfCard) {
        List<Card> cardList = createCard();
        for (int i = 0; i < numOfCard; i++) {
            player.addCard(cardList.get(i));
        }
    }

    private List<Card> createCard() {
        return CardFactory.create();
    }



}
