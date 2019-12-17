package api;

import java.util.List;

import domain.user.Dealer;
import domain.user.Player;



public interface BlackjackApi {
    List<Player> join();
    void shuffle();
    void receiveDefaultCards(Dealer dealer, List<Player> players);
    void confirmCards(List<Player> players, Dealer dealer);
    void match(Dealer dealer, List<Player> players);
    void analyze(Dealer dealer, List<Player> players);

}
