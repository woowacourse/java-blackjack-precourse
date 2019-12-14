package domain.user;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

import static java.util.stream.Collectors.toList;

public class Gamers {
    private final List<Gamer> gamers;

    public Gamers(Dealer dealer, List<Player> players) {
        gamers = new ArrayList<>();
        gamers.add(dealer);
        gamers.addAll(players);
        initCard(dealer);
    }

    private void initCard(Dealer dealer) {
        for (int i = 0; i < 2; i++) {
            gamers.forEach(gamer -> gamer.addCard(dealer.pickCard()));
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

    public List<Gamer> getPlayers() {
        return Collections.unmodifiableList(gamers.stream()
                .filter(Gamer::isPlayer)
                .collect(toList()));
    }

    public Dealer getDealer() {
        return gamers.stream()
                .filter(Gamer::isDealer)
                .findFirst()
                .map(Dealer.class::cast)
                .orElseThrow(() -> new NoSuchElementException("딜러가 없습니다."));
    }

    public List<Gamer> getGamers() {
        return Collections.unmodifiableList(gamers);
    }
}
