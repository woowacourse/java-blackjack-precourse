package com.github.callmewaggs.game;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.github.callmewaggs.game.domain.user.Dealer;
import com.github.callmewaggs.game.domain.user.Player;
import java.util.ArrayList;
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
    blackjackRule = mock(BlackjackRule.class);
  }

  @DisplayName("딜러가 블랙잭이면 블랙잭 규칙에 의해 딜러의 블랙잭 루틴이 수행되고 종료된다.")
  @Test
  public void run_dealers_blackjack_routine_when_dealer_is_blackjack() {
    // Arrange
    players = mock(List.class);

    when(dealer.isBlackjack()).thenReturn(true);

    blackjackGame = new BlackjackGame(dealer, players, blackjackRule);

    // Act
    blackjackGame.gameStart();

    // Assert
    verify(blackjackRule, times(1)).whenDealerIsBlackjack(dealer, players);
  }

  @DisplayName("딜러가 블랙잭이 아니고 플레이어중 블랙잭이 있다면 해당 플레이어는 게임이 끝나 플레이어에서 제외된다.")
  @Test
  public void exclude_player_who_is_blackjack_when_dealer_is_not_blackjack() {
    // Arrange
    Player player1 = createMockPlayerWithIsBlackjackResult(true);
    Player player2 = createMockPlayerWithIsBlackjackResult(false);
    players = new ArrayList<>();
    players.add(player1);
    players.add(player2);

    when(dealer.isBlackjack()).thenReturn(false);

    blackjackGame = new BlackjackGame(dealer, players, blackjackRule);

    // Act
    blackjackGame.gameStart();

    // Assert
    verify(blackjackRule, times(1)).whenPlayerIsBlackjack(dealer, player1);
    int expected = 1;
    assertEquals(expected, blackjackGame.getNumberOfPlayers());
  }

  @DisplayName("dealer bust인 경우 dealer가 진 것이므로, dealer lose 루틴이 수행된다.")
  @Test
  public void execute_dealer_lose_routine_when_dealer_bust() {
    // Arrange
    Player player1 = createMockPlayerWithIsBlackjackResult(false);
    Player player2 = createMockPlayerWithIsBlackjackResult(false);
    players = new ArrayList<>();
    players.add(player1);
    players.add(player2);

    when(dealer.isBlackjack()).thenReturn(false);
    when(dealer.isBust()).thenReturn(true);

    blackjackGame = new BlackjackGame(dealer, players, blackjackRule);

    // Act
    blackjackGame.gameStart();

    // Assert
    verify(blackjackRule, times(1)).dealerLose(dealer, players);
  }

  private Player createMockPlayerWithIsBlackjackResult(boolean result) {
    Player player = mock(Player.class);
    when(player.isBlackjack()).thenReturn(result);
    return player;
  }
}