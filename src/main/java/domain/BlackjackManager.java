package domain;

import com.sun.org.apache.xpath.internal.operations.Bool;
import domain.card.Card;
import domain.card.CardFactory;
import domain.user.Player;
import sun.print.BackgroundLookupListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 게임 전반을 관리하는 객체
 */
public class BlackjackManager {
    GamePrinter gamePrinter = new GamePrinter();
    CardFactory cardFactory = new CardFactory();
    List<Player> players = new ArrayList<>();
    List<Card> cards;


    void makePlayer() {
        String[] playerName = gamePrinter.getPlayerNameFromUser();
        for(int i = 0; i < playerName.length; i++){
            int bettingMoney = gamePrinter.getBettingMoneyFromUser(playerName[i]);
            players.add(new Player(playerName[i], bettingMoney));
        }
    }

    void makeCard(){
        cards = cardFactory.create();
    }
}
