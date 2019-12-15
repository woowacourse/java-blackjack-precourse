package domain;

import domain.user.Dealer;
import domain.user.Player;
import domain.user.User;

import java.util.List;

public interface BlackjackPrinter {

    void printUserState(User user);

    void printResult(Dealer dealer, List<Player> players);
}
