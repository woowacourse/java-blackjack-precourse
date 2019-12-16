package domain.gameprocess;

import domain.card.CardDivider;
import domain.card.CardFactory;
import domain.scoring.ScoreCalculator;
import domain.ui.UserInterface;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.User;
import java.util.ArrayList;
import java.util.List;

public class GameProcess {
    private final UserInterface userInterface = new UserInterface();
    private final CardDivider cardDivider = new CardDivider(new CardFactory().create());

    public void play() {
        Dealer dealer = createDealer();
        List<Player> players = createPlayers();

        handOutTwoCardsToAllUsers(dealer, players);

        for (Player player : players) {
            handOutCardsUntilOnePlayerGameIsEnded(player);
        }

        handOutCardsUntilDealerCannotGet(dealer);

        userInterface.printUsersCardsResults(dealer, players);
        userInterface.printUsersFinalRevenues(dealer, players);
    }

    private Dealer createDealer() {
        return new Dealer();
    }

    private List<Player> createPlayers() {
        List<Player> players = new ArrayList<Player>();
        List<String> playerNames = userInterface.scanNames();

        for (String playerName : playerNames) {
            players.add(new Player(playerName,
                userInterface.scanBetAmountOfPlayer(playerName)));
        }

        return players;
    }

    private void handOutTwoCardsToAllUsers(Dealer dealer, List<Player> players) {
        handOutTwoCardsToUser(dealer);

        for (Player player : players) {
            handOutTwoCardsToUser(player);
        }

        userInterface.printFirstTwoCard(dealer, players);
    }

    private void handOutTwoCardsToUser(User user) {
        handOutCardToUser(user);
        handOutCardToUser(user);
    }

    private void handOutCardToUser(User user) {
        user.addCard(cardDivider.getOneRandomCard());
    }

    private void handOutCardsUntilOnePlayerGameIsEnded(Player player) {

        while (ScoreCalculator.getTotalScore(player.openAllCards()) < ScoreCalculator.BLACKJACK_MAX_SCORE
                    && userInterface.scanWhetherPlayerReceiveCard(player)) {
            /* 플레이어의 카드 총 합이 아직 21 미만이고, 플레이어가 카드를 더 뽑기를 원할 때 실행 */
            handOutCardToUser(player);
            userInterface.printAllCards(player);
        }

    }

    private void handOutCardsUntilDealerCannotGet (Dealer dealer) {

        while (shouldDealerGetCard(dealer)) {
            handOutCardToUser(dealer);
        }

    }

    private boolean shouldDealerGetCard(Dealer dealer) {

        if (ScoreCalculator.getTotalScore(dealer.openAllCards())
                < ScoreCalculator.DEALER_GETTING_CARD_BOUNDARY) {
            return true;                    /* 딜러의 카드 총 합이 16 이하이면 true 를 return */
        }

        return false;
    }

    private boolean shouldDealerGetCard(User dealer) {
        return true;
    }
}
