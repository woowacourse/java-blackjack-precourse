package view.dto;

import domain.user.Dealer;
import domain.user.Gamer;
import domain.user.Gamers;
import domain.user.Player;
import view.InputView;
import view.OutputView;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class BlackJackGame {

    private final Gamers gamers;

    private BlackJackGame() {
        gamers = new Gamers(new Dealer(), makePlayers());

        OutputView.showStartStatus(gamers);
    }

    public static BlackJackGame init() {
        return new BlackJackGame();
    }

    private List<Player> makePlayers() {
        List<String> names = InputView.inputNames();
        List<PlayerDTO> playerDTOs = InputView.inputBettingMoney(names);

        return playerDTOs.stream()
                .map(PlayerDTO::toEntity)
                .collect(toList());
    }

    public void start() {
        if (gamers.hasNotBlackJack()) {
            distributeCardToPlayers();
            distributeCardToDealer();
        }
        OutputView.showLastStatus(gamers.getGamers());

        GameResults gameResults = new GameResults(gamers);
        OutputView.showResult(gameResults.getGameResults());
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
