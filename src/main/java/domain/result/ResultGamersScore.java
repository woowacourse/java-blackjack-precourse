package domain.result;

import domain.user.ActiveGamers;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ResultGamersScore {

    public static final int BLACK_JACK_NUMBER = 21;
    public static final int COMPLETE_BLACK_JACK = 2;

    private DealerResult dealerResult;
    private List<PlayerResult> playerResults;

    public ResultGamersScore(ActiveGamers activeGamers) {
        this.dealerResult = new DealerResult(activeGamers.getDealer());
        this.playerResults = activeGamers.getPlayers().stream()
                .map(PlayerResult::new)
                .collect(Collectors.toList());
        setPlayerResults();
        setDealerResult();
    }

    public List<GamerResult> getGamerResults() {
        List<GamerResult> gamerResults = playerResults.stream()
                .map(x -> (GamerResult) x)
                .collect(Collectors.toList());
        gamerResults.add(dealerResult);

        return gamerResults;
    }

    private void setDealerResult() {
        double sum = playerResults.stream()
                .mapToDouble(GamerResult::getResultMoney)
                .sum();
        dealerResult.setResultMoney(sum * -1);
    }

    private void setPlayerResults() {
        playerResults.forEach(c -> c.setResultMoney(Result.LOOSE));
        if (isOneOfPlayersCompleteBlackJack()) {
            setCompleteBlackJackMoney(getCompleteBlackJackPlayers());
            return;
        }
        if (dealerResult.isDealerOver()) {
            setDealerOverMoney(playerResults);
            return;
        }
        if (dealerResult.isBlackJack() && isOneOfPlayersBlackJack()) {
            setBothBlackJackMoney(getCompleteBlackJackPlayers());
            return;
        }
        setDefaultMoney();
    }

    private void setCompleteBlackJackMoney(List<PlayerResult> playerResults) {
        playerResults
                .forEach(x -> x.setResultMoney(Result.COMPLETE));
    }

    private void setDealerOverMoney(List<PlayerResult> playerResults) {
        playerResults.forEach(
                x -> x.setResultMoney(Result.GET)
        );
    }

    private void setBothBlackJackMoney(List<PlayerResult> playerResults) {
        playerResults.forEach(
                x -> x.setResultMoney(Result.DRAW)
        );
    }

    private void setDefaultMoney() {
        List<PlayerResult> diffMinPlayer = getDiffMinPlayer(getDiffMinValue());
        if (dealerResult.getBlackJackScore() == getDiffMinValue()) {
            setBothBlackJackMoney(diffMinPlayer);
            return;
        }
        if (dealerResult.getBlackJackScore() < getDiffMinValue()) {
            setDealerOverMoney(diffMinPlayer);
        }
    }

    private List<PlayerResult> getDiffMinPlayer(int min) {
        return playerResults.stream()
                .filter(c -> c.getBlackJackScore() == min)
                .collect(Collectors.toList());
    }

    private int getDiffMinValue() {
        List<Integer> blackJackScore = playerResults.stream()
                .map(GamerResult::getBlackJackScore)
                .collect(Collectors.toList());
        return Collections.min(blackJackScore);
    }

    private List<PlayerResult> getBlackJackPlayers() {
        return playerResults.stream()
                .filter(GamerResult::isBlackJack)
                .collect(Collectors.toList());
    }

    private boolean isOneOfPlayersBlackJack() {
        return getBlackJackPlayers().size() > 0;
    }

    private List<PlayerResult> getCompleteBlackJackPlayers() {
        return playerResults.stream()
                .filter(GamerResult::isBlackJack)
                .filter(c -> c.getCardSize() == COMPLETE_BLACK_JACK)
                .collect(Collectors.toList());
    }

    private boolean isOneOfPlayersCompleteBlackJack() {
        return getCompleteBlackJackPlayers().size() > 0;
    }

}
