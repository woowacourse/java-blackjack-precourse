package com.github.callmewaggs.game.domain.user;

import com.github.callmewaggs.game.domain.card.Card;
import java.util.ArrayList;
import java.util.List;

public class Participant {

  private final List<Card> cards = new ArrayList<>();

  public void addCard(Card card) {
    cards.add(card);
  }

}
