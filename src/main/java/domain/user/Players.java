package domain.user;

import domain.card.Deck;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static domain.user.Player.BLACK_JACK;

public class Players {

    private final List<Player> players = new ArrayList<>();

    public Players( Dealer dealer, List<Player> players ) {
        this.players.add(dealer);
        this.players.addAll(players);
    }

    public void addCard( Deck deck ) {
        for (Player player : players) {
            player.addCard(deck.drawCard());
        }
    }

    public Dealer getDealer() {
        return players.stream()
                .filter(Player::isDealer)
                .findFirst()
                .map(Dealer.class::cast)
                .orElseThrow(() -> new NoSuchElementException("딜러가 없습니다."));
    }

    public List<Player> getPlayers() {
        return players.stream()
                .filter(Player::isPlayer)
                .collect(Collectors.toList());
    }

    public boolean isDealerBlackJack() {
        return getDealer().isBlackJack();
    }

    public boolean isNoneBlackJack() {
        return players.stream()
                .noneMatch(Player::isBlackJack);
    }

    public boolean isWin( Player player ) {
        return player.getScore() == getMaxScore();
    }

    private int getMaxScore() {
        int maxScore = players.stream()
                .mapToInt(Player::getScore)
                .max()
                .orElse(0);

        if (maxScore > BLACK_JACK)
            maxScore = 0;
        return maxScore;
    }
}
