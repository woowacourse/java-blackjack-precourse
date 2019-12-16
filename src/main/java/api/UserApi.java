package api;

import domain.BlackjackPrinter;
import domain.user.*;

import java.util.List;

public class UserApi {

    private DealerServiceImpl dealerService;
    private PlayerServiceImpl playerService;
    private BlackjackPrinter blackjackPrinter;

    public UserApi(DealerServiceImpl dealerService, PlayerServiceImpl playerService, BlackjackPrinter blackjackPrinter) {
        this.dealerService = dealerService;
        this.playerService = playerService;
        this.blackjackPrinter = blackjackPrinter;
    }

    public void receiveDefaultCards(Dealer dealer, List<Player> players) {
        blackjackPrinter.printStart(dealer, players);
        dealerService.receiveDefaultCards(dealer);
        playerService.receiveDefaultCards(players);
        blackjackPrinter.printBreaktime();

    }

    public void confirmCards(List<Player> players, Dealer dealer) {
        playerService.confirmCards(players);
        dealerService.confirmCards(dealer);
        blackjackPrinter.printBreaktime();
    }

    public void analyze(Dealer dealer, List<Player> players) {
        printResult(dealer, players);
        printProfit(dealer, players);
    }

    private void printResult(Dealer dealer, List<Player> players) {
        dealerService.printResult(dealer);
        playerService.printResult(players);
    }

    private void printProfit(Dealer dealer, List<Player> players) {
        //todo: refac
        System.out.println("##최종수익");
        dealerService.printProfit(dealer);
        playerService.printProfit(players);
    }

    public List<Player> join() {
        return playerService.join();
    }
}
