package domain;

import domain.user.Dealer;
import domain.user.Player;
import domain.user.User;

import java.util.List;

public interface BlackjackPrinter {

    void printUserState(User user);

    void printUserResult(User user);

    void printDealerHit(User user);

    void printResult(Dealer dealer, List<Player> players);

    void printRequestForNames();

    void printRequestForMoney(String name);

    void printRequestForHit(User user);

    void printStart(Dealer dealer, List<Player> players);

    void printBreaktime();
}
