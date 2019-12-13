package domain;

import domain.card.CardDistributor;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.PlayerInputData;

import java.util.List;

public class BlackJackGame {
    private static final int FIRST_DISTRIBUTED_CARD_AMOUNT = 2;
    private static List<Player> playerList;
    private static Dealer dealer;
    private static CardDistributor cardDistributor;

    public static void main(String[] args) {
        setGameParticipant();
        distributeCardsToGameParticipant();
    }

    private static void setGameParticipant() {
        PlayerInputData inputData = new PlayerInputData();
        playerList = inputData.getPlayerList();
        dealer = new Dealer();
    }

    private static void distributeCardsToGameParticipant() {
        cardDistributor = new CardDistributor();
        cardDistributor.giveCardToDealer(FIRST_DISTRIBUTED_CARD_AMOUNT, dealer);
        for (Player player : playerList) {
            cardDistributor.giveCardToPlayer(FIRST_DISTRIBUTED_CARD_AMOUNT, player);
        }
    }

}
