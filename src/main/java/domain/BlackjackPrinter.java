package domain;

import domain.user.Dealer;
import domain.user.Player;
import domain.user.User;

import java.util.List;

public interface BlackjackPrinter {

    void printUserState(User user);

    void printUserResult(User user);

    void printDealerHit(User user);

    void printRequestForNames();

    void printRequestForMoney(String name);

    void printRequestForHit(User user);

    void printStart(Dealer dealer, List<Player> players);

    void printBreaktime();

    void printError(RuntimeException e);

    void printProfit(User user);

    void printBurst(User user);

    void printProfitGuide();
}
