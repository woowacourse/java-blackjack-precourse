package domain.card;

import game.GameConstants;
import util.RandomNumberGenerator;
import view.OutputView;

import java.util.List;

public class CardDeck {
    private List<Card> cardList;
    private RandomNumberGenerator generator;

    public CardDeck() {
        initCardList();
        this.generator = new RandomNumberGenerator(GameConstants.RANGE_MIN, cardList.size() - 1);
    }

    private void initCardList() {
        cardList = CardFactory.create();
    }

    public void resetCardIfNotExist() {
        if (cardList.size() <= 0) {
            OutputView.printNoCard();
            initCardList();
        }
    }

    public Card drawNewCard() {
        int index = generator.getNumber();
        Card card = cardList.get(index);
        cardList.remove(index);
        generator.setRange(GameConstants.RANGE_MIN, cardList.size() - 1);
        return card;
    }
}
