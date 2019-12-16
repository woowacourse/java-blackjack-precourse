package domain.user;

import domain.card.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static util.Validation.sizeEqualValidate;

public class ActiveGamers {

    private final int DEALER_SIZE = 1;

    private List<Player> players = new ArrayList<>();
    private Dealer dealer = new Dealer();

    public ActiveGamers(List<String> playerNames, List<Double> playerBettingMoneys) {
        sizeEqualValidate(playerNames.size(), playerBettingMoneys.size());
        for (int index = 0; index < playerNames.size(); index++) {
            players.add(new Player(playerNames.get(index), playerBettingMoneys.get(index)));
        }
    }

    public void addCardToEveryOne(List<Card> cards) {
        sizeEqualValidate(cards.size(), size());
        int index;
        for (index = 0; index < players.size(); index++) {
            players.get(index).addCard(cards.get(index));
        }
        dealer.addCard(cards.get(index));
    }

    public int size() {
        return players.size() + DEALER_SIZE;
    }

    public List<String> getPlayersName() {
        return players.stream()
                .map(Player::getName)
                .collect(Collectors.toList());
    }

    public List<Gamer> getGamers() {
        List<Gamer> gamers = players.stream()
                .map(x -> (Gamer) x)
                .collect(Collectors.toList());
        gamers.add(dealer);

        return gamers;
    }

    public Dealer getDealer() {
        return dealer;
    }
}
