package util;

import domain.user.Dealer;
import domain.user.Player;
import domain.user.User;

import java.util.List;

public interface BlackjackPrinter {

    void printUser(User user);

    void printResult(Dealer dealer, List<Player> players);
}
