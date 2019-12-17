import domain.distribution.CardDistributionMachine;
import domain.user.Dealer;
import domain.user.Players;
import domain.user.PlayersGenerator;
import game.Blackjack;

public class Main {

    public static void main(String[] args) {
        Players players = PlayersGenerator.createByInput();
        Dealer dealer = new Dealer();
        CardDistributionMachine cardDistributionMachine = new CardDistributionMachine();
        Blackjack b = new Blackjack(cardDistributionMachine, players, dealer);

        b.startGame();
    }
}
