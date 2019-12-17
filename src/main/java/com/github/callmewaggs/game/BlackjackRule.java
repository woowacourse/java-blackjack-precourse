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

  private BlackjackGameResult gameResult;
  private CardShuffler cardShuffler;

  public BlackjackRule(Dealer dealer, List<Player> players, List<Card> cards) {
    this.gameResult = new BlackjackGameResult(dealer, players);
    this.cardShuffler = new CardShuffler(cards);
  }

  public void dealInitialCards(Dealer dealer, List<Player> players) {
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

  public void askHitOrStay(Dealer dealer, List<Player> players) {
    for (Player player : players) {
      takeCardIfPlayerHit(player);
    }
    if (dealer.hitOrStay()) {
      dealer.takeCard(cardShuffler.pickCard());
    }
    IOHelper.printCardsWithScore(getAllParticipants(dealer, players));
  }

  public void dealerLose(Dealer dealer, List<Player> players) {
    IOHelper.printFinalResultMessage();
    for (Player player : players) {
      gameResult.playerWin(dealer, player);
    }
    gameResult.printIncomes();
  }

  public void judgeWinOrLose(Dealer dealer, List<Player> players) {
    for (Player player : players) {
      if (dealer.getCurrentScore() < player.getCurrentScore()) {
        gameResult.playerWin(dealer, player);
      }
      if (dealer.getCurrentScore() == player.getCurrentScore()) {
        gameResult.push(dealer, player);
      }
    }
    gameResult.printIncomes();
  }

  // TODO : 2depth
  private void takeCardIfPlayerHit(Player player) {
    while (true) {
      if (player.hitOrStay()) {
        player.takeCard(cardShuffler.pickCard());
        IOHelper.printCardsWithoutScore(player);
        continue;
      }
      break;
    }
  }

  private List<Participant> getAllParticipants(Dealer dealer, List<Player> players) {
    List<Participant> participants = new ArrayList<>(players);
    participants.add(dealer);
    return participants;
  }
}
