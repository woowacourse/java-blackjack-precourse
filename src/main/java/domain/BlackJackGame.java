package domain;

import domain.card.CardDistributor;
import domain.card.CardPrinter;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.PlayerInputData;

import java.util.List;

public class BlackJackGame {
    private static final int FIRST_DISTRIBUTED_CARD_AMOUNT = 2;
    private static List<Player> playerList;
    private static List<String> playerNameList;
    private static Dealer dealer;
    private static CardDistributor cardDistributor;

    public static void main(String[] args) {
        setGameParticipant();
        distributeCardsToGameParticipant();
        printDistributedCards();
    }

    private static void setGameParticipant() {
        PlayerInputData inputData = new PlayerInputData();
        playerList = inputData.getPlayerList();
        playerNameList = inputData.getPlayerNameList();
        dealer = new Dealer();
    }

    private static void distributeCardsToGameParticipant() {
        cardDistributor = new CardDistributor();
        cardDistributor.giveCardToDealer(FIRST_DISTRIBUTED_CARD_AMOUNT, dealer);
        for (Player player : playerList) {
            cardDistributor.giveCardToPlayer(FIRST_DISTRIBUTED_CARD_AMOUNT, player);
        }
    }

    private static void printDistributedCards() {
        String playerName = String.join(",", playerNameList);
        System.out.println(String.format("딜러와 %s에게 각각 %d장의 카드를 나누어 주었습니다.", playerName, FIRST_DISTRIBUTED_CARD_AMOUNT));
        CardPrinter.printDealerFirstCardOnly(dealer);
        CardPrinter.printPlayerAllCards(playerList, playerNameList);
    }

}
