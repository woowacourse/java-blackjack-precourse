package domain.blackjack;

import domain.card.Card;
import domain.card.CardFactory;
import java.util.List;

public class Deck {

  public List<Card> cards;

  public Deck() {
    this.cards = CardFactory.create();
  }
}
