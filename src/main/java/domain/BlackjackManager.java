package domain;

import domain.card.Card;
import domain.card.CardFactory;
import domain.user.Dealer;
import domain.user.Participant;
import domain.user.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * 게임 전반을 관리하는 객체
 */
public class BlackjackManager {
    GamePrinter gamePrinter = new GamePrinter();
    CardFactory cardFactory = new CardFactory();
    List<Participant> participants = new ArrayList<>(); // 딜러와 플레이어를 모두 포함하는 리스트
    List<Card> cards;
    List<Card> addedCards = new ArrayList<>();

    void makeParticipants() {
        participants.add(new Dealer());
        String[] playerName = gamePrinter.getPlayerNameFromUser();
        for (int i = 0; i < playerName.length; i++) {
            int bettingMoney = gamePrinter.getBettingMoneyFromUser(playerName[i]);
            participants.add(new Player(playerName[i], bettingMoney));
        }
    }

    void makeCard() {
        cards = cardFactory.create();
    }

    void giveCardToParticipant(Participant participant) {
        int cardIndex = (int) (Math.random() * cards.size());
        boolean availableToGive = true;
        for (int i = 0; i < addedCards.size(); i++) {
            cardOverlap(cards.get(cardIndex), addedCards.get(i), availableToGive);
        }

        if (availableToGive) {
            participant.addCard(cards.get(cardIndex));
            addedCards.add(cards.get(cardIndex));
            return;
        }
        giveCardToParticipant(participant);
    }

    // 새로 추가할 카드가 이미 추가된 카드인지 확인한다.
    void cardOverlap(Card newCard, Card addedCard, boolean availableToGive) {
        if (newCard.equals(addedCard))
            availableToGive = false;
    }

    void giveInitialCards() {
        for (int i = 0; i < participants.size(); i++) {
            giveCardToParticipant(participants.get(i));
            giveCardToParticipant(participants.get(i));
        }
    }
}
