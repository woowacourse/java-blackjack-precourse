package domain.user;

import domain.card.Card;
import domain.card.CardFactory;
import domain.ui.Utill;

import java.util.List;
import java.util.Random;

/**
 * 게임 딜러를 의미하는 객체
 */

public class Dealer extends GameParticipant {
    private final static int NUMBER_OF_CARD_GIVEN_FIRST = 2;
    private List<Card> cardpack = new CardFactory().create();// 딜러는 꼭 자신의 카드팩을 가져야 한다고 생각해서 불가피하게 추가하게 되었습니다.

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
        Utill utill = new Utill();
        if (getSumOfCardScore() < 17) {
            utill.printDealerReceiveMoreCard();
            addCard(card);
        }
    }

}
