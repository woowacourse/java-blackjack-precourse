package dto;

import domain.user.Dealer;
import domain.user.Player;
import domain.user.Players;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Results {

    private final List<Result> results;

    public Results( Players players ) {
        results = new ArrayList<>();

        Dealer dealer = players.getDealer();
        makeResults(players, dealer);
    }

    private void makeResults( Players players, Dealer dealer ) {
        Result dealerResult = new Result(dealer);
        results.add(dealerResult);
        for (Player player : players.getPlayers()) {
            results.add(new Result(players, player));
        }
        dealerResult.calculateDealerRewardMoney(results);
    }

    public List<Result> getResults() {
        return Collections.unmodifiableList(results);
    }
}
