package com.github.callmewaggs.game;

import com.github.callmewaggs.game.domain.card.Card;
import com.github.callmewaggs.game.domain.user.Participant;
import java.util.List;

public class BlackjackGame {
  List<Participant> participants;
  List<Card> cards;

  public BlackjackGame(List<Participant> participants, List<Card> cards) {
    this.participants = participants;
    this.cards = cards;
  }

  public void gameStart() {
  }
}
