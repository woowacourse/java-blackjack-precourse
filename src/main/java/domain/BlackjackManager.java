package domain;

import domain.card.Card;
import domain.card.CardFactory;
import domain.user.Participant;
import domain.user.Player;

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

    void giveCard(Participant participant){
        int cardIndex = (int) (Math.random() * cards.size());
        participant.addCard(cards.get(cardIndex));
    }
}
