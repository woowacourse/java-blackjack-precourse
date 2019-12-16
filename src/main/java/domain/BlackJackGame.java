package domain;

import domain.function.CardDistributor;
import domain.function.Viewer;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.PlayerInputData;

import java.util.ArrayList;
import java.util.List;

import static domain.user.Dealer.MAX_SCORE_NEEDS_MORE_CARD;

public class BlackJackGame {

    public static void main(String[] args) {
        Dealer dealer = new Dealer();
        List<Player> playerList = PlayerInputData.getPlayerList();
        List<String> playerNameList = getPlayerNameList(playerList);
        CardDistributor cardDistributor = new CardDistributor();
        cardDistributor.giveTwoCardsToGameParticipant(dealer, playerList, playerNameList);
        addCardToParticipant(dealer, playerList, cardDistributor);
        Viewer.printGameParticipantTotalScore(dealer, playerList);
        Viewer.printGameParticipantProfit(dealer, playerList);
    }

    private static List<String> getPlayerNameList(List<Player> playerList) {
        List<String> playerNameList = new ArrayList<>();
        for (Player player : playerList) {
            playerNameList.add(player.getName());
        }
        return playerNameList;
    }

    private static void addCardToParticipant(Dealer dealer, List<Player> playerList, CardDistributor cardDistributor) {
        addCardToPlayer(playerList, cardDistributor);
        addCardToDealer(dealer, cardDistributor);
    }

    private static void addCardToPlayer(List<Player> playerList, CardDistributor cardDistributor) {
        for (Player player : playerList) {
            PlayerInputData.askCardAddition(player, cardDistributor);
        }
    }

    private static void addCardToDealer(Dealer dealer, CardDistributor cardDistributor) {
        if (dealer.doesNeedMoreCard()) {
            cardDistributor.giveCardToBlackjackUser(dealer);
            System.out.println(String.format("딜러는 %d 이하라 한 장의 카드를 더 받았습니다. \n", MAX_SCORE_NEEDS_MORE_CARD));
            return;
        }
        System.out.println(String.format("딜러는 %d 초과로 카드를 더 받지 않습니다. \n", MAX_SCORE_NEEDS_MORE_CARD));
    }

}
