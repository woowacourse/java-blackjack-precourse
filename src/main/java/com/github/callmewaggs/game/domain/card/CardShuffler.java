package com.github.callmewaggs.game.domain.card;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class CardShuffler {

  private static final int SHUFFLE_TIMES = 10000;
  private static final int RANDOM_DIGIT = 1000;

  private final Queue<Card> shuffledCards;

  public CardShuffler(List<Card> cards) {
    List<Card> newCards = new ArrayList<>(cards);
    this.shuffledCards = shuffleCards(newCards);
  }

  public Queue<Card> getShuffledCards() {
    return shuffledCards;
  }

  public Card pickCard() {
    return shuffledCards.poll();
  }

  private Queue<Card> shuffleCards(List<Card> cards) {
    for (int i = 0; i < SHUFFLE_TIMES; ++i) {
      int index1 = (int) (Math.random() * RANDOM_DIGIT) % cards.size();
      int index2 = (int) (Math.random() * RANDOM_DIGIT) % cards.size();

      swapCards(cards, index1, index2);
    }

    return new ArrayDeque<>(cards);
  }

  private void swapCards(List<Card> cards, int index1, int index2) {
    Card temp = cards.get(index1);
    cards.set(index1, cards.get(index2));
    cards.set(index2, temp);
  }
}
