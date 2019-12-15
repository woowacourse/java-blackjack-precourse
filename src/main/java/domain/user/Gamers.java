package domain.user;

import domain.dispenser.CardDispenser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

import static java.util.stream.Collectors.toList;

public class Gamers {
    private final List<Gamer> gamers = new ArrayList<>();

    public Gamers(Dealer dealer, List<Player> players) {
        gamers.add(dealer);
        gamers.addAll(players);
    }

    public void initCard(CardDispenser cardDispenser) {
        for (int i = 0; i < 2; i++) {
            gamers.forEach(gamer -> gamer.addCard(cardDispenser.pick()));
        }
    }

    public List<String> getPlayerName() {
        return getPlayers().stream()
                .map(Gamer::getName)
                .collect(toList());
    }

    public boolean hasNotBlackJack() {
        return gamers.stream()
                .noneMatch(Gamer::isBlackJack);
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(gamers.stream()
                .filter(Gamer::isPlayer)
                .map(Player.class::cast)
                .collect(toList()));
    }

    public Dealer getDealer() {
        return gamers.stream()
                .filter(Gamer::isDealer)
                .findFirst()
                .map(Dealer.class::cast)
                .orElseThrow(() -> new NoSuchElementException("딜러가 없습니다."));
    }

    public boolean isDealerBlackJack() {
        return getDealer().isBlackJack();
    }

    public List<Gamer> getGamers() {
        return Collections.unmodifiableList(gamers);
    }

    public boolean isWinner(Player player) {
        return player.getScore() == findMaxScore();
    }

    private double findMaxScore() {
        return gamers.stream()
                .mapToInt(Gamer::getScore)
                .max()
                .orElseThrow(() -> new NoSuchElementException("참가자가 없습니다."));
    }
}
