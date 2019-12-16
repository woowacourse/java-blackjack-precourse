package domain.blackjack;

import domain.card.Card;
import domain.card.CardFactory;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {

  private List<Card> cards;

  public Deck() {
    this.cards = new ArrayList<>(CardFactory.create());
  }

  public void shuffle() {
    Collections.shuffle(this.cards);
  }

  public Card draw(){
    Card card = this.cards.get(0);
    cards.remove(0);

    return card;
  }
}
