package com.github.callmewaggs.game;

import com.github.callmewaggs.game.domain.card.Card;
import com.github.callmewaggs.game.domain.card.CardShuffler;
import com.github.callmewaggs.game.domain.user.Dealer;
import com.github.callmewaggs.game.domain.user.Participant;
import com.github.callmewaggs.game.domain.user.Player;
import java.util.ArrayList;
import java.util.List;

public class BlackjackRule {

  private static final int INITIAL_CARDS_COUNT = 2;
  public static final int BLACKJACK_NUMBER = 21;

  private final BlackjackGameResult gameResult;
  private final CardShuffler cardShuffler;

  public BlackjackRule(Dealer dealer, List<Player> players, List<Card> cards) {
    this.gameResult = new BlackjackGameResult(dealer, players);
    this.cardShuffler = new CardShuffler(cards);
  }

  void dealInitialCards(Dealer dealer, List<Player> players) {
    List<Participant> participants = getAllParticipants(dealer, players);
    for (int i = 0; i < INITIAL_CARDS_COUNT; ++i) {
      dealCardToDealerAndPlayers(participants);
    }
    IOHelper.printAfterDealInitialCards(getAllParticipants(dealer, players));
  }

  private void dealCardToDealerAndPlayers(List<Participant> participants) {
    for (Participant participant : participants) {
      participant.takeCard(cardShuffler.pickCard());
    }
  }

  void askHitOrStay(Dealer dealer, List<Player> players) {
    for (Player player : players) {
      takeCardUntilPlayerHit(player);
    }
    if (dealer.hitOrStay()) {
      dealer.takeCard(cardShuffler.pickCard());
    }
    IOHelper.printCardsWithScore(getAllParticipants(dealer, players));
  }

  void dealerLose(Dealer dealer, List<Player> players) {
    IOHelper.printDealerBustMessage();
    for (Player player : players) {
      gameResult.playerWin(dealer, player);
    }
    gameResult.printIncomes();
  }

  void judgeWinOrLose(Dealer dealer, List<Player> players) {
    for (Player player : players) {
      judgeWinOrLoseByPlayer(dealer, player);
    }
    gameResult.printIncomes();
  }

  private void judgeWinOrLoseByPlayer(Dealer dealer, Player player) {
    if (player.isBust()) {
      return;
    }
    if (dealer.getCurrentScore() < player.getCurrentScore()) {
      gameResult.playerWin(dealer, player);
    }
    if (dealer.getCurrentScore() == player.getCurrentScore()) {
      gameResult.push(dealer, player);
    }
  }

  private void takeCardUntilPlayerHit(Player player) {
    boolean hit = true;
    while (hit) {
      hit = takeCardIfPlayerHit(player);
    }
  }

  private boolean takeCardIfPlayerHit(Player player) {
    if (player.hitOrStay()) {
      player.takeCard(cardShuffler.pickCard());
      IOHelper.printCardsWithoutScore(player);
      return true;
    }
    return false;
  }

  private List<Participant> getAllParticipants(Dealer dealer, List<Player> players) {
    List<Participant> participants = new ArrayList<>(players);
    participants.add(dealer);
    return participants;
  }

  void whenDealerIsBlackjack(Dealer dealer, List<Player> players) {
    for (Player player : players) {
      checkPlayerIsBlackjackToo(dealer, player);
    }
  }

  private void checkPlayerIsBlackjackToo(Dealer dealer, Player player) {
    if (player.isBlackjack()) {
      gameResult.push(dealer, player);
    }
  }

  void whenPlayerIsBlackjack(Dealer dealer, Player player) {
    gameResult.playersBlackjack(dealer, player);
    IOHelper.printBlackjackMessage(player.getName());
  }
}
