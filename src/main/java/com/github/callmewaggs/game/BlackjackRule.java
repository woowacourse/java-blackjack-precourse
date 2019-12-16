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

  private CardShuffler cardShuffler;

  public BlackjackRule(List<Card> cards) {
    this.cardShuffler = new CardShuffler(cards);
  }

  public void dealInitialCards(Dealer dealer, List<Player> players) {
    for (int i = 0; i < INITIAL_CARDS_COUNT; ++i) {
      dealCardToDealerAndPlayers(dealer, players);
    }
    IOHelper.printAfterDealInitialCards(getAllParticipants(dealer, players));
  }

  private void dealCardToDealerAndPlayers(Dealer dealer, List<Player> players) {
    dealer.takeACard(cardShuffler.pickACard());
    for (Player player : players) {
      player.takeACard(cardShuffler.pickACard());
    }
  }

  private List<Participant> getAllParticipants(Dealer dealer, List<Player> players) {
    List<Participant> participants = new ArrayList<>();
    participants.add(dealer);
    participants.addAll(players);
    return participants;
  }
}
