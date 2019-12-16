package gameRunner;

import domain.card.Card;
import domain.card.CardFactory;
import domain.user.ActiveGamers;

import java.util.List;
import java.util.stream.IntStream;

import static view.OutputView.showGamersCard;
import static view.OutputView.showGiveCardToEachInfo;

public class BlackJackGame {

    private final int INITIAL_GIVING_CARD_COUNT = 2;

    private List<Card> cards = CardFactory.create();
    private ActiveGamers activeGamers;
    private CardFactory cardFactory;

    public BlackJackGame(final List<String> names, final List<Double> bettingMoneys) {
        this.activeGamers = new ActiveGamers(names, bettingMoneys);
        this.cardFactory = new CardFactory();
    }

    public void giveInitCard() {
        IntStream.range(0, INITIAL_GIVING_CARD_COUNT).boxed()
                .forEach(x -> giveCardToGamers());
        showGiveCardToEachInfo(activeGamers.getPlayersName());
        showGamersCard(activeGamers);
    }

    private void giveCardToGamers() {
        List<Card> givingCards = cardFactory.popRandomCard(activeGamers.size());
        this.activeGamers.addCardToEveryOne(givingCards);
    }


}
