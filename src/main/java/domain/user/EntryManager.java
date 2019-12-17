package domain.user;

import domain.card.CardsOnGame;

import java.util.List;
import java.util.stream.Collectors;

public class EntryManager {
    private static final int FIND_WHO_BURST = 0;

    private List<Player> players;

    public EntryManager(List<Player> players) {
        this.players = players;
    }

    public void initPlayerCard(CardsOnGame cards) {
        players.forEach(x -> x.initCardShare(cards));
    }

    public void hitCard(CardsOnGame gameCard) {
        players.stream().filter(x -> !x.isDealer())
                .forEach(x -> x.isPlayerWillHit(gameCard));
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Dealer getDealer() {
        return (Dealer) players.stream().filter(Player::isDealer)
                .findFirst()
                .orElseThrow(IllegalStateException::new);
    }

    public List<Player> blackJackPlayers() {
        return players.stream().filter(Player::isBlackJack)
                .collect(Collectors.toList());
    }

    public List<Player> burstPlayers() {
        return players.stream()
                .filter(x -> x.getCountedScoreWithAceBonus() == FIND_WHO_BURST)
                .collect(Collectors.toList());
    }

    public List<Player> higherScorePlayers(final Player player) {
        return players.stream().filter(x -> x.isHigherThanAnotherPlayer(player))
                .collect(Collectors.toList());
    }

    public List<Player> sameScorePlayers(final Player player) {
        return players.stream().filter(x -> x.isSameScoreWithAnotherPlayer(player))
                .collect(Collectors.toList());
    }

    public String getEntryResultScore() {
        return players.stream()
                .map(x -> x.toString() + " - 결과: "
                        + messageAdder(x.getCountedScoreWithAceBonus(), x))
                .collect(Collectors.joining("\n"));
    }

    public String messageAdder(int score, Player p) {
        if (!p.isBurst(score)) {
            return addBurstMessage(FIND_WHO_BURST);
        }
        if (p.isBlackJack()) {
            return addBlackJackMessage(score);
        }
        return Integer.toString(score);
    }

    public String  addBurstMessage(int score) {
        return (Integer.toString(score) + " (버스트)");
    }

    public String addBlackJackMessage(int score) {
        return (Integer.toString(score) + " (블랙잭)");
    }

    @Override
    public String toString() {
        return players.stream().map(Player::toString)
                .collect(Collectors.joining("\n"));
    }
}
