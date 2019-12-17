package application.domain.user;

import application.domain.card.Card;

public abstract class User {
    abstract public void addCard(Card card);

    public abstract String getFirstShuffleCardInfo();
}
