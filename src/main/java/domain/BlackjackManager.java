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
    List<Participant> winners = new ArrayList<>();

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

    boolean checkBlackjackParticipants() {
        for (int i = 0; i < participants.size(); i++) {
            if (participants.get(i).getSumScore() == 21) {
                winners.add(participants.get(i));
            }
        }
        if (winners.size() == 0)
            return false;
        return true;
    }

    void checkGiveCardToParticipant(Participant participant) {
        if (availableGiveCardToDealer(participant)) {
            giveCardToParticipant(participant);
            gamePrinter.printDealerGetMoreCard();
        }
        while (availableGiveCardToPlayer(participant)) {
            giveCardToParticipant(participant);
            gamePrinter.callPrintParticipantCardInfo(participant, false, false);
        }
    }

    boolean availableGiveCardToDealer(Participant participant) {
        if (participant.isDealer()
                && participant.getCards().size() < 3
                && participant.getSumScore() < 16) {
            return true;
        }
        return false;
    }

    boolean availableGiveCardToPlayer(Participant participant) {
        String result = "n";
        if (!participant.isDealer() && participant.getSumScore() < 21) {
            result = gamePrinter.getAddInfoDecisionFromUser(participant);
        }
        if (result.equals("y"))
            return true;
        return false;
    }
    /**
     * 우승자를 찾고 경우에 따라 우승자의 베팅 금액을 결정하는 함수
     **/
    void setWinnerAndProfit() {
        double winnerMultiple = 1;
        // 이미 처음 카드에서 blackjack이 나왔으며, 딜러가 우승하지 않아 1.5배를 가져가는 경우
        if (winners.size() > 0 && !existsDealerInWinners())
            winnerMultiple = 1.5;
        // 초기에 blackjack이 나오지 않아 우승자를 찾아야 하는 경우
        if (winners.size() == 0)
            findWinners();
        setWinnersProfit(winnerMultiple);
    }

    boolean existsDealerInWinners() {
        for (int i = 0; i < winners.size(); i++) {
            if (winners.get(i).isDealer())
                return true;
        }
        return false;
    }

    boolean overBlackjack(Participant participant) {
        if (participant.getSumScore() > 21)
            return true;
        return false;
    }

    /**
     * 처음에 우승자가 없어서 우승자를 찾아야하는 경우
     */
    void findWinners() {
        // dealer의 score가 21을 넘어서, score가 21 미만인 모든 player가 상금을 받을 수 있는 경우
        if (overBlackjack(participants.get(0))) {
            addAllPlayerToWinnerExceptOverBlackjack();
            return;
        }
        // 최대 score를 가진 플레이어가 우승하는 경우
        int maxSumScore = getMaxSumScore();
        addPlayerToWinnerWhoGotMaxSumScore(maxSumScore);
    }

    void addAllPlayerToWinnerExceptOverBlackjack() {
        for (int i = 0; i < participants.size(); i++) {
            if (overBlackjack(participants.get(i)))
                continue;
            winners.add(participants.get(i));
        }
    }

    int getMaxSumScore() {
        int maxSumScore = 0;
        for (int i = 0; i < participants.size(); i++) {
            if (overBlackjack(participants.get(i)))
                continue;
            if (participants.get(i).getSumScore() > maxSumScore)
                maxSumScore = participants.get(i).getSumScore();
        }
        return maxSumScore;
    }

    void addPlayerToWinnerWhoGotMaxSumScore(int maxSumScore) {
        for (int i = 0; i < participants.size(); i++) {
            if (participants.get(i).getSumScore() == maxSumScore)
                winners.add(participants.get(i));
        }
    }

    void setWinnersProfit(double multiple) {
        for (int i = 0; i < winners.size(); i++)
            winners.get(i).setProfitByMultiple(multiple);
    }
}
