package com.github.callmewaggs.game.domain.card;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardStateTest {
  @DisplayName("CardState는 현재까지 뽑은 카드들의 점수를 계산한다.")
  @Test
  public void calculate_total_score_of_current_cards() {
    // Arrange
    Card queenClub = new Card(Symbol.QUEEN, Type.CLUB);
    Card nineSpade = new Card(Symbol.NINE, Type.SPADE);

    CardState cardState = new CardState();
    cardState.addCard(queenClub);
    cardState.addCard(nineSpade);

    // Act
    int actual = cardState.getCardsTotalScore();

    // Assert
    int expected = 19;
    assertEquals(expected, actual);
  }
  @DisplayName("점수를 계산할 때 Ace를 가지고 있는 경우 21에 더 가까운 수로 점수를 계산한다.")
  @Test
  public void calculate_ace_close_to_21() {
    // Arrange
    Card aceClub = new Card(Symbol.ACE, Type.CLUB);
    Card nineSpade = new Card(Symbol.NINE, Type.SPADE);

    CardState cardState = new CardState();
    cardState.addCard(aceClub);
    cardState.addCard(nineSpade);

    // Act
    int actual = cardState.getCardsTotalScore();

    // Assert
    int expected = 20;
    assertEquals(expected, actual);
  }
  @DisplayName("점수를 계산할 때 Ace를 가지고 있는 경우 11로 계산했을때 21을 초과하면 1로 계산한다.")
  @Test
  public void calculate_ace_to_one_when_total_score_of_cards_over_21() {
    // Arrange
    Card aceClub = new Card(Symbol.ACE, Type.CLUB);
    Card nineSpade = new Card(Symbol.NINE, Type.SPADE);
    Card kingDiamond = new Card(Symbol.KING, Type.DIAMOND);

    CardState cardState = new CardState();
    cardState.addCard(aceClub);
    cardState.addCard(nineSpade);
    cardState.addCard(kingDiamond);

    // Act
    int actual = cardState.getCardsTotalScore();

    // Assert
    int expected = 20;
    assertEquals(expected, actual);
  }

  @DisplayName("CardState는 현재까지 뽑은 카드들의 정보를 알려준다.")
  @Test
  public void shows_card_info() {
    // Arrange
    Card aceClub = new Card(Symbol.ACE, Type.CLUB);
    Card nineSpade = new Card(Symbol.NINE, Type.SPADE);
    Card kingDiamond = new Card(Symbol.KING, Type.DIAMOND);

    CardState cardState = new CardState();
    cardState.addCard(aceClub);
    cardState.addCard(nineSpade);
    cardState.addCard(kingDiamond);

    // Act
    String actual = cardState.getCardsInfo();

    // Assert
    String expected = "A/CLUB, 9/SPADE, K/DIAMOND";
    assertEquals(expected, actual);
  }
}