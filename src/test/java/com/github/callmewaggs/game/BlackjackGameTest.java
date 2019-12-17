package com.github.callmewaggs.game;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.github.callmewaggs.game.domain.user.Dealer;
import com.github.callmewaggs.game.domain.user.Player;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackjackGameTest {

  private Dealer dealer;
  private List<Player> players;
  private BlackjackRule blackjackRule;

  private BlackjackGame blackjackGame;

  @BeforeEach
  public void init() {
    dealer = mock(Dealer.class);
    players = mock(List.class);
    blackjackRule = mock(BlackjackRule.class);
    blackjackGame = new BlackjackGame(dealer, players, blackjackRule);
  }

  @DisplayName("딜러가 블랙잭이면 블랙잭 규칙에 의해 딜러의 블랙잭 루틴이 수행되고 종료된다.")
  @Test
  public void run_dealers_blackjack_routine_when_dealer_is_blackjack() {
    // Arrange
    when(dealer.isBlackjack()).thenReturn(true);

    // Act
    blackjackGame.gameStart();

    // Assert
    verify(blackjackRule, times(1)).whenDealerIsBlackjack(dealer, players);
  }

}