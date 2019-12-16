package com.github.callmewaggs.game.domain.user;

import com.github.callmewaggs.game.domain.card.Card;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Participant {

  private final List<Card> cards = new ArrayList<>();

  public void takeACard(Card card) {
    cards.add(card);
  }

  public String getCardsInfo() {
    return cards.stream().map(Card::getScoreAndType).collect(Collectors.joining(", "));
  }

  public abstract String getName();
}
