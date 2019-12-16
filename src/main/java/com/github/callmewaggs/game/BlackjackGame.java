package com.github.callmewaggs.game;

import com.github.callmewaggs.game.domain.card.Card;
import com.github.callmewaggs.game.domain.user.Participant;
import java.util.List;

public class BlackjackGame {
  List<Participant> participants;
  CardShuffler cardShuffler;

  public BlackjackGame(List<Participant> participants, List<Card> cards) {
    this.participants = participants;
    this.cardShuffler = CardShuffler(cards);
  }

  public void gameStart() {
  }
}
