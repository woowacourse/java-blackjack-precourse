package view;

import domain.dispenser.CardDispenser;
import domain.dispenser.RandomDispenser;
import domain.user.Dealer;
import domain.user.Gamer;
import domain.user.Gamers;
import domain.user.Player;
import view.dto.GameResults;
import view.dto.PlayerDTO;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class BlackJackGame {

    private final Gamers gamers;
    private final CardDispenser cardDispenser;

    private BlackJackGame() {
        cardDispenser = new RandomDispenser();
        gamers = new Gamers(new Dealer(), makePlayers());
        gamers.initCard(cardDispenser);

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
            pickCard(player);
        }
    }

    private void distributeCardToDealer() {
        Dealer dealer = gamers.getDealer();
        boolean canReceive = dealer.canReceive();
        OutputView.showDealerCanReceive(canReceive);
        if (canReceive) {
            dealer.addCard(cardDispenser.pick());
        }
    }

    private void pickCard(Gamer player) {
        while (player.canReceive() && InputView.receiveCard(player.getName())) {
            player.addCard(cardDispenser.pick());
            OutputView.showGamerStatus(player);
        }
    }
}
