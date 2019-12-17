package com.github.callmewaggs.game.domain.card;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardShufflerTest {

  @DisplayName("CardShuffler는 한 장씩 카드를 뽑아준다.")
  @Test
  public void card_shuffler_pick_a_card() {
    // Arrange
    List<Card> cards = CardFactory.create();
    CardShuffler cardShuffler = new CardShuffler(cards);
    int initialCardsCount = cardShuffler.getShuffledCards().size();

    // Act
    cardShuffler.pickCard();
    int actual = cardShuffler.getShuffledCards().size();

    // Assert
    int expected = initialCardsCount - 1;
    assertEquals(expected, actual);
  }
}