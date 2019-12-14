package view.dto;

import domain.user.Dealer;
import domain.user.Gamers;
import domain.user.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameResults {

    private final List<GameResult> gameResults;

    public GameResults(Gamers gamers) {
        gameResults = new ArrayList<>();

        Dealer dealer = gamers.getDealer();
        makeGameResult(gamers, dealer);
    }

    private void makeGameResult(Gamers gamers, Dealer dealer) {
        GameResult dealerResult = new GameResult(dealer.getName());
        gameResults.add(dealerResult);
        for (Player player : gamers.getPlayers()) {
            gameResults.add(new GameResult(player, gamers));
        }
        dealerResult.calculateDealerMoney(gameResults);
    }

    public List<GameResult> getGameResults() {
        return Collections.unmodifiableList(gameResults);
    }
}
