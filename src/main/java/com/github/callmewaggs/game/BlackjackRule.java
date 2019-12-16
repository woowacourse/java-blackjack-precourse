package com.github.callmewaggs.game;

import com.github.callmewaggs.game.domain.card.Card;
import com.github.callmewaggs.game.domain.card.CardShuffler;
import java.util.List;

public class BlackjackRule {

  CardShuffler cardShuffler;

  public BlackjackRule(List<Card> cards) {
    this.cardShuffler = new CardShuffler(cards);
  }
}
