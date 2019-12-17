package domain.game;

import domain.card.Deck;
import domain.user.Dealer;
import domain.user.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Players {
    private static final int MINIMUM_NUMBER_OF_PLAYERS = 1;

    private List<Player> players;

    public Players(List<Player> players) {
        validateSize(players);
        this.players = players;
    }

    private void validateSize(List<Player> players) {
        if (players.size() < MINIMUM_NUMBER_OF_PLAYERS) {
            throw new IllegalArgumentException("플레이어는 최소 1명 있어야 합니다.");
        }
    }

    public void addCards(Deck deck) {
        for (Player player : this.players) {
            player.addCard(deck.pop());
        }
    }

    public void hit(Deck deck) {
        for (Player player : this.players) {
            BlackJack.hit(player, deck);
        }
    }

    public boolean isBeatenBy(Dealer dealer) {
        return dealer.getDistanceToTarget() < getMinDistance();
    }

    public List<Player> getWinners(boolean blackJack) {
        int minDistance = getWinningDistance(blackJack);
        return getSurvivors().stream()
                .filter(player -> player.hasDistanceEqualTo(minDistance))
                .collect(Collectors.toList());
    }

    public List<Player> getSurvivors() {
        return players.stream()
                .filter(Player::hasPositiveDistance)
                .collect(Collectors.toList());
    }

    private int getWinningDistance(boolean blackJack) {
        if (blackJack) {
            return 0;
        }
        return getMinDistance();
    }

    public int getMinDistance() {
        return this.players.stream()
                .filter(Player::hasPositiveDistance)
                .mapToInt(Player::getDistanceToTarget)
                .min()
                .getAsInt();
    }

    public List<String> getCardInfo() {
        List<String> cardInfo = new ArrayList<>();
        for (Player player : this.players) {
            cardInfo.add(player.getCardInfo());
        }
        return cardInfo;
    }

    public List<String> getCardInfoWithScore() {
        List<String> cardInfoWithScore = new ArrayList<>();
        for (Player player : this.players) {
            cardInfoWithScore.add(player.getCardInfoWithScore());
        }
        return cardInfoWithScore;
    }

    public HashMap<String, Double> initializeCashFlows() {
        HashMap<String, Double> balances = new HashMap<>();
        for (Player player : this.players) {
            balances.put(player.getName(), -player.getBettingMoney());
        }
        return balances;
    }
}
