package domain.user;

import java.util.List;
import java.util.stream.Collectors;

import domain.card.Card;
import exception.Validator;

public class Players {
    private final List<Player> players;

    public Players(List<Player> players) {
        Validator.validatePlayersSize(players.size());
        Validator.validateDuplicatedName(players);
        this.players = players;
    }

    public Player getPlayer(int index) {
        return players.get(index);
    }

    public int size() {
        return players.size();
    }

    public void addCardAllPlayer(List<Card> cards) {
        Validator.validateCardSizeAndPlayerSize(cards.size(), this.size());
        for (int index = 0; index < size(); index++) {
            players.get(index).addCard(cards.get(index));
        }
    }

    public List<Player> findByBust() {
        return players.stream()
                .filter(Player::isBust)
                .collect(Collectors.toList());
    }

    public List<Player> findByNotBust() {
        return players.stream()
                .filter(player -> !player.isBust())
                .collect(Collectors.toList());
    }

    public List<Player> findByBlackjack() {
        return players.stream()
                .filter(Player::isBlackjack)
                .collect(Collectors.toList());
    }

    public List<Player> findByNotBlackjack() {
        return players.stream()
                .filter(player -> !player.isBlackjack())
                .collect(Collectors.toList());
    }
}
