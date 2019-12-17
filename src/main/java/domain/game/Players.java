package domain.game;

import domain.card.Deck;
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

    private int getMinDistance() {
        return this.players.stream()
                .mapToInt(Player::getDistanceToTarget)
                .min()
                .getAsInt();
    }

    public List<Player> getWinners() {
        int minDistance = getMinDistance();
        return players.stream()
                .filter(player -> player.hasDistanceEqualTo(minDistance))
                .collect(Collectors.toList());
    }

    public HashMap<String, Double> initializeCashFlows() {
        HashMap<String, Double> balances = new HashMap<>();
        for (Player player : this.players) {
            balances.put(player.getName(), -player.getBettingMoney());
        }
        return balances;
    }

    public List<String> getCardInfo() {
        List<String> cardInfo = new ArrayList<>();
        for (Player player : this.players) {
            cardInfo.add(player.getCardInfo());
        }
        return cardInfo;
    }
}
