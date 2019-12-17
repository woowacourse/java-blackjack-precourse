package domain.user;

import java.util.List;

public interface PlayerService {
    void confirmCards(List<Player> players);
    void confirmCards(User user);
    void receiveDefaultCards(List<Player> players);
    void printResult(List<Player> players);
    void printProfit(List<Player> players);
    List<Player> join();
}
