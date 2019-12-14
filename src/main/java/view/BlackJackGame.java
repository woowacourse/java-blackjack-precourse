package view;

import domain.user.Dealer;
import domain.user.Gamer;
import domain.user.Gamers;
import domain.user.Player;
import domain.user.Players;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class BlackJackGame {

    private final Gamers gamers;

    private BlackJackGame() {
        Dealer dealer = new Dealer();
        Players players = new Players(makePlayers());

        gamers = new Gamers(dealer, players.getPlayers());

        OutputView.showStartStatus(dealer, players);
    }

    public static BlackJackGame init() {
        return new BlackJackGame();
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
        OutputView.showLastStatus(gamers.getGamers());
    }

    private void distributeCardToPlayers() {
        for (Gamer player : gamers.getPlayers()) {
            pickCard(player, gamers.getDealer());
        }
    }

    private void distributeCardToDealer() {
        Dealer dealer = gamers.getDealer();
        boolean canReceive = dealer.canReceive();
        OutputView.showDealerCanReceive(canReceive);
        if (canReceive) {
            dealer.addCard(dealer.pickCard());
        }
    }

    private void pickCard(Gamer player, Dealer dealer) {
        while (player.canReceive() && InputView.receiveCard(player.getName())) {
            player.addCard(dealer.pickCard());
            OutputView.showGamerStatus(player);
        }
    }
}
