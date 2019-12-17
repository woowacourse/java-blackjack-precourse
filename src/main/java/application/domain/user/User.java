package application.domain.user;

import application.domain.card.Card;
import application.domain.card.CardSupplier;
import application.view.Output;

public abstract class User {
    protected static final int BLACK_JACK = 21;
    protected static final int PLUS = 10;
    protected static final int BUST = 0;

    abstract public void addCard(Card card);

    public abstract String getFirstShuffleCardInfo();

    public abstract String getAllCardsInfo();

    public void checkCardAndAddCard(CardSupplier supplier) {
        while (isAbleAddingCard()) {
            addCard(supplier.supply());
            Output.printAllCardsInfo(this);
        }
    }

    private boolean isAbleAddingCard() {
        return !isBlackJack() && !isBust() && !isSatisfiedAddingCardCondition();
    }

    protected abstract int getScoreTo(int index);

    protected abstract boolean isBlackJack();

    protected abstract boolean isBust();

    protected abstract boolean isSatisfiedAddingCardCondition();
}
