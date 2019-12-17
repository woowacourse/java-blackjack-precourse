package domain.user;

import common.BlackjackConfig;
import domain.BlackjackPrinter;
import domain.card.Deck;

public class DealerServiceImpl extends UserService {

    public DealerServiceImpl(Deck deck, BlackjackPrinter blackjackPrinter) {
        super(deck, blackjackPrinter);
    }

    @Override
    public void confirmCards(User user) {
        int point = user.calculateScore();

        while (pointInRange(point)) {
            blackjackPrinter.printDealerHit(user);
            user.addCard(deck.pick());
            point = user.calculateScore();
        }
    }

    private boolean pointInRange(int point) {
        return point <= BlackjackConfig.STANDARD_TO_HIT_FOR_DEALER;
    }

    public Deck shuffle() {
        deck.shuffle();
        return deck;
    }
}
