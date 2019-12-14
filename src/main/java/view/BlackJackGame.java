package view;

import domain.user.Dealer;
import domain.user.Gamers;
import domain.user.Player;
import domain.user.Players;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class BlackJackGame {

    private final Dealer dealer;
    private final Players players;

    private BlackJackGame() {
        this.dealer = new Dealer();
        this.players = new Players(makePlayers());
        initGamer();
        OutputView.showStartStatus(dealer, players);
    }

    public static BlackJackGame init() {
        return new BlackJackGame();
    }

    private void initGamer() {
        Gamers gamers = new Gamers(dealer, players.getPlayers());
        gamers.initCard(dealer);
    }

    private List<Player> makePlayers() {
        List<String> names = InputView.inputNames();
        List<PlayerDto> playerDtos = InputView.inputBettingMoney(names);

        return playerDtos.stream()
                .map(PlayerDto::toEntity)
                .collect(toList());
    }

    public void start() {
        distributeCardToPlayers();
        distributeCardToDealer();
        OutputView.showLastStatus(dealer, players);
    }

    private void distributeCardToPlayers() {
        for (Player player : players.getPlayers()) {
            pickCard(player);
        }
    }

    private void distributeCardToDealer() {
        boolean canReceive = dealer.canReceive();
        OutputView.showDealerCanReceive(canReceive);
        if (canReceive) {
            dealer.addCard(dealer.pickCard());
        }
    }

    private void pickCard(Player player) {
        while (player.canReceive() && InputView.receiveCard(player.getName())) {
            player.addCard(dealer.pickCard());
            OutputView.showGamerStatus(player);
        }
    }
}
