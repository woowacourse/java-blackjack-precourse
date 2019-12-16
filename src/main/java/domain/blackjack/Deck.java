package domain.blackjack;

import domain.card.Card;
import domain.card.CardFactory;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {

  public List<Card> cards;

  public Deck() {
    this.cards = new ArrayList<>(CardFactory.create());
  }

  public void shuffle() {
    Collections.shuffle(this.cards);
  }
}
