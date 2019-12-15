package domain.view;

import domain.user.Dealer;
import domain.user.Player;

public class GameStatusAnnouncer {
    Announcer announce = new Announcer();

    public void afterStandByUser (Dealer dealer, Player[] players){
        announce.announceDoneUserCreate(players);
        announce.announceCardStatus(dealer);
        announce.announceCardStatus(players);
    }

}
