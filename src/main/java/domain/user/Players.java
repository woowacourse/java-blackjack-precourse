package domain.user;

import domain.card.Deck;

import java.util.ArrayList;
import java.util.List;

public class Players {
    private static final int MINIMUM_NUMBER_OF_PLAYERS = 1;

    private final List<Player> players;

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

    public List<Integer> sumCardScores() {
        List<Integer> scoreSums = new ArrayList<>();
        for (Player player : this.players) {
            scoreSums.add(new Integer(player.sumCardScores()));
        }
        return scoreSums;
    }

    public List<String> getCardInfo() {
        List<String> cardInfos = new ArrayList<>();
        for (Player player : this.players) {
            cardInfos.add(player.getCardInfo());
        }
        return cardInfos;
    }
}
