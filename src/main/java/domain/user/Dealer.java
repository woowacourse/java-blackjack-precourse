package domain.user;

import domain.card.Card;
import domain.card.CardFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 게임 딜러를 의미하는 객체
 */

public class Dealer extends GameParticipant {
    private final static int NUMBER_OF_CARD_GIVEN_FIRST = 2;
    private List<Card> cardpack = new CardFactory().create();

    public Dealer() {
        super("dealer");
    }

    public Card giveCard() {
        Random random = new Random();
        Card pickedCard = cardpack.get(random.nextInt(cardpack.size()));
        cardpack.remove(pickedCard);

        if (cardpack.size() == 0) {
            cardpack = new CardFactory().create();
        }

        return pickedCard;
    }

    @Override
    public void addMoreCard(Card card) {
        if(getSumOfCardScore() <17) {
            addCard(card);
        }
    }

}
